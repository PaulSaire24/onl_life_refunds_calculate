package com.bbva.rbvd.dto.insurancerefunds.refund;

import java.io.Serializable;

public class AccumulatedTotalAmountDTO implements Serializable {
    private Double amount;
    private String currency;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "accumulatedTotalAmountDTO{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
