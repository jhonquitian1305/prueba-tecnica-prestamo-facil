package com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan;

import com.cotrafa.prueba_tecnica.domain.loan.ports.out.LoanStateRepositoryPort;
import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.repository.LoanStateJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class LoanStateJpaAdapter implements LoanStateRepositoryPort {

    private final LoanStateJpaRepository loanStateJpaRepository;

    @Override
    public boolean existsById(Long id) {
        return this.loanStateJpaRepository.existsById(id);
    }
}
