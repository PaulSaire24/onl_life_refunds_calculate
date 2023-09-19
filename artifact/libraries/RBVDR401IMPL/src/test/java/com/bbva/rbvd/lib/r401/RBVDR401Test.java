package com.bbva.rbvd.lib.r401;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;
import javax.annotation.Resource;

import com.bbva.pisd.dto.insurance.amazon.SignatureAWS;
import com.bbva.pisd.lib.r014.PISDR014;
import com.bbva.elara.utility.api.connector.APIConnector;
import com.bbva.rbvd.dto.insuranceroyal.rimac.calculate.RefundCalculatedBO;
import com.bbva.rbvd.dto.insuranceroyal.rimac.calculate.RefundRequestBO;
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

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/RBVDR401-app.xml",
		"classpath:/META-INF/spring/RBVDR401-app-test.xml",
		"classpath:/META-INF/spring/RBVDR401-arc.xml",
		"classpath:/META-INF/spring/RBVDR401-arc-test.xml" })
public class RBVDR401Test {

	private static final Logger LOGGER = LoggerFactory.getLogger(RBVDR401Test.class);

	@Resource(name = "externalApiConnector")
	private APIConnector externalApiConnector;

	@Spy
	private Context context;

	@Resource(name = "rbvdR401")
	private RBVDR401 rbvdR401;

	@Resource(name = "pisdR014")
	private PISDR014 pisdr014;

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
		Object result = this.rbvdR401;
		if(this.rbvdR401 instanceof Advised){
			Advised advised = (Advised) this.rbvdR401;
			result = advised.getTargetSource().getTarget();
		}
		return result;
	}
	
	@Test
	public void executeTest(){
		LOGGER.info("executeTest Start");

		/*List<ParticipantDTO> participantDTOList = new ArrayList<>();
		ParticipantDTO participantDTO = new ParticipantDTO();
		participantDTO.setId("2452");
		ParticipantTypeDTO participantTypeDTO = new ParticipantTypeDTO();
		participantTypeDTO.setId("BENEFICIARY");
		participantDTO.setParticipantType(participantTypeDTO);
		IdentityDocumentDTO identityDocumentDTO = new IdentityDocumentDTO();
		DocumentTypeDTO documentTypeDTO = new DocumentTypeDTO();
		documentTypeDTO.setId("DNI");
		identityDocumentDTO.setDocumentType(documentTypeDTO);
		identityDocumentDTO.setDocumentNumber("45784578");
		participantDTO.setIdentityDocument(identityDocumentDTO);
		participantDTO.setBirthDate("2023-05-15");
		participantDTO.setId("90008806");
		participantDTO.setTraceId("56468446846");
		participantDTOList.add(participantDTO);*/

		//when(pisdr014.executeSignatureConstruction(anyString(),anyString(),anyString(),anyString(),anyString())).thenReturn(new SignatureAWS());
		SignatureAWS signatureAWS = new SignatureAWS("sfe","hhh","6454454","jhgygygygyg");
		when(pisdr014.executeSignatureConstruction(anyString(), anyString(), anyString(), anyString(), anyString()))
				.thenReturn(signatureAWS);

		/*RefundCalculatedBO refundCalculatedBO = new RefundCalculatedBO();
		RefundCalculatedPayloadBO refundCalculatedPayloadBO = new RefundCalculatedPayloadBO();
		refundCalculatedPayloadBO.setNroDocumento("75489614");

		PorcentajeDTO porcentajeDTO = new PorcentajeDTO();
		porcentajeDTO.setPorcentaje(45);

		refundCalculatedPayloadBO.setPorcentajes(Collections.singletonList(porcentajeDTO));
		refundCalculatedBO.setPayload(Collections.singletonList(refundCalculatedPayloadBO));

		when(externalApiConnector.postForObject(anyString(), any(HttpEntity.class),eq(RefundCalculatedBO.class))).
				thenReturn(refundCalculatedBO);*/

		RefundRequestBO payload = new RefundRequestBO();
		String traceId = "123456789";

		rbvdR401.executeCalculateService(payload,traceId);

		Assert.assertEquals(0, context.getAdviceList().size());
	}

	@Test
	public void executeTestException(){
		LOGGER.info("executeTestException Start");

		//when(pisdr014.executeSignatureConstruction(anyString(),anyString(),anyString(),anyString(),anyString())).thenReturn(new SignatureAWS());
		SignatureAWS signatureAWS = new SignatureAWS("sfe","hhh","6454454","jhgygygygyg");
		when(pisdr014.executeSignatureConstruction(anyString(), anyString(), anyString(), anyString(), anyString()))
				.thenReturn(signatureAWS);

		when(externalApiConnector.postForObject(anyString(), any(HttpEntity.class),eq(RefundCalculatedBO.class))).
				thenThrow(new RestClientException("error en el servicio"));

		RefundRequestBO payload = new RefundRequestBO();
		String traceId = "123456789";

		rbvdR401.executeCalculateService(payload,traceId);

		Assert.assertEquals(0, context.getAdviceList().size());
	}

	
}
