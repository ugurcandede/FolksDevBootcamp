package dede.ugurcan.bootcampblog.dto.converter;

import dede.ugurcan.bootcampblog.dto.UserCommentDto;
import dede.ugurcan.bootcampblog.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class UserCommentDtoConverter {

    public UserCommentDto convert(Comment from) {
        return new UserCommentDto(
                from.getId(),
                from.getBody(),
                from.getCreatedAt()
        );
    }
}
