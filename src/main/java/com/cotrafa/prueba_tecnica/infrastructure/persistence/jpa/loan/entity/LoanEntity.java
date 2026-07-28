package com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.entity;

import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "loan")
@Table(name = "loans")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private int termMonths;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_type_id", nullable = false)
    private LoanTypeEntity loanType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    private LoanStateEntity loanState;
}
