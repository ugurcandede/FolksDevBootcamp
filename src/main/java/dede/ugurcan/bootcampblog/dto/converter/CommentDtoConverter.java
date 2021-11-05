package dede.ugurcan.bootcampblog.dto.converter;

import dede.ugurcan.bootcampblog.dto.CommentDto;
import dede.ugurcan.bootcampblog.model.Comment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentDtoConverter {

    private final CommentUserDtoConverter commentUserDtoConverter;

    public CommentDtoConverter(CommentUserDtoConverter commentUserDtoConverter) {
        this.commentUserDtoConverter = commentUserDtoConverter;
    }

    public CommentDto convertToCommentDto(Comment from) {
        return new CommentDto(
                from.getId(),
                from.getBody(),
                from.getCreationDate(),
                commentUserDtoConverter.convert(from.getAuthor())
        );
    }

    public List<CommentDto> convertToCommentDtoList(List<Comment> from) {
        return from
                .stream()
                .map(this::convertToCommentDto)
                .collect(Collectors.toList());
    }
}
