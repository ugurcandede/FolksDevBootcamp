package dede.ugurcan.bootcampblog.service;

import dede.ugurcan.bootcampblog.dto.PostDto;
import dede.ugurcan.bootcampblog.dto.converter.PostDtoConverter;
import dede.ugurcan.bootcampblog.dto.request.CreatePostRequest;
import dede.ugurcan.bootcampblog.exception.NotFoundException;
import dede.ugurcan.bootcampblog.model.Post;
import dede.ugurcan.bootcampblog.model.User;
import dede.ugurcan.bootcampblog.repository.PostRepository;
import org.springframework.stereotype.Service;

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

}
