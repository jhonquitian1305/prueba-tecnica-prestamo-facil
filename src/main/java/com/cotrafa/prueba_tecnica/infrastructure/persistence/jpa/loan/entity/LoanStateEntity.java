package com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "loan_state")
@Table(name = "loan_states")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanStateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}
