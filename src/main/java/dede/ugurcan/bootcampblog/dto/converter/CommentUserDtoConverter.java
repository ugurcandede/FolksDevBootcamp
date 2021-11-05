package dede.ugurcan.bootcampblog.dto.converter;

import dede.ugurcan.bootcampblog.dto.CommentUserDto;
import dede.ugurcan.bootcampblog.model.User;
import org.springframework.stereotype.Component;

@Component
public class CommentUserDtoConverter {

    public CommentUserDto convert(User from) {
        return new CommentUserDto(
                from.getId(),
                from.getUsername(),
                from.getDisplayName()
        );
    }
}
