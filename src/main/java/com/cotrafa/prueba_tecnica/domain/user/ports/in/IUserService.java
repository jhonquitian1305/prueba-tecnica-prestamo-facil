package com.cotrafa.prueba_tecnica.domain.user.ports.in;

import com.cotrafa.prueba_tecnica.domain.user.User;

public interface IUserService {
    User createOne(User user);
}
