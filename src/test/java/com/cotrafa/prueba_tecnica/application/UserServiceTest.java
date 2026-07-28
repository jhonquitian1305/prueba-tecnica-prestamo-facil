package com.cotrafa.prueba_tecnica.application;

import com.cotrafa.prueba_tecnica.domain.user.User;
import com.cotrafa.prueba_tecnica.domain.user.builder.UserBuilder;
import com.cotrafa.prueba_tecnica.domain.user.ports.out.UserRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepositoryPort userRepositoryPort;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp(){
        user = new UserBuilder.Builder()
                .name("John")
                .lastname("Doe")
                .email("johndoe@mail.com")
                .typeIdentification("CC")
                .identification("12345678")
                .baseSalary(2_000_000L)
                .build();
    }

    @Test
    void shouldCreateOneWhenEmailDoesNotExist(){
        given(this.userRepositoryPort.existsByEmail(user.getEmail()))
                .willReturn(false);

        given(this.userRepositoryPort.saveOne(user))
                .willReturn(user);

        User userCreated = this.userService.createOne(user);

        assertNotNull(userCreated);
        assertEquals(user.getEmail(), userCreated.getEmail());

        verify(this.userRepositoryPort).existsByEmail(user.getEmail());
        verify(this.userRepositoryPort).saveOne(user);
    }

}
