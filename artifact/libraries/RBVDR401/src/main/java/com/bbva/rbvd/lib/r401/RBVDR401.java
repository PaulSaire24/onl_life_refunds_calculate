package com.bbva.rbvd.lib.r401;

import com.bbva.rbvd.dto.insuranceroyal.refund.ParticipantDTO;
import com.bbva.rbvd.dto.insuranceroyal.refund.RefundCalculateDTO;

import java.util.List;

public interface RBVDR401 {
	List<RefundCalculateDTO> executeCalculateRefund(List<ParticipantDTO> participantDTOList);

}
