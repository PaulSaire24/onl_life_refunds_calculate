package com.bbva.rbvd.lib.r402;

import com.bbva.rbvd.dto.insuranceroyal.refund.RefundCalculateDTO;
import com.bbva.rbvd.dto.insuranceroyal.refund.ParticipantDTO;

import java.util.List;

public interface RBVDR402 {

	List<RefundCalculateDTO> executeCalculateRefund(List<ParticipantDTO> participantDTO);

}
