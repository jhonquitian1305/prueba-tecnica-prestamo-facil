package com.cotrafa.prueba_tecnica.infrastructure.web.controller;

import com.cotrafa.prueba_tecnica.domain.user.User;
import com.cotrafa.prueba_tecnica.domain.user.ports.in.IUserService;
import com.cotrafa.prueba_tecnica.infrastructure.web.controller.dto.UserDTO;
import com.cotrafa.prueba_tecnica.infrastructure.web.controller.dto.UserResponse;
import com.cotrafa.prueba_tecnica.infrastructure.web.controller.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Users", description = "Operaciones relacionadas a los usuarios")
@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @Operation(
            summary = "Crear un usuario",
            description = "Registra un nuevo usuario en el sistema."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Usuario creado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos de entrada inválidos"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "El correo electrónico ya está registrado"
            )
    })
    @PostMapping
    public ResponseEntity<UserResponse> createOne(@Valid @RequestBody UserDTO userDTO){
        User user = UserMapper.toModel(userDTO);
        User userCreated = this.userService.createOne(user);
        return new ResponseEntity<>(UserMapper.toResponse(userCreated), HttpStatus.CREATED);
    }
}
