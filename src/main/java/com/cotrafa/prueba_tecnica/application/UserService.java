package com.cotrafa.prueba_tecnica.application;

import com.cotrafa.prueba_tecnica.application.exception.DuplicateEmailException;
import com.cotrafa.prueba_tecnica.domain.user.User;
import com.cotrafa.prueba_tecnica.domain.user.ports.in.IUserService;
import com.cotrafa.prueba_tecnica.domain.user.ports.out.UserRepositoryPort;

public class UserService implements IUserService {

    private final UserRepositoryPort userRepositoryPort;

    public UserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User createOne(User user) {
        boolean existsByEmail = this.userRepositoryPort.existsByEmail(user.getEmail());
        if(existsByEmail){
            throw new DuplicateEmailException(user.getEmail());
        }
        return this.userRepositoryPort.saveOne(user);
    }
}
