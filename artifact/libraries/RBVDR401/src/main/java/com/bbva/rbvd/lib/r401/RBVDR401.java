package com.bbva.rbvd.lib.r401;

import com.bbva.rbvd.dto.insurancerefunds.refund.ParticipantDTO;
import com.bbva.rbvd.dto.insurancerefunds.refund.RefundCalculateDTO;

import java.util.List;

public interface RBVDR401 {
	List<RefundCalculateDTO> executeCalculateRefund(ParticipantDTO participantDTO);

}
