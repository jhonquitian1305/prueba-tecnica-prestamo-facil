package com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.user.repository;

import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, String> {
    boolean existsByEmail(String email);
}
