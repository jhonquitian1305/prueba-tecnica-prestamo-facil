package com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.repository;

import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.entity.LoanTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanTypeJpaRepository extends JpaRepository<LoanTypeEntity, Long> {
}
