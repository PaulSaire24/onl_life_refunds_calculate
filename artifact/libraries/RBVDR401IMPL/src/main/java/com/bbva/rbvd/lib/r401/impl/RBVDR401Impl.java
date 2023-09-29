package com.bbva.rbvd.lib.r401.impl;

import com.bbva.rbvd.dto.insuranceroyal.refund.ParticipantDTO;
import com.bbva.rbvd.dto.insuranceroyal.refund.RefundCalculateDTO;
import com.bbva.rbvd.dto.insuranceroyal.rimac.calculate.RefundCalculatedBO;
import com.bbva.rbvd.dto.insuranceroyal.rimac.calculate.RefundRequestBO;
import com.bbva.rbvd.lib.r401.transform.bean.RefundRequestBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.bbva.rbvd.lib.r401.transform.list.RefundCalculateList.constructionResponse;


public class RBVDR401Impl extends RBVDR401Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(RBVDR401Impl.class);


	@Override
	public List<RefundCalculateDTO> executeCalculateRefund(ParticipantDTO participantDTO) {
		LOGGER.info(" RBVDR401Impl -- executeCalculateRefund Start");
		LOGGER.info("RBVDR401Impl --executeCalculateRefund participantDTO: {}",participantDTO);
		RefundRequestBean refundRequestBean = new RefundRequestBean(this.applicationConfigurationService);
		RefundRequestBO payload = refundRequestBean.contructionRequestRimac(participantDTO);

		RefundCalculatedBO response = this.rbvdR402.executeCalculateService(payload,participantDTO.getTraceId());

		LOGGER.info("RBVDR401Impl -- executeCalculateRefund end");
		return constructionResponse(response,participantDTO);
	}
}
