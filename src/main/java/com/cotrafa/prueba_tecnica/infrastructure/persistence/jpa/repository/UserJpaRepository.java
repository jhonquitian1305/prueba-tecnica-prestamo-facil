package com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.repository;

import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, String> {
    boolean existsByEmail(String email);
}
