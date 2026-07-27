package com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.mapper;

import com.cotrafa.prueba_tecnica.domain.user.User;
import com.cotrafa.prueba_tecnica.domain.user.builder.UserBuilder;
import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.entity.UserEntity;

public class UserMapperJpa {
    public static UserEntity toEntity(User user){
        return UserEntity.builder()
                .name(user.getName())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .typeIdentification(user.getTypeIdentification())
                .identification(user.getIdentification())
                .baseSalary(user.getBaseSalary())
                .build();
    }

    public static User toModel(UserEntity userEntity){
        return new UserBuilder.Builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .lastname(userEntity.getLastname())
                .email(userEntity.getEmail())
                .typeIdentification(userEntity.getTypeIdentification())
                .identification(userEntity.getIdentification())
                .baseSalary(userEntity.getBaseSalary())
                .build();
    }
}
