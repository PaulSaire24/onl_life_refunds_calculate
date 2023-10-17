package com.bbva.rbvd.dto.insurancerefunds.rimac;

import java.io.Serializable;

public class RefundRequestBO implements Serializable {
    private RefundCalculatedPayloadBO payload;

    public RefundCalculatedPayloadBO getPayload() {
        return payload;
    }

    public void setPayload(RefundCalculatedPayloadBO payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "RefundRequestBO{" +
                "payload=" + payload +
                '}';
    }
}
