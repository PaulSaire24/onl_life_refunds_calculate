package com.bbva.rbvd.dto.insurancerefunds.refund;

import java.util.List;

public class RefundCalculateDTO {
    private List<ParticipantDTO> participants;
    private TermDTO term;
    private List<RefundsDTO> refunds;
    private AccumulatedTotalAmountDTO accumulatedTotalAmount;

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

    public AccumulatedTotalAmountDTO getAccumulatedTotalAmount() {
        return accumulatedTotalAmount;
    }

    public void setAccumulatedTotalAmount(AccumulatedTotalAmountDTO accumulatedTotalAmount) {
        this.accumulatedTotalAmount = accumulatedTotalAmount;
    }

    @Override
    public String toString() {
        return "RefundCalculateDTO{" +
                "participants=" + participants +
                ", term=" + term +
                ", refunds=" + refunds +
                ", accumulatedTotalAmount=" + accumulatedTotalAmount +
                '}';
    }
}