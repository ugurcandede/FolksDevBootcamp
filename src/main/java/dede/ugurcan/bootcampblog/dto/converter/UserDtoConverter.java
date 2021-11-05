package dede.ugurcan.bootcampblog.dto.converter;

import dede.ugurcan.bootcampblog.dto.UserDto;
import dede.ugurcan.bootcampblog.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    private final UserPostDtoConverter userPostDtoConverter;
    private final UserCommentDtoConverter userCommentDtoConverter;

    public UserDtoConverter(UserPostDtoConverter userPostDtoConverter, UserCommentDtoConverter userCommentDtoConverter) {
        this.userPostDtoConverter = userPostDtoConverter;
        this.userCommentDtoConverter = userCommentDtoConverter;
    }

    public UserDto convertToUserDto(User from) {
        return new UserDto(
                from.getId(),
                from.getUsername(),
                from.getEmail(),
                from.getDisplayName(),
                from.isActive(),
                from.getPosts()
                        .stream()
                        .map(userPostDtoConverter::convert)
                        .collect(Collectors.toList()),
                from.getComments()
                        .stream()
                        .map(userCommentDtoConverter::convert)
                        .collect(Collectors.toList())
        );
    }

    public List<UserDto> convertToUserDtoList(List<User> from) {
        return from
                .stream()
                .map(this::convertToUserDto)
                .collect(Collectors.toList());
    }
}
