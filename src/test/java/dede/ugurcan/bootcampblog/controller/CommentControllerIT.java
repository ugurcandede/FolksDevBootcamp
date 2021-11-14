package dede.ugurcan.bootcampblog.controller;

import dede.ugurcan.bootcampblog.IntegrationTestSupport;
import dede.ugurcan.bootcampblog.dto.request.CreateCommentRequest;
import dede.ugurcan.bootcampblog.dto.request.UpdateCommentRequest;
import dede.ugurcan.bootcampblog.model.Comment;
import dede.ugurcan.bootcampblog.model.Post;
import dede.ugurcan.bootcampblog.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Objects;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CommentControllerIT extends IntegrationTestSupport {

    private final String URL = "/v1/comment/";

    @AfterEach
    void tearDown() {
        commentRepository.deleteAll();
    }

    @Test
    public void testGetAllComments_shouldReturnEmptyList() throws Exception {

        this.mockMvc.perform(get(URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        List<Comment> commentsFromDB = commentRepository.findAll();
        assertEquals(0, commentsFromDB.size());
    }

    @Test
    public void testGetAllComments_shouldReturnCommentDtoList() throws Exception {

        commentRepository.save(generateComment());

        this.mockMvc.perform(get(URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        List<Comment> commentsFromDB = commentRepository.findAll();
        assertEquals(1, commentsFromDB.size());
    }

    @Test
    public void testGetCommentId_whenCalledValidId_shouldReturnCommentDto() throws Exception {

        Comment comment = commentRepository.save(generateComment());

        this.mockMvc.perform(get(URL + comment.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(comment.getId())))
                .andExpect(jsonPath("$.body", is(comment.getBody())))
                .andExpect(jsonPath("$.createdAt", notNullValue()))
                .andExpect(jsonPath("$.updatedAt", notNullValue()))
                .andExpect(jsonPath("$.author", notNullValue()));

        Comment commentFromDB = commentRepository.findById(comment.getId()).get();
        assertEquals(comment, commentFromDB);
    }

    @Test
    public void testGetCommentById_whenCalledInvalidId_shouldReturnNotFoundException() throws Exception {

        this.mockMvc.perform(get(URL + "not-valid-id")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testCreateComment_whenCreateCommentRequestIsInvalid_shouldNotCreateCommentAndReturn400BadRequest() throws Exception {

        CreateCommentRequest request = new CreateCommentRequest("", "", "");

        this.mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        List<Comment> commentsFromDB = commentRepository.findAll();
        assertEquals(0, commentsFromDB.size());
    }

    @Test
    public void testCreateComment_whenCreateCommentRequestIsValid_shouldCreateCommentAndReturnCommentDto() throws Exception {

        User user = userRepository.save(generateUser());
        Post post = postRepository.save(generatePost());
        CreateCommentRequest request = new CreateCommentRequest(
                Objects.requireNonNull(user.getId()),
                Objects.requireNonNull(post.getId()),
                "body");

        this.mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.body", is(request.getBody())))
                .andExpect(jsonPath("$.createdAt", notNullValue()))
                .andExpect(jsonPath("$.updatedAt", notNullValue()))
                .andExpect(jsonPath("$.author", notNullValue()));

        List<Comment> commentsFromDB = commentRepository.findAll();
        assertEquals(1, commentsFromDB.size());
    }

    @Test
    public void testDeleteCommentById_whenCalledValidId_shouldDeleteCommentAndReturnString() throws Exception {

        Comment comment = commentRepository.save(generateComment());

        this.mockMvc.perform(delete(URL + comment.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andExpect(header().string("Content-Type", "text/plain;charset=UTF-8"))
                .andExpect(content().string(comment.getId() + " deleted"));
    }

    @Test
    public void testDeleteCommentById_whenCalledInvalidId_shouldReturnNotFoundException() throws Exception {

        this.mockMvc.perform(delete(URL + "not-valid-id")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testUpdateComment_whenCalledValidIdAndUpdateCommentRequest_shouldUpdateCommentAndReturnCommentDto() throws Exception {

        Comment comment = commentRepository.save(generateComment());
        UpdateCommentRequest request = generateUpdateCommentRequest();
        Comment updatedComment = generateUpdatedComment(comment, request);

        this.mockMvc.perform(put(URL + comment.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(comment.getId())))
                .andExpect(jsonPath("$.body", is(request.getBody())))
                .andExpect(jsonPath("$.author", notNullValue()));

        Comment commentFromDB = commentRepository.findById(comment.getId()).get();
        assertEquals(updatedComment, commentFromDB);
    }

    @Test
    public void testUpdateComment_whenCalledInvalidIdAndUpdateCommentRequest_shouldReturnNotFoundException() throws Exception {

        this.mockMvc.perform(put(URL + "not-valid-id")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testUpdateComment_whenCalledValidIdButInvalidUpdateCommentRequest_shouldReturnNotFoundException() throws Exception {

        Comment comment = commentRepository.save(generateComment());
        UpdateCommentRequest request = new UpdateCommentRequest("");

        this.mockMvc.perform(put(URL + comment.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().is4xxClientError());
    }
}
