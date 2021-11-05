package dede.ugurcan.bootcampblog.dto.converter;

import dede.ugurcan.bootcampblog.dto.PostUserDto;
import dede.ugurcan.bootcampblog.model.User;
import org.springframework.stereotype.Component;

@Component
public class PostUserDtoConverter {

    public PostUserDto convert(User from) {
        return new PostUserDto(
                from.getId(),
                from.getUsername(),
                from.getEmail(),
                from.getDisplayName()
        );
    }
}
