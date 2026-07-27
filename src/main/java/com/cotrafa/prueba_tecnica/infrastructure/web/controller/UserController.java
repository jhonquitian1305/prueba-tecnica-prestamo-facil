package com.cotrafa.prueba_tecnica.infrastructure.web.controller;

import com.cotrafa.prueba_tecnica.domain.User;
import com.cotrafa.prueba_tecnica.domain.ports.in.IUserService;
import com.cotrafa.prueba_tecnica.infrastructure.web.controller.dto.UserDTO;
import com.cotrafa.prueba_tecnica.infrastructure.web.controller.dto.UserResponse;
import com.cotrafa.prueba_tecnica.infrastructure.web.controller.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createOne(@Valid @RequestBody UserDTO userDTO){
        User user = UserMapper.toModel(userDTO);
        User userCreated = this.userService.createOne(user);
        return new ResponseEntity<>(UserMapper.toResponse(userCreated), HttpStatus.CREATED);
    }
}
