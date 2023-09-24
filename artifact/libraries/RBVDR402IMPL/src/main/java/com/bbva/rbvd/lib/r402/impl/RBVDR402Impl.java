package com.bbva.rbvd.lib.r402.impl;

import com.bbva.pisd.dto.insurance.amazon.SignatureAWS;
import com.bbva.rbvd.dto.insuranceroyal.rimac.calculate.RefundCalculatedBO;
import com.bbva.rbvd.dto.insuranceroyal.rimac.calculate.RefundRequestBO;
import com.bbva.rbvd.dto.insuranceroyal.utils.InsuranceRoyalProperties;
import com.bbva.rbvd.lib.r402.util.JsonUtil;
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
	private static final String AUTHORIZATION = "Authorization";

	@Override
	public RefundCalculatedBO executeCalculateService(RefundRequestBO payload, String traceId) {
		LOGGER.info("***** RBVDR402Impl - executeCalculateService ***** payload body: {}", payload);
		String requestJson = getRequestJson(payload);

		LOGGER.info("***** RBVDR402Impl - executeCalculateService ***** Request body: {}", requestJson);

		String uri = this.applicationConfigurationService.getProperty(InsuranceRoyalProperties.REFUNDS_LIFE_CALCULATE_URI.getValue());

		SignatureAWS signatureAWS = this.pisdR014.executeSignatureConstruction(requestJson , HttpMethod.POST,
				uri, null, traceId);

		HttpEntity<String> entity = new HttpEntity<>(requestJson, createHttpHeadersAWS(signatureAWS));

		RefundCalculatedBO response = null;

		try {
			response = this.externalApiConnector.postForObject(InsuranceRoyalProperties.REFUNDS_LIFE_CALCULATE.getValue(),entity,RefundCalculatedBO.class);
			LOGGER.info("*** RBVDR402Impl - executeCalculateService *** Response: {}", getRequestJson(response));
		}catch (RestClientException e){
			LOGGER.debug("*** RBVDR402Impl - executeCalculateService *** Exception: {}", e.getMessage());
		}
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
		headers.set(AUTHORIZATION, signature.getAuthorization());
		headers.set("X-Amz-Date", signature.getxAmzDate());
		headers.set("x-api-key", signature.getxApiKey());
		headers.set("traceId", signature.getTraceId());
		return headers;
	}
}
