package com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan;

import com.cotrafa.prueba_tecnica.domain.loan.LoanType;
import com.cotrafa.prueba_tecnica.domain.loan.ports.out.LoanTypeRepositoryPort;
import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.mapper.LoanTypeMapperJpa;
import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.repository.LoanTypeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class LoanTypeJpaAdapter implements LoanTypeRepositoryPort {

    private final LoanTypeJpaRepository loanTypeJpaRepository;

    @Override
    public Optional<LoanType> findById(Long idLoanType) {
        return this.loanTypeJpaRepository.findById(idLoanType).map(LoanTypeMapperJpa::toModel);
    }
}
