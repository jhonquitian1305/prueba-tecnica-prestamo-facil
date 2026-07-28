package com.cotrafa.prueba_tecnica.application;

import com.cotrafa.prueba_tecnica.application.exception.NotFoundException;
import com.cotrafa.prueba_tecnica.domain.loan.Loan;
import com.cotrafa.prueba_tecnica.domain.loan.LoanStateEnum;
import com.cotrafa.prueba_tecnica.domain.loan.LoanType;
import com.cotrafa.prueba_tecnica.domain.loan.builder.LoanBuilder;
import com.cotrafa.prueba_tecnica.domain.loan.builder.LoanTypeBuilder;
import com.cotrafa.prueba_tecnica.domain.loan.ports.out.LoanProcedureRepositoryPort;
import com.cotrafa.prueba_tecnica.domain.loan.ports.out.LoanRepositoryPort;
import com.cotrafa.prueba_tecnica.domain.loan.ports.out.LoanStateRepositoryPort;
import com.cotrafa.prueba_tecnica.domain.loan.ports.out.LoanTypeRepositoryPort;
import com.cotrafa.prueba_tecnica.domain.payment_plan.port.in.IPaymentPlanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoanServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private LoanRepositoryPort loanRepositoryPort;

    @Mock
    private LoanTypeRepositoryPort loanTypeRepositoryPort;

    @Mock
    private LoanStateRepositoryPort loanStateRepositoryPort;

    @Mock
    private LoanProcedureRepositoryPort loanProcedureRepositoryPort;

    @Mock
    private IPaymentPlanService paymentPlanService;

    @InjectMocks
    private LoanService loanService;

    private Loan loan;


    @BeforeEach
    void setUp() {
        loan = new LoanBuilder.Builder()
                .amount(1_000_000L)
                .termMonths(12)
                .userId("12345678")
                .idLoanType(1L)
                .build();

    }

    @Test
    void shouldCreateLoanSuccessfullyWhenValidationIsManual() {
        Long idState = LoanStateEnum.PENDIENTE_REVISION.getId();

        Loan loanSaved = this.createLoan();

        LoanType loanType = this.createManualLoanType();

        given(this.loanStateRepositoryPort.existsById(idState))
                .willReturn(true);

        given(this.loanTypeRepositoryPort.findById(loan.getIdLoanType()))
                .willReturn(Optional.of(loanType));

        given(this.loanRepositoryPort.createOne(any(Loan.class)))
                .willReturn(loanSaved);

        Loan result = this.loanService.createOne(loan);

        assertNotNull(result);
        assertEquals(loanSaved.getId(), result.getId());

        verify(this.userService).validateUserById(loan.getUserId());
        verify(this.loanRepositoryPort).createOne(any(Loan.class));

        verifyNoInteractions(this.loanProcedureRepositoryPort);
        verifyNoInteractions(this.paymentPlanService);
        verify(this.loanRepositoryPort, never()).update(any());
    }

    private Loan createLoan() {
        return new LoanBuilder.Builder()
                .id(1L)
                .userId(loan.getUserId())
                .idLoanType(loan.getIdLoanType())
                .amount(loan.getAmount())
                .termMonths(loan.getTermMonths())
                .build();
    }

    private LoanType createManualLoanType() {
        return new LoanTypeBuilder.Builder()
                .id(1L)
                .automaticValidation(false)
                .interestRate(new BigDecimal("0.13"))
                .build();
    }
}