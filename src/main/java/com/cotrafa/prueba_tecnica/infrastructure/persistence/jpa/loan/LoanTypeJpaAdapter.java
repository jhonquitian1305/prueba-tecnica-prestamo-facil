package com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan;

import com.cotrafa.prueba_tecnica.domain.loan.ports.out.LoanTypeRepositoryPort;
import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.repository.LoanTypeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class LoanTypeJpaAdapter implements LoanTypeRepositoryPort {

    private final LoanTypeJpaRepository loanTypeJpaRepository;

    @Override
    public boolean existsById(Long id) {
        return this.loanTypeJpaRepository.existsById(id);
    }
}
