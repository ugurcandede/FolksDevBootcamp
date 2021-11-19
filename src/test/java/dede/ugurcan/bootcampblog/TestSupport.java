package dede.ugurcan.bootcampblog;

import dede.ugurcan.bootcampblog.dto.CommentDto;
import dede.ugurcan.bootcampblog.dto.PostDto;
import dede.ugurcan.bootcampblog.dto.UserDto;
import dede.ugurcan.bootcampblog.dto.request.*;
import dede.ugurcan.bootcampblog.model.Comment;
import dede.ugurcan.bootcampblog.model.Post;
import dede.ugurcan.bootcampblog.model.PostStatus;
import dede.ugurcan.bootcampblog.model.User;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;


public class TestSupport {

    // User Test Support
    public User generateUser() {
        return new User(
                "username",
                "test@email.com",
                "displayName"
        );
    }

    public UserDto generateUserDto() {
        return new UserDto(
                "id",
                "username",
                "email",
                "displayName",
                false
        );
    }

    public List<User> generateListOfUser() {
        User user = generateUser();
        return List.of(user);
    }

    public List<UserDto> generateListOfUserDto() {
        UserDto user = generateUserDto();
        return List.of(user);
    }

    public CreateUserRequest generateCreateUserRequest() {
        return new CreateUserRequest(
                "username",
                "test@email.com",
                "displayName"
        );
    }

    public UpdateUserRequest generateUpdateUserRequest() {
        return new UpdateUserRequest("displayName");
    }

    public User generateUpdatedUser(User from, UpdateUserRequest request) {
        return new User(
                from.getId(),
                from.getUsername(),
                from.getEmail(),
                request.getDisplayName(),
                from.isActive(),
                from.getPosts(),
                from.getComments(),
                from.getCreatedAt(),
                from.getUpdatedAt()
        );
    }

    // Post Test Support
    public Post generatePost() {
        User user = generateUser();
        return new Post(
                "Test Post",
                "Test Body",
                PostStatus.PUBLISHED,
                user
        );
    }

    public Post generatePostWithFields(String title, String body) {
        User user = generateUser();
        return new Post(
                title,
                body,
                PostStatus.PUBLISHED,
                user
        );
    }

    public PostDto generatePostDto() {
        UserDto user = generateUserDto();
        return new PostDto(
                "Id",
                "Test Post",
                "Test Body",
                LocalDateTime.of(2020, 11, 5, 0, 0),
                LocalDateTime.of(2020, 11, 5, 0, 0),
                PostStatus.PUBLISHED,
                user,
                Collections.emptyList()
        );
    }

    public List<Post> generatePostList() {
        return List.of(generatePost());
    }

    public List<PostDto> generatePostDtoList() {
        return List.of(generatePostDto());
    }

    public CreatePostRequest generateCreatePostRequest() {
        return new CreatePostRequest(
                "Test Post",
                "Test Body",
                PostStatus.PUBLISHED
        );
    }

    public UpdatePostRequest generateUpdatePostRequest() {
        return new UpdatePostRequest(
                "title",
                "body",
                PostStatus.PUBLISHED);
    }

    public Post generateUpdatedPost(Post from, UpdatePostRequest request) {
        return new Post(
                from.getId(),
                request.getTitle(),
                request.getBody(),
                from.getCreatedAt(),
                from.getUpdatedAt(),
                request.getStatus(),
                from.getUser(),
                from.getComments()
        );

    }

    // Comment Test Support
    public Comment generateComment() {
        User user = generateUser();
        Post post = generatePost();
        return new Comment(
                "Test Comment",
                user,
                post);
    }

    public CommentDto generateCommentDto() {
        UserDto userDto = generateUserDto();
        return new CommentDto(
                "Id",
                "Test Body",
                LocalDateTime.of(2020, 11, 5, 0, 0),
                LocalDateTime.of(2020, 11, 5, 0, 0),
                userDto
        );
    }

    public List<Comment> generateListOfComment() {
        Comment comment = generateComment();
        return List.of(comment);
    }

    public List<CommentDto> generateListOfCommentDto() {
        CommentDto commentDto = generateCommentDto();
        return List.of(commentDto);
    }

    public CreateCommentRequest generateCreateCommentRequest() {
        return new CreateCommentRequest(
                "UserId",
                "PostId",
                "Test Body"
        );
    }

    public UpdateCommentRequest generateUpdateCommentRequest() {
        return new UpdateCommentRequest("body");
    }

    public Comment generateUpdatedComment(Comment from, UpdateCommentRequest request) {
        return new Comment(
                from.getId(),
                request.getBody(),
                from.getCreatedAt(),
                from.getUpdatedAt(),
                from.getAuthor(),
                from.getPost()
        );
    }
}
