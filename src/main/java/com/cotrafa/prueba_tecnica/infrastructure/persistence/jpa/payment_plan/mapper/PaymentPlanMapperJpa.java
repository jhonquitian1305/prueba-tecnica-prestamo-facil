package com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.payment_plan.mapper;

import com.cotrafa.prueba_tecnica.domain.payment_plan.PaymentPlan;
import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.entity.LoanEntity;
import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.payment_plan.entity.PaymentPlanEntity;

public class PaymentPlanMapperJpa {

    public static PaymentPlanEntity toEntity(PaymentPlan paymentPlan){
        return PaymentPlanEntity.builder()
                .loan(LoanEntity.builder().id(paymentPlan.getIdLoan()).build())
                .feeNumber(paymentPlan.getFeeNumber())
                .monthlyPayment(paymentPlan.getMonthlyPayment())
                .interest(paymentPlan.getInterest())
                .principalPayment(paymentPlan.getPrincipalPayment())
                .remainingBalance(paymentPlan.getRemainingBalance())
                .build();
    }
}
