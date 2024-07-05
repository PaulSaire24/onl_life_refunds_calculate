package com.bbva.rbvd.lib.r402.impl;

import com.bbva.apx.exception.business.BusinessException;
import com.bbva.apx.exception.io.network.TimeoutException;
import com.bbva.pisd.dto.insurance.amazon.SignatureAWS;
import com.bbva.rbvd.dto.insurancerefunds.rimac.RefundCalculateResponseBO;
import com.bbva.rbvd.dto.insurancerefunds.utils.Constans;
import com.bbva.rbvd.dto.insurancerefunds.utils.Constans.Headers;
import com.bbva.rbvd.dto.insurancerefunds.utils.InsuranceRefundsProperties;
import com.bbva.rbvd.lib.r402.util.JsonUtil;
import com.bbva.rbvd.lib.r402.util.RimacExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;

import java.nio.charset.StandardCharsets;


public class RBVDR402Impl extends RBVDR402Abstract {
	private static final Logger LOGGER = LoggerFactory.getLogger(RBVDR402Impl.class);

	@Override
	public RefundCalculateResponseBO executeCalculateService(SignatureAWS signatureAWS, String requestJson) {

		HttpEntity<String> entity = new HttpEntity<>(requestJson, createHttpHeadersAWS(signatureAWS));
		LOGGER.info("***** RBVDR401Impl - executeCalculateRefund ***** HttpEntity: {}", entity);

		RefundCalculateResponseBO response = null;

		try {
			response = this.externalApiConnector.postForObject(InsuranceRefundsProperties.REFUNDS_LIFE_CALCULATE.getValue(),entity,RefundCalculateResponseBO.class);
			LOGGER.info("*** RBVDR402Impl - executeCalculateService *** Response: {}", getRequestJson(response));
		}catch (RestClientException e){
			LOGGER.debug("*** RBVDR402Impl - executeCalculateService *** Exception: {}", e.getMessage());
			RimacExceptionHandler exceptionHandler = new RimacExceptionHandler();
			exceptionHandler.handler(e);
		}catch(TimeoutException ex){
			LOGGER.debug("***** RBVDR402Impl - executeCalculateService ***** TimeoutException: {}", ex.getMessage());
			throw new BusinessException(Constans.Error.BBVAE2, false, "Lo sentimos, el servicio de rimac está tardando más de lo esperado. Por favor,  intentelo nuevamente en unos minutos.");
		}

		LOGGER.info("executeCalculateRefund response {}",response);
		LOGGER.info("executeCalculateRefund end");

		return response;
	}
	private String getRequestJson(Object o) {
		return JsonUtil.getInstance().serialization(o);
	}

	private HttpHeaders createHttpHeadersAWS(SignatureAWS signature) {
		HttpHeaders headers = new HttpHeaders();
		MediaType mediaType = new MediaType(Headers.APPLICATION, Headers.JSON, StandardCharsets.UTF_8);
		headers.setContentType(mediaType);
		headers.set(Headers.AUTHORIZATION, signature.getAuthorization());
		headers.set(Headers.AMZ_DATE, signature.getxAmzDate());
		headers.set(Headers.API_KEY, signature.getxApiKey());
		headers.set(Headers.TRACE_ID, signature.getTraceId());
		return headers;
	}

}
