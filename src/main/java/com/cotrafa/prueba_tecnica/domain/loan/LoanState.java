package com.cotrafa.prueba_tecnica.domain.loan;

public class LoanState {
    private Long id;
    private String name;

    public LoanState(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public LoanState() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
