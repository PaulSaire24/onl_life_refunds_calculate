package com.bbva.rbvd.dto.insurancerefunds.refund;

import java.io.Serializable;

public class RefundsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private UnitDTO unit;

    public UnitDTO getUnit() {
        return unit;
    }
    public void setUnit(UnitDTO unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "RefundsDTO [unit=" + unit + "]";
    }
}
