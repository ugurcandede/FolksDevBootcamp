package dede.ugurcan.bootcampblog.controller;

import dede.ugurcan.bootcampblog.IntegrationTestSupport;
import dede.ugurcan.bootcampblog.dto.request.CreatePostRequest;
import dede.ugurcan.bootcampblog.dto.request.UpdatePostRequest;
import dede.ugurcan.bootcampblog.model.Post;
import dede.ugurcan.bootcampblog.model.PostStatus;
import dede.ugurcan.bootcampblog.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PostControllerIT extends IntegrationTestSupport {

    private final String URL = "/v1/post/";

    @AfterEach
    void tearDown() {
        postRepository.deleteAll();
    }

    @Test
    public void testGetAllPosts_shouldReturnEmptyList() throws Exception {

        this.mockMvc.perform(get(URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        List<Post> postFromDb = postRepository.findAll();
        assertEquals(0, postFromDb.size());
    }

    @Test
    public void testGetAllPosts_shouldReturnPostDtoList() throws Exception {

        postRepository.save(generatePost());

        this.mockMvc.perform(get(URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        List<Post> postFromDb = postRepository.findAll();
        assertEquals(1, postFromDb.size());
    }

    @Test
    public void testGetPostById_whenCalledValidId_shouldReturnPostDto() throws Exception {

        Post post = postRepository.save(generatePost());

        this.mockMvc.perform(get(URL + post.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(post.getId())))
                .andExpect(jsonPath("$.title", is(post.getTitle())))
                .andExpect(jsonPath("$.body", is(post.getBody())))
                .andExpect(jsonPath("$.createdAt", notNullValue()))
                .andExpect(jsonPath("$.updatedAt", notNullValue()))
                .andExpect(jsonPath("$.status", is(post.getStatus().name())))
                .andExpect(jsonPath("$.user", notNullValue()))
                .andExpect(jsonPath("$.comments", is(post.getComments())));

        Post postFromDb = postRepository.findById(post.getId()).get();
        assertEquals(post, postFromDb);
    }

    @Test
    public void testGetPostById_whenCalledInvalidId_shouldReturnNotFoundException() throws Exception {

        this.mockMvc.perform(get(URL + "not-valid-id")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testCreatePost_whenCreatePostRequestIsInvalid_shouldNotCreatePostAndReturn400BadRequest() throws Exception {

        User user = userRepository.save(generateUser());
        CreatePostRequest request = new CreatePostRequest(
                "",
                "",
                PostStatus.DRAFT
        );

        this.mockMvc.perform(post(URL + user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", notNullValue()))
                .andExpect(jsonPath("$.body", notNullValue()));

        List<Post> postFromDb = postRepository.findAll();
        assertEquals(0, postFromDb.size());
    }

    @Test
    public void testCreatePost_whenCreatePostRequestIsValid_shouldCreatePostAndReturnPostDto() throws Exception {

        User user = userRepository.save(generateUser());
        CreatePostRequest request = generateCreatePostRequest();

        this.mockMvc.perform(post(URL + user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.title", notNullValue()))
                .andExpect(jsonPath("$.body", notNullValue()))
                .andExpect(jsonPath("$.status", notNullValue()))
                .andExpect(jsonPath("$.user", notNullValue()))
                .andExpect(jsonPath("$.comments", hasSize(0)));

        List<Post> postFromDb = postRepository.findAll();
        assertEquals(1, postFromDb.size());
    }

    @Test
    public void testDeletePostById_whenCalledValidId_shouldDeletePostAndReturnString() throws Exception {

        Post post = postRepository.save(generatePost());

        this.mockMvc.perform(delete(URL + post.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andExpect(header().string("Content-Type", "text/plain;charset=UTF-8"))
                .andExpect(content().string(post.getId() + " deleted"));
    }

    @Test
    public void testDeletePostById_whenCalledInvalidId_shouldReturnNotFoundException() throws Exception {

        this.mockMvc.perform(delete(URL + "not-valid-id")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testUpdatePost_whenCalledValidIdAndUpdatePostRequest_shouldUpdatePostAndReturnPostDto() throws Exception {

        Post post = postRepository.save(generatePost());
        UpdatePostRequest request = generateUpdatePostRequest();
        Post updatedPost = generateUpdatedPost(post, request);

        this.mockMvc.perform(put(URL + post.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(post.getId())))
                .andExpect(jsonPath("$.title", is(request.getTitle())))
                .andExpect(jsonPath("$.body", is(request.getBody())))
                .andExpect(jsonPath("$.status", is(request.getStatus().name())))
                .andExpect(jsonPath("$.user", notNullValue()))
                .andExpect(jsonPath("$.comments", is(post.getComments())));

        Post postFromDb = postRepository.findById(post.getId()).get();
        assertEquals(updatedPost, postFromDb);
    }

    @Test
    public void testUpdatePost_whenCalledInvalidIdAndUpdatePostRequest_shouldReturnNotFoundException() throws Exception {

        this.mockMvc.perform(put(URL + "not-valid-id")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testUpdatePost_whenCalledValidIdButInvalidUpdatePostRequest_shouldReturnNotFoundException() throws Exception {

        Post post = postRepository.save(generatePost());
        UpdatePostRequest request = new UpdatePostRequest("", "", PostStatus.PUBLISHED);

        this.mockMvc.perform(put(URL + post.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().is4xxClientError());
    }
}
