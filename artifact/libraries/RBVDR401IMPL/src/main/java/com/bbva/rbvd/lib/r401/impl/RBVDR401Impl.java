package com.bbva.rbvd.lib.r401.impl;

import com.bbva.rbvd.dto.insuranceroyal.refund.ParticipantDTO;
import com.bbva.rbvd.dto.insuranceroyal.refund.RefundCalculateDTO;
import com.bbva.rbvd.dto.insuranceroyal.rimac.calculate.RefundCalculatedBO;
import com.bbva.rbvd.dto.insuranceroyal.rimac.calculate.RefundRequestBO;
import com.bbva.rbvd.lib.r401.mapper.MapperMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.bbva.rbvd.lib.r401.mapper.MapperBean.constructionResponse;


public class RBVDR401Impl extends RBVDR401Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(RBVDR401Impl.class);


	@Override
	public List<RefundCalculateDTO> executeCalculateRefund(List<ParticipantDTO> participantDTOList) {
		LOGGER.info(" RBVDR401Impl -- executeCalculateRefund Start");
		LOGGER.info("RBVDR401Impl --executeCalculateRefund participantDTO: {}",participantDTOList);
		RefundRequestBO payload = new RefundRequestBO();
		MapperMap mapperMap = new MapperMap(this.applicationConfigurationService);
		mapperMap.contructionRequestRimac(participantDTOList,payload);
		RefundCalculatedBO response = this.rbvdR402.executeCalculateService(payload,participantDTOList.get(0).getTraceId());
		LOGGER.info("RBVDR401Impl -- executeCalculateRefund end");
		return constructionResponse(response,participantDTOList);
	}
}
