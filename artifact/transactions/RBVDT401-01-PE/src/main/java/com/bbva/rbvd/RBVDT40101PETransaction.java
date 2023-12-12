package com.bbva.rbvd;

import com.bbva.elara.domain.transaction.RequestHeaderParamsName;
import com.bbva.elara.domain.transaction.Severity;
import com.bbva.elara.domain.transaction.response.HttpResponseCode;
import com.bbva.rbvd.dto.insurancerefunds.refund.ParticipantDTO;
import com.bbva.rbvd.dto.insurancerefunds.refund.RefundCalculateDTO;
import com.bbva.rbvd.lib.r401.RBVDR401;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class RBVDT40101PETransaction extends AbstractRBVDT40101PETransaction {

	@Override
	public void execute() {

	}

}
