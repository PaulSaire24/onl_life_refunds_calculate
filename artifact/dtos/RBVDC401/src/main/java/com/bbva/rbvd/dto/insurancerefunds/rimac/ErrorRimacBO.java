package com.bbva.rbvd.dto.insurancerefunds.rimac;

import java.io.Serializable;


public class ErrorRimacBO implements Serializable {

    private ErrorResponseBO error;

    public ErrorResponseBO getError() {
        return error;
    }

    public void setError(ErrorResponseBO error) {
        this.error = error;
    }
}
