package dede.ugurcan.bootcampblog.dto.converter;

import dede.ugurcan.bootcampblog.dto.CommentDto;
import dede.ugurcan.bootcampblog.dto.PostDto;
import dede.ugurcan.bootcampblog.dto.UserDto;
import dede.ugurcan.bootcampblog.model.Post;
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
                new UserDto(
                        from.getUser().getId(),
                        from.getUser().getUsername(),
                        from.getUser().getEmail(),
                        from.getUser().getDisplayName(),
                        from.getUser().isActive()
                ),
                from.getComments()
                        .stream()
                        .map(c -> new CommentDto(
                                c.getId(),
                                c.getBody(),
                                c.getCreatedAt(),
                                c.getUpdatedAt()
                        ))
                        .collect(Collectors.toList())
        );
    }

    public List<PostDto> convertToPostDtoList(List<Post> posts) {
        return posts
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
