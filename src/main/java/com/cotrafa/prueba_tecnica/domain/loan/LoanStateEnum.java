package com.cotrafa.prueba_tecnica.domain.loan;

public enum LoanStateEnum {
    PENDIENTE_REVISION(1L, "Pendiente de revisión"),
    APROBADA(2L, "Aprobada"),
    REVISION_MANUAL(3L, "Revisión manual"),
    RECHAZADA(4L, "Rechazada");

    private Long id;
    private String name;

    LoanStateEnum(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
