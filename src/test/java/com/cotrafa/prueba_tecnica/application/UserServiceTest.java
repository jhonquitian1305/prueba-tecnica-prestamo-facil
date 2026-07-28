package com.cotrafa.prueba_tecnica.application;

import com.cotrafa.prueba_tecnica.application.exception.DuplicateEmailException;
import com.cotrafa.prueba_tecnica.application.exception.NotFoundException;
import com.cotrafa.prueba_tecnica.domain.user.User;
import com.cotrafa.prueba_tecnica.domain.user.builder.UserBuilder;
import com.cotrafa.prueba_tecnica.domain.user.ports.out.UserRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

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

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {

        given(userRepositoryPort.existsByEmail(user.getEmail()))
                .willReturn(true);

        assertThrows(
                DuplicateEmailException.class,
                () -> userService.createOne(user)
        );

        verify(userRepositoryPort).existsByEmail(user.getEmail());
        verify(userRepositoryPort, never())
                .saveOne(any(User.class));
    }

    @Test
    void shouldValidateUserSuccessfully() {
        String userId = "12345";
        given(this.userRepositoryPort.existsById(userId))
                .willReturn(true);

        this.userService.validateUserById(userId);

        verify(this.userRepositoryPort).existsById(userId);
    }

    @Test
    void shouldThrowExceptionWhenUserDoesNotExistById() {
        String userId = "12345";

        given(userRepositoryPort.existsById(userId))
                .willReturn(false);

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> userService.validateUserById(userId)
        );

        assertEquals("El usuario no existe", exception.getMessage());

        verify(userRepositoryPort).existsById(userId);
    }

}
