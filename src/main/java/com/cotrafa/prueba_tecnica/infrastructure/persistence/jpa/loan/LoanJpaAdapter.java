package com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan;

import com.cotrafa.prueba_tecnica.domain.loan.Loan;
import com.cotrafa.prueba_tecnica.domain.loan.ports.out.LoanRepositoryPort;
import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.entity.LoanEntity;
import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.mapper.LoanMapperJpa;
import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.repository.LoanJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LoanJpaAdapter implements LoanRepositoryPort {

    private final LoanJpaRepository loanJpaRepository;

    @Override
    public Loan createOne(Loan loan) {
        LoanEntity loanEntityToSave = LoanMapperJpa.toEntity(loan);
        LoanEntity loanEntitySaved = this.loanJpaRepository.save(loanEntityToSave);

        return LoanMapperJpa.toModel(loanEntitySaved);
    }
}
