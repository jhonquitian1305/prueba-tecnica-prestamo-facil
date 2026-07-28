package com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.payment_plan.entity;

import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.entity.LoanEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "payment_plan")
@Table(name = "payment_plans")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentPlanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private LoanEntity loan;

    @Column(nullable = false)
    private Integer feeNumber;

    @Column(nullable = false)
    private BigDecimal monthlyPayment;

    @Column(nullable = false)
    private BigDecimal interest;

    @Column(nullable = false)
    private BigDecimal principalPayment;

    @Column(nullable = false)
    private BigDecimal remainingBalance;
}
