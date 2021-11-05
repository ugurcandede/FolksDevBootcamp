package dede.ugurcan.bootcampblog.service;

import dede.ugurcan.bootcampblog.TestSupport;
import dede.ugurcan.bootcampblog.dto.PostDto;
import dede.ugurcan.bootcampblog.dto.converter.PostDtoConverter;
import dede.ugurcan.bootcampblog.dto.request.CreatePostRequest;
import dede.ugurcan.bootcampblog.exception.NotFoundException;
import dede.ugurcan.bootcampblog.model.Post;
import dede.ugurcan.bootcampblog.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostServiceTest extends TestSupport {

    private PostRepository postRepository;
    private PostDtoConverter postDtoConverter;
    private UserService userService;
    private PostService postService;

    @BeforeEach
    void setUp() {

        postRepository = Mockito.mock(PostRepository.class);
        postDtoConverter = Mockito.mock(PostDtoConverter.class);
        userService = Mockito.mock(UserService.class);

        postService = new PostService(postRepository, postDtoConverter, userService);
    }

    @Test
    void testGetPostById_whenCalledWithId_itShouldReturnPostDto() {
        Post post = generatePost();
        PostDto postDto = generatePostDto();

        Mockito.when(postRepository.findById("id")).thenReturn(Optional.of(post));
        Mockito.when(postDtoConverter.convertToPostDto(post)).thenReturn(postDto);

        PostDto result = postService.getPostById("id");

        assertEquals(postDto, result);

        Mockito.verify(postRepository).findById("id");
        Mockito.verify(postDtoConverter).convertToPostDto(post);

    }

    @Test
    void testGetPostById_whenIdNotExists_itShouldThrowNotFoundException() {

        Mockito.when(postRepository.findById("id")).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> postService.getPostById("id"));

        Mockito.verify(postRepository).findById("id");
        Mockito.verifyNoInteractions(postDtoConverter);
    }

    @Test
    void testGetPostList_tShouldReturnListOfPostDto() {
        List<Post> postList = generatePostList();
        List<PostDto> postDtoList = generatePostDtoList();

        Mockito.when(postRepository.findAll()).thenReturn(postList);
        Mockito.when(postDtoConverter.convertToPostDtoList(postList)).thenReturn(postDtoList);

        List<PostDto> result = postService.getAllPostDtoList();

        assertEquals(postDtoList, result);

        Mockito.verify(postRepository).findAll();
        Mockito.verify(postDtoConverter).convertToPostDtoList(postList);
    }

    @Test
    void testCreatePost_whenCalledValidRequest_itShouldReturnPostDto() {
        CreatePostRequest request = generateCreatePostRequest();
        Post post = generatePostWithFields(request.getTitle(),request.getBody());
        PostDto postDto = generatePostDto();

        Mockito.when(userService.findByUserId("id")).thenReturn(generateUser());
        Mockito.when(postDtoConverter.convertToPostDto(post)).thenReturn(postDto);
        Mockito.when(postRepository.save(post)).thenReturn(post);

        PostDto result=postService.createPost("id",request);

        assertEquals(postDto,result);

        Mockito.verify(userService).findByUserId("id");
        Mockito.verify(postDtoConverter).convertToPostDto(post);
        Mockito.verify(postRepository).save(post);
    }

    @Test
    void testCreatePost_whenUserDoesntExists_itThrowNotFoundException() {

        CreatePostRequest request = generateCreatePostRequest();

        Mockito.when(userService.findByUserId("id")).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class,()->postService.createPost("id",request));

        Mockito.verify(userService).findByUserId("id");
        Mockito.verifyNoInteractions(postDtoConverter);
        Mockito.verifyNoInteractions(postRepository);
    }

}
