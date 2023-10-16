package com.bbva.rbvd.dto.insurancerefunds.utils;

public enum Errors {
    SERVER_ERROR("RBVD00000158", false, "Ocurrio un problema en el servidor"),
    ERROR_FROM_RIMAC("RBVD00000119", false, "Error desde Rimac con la cotizacion enviada");

    private final String adviceCode;
    private final boolean rollback;
    private final String message;

    Errors(String adviceCode, boolean rollback, String message) {
        this.adviceCode = adviceCode;
        this.rollback = rollback;
        this.message = message;
    }

    public String getAdviceCode() {
        return this.adviceCode;
    }

    public boolean isRollback() {
        return this.rollback;
    }

    public String getMessage() {
        return this.message;
    }


}
