package dede.ugurcan.bootcampblog.service;

import dede.ugurcan.bootcampblog.TestSupport;
import dede.ugurcan.bootcampblog.dto.UserDto;
import dede.ugurcan.bootcampblog.dto.converter.UserDtoConverter;
import dede.ugurcan.bootcampblog.dto.request.CreateUserRequest;
import dede.ugurcan.bootcampblog.dto.request.UpdateUserRequest;
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
    void testGetUserById_whenCalledExistId_itShouldReturnUserDto() {

        User user = generateUser();
        UserDto userDto = generateUserDto();

        Mockito.when(userRepository.findById("id")).thenReturn(Optional.of(user));
        Mockito.when(userDtoConverter.convert(user)).thenReturn(userDto);

        UserDto result = userService.getUserById("id");

        assertEquals(userDto, result);

        Mockito.verify(userRepository).findById("id");
        Mockito.verify(userDtoConverter).convert(user);
    }

    @Test
    void testGetUserById_whenCalledIdNotExists_itShouldThrowNotFoundException() {

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
    void testCreateUser_whenCalledCreateUserRequest_itShouldReturnUserDto() {

        CreateUserRequest request = generateCreateUserRequest();
        User user = generateUser();
        UserDto userDto = generateUserDto();

        Mockito.when(userDtoConverter.convert(user)).thenReturn(userDto);
        Mockito.when(userRepository.save(user)).thenReturn(user);

        UserDto result = userService.createUser(request);

        assertEquals(userDto, result);

        Mockito.verify(userDtoConverter).convert(user);
        Mockito.verify(userRepository).save(user);
    }

    @Test
    void testDeleteUser_whenCalledValidId_itShouldReturnString() {

        User user = generateUser();
        UserDto userDto = generateUserDto();

        Mockito.when(userRepository.findById("id")).thenReturn(Optional.of(user));
        Mockito.when(userDtoConverter.convert(user)).thenReturn(userDto);

        String result = userService.deleteUser("id");

        assertEquals("id deleted", result);

        Mockito.verify(userRepository).findById("id");
        Mockito.verify(userDtoConverter).convert(user);
    }

    @Test
    void testDeleteUser_whenCalledInValidId_itShouldThrowNotFoundException() {

        Mockito.when(userRepository.findById("id")).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> userService.getUserById("id"));

        Mockito.verify(userRepository).findById("id");
        Mockito.verifyNoInteractions(userDtoConverter);
    }

    @Test
    void testUpdateUser_whenCalledValidRequest_itShouldReturnUserDto() {

        UpdateUserRequest request = generateUpdateUserRequest();
        User updatedUser = generateUpdatedUser(generateUser(), request);
        UserDto userDto = generateUserDto();

        Mockito.when(userRepository.findById("id")).thenReturn(Optional.of(generateUser()));
        Mockito.when(userRepository.save(updatedUser)).thenReturn(updatedUser);
        Mockito.when(userDtoConverter.convert(updatedUser)).thenReturn(userDto);

        UserDto result = userService.updateUser("id", request);

        assertEquals(userDto, result);

        Mockito.verify(userRepository).findById("id");
        Mockito.verify(userRepository).save(updatedUser);
        Mockito.verify(userDtoConverter).convert(updatedUser);
    }

    @Test
    void testUpdateUser_whenCalledIdInValid_itShouldThrowNotFoundException() {

        UpdateUserRequest request = generateUpdateUserRequest();

        Mockito.when(userRepository.findById("id")).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> userService.updateUser("id", request));

        Mockito.verify(userRepository).findById("id");
        Mockito.verifyNoInteractions(userDtoConverter);
    }

}
