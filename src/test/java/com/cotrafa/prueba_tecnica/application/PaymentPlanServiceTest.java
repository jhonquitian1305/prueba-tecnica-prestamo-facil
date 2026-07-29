package com.cotrafa.prueba_tecnica.application;

import com.cotrafa.prueba_tecnica.domain.payment_plan.PaymentPlan;
import com.cotrafa.prueba_tecnica.domain.payment_plan.port.out.PaymentPlanRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PaymentPlanServiceTest {

    @Mock
    private PaymentPlanRepositoryPort paymentPlanRepositoryPort;

    @InjectMocks
    private PaymentPlanService paymentPlanService;

    @Test
    void shouldGeneratePaymentPlanSuccessfully() {
        // Given
        Long idLoan = 1L;
        BigDecimal amount = new BigDecimal("1200");
        BigDecimal annualRate = new BigDecimal("0.13");
        int termMonths = 4;
        BigDecimal monthlyPayment = new BigDecimal("306.54");

        // When
        paymentPlanService.generate(
                idLoan,
                amount,
                annualRate,
                termMonths,
                monthlyPayment
        );

        // Then
        ArgumentCaptor<List<PaymentPlan>> captor =
                ArgumentCaptor.forClass(List.class);

        verify(paymentPlanRepositoryPort)
                .generate(captor.capture());

        List<PaymentPlan> plans = captor.getValue();

        assertEquals(4, plans.size());

        PaymentPlan first = plans.get(0);

        assertEquals(idLoan, first.getIdLoan());
        assertEquals(1, first.getFeeNumber());
        assertEquals(monthlyPayment, first.getMonthlyPayment());

        assertEquals(
                new BigDecimal("13.00"),
                first.getInterest()
        );

        assertEquals(
                new BigDecimal("293.54"),
                first.getPrincipalPayment()
        );

        assertEquals(
                new BigDecimal("906.46"),
                first.getRemainingBalance()
        );

        PaymentPlan last = plans.get(3);

        assertEquals(4, last.getFeeNumber());
    }
}
