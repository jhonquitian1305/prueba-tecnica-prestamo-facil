package com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.user;

import com.cotrafa.prueba_tecnica.domain.user.User;
import com.cotrafa.prueba_tecnica.domain.user.ports.out.UserRepositoryPort;
import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.user.entity.UserEntity;
import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.user.mapper.UserMapperJpa;
import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserJpaAdapter implements UserRepositoryPort {

    private final UserJpaRepository userJpaRepository;

    @Override
    public User saveOne(User user) {
        UserEntity userEntityToSave = UserMapperJpa.toEntity(user);
        UserEntity userEntitySaved = this.userJpaRepository.save(userEntityToSave);

        return UserMapperJpa.toModel(userEntitySaved);
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.userJpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsById(String userId) {
        return this.userJpaRepository.existsById(userId);
    }
}
