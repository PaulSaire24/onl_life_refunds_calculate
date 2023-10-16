package com.bbva.rbvd.dto.insurancerefunds.refund;

import com.bbva.rbvd.dto.insurancerefunds.commons.CommonsFieldsDTO;

import java.math.BigDecimal;

public class UnitDTO extends CommonsFieldsDTO {
    private String unitType;
    private String text;
    private BigDecimal decimal;
    private BigDecimal percentage;

    public String getUnitType() {
        return this.unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BigDecimal getDecimal() {
        return this.decimal;
    }

    public void setDecimal(BigDecimal decimal) {
        this.decimal = decimal;
    }

    public BigDecimal getPercentage() {
        return this.percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "UnitDTO{" +
                "unitType='" + unitType + '\'' +
                ", text='" + text + '\'' +
                ", decimal=" + decimal +
                ", percentage=" + percentage +
                '}';
    }
}