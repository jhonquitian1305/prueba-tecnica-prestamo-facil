package com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.payment_plan;

import com.cotrafa.prueba_tecnica.domain.payment_plan.PaymentPlan;
import com.cotrafa.prueba_tecnica.domain.payment_plan.port.out.PaymentPlanRepositoryPort;
import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.payment_plan.entity.PaymentPlanEntity;
import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.payment_plan.mapper.PaymentPlanMapperJpa;
import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.payment_plan.repository.PaymentPlanJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class PaymentPlanJpaAdapter implements PaymentPlanRepositoryPort {

    private final PaymentPlanJpaRepository paymentPlanJpaRepository;

    @Override
    public void generate(List<PaymentPlan> paymentPlans) {
        List<PaymentPlanEntity> paymentPlanToSave = paymentPlans.stream().map(PaymentPlanMapperJpa::toEntity).toList();
        this.paymentPlanJpaRepository.saveAll(paymentPlanToSave);
    }
}
