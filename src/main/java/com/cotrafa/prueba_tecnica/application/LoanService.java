package com.cotrafa.prueba_tecnica.application;

import com.cotrafa.prueba_tecnica.application.dto.LoanInformationDTO;
import com.cotrafa.prueba_tecnica.application.dto.LoanResponse;
import com.cotrafa.prueba_tecnica.application.dto.PageResponseDTO;
import com.cotrafa.prueba_tecnica.application.dto.UpdateStateDTO;
import com.cotrafa.prueba_tecnica.application.exception.NotFoundException;
import com.cotrafa.prueba_tecnica.domain.loan.Loan;
import com.cotrafa.prueba_tecnica.domain.loan.LoanStateEnum;
import com.cotrafa.prueba_tecnica.domain.loan.ports.in.ILoanService;
import com.cotrafa.prueba_tecnica.domain.loan.ports.out.LoanRepositoryPort;
import com.cotrafa.prueba_tecnica.domain.loan.ports.out.LoanStateRepositoryPort;
import com.cotrafa.prueba_tecnica.domain.loan.ports.out.LoanTypeRepositoryPort;
import com.cotrafa.prueba_tecnica.domain.loan.ports.out.NotificationRepositoryPort;
import com.cotrafa.prueba_tecnica.domain.payment_plan.port.in.IPaymentPlanService;
import com.cotrafa.prueba_tecnica.domain.user.ports.in.IUserService;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class LoanService implements ILoanService {

    private final IUserService userService;
    private final LoanRepositoryPort loanRepositoryPort;
    private final LoanTypeRepositoryPort loanTypeRepositoryPort;
    private final LoanStateRepositoryPort loanStateRepositoryPort;
    private final IPaymentPlanService paymentPlanService;
    private final NotificationRepositoryPort notificationRepositoryPort;

    public LoanService(IUserService userService, LoanRepositoryPort loanRepositoryPort,
                       LoanTypeRepositoryPort loanTypeRepositoryPort, LoanStateRepositoryPort loanStateRepositoryPort,
                       IPaymentPlanService paymentPlanService, NotificationRepositoryPort notificationRepositoryPort) {
        this.userService = userService;
        this.loanRepositoryPort = loanRepositoryPort;
        this.loanTypeRepositoryPort = loanTypeRepositoryPort;
        this.loanStateRepositoryPort = loanStateRepositoryPort;
        this.paymentPlanService = paymentPlanService;
        this.notificationRepositoryPort = notificationRepositoryPort;
    }

    @Override
    public Loan createOne(Loan loan) {
        Long idState = LoanStateEnum.PENDIENTE_REVISION.getId();

        this.userService.validateUserById(loan.getUserId());

        if(!loanTypeRepositoryPort.existsById(loan.getIdLoanType())){
            throw new NotFoundException("El tipo de préstamo no existe");
        }

        if(!loanStateRepositoryPort.existsById(idState)){
            throw new NotFoundException("Estado no existe");
        }

        loan.setIdState(idState);

        return loanRepositoryPort.createOne(loan);
    }

    @Override
    public PageResponseDTO<LoanResponse> getAll(Long loanStateId, int page, int size) {
        return this.loanRepositoryPort.getAll(loanStateId, page, size);
    }

    @Override
    @Transactional
    public void updateState(UpdateStateDTO updateStateDTO) {
        LoanInformationDTO loan = this.loanRepositoryPort.getById(updateStateDTO.idLoan())
                .orElseThrow(() -> new NotFoundException("El tipo de préstamo no existe"));

        boolean approved = updateStateDTO.idState().equals(LoanStateEnum.APROBADA.getId());

        if(approved){
            BigDecimal monthlyPayment = this.calculateMonthlyPayment(loan);

            this.paymentPlanService.generate(loan.id(), BigDecimal.valueOf(loan.amount()), loan.interestRate(), loan.termMonth(), monthlyPayment);
        }

        this.loanRepositoryPort.update(updateStateDTO);

        this.notificationRepositoryPort.sendNotification(loan.emailUser(), approved);
    }

    @Override
    public BigDecimal getTotalApproved() {
        return this.loanRepositoryPort.getTotalApproved();
    }

    private BigDecimal calculateMonthlyPayment(LoanInformationDTO loan){
        // Redondear con 20
        MathContext mc = new MathContext(20, RoundingMode.HALF_EVEN);

        // tasa de interés mensual
        BigDecimal monthsOfYear = BigDecimal.valueOf(12);
        BigDecimal monthlyRate = loan.interestRate().divide(monthsOfYear, mc);

        // (1 + i)
        BigDecimal onePlusRate = BigDecimal.ONE.add(monthlyRate);

        // (1 + i)^n
        BigDecimal expressionPow = onePlusRate.pow(loan.termMonth(), mc);

        // P * i * (1+i)^n
        BigDecimal numerator = BigDecimal.valueOf(loan.amount())
                .multiply(monthlyRate, mc)
                .multiply(expressionPow, mc);

        // (1 + i)^n - 1
        BigDecimal denominator = expressionPow.subtract(BigDecimal.ONE);

        return numerator.divide(
                denominator,
                2,
                RoundingMode.HALF_UP);
    }
}
