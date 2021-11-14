package dede.ugurcan.bootcampblog.dto.converter;

import dede.ugurcan.bootcampblog.dto.CommentDto;
import dede.ugurcan.bootcampblog.dto.PostDto;
import dede.ugurcan.bootcampblog.dto.UserDto;
import dede.ugurcan.bootcampblog.model.Comment;
import dede.ugurcan.bootcampblog.model.Post;
import dede.ugurcan.bootcampblog.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostDtoConverter {

    public PostDto convert(Post from) {
        return new PostDto(
                from.getId(),
                from.getTitle(),
                from.getBody(),
                from.getCreatedAt(),
                from.getUpdatedAt(),
                from.getStatus(),
                convertToUserDto(from.getUser()),
                convertToCommentDtoList(from.getComments())
        );
    }

    private List<CommentDto> convertToCommentDtoList(List<Comment> comments) {
        return comments
                .stream()
                .map(c -> new CommentDto(
                        c.getId(),
                        c.getBody(),
                        c.getCreatedAt(),
                        c.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }

    public List<PostDto> convertToPostDtoList(List<Post> posts) {
        return posts
                .stream()
                .map(p -> new PostDto(
                        p.getId(),
                        p.getTitle(),
                        p.getBody(),
                        p.getCreatedAt(),
                        p.getUpdatedAt(),
                        p.getStatus(),
                        convertToUserDto(p.getUser()),
                        convertToCommentDtoList(p.getComments())
                ))
                .collect(Collectors.toList());
    }

    private UserDto convertToUserDto(User from) {
        return new UserDto(
                from.getId(),
                from.getUsername(),
                from.getEmail(),
                from.getDisplayName(),
                from.isActive()
        );
    }
}
