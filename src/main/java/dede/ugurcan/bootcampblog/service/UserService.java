package dede.ugurcan.bootcampblog.service;

import dede.ugurcan.bootcampblog.dto.UserDto;
import dede.ugurcan.bootcampblog.dto.converter.UserDtoConverter;
import dede.ugurcan.bootcampblog.dto.request.CreateUserRequest;
import dede.ugurcan.bootcampblog.dto.request.UpdateUserRequest;
import dede.ugurcan.bootcampblog.exception.NotFoundException;
import dede.ugurcan.bootcampblog.model.User;
import dede.ugurcan.bootcampblog.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public UserService(UserRepository userRepository,
                       UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
    }

    protected User findByUserId(String id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    protected List<User> getAllUserList() {
        return userRepository.findAll();
    }

    public UserDto getUserById(String id) {
        return userDtoConverter.convert(findByUserId(id));
    }

    public List<UserDto> getUserList() {
        return userDtoConverter.convertToUserDtoList(getAllUserList());
    }

    public UserDto createUser(CreateUserRequest request) {

        User user = new User(
                request.getUsername(),
                request.getEmail(),
                request.getDisplayName(),
                Collections.emptyList(),
                Collections.emptyList()
        );

        return userDtoConverter.convert(userRepository.save(user));
    }

    public String deleteUser(String userId) {

        getUserById(userId);
        userRepository.deleteById(userId);

        return userId + " deleted";
    }

    public UserDto updateUser(String userId, UpdateUserRequest request) {

        User user = findByUserId(userId);

        User updatedUser = new User(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                request.getDisplayName(),
                user.isActive(),
                user.getPosts(),
                user.getComments(),
                user.getCreatedAt(),
                LocalDateTime.now()
        );

        return userDtoConverter.convert(userRepository.save(updatedUser));
    }
}
