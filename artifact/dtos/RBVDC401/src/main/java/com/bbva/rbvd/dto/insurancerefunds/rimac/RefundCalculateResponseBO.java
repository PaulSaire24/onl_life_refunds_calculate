package com.bbva.rbvd.dto.insurancerefunds.rimac;

import com.bbva.apx.dto.AbstractDTO;

import java.util.List;

public class RefundCalculateResponseBO extends AbstractDTO {

    private List<RefundCalculatedPayloadBO> payload;

    public List<RefundCalculatedPayloadBO> getPayload() {
        return payload;
    }

    public void setPayload(List<RefundCalculatedPayloadBO> payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "RefundCalculateResponseBO{" +
                "payload=" + payload +
                '}';
    }
}
