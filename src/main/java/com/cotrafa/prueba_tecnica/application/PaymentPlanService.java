package com.cotrafa.prueba_tecnica.application;

import com.cotrafa.prueba_tecnica.domain.payment_plan.PaymentPlan;
import com.cotrafa.prueba_tecnica.domain.payment_plan.port.in.IPaymentPlanService;
import com.cotrafa.prueba_tecnica.domain.payment_plan.port.out.PaymentPlanRepositoryPort;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class PaymentPlanService implements IPaymentPlanService {

    private final PaymentPlanRepositoryPort paymentPlanRepositoryPort;

    public PaymentPlanService(PaymentPlanRepositoryPort paymentPlanRepositoryPort) {
        this.paymentPlanRepositoryPort = paymentPlanRepositoryPort;
    }

    @Override
    public void generate(Long idLoan, BigDecimal amount, BigDecimal annualRate, int termMonths, BigDecimal monthlyPayment) {
        List<PaymentPlan> paymentPlan = new ArrayList<>();

        BigDecimal monthlyRate = annualRate.divide(
                BigDecimal.valueOf(12),
                20,
                RoundingMode.HALF_UP);

        BigDecimal remainingBalance = amount;

        for (int feeNumber = 1; feeNumber <= termMonths; feeNumber++) {

            // Interés del mes
            BigDecimal interest = remainingBalance.multiply(monthlyRate)
                    .setScale(2, RoundingMode.HALF_UP);

            // Abono a capital
            BigDecimal principalPayment = monthlyPayment.subtract(interest);

            // Nuevo saldo
            remainingBalance = remainingBalance.subtract(principalPayment)
                    .setScale(2, RoundingMode.HALF_UP);

            // Evitar saldos negativos por redondeos
            if (remainingBalance.compareTo(BigDecimal.ZERO) < 0) {
                remainingBalance = BigDecimal.ZERO;
            }

            paymentPlan.add(new PaymentPlan(
                    idLoan,
                    feeNumber,
                    monthlyPayment,
                    interest,
                    principalPayment,
                    remainingBalance
            ));
        }

        this.paymentPlanRepositoryPort.generate(paymentPlan);
    }
}
