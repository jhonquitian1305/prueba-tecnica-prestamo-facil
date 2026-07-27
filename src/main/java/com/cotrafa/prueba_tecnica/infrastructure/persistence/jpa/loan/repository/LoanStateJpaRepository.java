package com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.repository;

import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.entity.LoanStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanStateJpaRepository extends JpaRepository<LoanStateEntity, Long> {
}
