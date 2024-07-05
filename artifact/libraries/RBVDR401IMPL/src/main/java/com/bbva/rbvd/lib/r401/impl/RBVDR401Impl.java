package com.bbva.rbvd.lib.r401.impl;

import com.bbva.apx.exception.business.BusinessException;
import com.bbva.pisd.dto.insurance.amazon.SignatureAWS;
import com.bbva.rbvd.dto.insurancerefunds.refund.ParticipantDTO;
import com.bbva.rbvd.dto.insurancerefunds.refund.RefundCalculateDTO;
import com.bbva.rbvd.dto.insurancerefunds.rimac.RefundCalculateResponseBO;
import com.bbva.rbvd.dto.insurancerefunds.rimac.RefundRequestBO;
import com.bbva.rbvd.dto.insurancerefunds.utils.Constans;
import com.bbva.rbvd.dto.insurancerefunds.utils.InsuranceRefundsProperties;
import com.bbva.rbvd.lib.r401.transform.bean.RefundRequestBean;
import com.bbva.rbvd.lib.r401.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.HttpMethod;
import java.util.List;

import static com.bbva.rbvd.lib.r401.transform.list.RefundCalculateList.constructionResponse;


public class RBVDR401Impl extends RBVDR401Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(RBVDR401Impl.class);


	@Override
	public List<RefundCalculateDTO> executeCalculateRefund(ParticipantDTO participantDTO) {
		LOGGER.info(" RBVDR401Impl -- executeCalculateRefund Start");
		LOGGER.info("RBVDR401Impl --executeCalculateRefund participantDTO: {}",participantDTO);
		List<RefundCalculateDTO> refunds = null;
		try {
			RefundRequestBean refundRequestBean = new RefundRequestBean(this.applicationConfigurationService);
			RefundRequestBO payload = refundRequestBean.contructionRequestRimac(participantDTO);
			String requestJson = getRequestJson(payload);
			LOGGER.info("***** RBVDR401Impl - executeCalculateRefund ***** Request body: {}", requestJson);
			String uri = this.applicationConfigurationService.getProperty(InsuranceRefundsProperties.REFUNDS_LIFE_CALCULATE_URI.getValue());
			SignatureAWS signatureAWS = this.pisdR014.executeSignatureConstruction(requestJson, HttpMethod.POST,
					uri, null, participantDTO.getTraceId());
			LOGGER.info("***** RBVDR401Impl - executeCalculateRefund ***** signatureAWS: {}", signatureAWS);


			RefundCalculateResponseBO response = this.rbvdR402.executeCalculateService(signatureAWS, requestJson);

			LOGGER.info("RBVDR401Impl -- executeCalculateRefund end");
			refunds= constructionResponse(response, participantDTO);

		}catch (BusinessException ex){
			this.addAdviceWithDescription(ex.getAdviceCode(), ex.getMessage());
		}
		return refunds;

	}
	private String getRequestJson(Object o) {
		return JsonUtil.getInstance().serialization(o);
	}
}
