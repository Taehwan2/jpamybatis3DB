package com.kfr.admin.Presentation.user;

import com.kfr.admin.Presentation.user.converter.UserConverter;
import com.kfr.admin.Presentation.user.model.UserRequestDTO;
import com.kfr.admin.Presentation.user.model.UserResponseDTO;
import com.kfr.admin.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;

    @PostMapping("/")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDto) {
            var user = userConverter.requestToEntity(userRequestDto);
            userService.userSave(user);
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));

    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable(name = "userId") Long userId) {
       var user =  userService.getUserById(userId);
       var userResponse = userConverter.EntityToResponse(user);
        return new ResponseEntity<>(userResponse,HttpStatusCode.valueOf(200));
    }



}
