package com.cotrafa.prueba_tecnica.domain.payment_plan.port.in;

import java.math.BigDecimal;

public interface IPaymentPlanService {
    void generate(Long idLoan, BigDecimal amount, BigDecimal annualRate, int termMonths, BigDecimal monthlyPayment);
}
