package com.cotrafa.prueba_tecnica.application;

import com.cotrafa.prueba_tecnica.application.dto.*;
import com.cotrafa.prueba_tecnica.application.exception.NotFoundException;
import com.cotrafa.prueba_tecnica.domain.loan.Loan;
import com.cotrafa.prueba_tecnica.domain.loan.LoanStateEnum;
import com.cotrafa.prueba_tecnica.domain.loan.LoanType;
import com.cotrafa.prueba_tecnica.domain.loan.builder.LoanBuilder;
import com.cotrafa.prueba_tecnica.domain.loan.builder.LoanTypeBuilder;
import com.cotrafa.prueba_tecnica.domain.loan.ports.out.*;
import com.cotrafa.prueba_tecnica.domain.payment_plan.port.in.IPaymentPlanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
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

    @Mock
    private NotificationRepositoryPort notificationRepositoryPort;

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

        LoanType loanType = this.createLoanType(false);

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

    @Test
    void shouldApproveLoanAutomatically() {
        Long idState = LoanStateEnum.PENDIENTE_REVISION.getId();

        LoanType loanType = this.createLoanType(true);

        Loan loanSaved = this.createLoan();

        LoanValidationResult validation =
                new LoanValidationResult(
                        LoanStateEnum.APROBADA.getId(),
                        new BigDecimal("350000")
                );

        given(this.loanStateRepositoryPort.existsById(idState))
                .willReturn(true);

        given(this.loanTypeRepositoryPort.findById(loan.getIdLoanType()))
                .willReturn(Optional.of(loanType));

        given(this.loanRepositoryPort.createOne(any()))
                .willReturn(loanSaved);

        given(this.loanProcedureRepositoryPort.evaluateAutomatic(loanSaved.getId()))
                .willReturn(validation);

        this.loanService.createOne(loan);

        verify(this.loanProcedureRepositoryPort)
                .evaluateAutomatic(loanSaved.getId());

        verify(this.paymentPlanService)
                .generate(
                        loanSaved.getId(),
                        BigDecimal.valueOf(loanSaved.getAmount()),
                        loanType.getInterestRate(),
                        loan.getTermMonths(),
                        validation.monthlyPayment()
                );

        verify(this.loanRepositoryPort)
                .update(any(UpdateStateDTO.class));
    }

    @Test
    void shouldThrowExceptionWhenStateDoesNotExist() {
        Long idState = LoanStateEnum.PENDIENTE_REVISION.getId();

        given(loanStateRepositoryPort.existsById(idState))
                .willReturn(false);

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> loanService.createOne(loan)
        );

        assertEquals("El estado no existe", exception.getMessage());

        verify(userService).validateUserById(loan.getUserId());

        verifyNoInteractions(loanTypeRepositoryPort);
        verify(loanRepositoryPort, never()).createOne(any());
    }

    @Test
    void shouldThrowExceptionWhenLoanTypeDoesNotExist() {
        Long idState = LoanStateEnum.PENDIENTE_REVISION.getId();

        given(loanStateRepositoryPort.existsById(idState))
                .willReturn(true);

        given(loanTypeRepositoryPort.findById(loan.getIdLoanType()))
                .willReturn(Optional.empty());

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> loanService.createOne(loan)
        );

        assertEquals("El tipo de préstamo no existe", exception.getMessage());

        verify(loanRepositoryPort, never()).createOne(any());
        verifyNoInteractions(loanProcedureRepositoryPort);
        verifyNoInteractions(paymentPlanService);
    }

    @Test
    void shouldSendLoanToManualReview() {
        Long idState = LoanStateEnum.PENDIENTE_REVISION.getId();

        LoanType loanType = this.createLoanType(true);

        Loan loanSaved = this.createLoan();

        LoanValidationResult validation =
                new LoanValidationResult(
                        LoanStateEnum.REVISION_MANUAL.getId(),
                        new BigDecimal("350000")
                );

        given(loanStateRepositoryPort.existsById(idState))
                .willReturn(true);

        given(loanTypeRepositoryPort.findById(loan.getIdLoanType()))
                .willReturn(Optional.of(loanType));

        given(loanRepositoryPort.createOne(any()))
                .willReturn(loanSaved);

        given(loanProcedureRepositoryPort.evaluateAutomatic(loanSaved.getId()))
                .willReturn(validation);

        loanService.createOne(loan);

        verifyNoInteractions(paymentPlanService);

        verify(loanRepositoryPort).update(any(UpdateStateDTO.class));
    }

    @Test
    void shouldRejectLoanAutomatically() {
        Long idState = LoanStateEnum.PENDIENTE_REVISION.getId();

        LoanType loanType = this.createLoanType(true);

        Loan loanSaved = this.createLoan();

        LoanValidationResult validation =
                new LoanValidationResult(
                        LoanStateEnum.RECHAZADA.getId(),
                        new BigDecimal("350000")
                );

        given(loanStateRepositoryPort.existsById(idState))
                .willReturn(true);

        given(loanTypeRepositoryPort.findById(loan.getIdLoanType()))
                .willReturn(Optional.of(loanType));

        given(loanRepositoryPort.createOne(any()))
                .willReturn(loanSaved);

        given(loanProcedureRepositoryPort.evaluateAutomatic(loanSaved.getId()))
                .willReturn(validation);

        loanService.createOne(loan);

        verifyNoInteractions(paymentPlanService);

        verify(loanRepositoryPort).update(any(UpdateStateDTO.class));
    }

    @Test
    void shouldReturnLoansPage() {
        Long loanStateId = 1L;
        int page = 0;
        int size = 10;

        PageResponseDTO<LoanResponse> expectedResponse =
                new PageResponseDTO<>(
                        1,
                        1,
                        page,
                        size,
                        List.of()
                );

        given(loanRepositoryPort.getAll(loanStateId, page, size))
                .willReturn(expectedResponse);

        PageResponseDTO<LoanResponse> result =
                loanService.getAll(loanStateId, page, size);

        assertEquals(expectedResponse, result);

        verify(loanRepositoryPort)
                .getAll(loanStateId, page, size);
    }

    @Test()
    void shouldApproveLoan() {
        UpdateStateDTO dto = UpdateStateDTO.builder()
                .idLoan(1L)
                .idState(LoanStateEnum.APROBADA.getId())
                .build();

        LoanInformationDTO loan = LoanInformationDTO.builder()
                .id(1L)
                .amount(1_000_000L)
                .termMonth(12)
                .emailUser("user@test.com")
                .interestRate(BigDecimal.valueOf(0.1))
                .build();

        given(loanRepositoryPort.getById(dto.idLoan()))
                .willReturn(Optional.of(loan));

        loanService.updateState(dto);

        verify(paymentPlanService).generate(
                eq(loan.id()),
                eq(BigDecimal.valueOf(loan.amount())),
                eq(loan.interestRate()),
                eq(loan.termMonth()),
                any(BigDecimal.class)
        );

        verify(loanRepositoryPort).update(dto);

        verify(notificationRepositoryPort)
                .sendNotification(loan.emailUser(), true);
    }

    @Test
    void shouldRejectLoan() {
        UpdateStateDTO dto = UpdateStateDTO.builder()
                .idLoan(1L)
                .idState(LoanStateEnum.RECHAZADA.getId())
                .build();

        LoanInformationDTO loan = LoanInformationDTO.builder()
                .id(1L)
                .amount(1_000_000L)
                .termMonth(12)
                .emailUser("user@test.com")
                .interestRate(BigDecimal.valueOf(0.1))
                .build();

        given(loanRepositoryPort.getById(dto.idLoan()))
                .willReturn(Optional.of(loan));

        loanService.updateState(dto);

        verifyNoInteractions(paymentPlanService);

        verify(loanRepositoryPort).update(dto);

        verify(notificationRepositoryPort)
                .sendNotification(loan.emailUser(), false);
    }

    @Test
    void shouldThrowExceptionWhenLoanDoesNotExist() {
        UpdateStateDTO dto = UpdateStateDTO.builder()
                .idLoan(1L)
                .idState(LoanStateEnum.APROBADA.getId())
                .build();

        given(loanRepositoryPort.getById(dto.idLoan()))
                .willReturn(Optional.empty());

        NotFoundException exception =
                assertThrows(
                        NotFoundException.class,
                        () -> loanService.updateState(dto)
                );

        assertEquals(
                "El tipo de préstamo no existe",
                exception.getMessage()
        );

        verify(loanRepositoryPort, never())
                .update(any());

        verifyNoInteractions(paymentPlanService);

        verifyNoInteractions(notificationRepositoryPort);
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

    private LoanType createLoanType(boolean isAutomaticValidation) {
        return new LoanTypeBuilder.Builder()
                .id(1L)
                .automaticValidation(isAutomaticValidation)
                .interestRate(new BigDecimal("0.13"))
                .build();
    }
}