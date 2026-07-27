package com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa;

import com.cotrafa.prueba_tecnica.domain.User;
import com.cotrafa.prueba_tecnica.domain.ports.out.UserRepositoryPort;
import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.entity.UserEntity;
import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.mapper.UserMapperJpa;
import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.repository.UserJpaRepository;
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
}
