package dede.ugurcan.bootcampblog.dto.converter;

import dede.ugurcan.bootcampblog.dto.PostCommentDto;
import dede.ugurcan.bootcampblog.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class PostCommentDtoConverter {

    public PostCommentDto convert(Comment from) {
        return new PostCommentDto(
                from.getId(),
                from.getBody(),
                from.getCreatedAt(),
                from.getUpdatedAt()
        );
    }
}
