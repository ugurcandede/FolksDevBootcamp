package dede.ugurcan.bootcampblog.dto.converter;

import dede.ugurcan.bootcampblog.dto.UserPostDto;
import dede.ugurcan.bootcampblog.model.Post;
import org.springframework.stereotype.Component;

@Component
public class UserPostDtoConverter {

    public UserPostDto convert(Post from) {
        return new UserPostDto(
                from.getId(),
                from.getTitle(),
                from.getBody(),
                from.getCreatedAt(),
                from.getUpdatedAt(),
                from.getStatus()
        );
    }
}
