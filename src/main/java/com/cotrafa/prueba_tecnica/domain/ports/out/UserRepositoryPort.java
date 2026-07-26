package com.cotrafa.prueba_tecnica.domain.ports.out;

import com.cotrafa.prueba_tecnica.domain.User;

public interface UserRepositoryPort {
    User saveOne(User user);
    boolean existsByEmail(String email);
}
