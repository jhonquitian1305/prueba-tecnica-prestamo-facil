package com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.payment_plan.repository;

import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.payment_plan.entity.PaymentPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentPlanJpaRepository extends JpaRepository<PaymentPlanEntity, Long> {
}
