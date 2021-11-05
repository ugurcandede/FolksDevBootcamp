package dede.ugurcan.bootcampblog;

import dede.ugurcan.bootcampblog.dto.*;
import dede.ugurcan.bootcampblog.dto.request.CreateCommentRequest;
import dede.ugurcan.bootcampblog.dto.request.CreatePostRequest;
import dede.ugurcan.bootcampblog.dto.request.CreateUserRequest;
import dede.ugurcan.bootcampblog.model.Comment;
import dede.ugurcan.bootcampblog.model.Post;
import dede.ugurcan.bootcampblog.model.PostStatus;
import dede.ugurcan.bootcampblog.model.User;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class TestSupport {

    public User generateUser() {
        return new User(
                "id",
                "email",
                "displayName",
                Collections.emptyList(),
                Collections.emptyList()
        );
    }

    public UserDto generateUserDto() {
        return new UserDto(
                "id",
                "username",
                "email",
                "displayName",
                false,
                Collections.emptyList(),
                Collections.emptyList()
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
                "email",
                "displayName"
        );
    }

    // PostTestSupport
    public Post generatePost() {
        User user = generateUser();
        return new Post(
                "Test Post",
                "Test Body",
                PostStatus.PUBLISHED,
                user
        );
    }
    public Post generatePostWithFields(String title,String body) {
        User user = generateUser();
        return new Post(
                title,
                body,
                PostStatus.PUBLISHED,
                user
        );
    }

    public PostDto generatePostDto() {
        PostUserDto user = generatePostUserDto();
        return new PostDto(
                "Id",
                "Test Post",
                "Test Body",
                LocalDateTime.of(2020, 11, 5, 0, 0),
                PostStatus.PUBLISHED,
                user,
                Collections.emptyList()
        );
    }

    public PostUserDto generatePostUserDto() {
        return new PostUserDto(
                "Id",
                "Username",
                "email",
                "DisplayName"
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

    // CommentTestSupport
    public Comment generateComment() {
        User user = generateUser();
        Post post = generatePost();
        return new Comment(
                "Test Comment",
                user,
                post);
    }

    public CommentDto generateCommentDto() {
        CommentUserDto commentUserDto = generateCommentUserDto();
        return new CommentDto(
                "Id",
                "Test Body",
                LocalDateTime.of(2020, 11, 5, 0, 0),
                commentUserDto
        );
    }

    public CommentUserDto generateCommentUserDto() {
        return new CommentUserDto(
                "Id",
                "Test Username",
                "Test User"
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

}
