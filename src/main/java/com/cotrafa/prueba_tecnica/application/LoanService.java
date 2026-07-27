package com.cotrafa.prueba_tecnica.application;

import com.cotrafa.prueba_tecnica.application.exception.NotFoundException;
import com.cotrafa.prueba_tecnica.domain.loan.Loan;
import com.cotrafa.prueba_tecnica.domain.loan.LoanStateEnum;
import com.cotrafa.prueba_tecnica.domain.loan.ports.in.ILoanService;
import com.cotrafa.prueba_tecnica.domain.loan.ports.out.LoanRepositoryPort;
import com.cotrafa.prueba_tecnica.domain.loan.ports.out.LoanStateRepositoryPort;
import com.cotrafa.prueba_tecnica.domain.loan.ports.out.LoanTypeRepositoryPort;
import com.cotrafa.prueba_tecnica.domain.user.ports.out.UserRepositoryPort;

public class LoanService implements ILoanService {

    private final UserRepositoryPort userRepositoryPort;
    private final LoanRepositoryPort loanRepositoryPort;
    private final LoanTypeRepositoryPort loanTypeRepositoryPort;
    private final LoanStateRepositoryPort loanStateRepositoryPort;

    public LoanService(UserRepositoryPort userRepositoryPort, LoanRepositoryPort loanRepositoryPort,
                       LoanTypeRepositoryPort loanTypeRepositoryPort, LoanStateRepositoryPort loanStateRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.loanRepositoryPort = loanRepositoryPort;
        this.loanTypeRepositoryPort = loanTypeRepositoryPort;
        this.loanStateRepositoryPort = loanStateRepositoryPort;
    }

    @Override
    public Loan createOne(Loan loan) {
        Long idState = LoanStateEnum.PENDIENTE_REVISION.getId();
        if(!userRepositoryPort.existsById(loan.getUserId())){
            throw new NotFoundException("El usuario no existe");
        }

        if(!loanTypeRepositoryPort.existsById(loan.getIdLoanType())){
            throw new NotFoundException("El tipo de préstamo no existe");
        }

        if(!loanStateRepositoryPort.existsById(idState)){
            throw new NotFoundException("Estado no existe");
        }

        loan.setIdState(idState);

        return loanRepositoryPort.createOne(loan);
    }
}
