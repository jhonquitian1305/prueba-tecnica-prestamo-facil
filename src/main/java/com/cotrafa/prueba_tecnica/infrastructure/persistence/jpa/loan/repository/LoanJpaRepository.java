package com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.repository;

import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanJpaRepository extends JpaRepository<LoanEntity, Long> {
}
