package dede.ugurcan.bootcampblog.controller;

import dede.ugurcan.bootcampblog.IntegrationTestSupport;
import dede.ugurcan.bootcampblog.dto.request.CreateUserRequest;
import dede.ugurcan.bootcampblog.dto.request.UpdateUserRequest;
import dede.ugurcan.bootcampblog.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerIT extends IntegrationTestSupport {

    private final String URL = "/v1/user/";

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void testGetAllUsers_shouldReturnEmptyList() throws Exception {

        this.mockMvc.perform(get(URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        List<User> userFromDb = userRepository.findAll();
        assertEquals(0, userFromDb.size());
    }

    @Test
    public void testGetAllUsers_shouldReturnUserDtoList() throws Exception {

        userRepository.save(generateUser());

        this.mockMvc.perform(get(URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        List<User> userFromDb = userRepository.findAll();
        assertEquals(1, userFromDb.size());
    }

    @Test
    public void testGetUserById_whenCalledValidId_shouldReturnUserDto() throws Exception {

        User user = userRepository.save(generateUser());

        this.mockMvc.perform(get(URL + user.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(user.getId())))
                .andExpect(jsonPath("$.username", is(user.getUsername())))
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(jsonPath("$.displayName", is(user.getDisplayName())))
                .andExpect(jsonPath("$.isActive", is(user.isActive())));

        User userFromDb = userRepository.findById(user.getId()).get();
        assertEquals(user, userFromDb);
    }

    @Test
    public void testGetUserById_whenCalledInvalidId_shouldReturnNotFoundException() throws Exception {

        this.mockMvc.perform(get(URL + "not-valid-id")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testCreateUser_whenCreateUserRequestIsInvalid_shouldNotCreateUserAndReturn400BadRequest() throws Exception {

        CreateUserRequest request = new CreateUserRequest(
                "",
                "",
                ""
        );

        this.mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username", notNullValue()))
                .andExpect(jsonPath("$.email", notNullValue()))
                .andExpect(jsonPath("$.displayName", notNullValue()));

        List<User> userFromDb = userRepository.findAll();
        assertEquals(0, userFromDb.size());
    }

    @Test
    public void testCreateUser_whenCreateUserRequestIsValid_shouldCreateUserAndReturnUserDto() throws Exception {

        CreateUserRequest request = generateCreateUserRequest();

        this.mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username", is("username")))
                .andExpect(jsonPath("$.email", is("test@email.com")))
                .andExpect(jsonPath("$.displayName", is("displayName")));

        List<User> userFromDb = userRepository.findAll();
        assertEquals(1, userFromDb.size());
    }

    @Test
    public void testDeleteUserById_whenCalledValidId_shouldDeleteUserAndReturnString() throws Exception {

        User user = userRepository.save(generateUser());

        this.mockMvc.perform(delete(URL + user.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andExpect(header().string("Content-Type", "text/plain;charset=UTF-8"))
                .andExpect(content().string(user.getId() + " deleted"));
    }

    @Test
    public void testDeleteUserById_whenCalledInvalidId_shouldReturnNotFoundException() throws Exception {

        this.mockMvc.perform(delete(URL + "not-valid-id")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testUpdateUser_whenCalledValidIdAndUpdateUserRequest_shouldUpdateUserAndReturnUserDto() throws Exception {

        User user = userRepository.save(generateUser());
        UpdateUserRequest request = generateUpdateUserRequest();
        User updatedUser = generateUpdatedUser(user, request);

        this.mockMvc.perform(put(URL + user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(user.getId())))
                .andExpect(jsonPath("$.username", is(user.getUsername())))
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(jsonPath("$.displayName", is(request.getDisplayName())))
                .andExpect(jsonPath("$.isActive", is(user.isActive())));

        User userFromDb = userRepository.findById(user.getId()).get();
        assertEquals(updatedUser, userFromDb);
    }

    @Test
    public void testUpdateUser_whenCalledInvalidIdAndUpdateUserRequest_shouldReturnNotFoundException() throws Exception {

        this.mockMvc.perform(put(URL + "not-valid-id")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testUpdateUser_whenCalledValidIdButInvalidUpdateUserRequest_shouldReturnNotFoundException() throws Exception {

        User user = userRepository.save(generateUser());
        UpdateUserRequest request = new UpdateUserRequest("");

        this.mockMvc.perform(put(URL + user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().is4xxClientError());
    }
}
