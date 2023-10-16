package com.bbva.rbvd.lib.r402.impl;

import com.bbva.pisd.dto.insurance.amazon.SignatureAWS;
import com.bbva.rbvd.dto.insurancerefunds.rimac.RefundCalculateResponseBO;
import com.bbva.rbvd.dto.insurancerefunds.rimac.RefundRequestBO;
import com.bbva.rbvd.dto.insurancerefunds.utils.InsuranceRefundsProperties;
import com.bbva.rbvd.dto.insurancerefunds.utils.Constans;
import com.bbva.rbvd.lib.r402.util.JsonUtil;
import com.bbva.rbvd.lib.r402.util.RimacExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;

import javax.ws.rs.HttpMethod;
import java.nio.charset.StandardCharsets;

public class RBVDR402Impl extends RBVDR402Abstract {
	private static final Logger LOGGER = LoggerFactory.getLogger(RBVDR402Impl.class);


	@Override
	public RefundCalculateResponseBO executeCalculateService(RefundRequestBO payload, String traceId) {
		LOGGER.info("***** RBVDR402Impl - executeCalculateService ***** payload body: {}", payload);
		String requestJson = getRequestJson(payload);

		LOGGER.info("***** RBVDR402Impl - executeCalculateService ***** Request body: {}", requestJson);

		String uri = this.applicationConfigurationService.getProperty(InsuranceRefundsProperties.REFUNDS_LIFE_CALCULATE_URI.getValue());

		SignatureAWS signatureAWS = this.pisdR014.executeSignatureConstruction(requestJson , HttpMethod.POST,
				uri, null, traceId);
		LOGGER.info("***** RBVDR402Impl - executeCalculateService ***** signatureAWS: {}", signatureAWS);

		HttpEntity<String> entity = new HttpEntity<>(requestJson, createHttpHeadersAWS(signatureAWS));
		LOGGER.info("***** RBVDR402Impl - executeCalculateService ***** HttpEntity: {}", entity);

		RefundCalculateResponseBO response = null;

		try {
			response = this.externalApiConnector.postForObject(InsuranceRefundsProperties.REFUNDS_LIFE_CALCULATE.getValue(),entity,RefundCalculateResponseBO.class);
			LOGGER.info("*** RBVDR402Impl - executeCalculateService *** Response: {}", getRequestJson(response));
		}catch (RestClientException e){
			LOGGER.debug("*** RBVDR402Impl - executeCalculateService *** Exception: {}", e.getMessage());
			RimacExceptionHandler exceptionHandler = new RimacExceptionHandler();
			exceptionHandler.handler(e);
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
		MediaType mediaType = new MediaType("application", "json", StandardCharsets.UTF_8);
		headers.setContentType(mediaType);
		headers.set(Constans.AUTHORIZATION, signature.getAuthorization());
		headers.set("X-Amz-Date", signature.getxAmzDate());
		headers.set("x-api-key", signature.getxApiKey());
		headers.set("traceId", signature.getTraceId());
		return headers;
	}
}
