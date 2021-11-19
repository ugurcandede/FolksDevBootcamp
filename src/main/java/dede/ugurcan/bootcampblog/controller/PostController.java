package dede.ugurcan.bootcampblog.controller;

import dede.ugurcan.bootcampblog.dto.PostDto;
import dede.ugurcan.bootcampblog.dto.request.CreatePostRequest;
import dede.ugurcan.bootcampblog.dto.request.UpdatePostRequest;
import dede.ugurcan.bootcampblog.service.PostService;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> postDtoList = postService.getAllPostDtoList();
        postDtoList.forEach(PostController::selfLinkGenerator);
        return ResponseEntity.ok(postDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable String id) {
        PostDto postDto = postService.getPostById(id);
        selfLinkGenerator(postDto);
        return ResponseEntity.ok(postDto);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<PostDto> createPost(@PathVariable String userId,
                                              @Valid @RequestBody CreatePostRequest request) {
        return ResponseEntity.ok(postService.createPost(userId, request));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable String postId) {
        return ResponseEntity.ok(postService.deletePost(postId));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable String postId,
                                              @Valid @RequestBody UpdatePostRequest request) {
        return ResponseEntity.ok(postService.updatePost(postId, request));
    }

    private static void selfLinkGenerator(PostDto postDto) {
        Link postDtoLink = linkTo(methodOn(PostController.class).getPostById(postDto.getId())).withSelfRel();
        postDto.add(postDtoLink);

        postDto.getUser().add(linkTo(methodOn(UserController.class).getUserById(postDto.getUser().getId())).withSelfRel());

        postDto.getComments().forEach(comment -> {
            Link commentLink = linkTo(methodOn(CommentController.class).getCommentById(comment.getId())).withSelfRel();
            comment.add(commentLink);
        });
    }
}
