package dede.ugurcan.bootcampblog.dto.converter;

import dede.ugurcan.bootcampblog.dto.CommentDto;
import dede.ugurcan.bootcampblog.dto.UserDto;
import dede.ugurcan.bootcampblog.model.Comment;
import dede.ugurcan.bootcampblog.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentDtoConverter {

    public CommentDto convert(Comment from) {
        return new CommentDto(
                from.getId(),
                from.getBody(),
                from.getCreatedAt(),
                from.getUpdatedAt(),
                convertToUserDto(from.getAuthor())
        );
    }

    private UserDto convertToUserDto(User from) {
        return new UserDto(from.getId(),
                from.getUsername(),
                from.getEmail(),
                from.getDisplayName(),
                from.isActive());
    }

    public List<CommentDto> convertToCommentDtoList(List<Comment> comments) {
        return comments
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
