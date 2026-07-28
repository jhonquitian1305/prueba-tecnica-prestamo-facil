package com.cotrafa.prueba_tecnica.domain.payment_plan;

import java.math.BigDecimal;

public class PaymentPlan {
    private Long idLoan;
    private Integer feeNumber;
    private BigDecimal monthlyPayment;
    private BigDecimal interest;
    private BigDecimal principalPayment;
    private BigDecimal remainingBalance;

    public PaymentPlan(Long idLoan, Integer feeNumber, BigDecimal monthlyPayment, BigDecimal interest,
                       BigDecimal principalPayment, BigDecimal remainingBalance) {
        this.idLoan = idLoan;
        this.feeNumber = feeNumber;
        this.monthlyPayment = monthlyPayment;
        this.interest = interest;
        this.principalPayment = principalPayment;
        this.remainingBalance = remainingBalance;
    }

    public PaymentPlan() {
    }

    public Long getIdLoan() {
        return idLoan;
    }

    public void setIdLoan(Long idLoan) {
        this.idLoan = idLoan;
    }

    public Integer getFeeNumber() {
        return feeNumber;
    }

    public void setFeeNumber(Integer feeNumber) {
        this.feeNumber = feeNumber;
    }

    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(BigDecimal monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getPrincipalPayment() {
        return principalPayment;
    }

    public void setPrincipalPayment(BigDecimal principalPayment) {
        this.principalPayment = principalPayment;
    }

    public BigDecimal getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(BigDecimal remainingBalance) {
        this.remainingBalance = remainingBalance;
    }
}
