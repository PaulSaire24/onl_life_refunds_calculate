package com.bbva.rbvd.lib.r402;

import com.bbva.apx.exception.business.BusinessException;
import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;
import javax.annotation.Resource;

import com.bbva.elara.utility.api.connector.APIConnector;
import com.bbva.pisd.dto.insurance.amazon.SignatureAWS;
import com.bbva.rbvd.dto.insurancerefunds.rimac.RefundCalculateResponseBO;
import com.bbva.rbvd.dto.insurancerefunds.rimac.RefundRequestBO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestClientException;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/RBVDR402-app.xml",
		"classpath:/META-INF/spring/RBVDR402-app-test.xml",
		"classpath:/META-INF/spring/RBVDR402-arc.xml",
		"classpath:/META-INF/spring/RBVDR402-arc-test.xml" })
public class RBVDR402Test {

	private static final Logger LOGGER = LoggerFactory.getLogger(RBVDR402Test.class);

	@Resource(name = "externalApiConnector")
	private APIConnector externalApiConnector;

	@Spy
	private Context context;

	@Resource(name = "rbvdR402")
	private RBVDR402 rbvdR402;

	@Resource(name = "applicationConfigurationService")
	private ApplicationConfigurationService applicationConfigurationService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		context = new Context();
		ThreadContext.set(context);
		getObjectIntrospection();
	}
	
	private Object getObjectIntrospection() throws Exception{
		Object result = this.rbvdR402;
		if(this.rbvdR402 instanceof Advised){
			Advised advised = (Advised) this.rbvdR402;
			result = advised.getTargetSource().getTarget();
		}
		return result;
	}
	
	@Test
	public void executeTest(){
		LOGGER.info("executeTest Start");

		SignatureAWS signatureAWS = new SignatureAWS("sfe","hhh","6454454","jhgygygygyg");

		/*when(pisdr014.executeSignatureConstruction(anyString(), anyString(), anyString(), anyString(), anyString()))
				.thenReturn(signatureAWS);*/

		RefundRequestBO payload = new RefundRequestBO();
		String requestJson = "requestJson";

		rbvdR402.executeCalculateService(signatureAWS,requestJson);

		Assert.assertEquals(0, context.getAdviceList().size());
	}

	@Test(expected = BusinessException.class)
	public void executeTestException(){
		LOGGER.info("executeTestException Start");

		SignatureAWS signatureAWS = new SignatureAWS("sfe","hhh","6454454","jhgygygygyg");
		/*when(pisdr014.executeSignatureConstruction(anyString(), anyString(), anyString(), anyString(), anyString()))
				.thenReturn(signatureAWS);*/

		when(externalApiConnector.postForObject(anyString(), any(HttpEntity.class),eq(RefundCalculateResponseBO.class))).
				thenThrow(new RestClientException("ANY ERROR",new Throwable()));

		RefundRequestBO payload = new RefundRequestBO();
		String requestJson = "requestJson";

		rbvdR402.executeCalculateService(signatureAWS,requestJson);
	}

}
