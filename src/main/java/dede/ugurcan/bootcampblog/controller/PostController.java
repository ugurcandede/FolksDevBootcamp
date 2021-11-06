package dede.ugurcan.bootcampblog.controller;

import dede.ugurcan.bootcampblog.dto.PostDto;
import dede.ugurcan.bootcampblog.dto.request.CreatePostRequest;
import dede.ugurcan.bootcampblog.dto.request.UpdatePostRequest;
import dede.ugurcan.bootcampblog.model.Post;
import dede.ugurcan.bootcampblog.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPostDtoList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable String id) {
        return ResponseEntity.ok(postService.getPostById(id));
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

}
