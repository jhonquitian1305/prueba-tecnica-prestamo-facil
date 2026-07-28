package com.cotrafa.prueba_tecnica.domain.payment_plan.port.out;

import com.cotrafa.prueba_tecnica.domain.payment_plan.PaymentPlan;

import java.util.List;

public interface PaymentPlanRepositoryPort {
    void generate(List<PaymentPlan> paymentPlans);
}
