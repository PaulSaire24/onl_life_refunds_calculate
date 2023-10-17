package com.bbva.rbvd.dto.insurancerefunds.refund;

import java.util.List;

public class RefundCalculateDTO {
    private List<ParticipantDTO> participants;
    private TermDTO term;
    private List<RefundsDTO> refunds;

    public List<ParticipantDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantDTO> participants) {
        this.participants = participants;
    }

    public TermDTO getTerm() {
        return term;
    }

    public void setTerm(TermDTO term) {
        this.term = term;
    }

    public List<RefundsDTO> getRefunds() {
        return refunds;
    }

    public void setRefunds(List<RefundsDTO> refunds) {
        this.refunds = refunds;
    }

    @Override
    public String toString() {
        return "RefundCalculateDTO{" +
                "participants=" + participants +
                ", term=" + term +
                ", refunds=" + refunds +
                '}';
    }
}