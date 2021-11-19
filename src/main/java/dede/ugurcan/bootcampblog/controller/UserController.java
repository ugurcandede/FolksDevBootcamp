package dede.ugurcan.bootcampblog.controller;

import dede.ugurcan.bootcampblog.dto.UserDto;
import dede.ugurcan.bootcampblog.dto.request.CreateUserRequest;
import dede.ugurcan.bootcampblog.dto.request.UpdateUserRequest;
import dede.ugurcan.bootcampblog.service.UserService;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtoList = userService.getUserList();
        userDtoList.forEach(UserController::selfLinkGenerator);
        return ResponseEntity.ok(userDtoList);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String userId) {
        UserDto userDto = userService.getUserById(userId);
        selfLinkGenerator(userDto);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String userId,
                                              @Valid @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(userService.updateUser(userId, request));
    }

    private static void selfLinkGenerator(UserDto userDto) {
        Link userDtoLink = linkTo(methodOn(UserController.class).getUserById(userDto.getId())).withSelfRel();
        userDto.add(userDtoLink);

        userDto.getPosts().forEach(postDto -> {
            Link postLink = linkTo(methodOn(PostController.class).getPostById(postDto.getId())).withSelfRel();
            postDto.add(postLink);
        });

        userDto.getComments().forEach(commentDto -> {
            Link commentLink = linkTo(methodOn(CommentController.class).getCommentById(commentDto.getId())).withSelfRel();
            commentDto.add(commentLink);
        });
    }
}
