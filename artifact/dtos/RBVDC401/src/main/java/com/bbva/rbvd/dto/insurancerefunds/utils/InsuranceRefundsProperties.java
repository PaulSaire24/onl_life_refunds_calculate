package com.bbva.rbvd.dto.insurancerefunds.utils;

public enum InsuranceRefundsProperties {
    REFUNDS_LIFE_CALCULATE_URI("refunds.calculate.rimac.uri"),
    REFUNDS_LIFE_CALCULATE("refunds.calculate");

    private final String value;

    public String getValue() { return this.value; }

    private InsuranceRefundsProperties(String value) { this.value = value; }

}
