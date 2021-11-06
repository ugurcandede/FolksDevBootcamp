package dede.ugurcan.bootcampblog.service;

import dede.ugurcan.bootcampblog.dto.PostDto;
import dede.ugurcan.bootcampblog.dto.converter.PostDtoConverter;
import dede.ugurcan.bootcampblog.dto.request.CreatePostRequest;
import dede.ugurcan.bootcampblog.dto.request.UpdatePostRequest;
import dede.ugurcan.bootcampblog.exception.NotFoundException;
import dede.ugurcan.bootcampblog.model.Post;
import dede.ugurcan.bootcampblog.model.User;
import dede.ugurcan.bootcampblog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostDtoConverter postDtoConverter;

    private final UserService userService;

    public PostService(PostRepository postRepository,
                       PostDtoConverter postDtoConverter, UserService userService) {
        this.postRepository = postRepository;
        this.postDtoConverter = postDtoConverter;
        this.userService = userService;
    }

    protected Post findByPostId(String id) {
        return postRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Post not found"));
    }

    protected List<Post> getAllPostList() {
        return postRepository.findAll();
    }

    public PostDto getPostById(String id) {
        return postDtoConverter.convertToPostDto(findByPostId(id));
    }

    public List<PostDto> getAllPostDtoList() {
        return postDtoConverter.convertToPostDtoList(getAllPostList());
    }

    public PostDto createPost(String userId, CreatePostRequest request) {

        User user = userService.findByUserId(userId);

        Post post = new Post(
                request.getTitle(),
                request.getBody(),
                request.getStatus(),
                user
        );

        return postDtoConverter.convertToPostDto(postRepository.save(post));
    }

    public String deletePost(String postId) {

        getPostById(postId);
        postRepository.deleteById(postId);

        return postId + " deleted";
    }

    public PostDto updatePost(String postId, UpdatePostRequest request) {

        Post post = findByPostId(postId);

        Post updatedPost = new Post(
                post.getId(),
                request.getTitle(),
                request.getBody(),
                post.getCreatedAt(),
                LocalDateTime.now(),
                request.getStatus(),
                post.getUser(),
                post.getComments()
        );

        return postDtoConverter.convertToPostDto(postRepository.save(updatedPost));
    }
}
