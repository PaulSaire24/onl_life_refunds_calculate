package com.bbva.rbvd.dto.insurancerefunds.rimac;

public class RefundRequestBO {
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
