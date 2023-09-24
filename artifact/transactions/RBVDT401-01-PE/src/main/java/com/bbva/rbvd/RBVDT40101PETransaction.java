package com.bbva.rbvd;

import com.bbva.elara.domain.transaction.Severity;
import com.bbva.elara.domain.transaction.RequestHeaderParamsName;
import com.bbva.elara.domain.transaction.response.HttpResponseCode;
import com.bbva.rbvd.dto.insuranceroyal.refund.ParticipantDTO;
import com.bbva.rbvd.dto.insuranceroyal.refund.RefundCalculateDTO;
import com.bbva.rbvd.lib.r401.RBVDR401;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

public class RBVDT40101PETransaction extends AbstractRBVDT40101PETransaction {

	private static final Logger LOGGER = LoggerFactory.getLogger(RBVDT40101PETransaction.class);
	@Override
	public void execute() {
		RBVDR401 rbvdR401 = this.getServiceLibrary(RBVDR401.class);
		List<ParticipantDTO> participantDTOList= this.getParticipants();
		if(Objects.nonNull(participantDTOList)){
			participantDTOList.get(0).setTraceId((String) this.getRequestHeader().getHeaderParameter(RequestHeaderParamsName.REQUESTID));
		}
		List<RefundCalculateDTO> dataDTOList = rbvdR401.executeCalculateRefund(participantDTOList);

		if(Objects.nonNull(dataDTOList)){
			this.setData(dataDTOList);
			this.setHttpResponseCode(HttpResponseCode.HTTP_CODE_200, Severity.OK);
			this.setSeverity(Severity.OK);
		}else{
			LOGGER.info("RBVDT40101PETransaction - execute - WITH ERRORS");
			this.setSeverity(Severity.ENR);
		}
	}

}
