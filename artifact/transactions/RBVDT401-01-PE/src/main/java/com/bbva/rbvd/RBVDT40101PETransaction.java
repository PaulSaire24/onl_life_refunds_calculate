package com.bbva.rbvd;

import com.bbva.rbvd.lib.r402.RBVDR402;
import com.bbva.elara.domain.transaction.RequestHeaderParamsName;
import com.bbva.elara.domain.transaction.Severity;
import com.bbva.elara.domain.transaction.response.HttpResponseCode;
import com.bbva.rbvd.dto.insuranceroyal.refund.RefundCalculateDTO;
import com.bbva.rbvd.dto.insuranceroyal.refund.ParticipantDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

public class RBVDT40101PETransaction extends AbstractRBVDT40101PETransaction {

	private static final Logger LOGGER = LoggerFactory.getLogger(RBVDT40101PETransaction.class);

	@Override
	public void execute() {
		RBVDR402 rbvdR402 = this.getServiceLibrary(RBVDR402.class);
		List<ParticipantDTO> participantDTOList= this.getParticipants();
		if(Objects.nonNull(participantDTOList)){
			participantDTOList.get(0).setSaleChannelId((String) this.getRequestHeader().getHeaderParameter(RequestHeaderParamsName.CHANNELCODE));
			participantDTOList.get(0).setCreationUser((String) this.getRequestHeader().getHeaderParameter(RequestHeaderParamsName.USERCODE));
			participantDTOList.get(0).setUserAudit((String) this.getRequestHeader().getHeaderParameter(RequestHeaderParamsName.USERCODE));
			participantDTOList.get(0).setTraceId((String) this.getRequestHeader().getHeaderParameter(RequestHeaderParamsName.REQUESTID));
			participantDTOList.get(0).setAap((String) this.getRequestHeader().getHeaderParameter(RequestHeaderParamsName.AAP));
		}


		List<RefundCalculateDTO> dataDTOList = rbvdR402.executeCalculateRefund(participantDTOList);

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
