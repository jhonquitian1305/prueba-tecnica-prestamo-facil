package com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "users")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String lastname;

    @Column(nullable = false, unique = true)
    String email;

    @Column(nullable = false)
    String typeIdentification;

    @Column(nullable = false)
    String identification;

    @Column(nullable = false)
    Long baseSalary;
}
