package com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.repository;

import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.entity.LoanEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface LoanJpaRepository extends JpaRepository<LoanEntity, Long> {

    @Query("""
    SELECT l
    FROM loan l
    WHERE (:loanStateId IS NULL OR l.loanState.id = :loanStateId)
    """)
    Page<LoanEntity> getAll(Long loanStateId, Pageable pageable);

    @Modifying
    @Query("""
        UPDATE loan l SET l.loanState.id = :idState WHERE l.id = :idLoan
    """)
    void update(Long idLoan, Long idState);

    @Query("""
        SELECT COALESCE(SUM(l.amount),0)
        FROM loan l
        WHERE l.loanState.id = 2
    """)
    BigDecimal getTotalApproved();
}
