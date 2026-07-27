package com.cotrafa.prueba_tecnica.domain.user.ports.out;

import com.cotrafa.prueba_tecnica.domain.user.User;

public interface UserRepositoryPort {
    User saveOne(User user);
    boolean existsByEmail(String email);
}
