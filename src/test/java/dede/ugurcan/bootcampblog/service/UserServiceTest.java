package dede.ugurcan.bootcampblog.service;

import dede.ugurcan.bootcampblog.TestSupport;
import dede.ugurcan.bootcampblog.dto.UserDto;
import dede.ugurcan.bootcampblog.dto.converter.UserDtoConverter;
import dede.ugurcan.bootcampblog.dto.request.CreateUserRequest;
import dede.ugurcan.bootcampblog.exception.NotFoundException;
import dede.ugurcan.bootcampblog.model.User;
import dede.ugurcan.bootcampblog.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceTest extends TestSupport {

    private UserRepository userRepository;
    private UserDtoConverter userDtoConverter;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userDtoConverter = Mockito.mock(UserDtoConverter.class);

        userService = new UserService(userRepository, userDtoConverter);
    }

    @Test
    void testGetUserById_whenCalledWithId_itShouldReturnUserDto() {
        User user = generateUser();

        UserDto userDto = generateUserDto();

        Mockito.when(userRepository.findById("id")).thenReturn(Optional.of(user));
        Mockito.when(userDtoConverter.convertToUserDto(user)).thenReturn(userDto);

        UserDto result = userService.getUserById("id");

        assertEquals(userDto, result);

        Mockito.verify(userRepository).findById("id");
        Mockito.verify(userDtoConverter).convertToUserDto(user);

    }

    @Test
    void testGetUserById_whenIdNotExist_itShouldThrowNotFoundException() {

        Mockito.when(userRepository.findById("id")).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> userService.getUserById("id"));

        Mockito.verify(userRepository).findById("id");
        Mockito.verifyNoInteractions(userDtoConverter);

    }

    @Test
    void testGetUserList_itShouldReturnListOfUserDto() {

        List<User> repositoryList = generateListOfUser();
        List<UserDto> userDtoList = generateListOfUserDto();

        Mockito.when(userRepository.findAll())
                .thenReturn(repositoryList);
        Mockito.when(userDtoConverter.convertToUserDtoList(repositoryList))
                .thenReturn(userDtoList);

        List<UserDto> result = userService.getUserList();

        assertEquals(userDtoList, result);

        Mockito.verify(userRepository).findAll();
        Mockito.verify(userDtoConverter).convertToUserDtoList(repositoryList);

    }

    @Test
    void testCreateUser_whenCalledValidRequest_itShouldReturnUserDto() {

        CreateUserRequest request = generateCreateUserRequest();
        User user = generateUser();
        UserDto userDto = generateUserDto();

        Mockito.when(userDtoConverter.convertToUserDto(user)).thenReturn(userDto);
        Mockito.when(userRepository.save(user)).thenReturn(user);

        UserDto result = userService.createUser(request);

        assertEquals(userDto, result);

        Mockito.verify(userDtoConverter).convertToUserDto(user);
        Mockito.verify(userRepository).save(user);

    }

}
