package com.cotrafa.prueba_tecnica.infrastructure.web.controller.mapper;

import com.cotrafa.prueba_tecnica.domain.User;
import com.cotrafa.prueba_tecnica.domain.builder.UserBuilder;
import com.cotrafa.prueba_tecnica.infrastructure.web.controller.dto.UserDTO;
import com.cotrafa.prueba_tecnica.infrastructure.web.controller.dto.UserResponse;

public class UserMapper {

    public static User toModel(UserDTO userDTO){
        return new UserBuilder.Builder()
                .name(userDTO.name())
                .lastname(userDTO.lastname())
                .email(userDTO.email())
                .typeIdentification(userDTO.typeIdentification())
                .identification(userDTO.identification())
                .baseSalary(userDTO.baseSalary())
                .build();
    }

    public static UserResponse toResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .typeIdentification(user.getTypeIdentification())
                .identification(user.getIdentification())
                .baseSalary(user.getBaseSalary())
                .build();
    }
}
