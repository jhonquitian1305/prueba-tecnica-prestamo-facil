package com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan;

import com.cotrafa.prueba_tecnica.application.dto.LoanValidationResult;
import com.cotrafa.prueba_tecnica.domain.loan.ports.out.LoanProcedureRepositoryPort;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class LoanProcedureRepositoryAdapter implements LoanProcedureRepositoryPort {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public LoanValidationResult evaluateAutomatic(Long loanId) {

        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery("evaluate_loan");

        query.registerStoredProcedureParameter("p_loan_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_state", Long.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter("p_monthly_payment", BigDecimal.class, ParameterMode.OUT);

        query.setParameter("p_loan_id", loanId);

        query.execute();

        return new LoanValidationResult(
                (Long) query.getOutputParameterValue("p_state"),
                (BigDecimal) query.getOutputParameterValue("p_monthly_payment")
        );
    }
}
