package dede.ugurcan.bootcampblog.dto.converter;

import dede.ugurcan.bootcampblog.dto.PostDto;
import dede.ugurcan.bootcampblog.model.Post;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostDtoConverter {


    private final PostUserDtoConverter postUserDtoConverter;
    private final PostCommentDtoConverter postCommentDtoConverter;

    public PostDtoConverter(PostUserDtoConverter postUserDtoConverter, PostCommentDtoConverter postCommentDtoConverter) {
        this.postUserDtoConverter = postUserDtoConverter;
        this.postCommentDtoConverter = postCommentDtoConverter;
    }


    public PostDto convertToPostDto(Post from) {
        return new PostDto(
                from.getId(),
                from.getTitle(),
                from.getBody(),
                from.getCreatedAt(),
                from.getUpdatedAt(),
                from.getStatus(),
                postUserDtoConverter.convert(from.getUser()),
                from.getComments()
                        .stream()
                        .map(postCommentDtoConverter::convert)
                        .collect(Collectors.toList())
        );
    }

    public List<PostDto> convertToPostDtoList(List<Post> from) {
        return from
                .stream()
                .map(this::convertToPostDto)
                .collect(Collectors.toList());
    }
}
