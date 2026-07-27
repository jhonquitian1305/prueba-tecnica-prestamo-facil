package com.cotrafa.prueba_tecnica.domain.loan;

public class Loan {
    private Long id;
    private Long amount;
    private int termMonths;
    private String userId;
    private Long idLoanType;
    private Long idState;

    public Loan(Long id, Long amount, int termMonths, String userId, Long idLoanType, Long idState) {
        this.id = id;
        this.amount = amount;
        this.termMonths = termMonths;
        this.userId = userId;
        this.idLoanType = idLoanType;
        this.idState = idState;
    }

    public Loan() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public int getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(int termMonths) {
        this.termMonths = termMonths;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getIdLoanType() {
        return idLoanType;
    }

    public void setIdLoanType(Long idLoanType) {
        this.idLoanType = idLoanType;
    }

    public Long getIdState() {
        return idState;
    }

    public void setIdState(Long idState) {
        this.idState = idState;
    }
}
