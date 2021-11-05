package dede.ugurcan.bootcampblog.service;

import dede.ugurcan.bootcampblog.dto.UserDto;
import dede.ugurcan.bootcampblog.dto.converter.UserDtoConverter;
import dede.ugurcan.bootcampblog.dto.request.CreateUserRequest;
import dede.ugurcan.bootcampblog.exception.NotFoundException;
import dede.ugurcan.bootcampblog.model.User;
import dede.ugurcan.bootcampblog.repository.UserRepository;
import org.springframework.stereotype.Service;

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
        return userDtoConverter.convertToUserDto(findByUserId(id));
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
        return userDtoConverter.convertToUserDto(userRepository.save(user));
    }


}
