package com.bbva.rbvd.lib.r401;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;

import com.bbva.pisd.dto.insurance.amazon.SignatureAWS;
import com.bbva.pisd.lib.r014.PISDR014;
import com.bbva.pisd.lib.r350.PISDR350;
import com.bbva.rbvd.dto.insurancerefunds.commons.DocumentTypeDTO;
import com.bbva.rbvd.dto.insurancerefunds.commons.IdentityDocumentDTO;
import com.bbva.rbvd.dto.insurancerefunds.refund.ParticipantDTO;
import com.bbva.rbvd.dto.insurancerefunds.refund.ParticipantTypeDTO;
import com.bbva.rbvd.dto.insurancerefunds.rimac.RefundCalculateResponseBO;
import com.bbva.rbvd.dto.insurancerefunds.rimac.RefundCalculatedPayloadBO;
import com.bbva.rbvd.lib.r401.dao.DataHandler;
import com.bbva.rbvd.lib.r401.impl.RBVDR401Impl;
import com.bbva.rbvd.lib.r402.RBVDR402;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/RBVDR401-app.xml",
		"classpath:/META-INF/spring/RBVDR401-app-test.xml",
		"classpath:/META-INF/spring/RBVDR401-arc.xml",
		"classpath:/META-INF/spring/RBVDR401-arc-test.xml" })
public class RBVDR401Test {

	private static final Logger LOGGER = LoggerFactory.getLogger(RBVDR401Test.class);
	@Spy
	private Context context;

	@InjectMocks
	private RBVDR401Impl rbvdR401;

	@InjectMocks
	private DataHandler dataHandler;
	@Mock
	private RBVDR402 rbvdr402;

	@Mock
	private PISDR350 pisdr350;

	@Mock
	private ApplicationConfigurationService applicationConfigurationService;

	@Mock
	private PISDR014 pisdR014;

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
		LOGGER.info(" RBVDR401Test executeTest Start");

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
		participantDTO.setBirthDate(new Date());
		participantDTO.setId("90008806");
		participantDTO.setTraceId("56468446846");


		RefundCalculateResponseBO refundCalculatedBO = new RefundCalculateResponseBO();
		RefundCalculatedPayloadBO refundCalculatedPayloadBO = new RefundCalculatedPayloadBO();
		refundCalculatedPayloadBO.setNroDocumento("75489614");
		List<Integer> er = new ArrayList<>();
		er.add(45);
		er.add(78);

		refundCalculatedPayloadBO.setPorcentajes(er);
		refundCalculatedBO.setPayload(Collections.singletonList(refundCalculatedPayloadBO));

		Map<String,Object> responseMockData1 = new HashMap<>();
		responseMockData1.put("INSURANCE_PRODUCT_ID",new BigDecimal("1245"));
		Map<String,Object> arguments = new HashMap<>();
		arguments.put("INSURANCE_PRODUCT_TYPE","841");


		Map<String,Object> response = new HashMap<>();
		List<Map<String,Object>> lisMap = new ArrayList<>();
		Map<String,Object> mapArgument = new HashMap<>();
		mapArgument.put("INSURED_AMOUNT",new BigDecimal("14"));
		Map<String,Object> mapArgument2 = new HashMap<>();
		mapArgument2.put("INSURED_AMOUNT",new BigDecimal("14"));
		lisMap.add(mapArgument);
		lisMap.add(mapArgument2);
		response.put("dtoInsurance",lisMap);
		Map<String,Object> arguments1 = new HashMap<>();
		arguments.put("INSURANCE_PRODUCT_ID","124566");
		arguments.put("CUSTOMER_ID","978568");
		arguments.put("PARTICIPANT_PERSONAL_ID","70221978");
		arguments.put("PARTICIPANT_ROLE_ID", new BigDecimal("2"));

		when(pisdr350.executeGetASingleRow("PISD.GET_PRODUCT_INFORMATION_LIFE",arguments)).thenReturn(responseMockData1);
		when(pisdr350.executeGetASingleRow("PISD.QUERY_GET_INSURANCE_AMOUNT",arguments1)).thenReturn(response);
		when(rbvdr402.executeCalculateService(anyObject(),anyString())).thenReturn(refundCalculatedBO);
		when(applicationConfigurationService.getProperty(anyString())).thenReturn("DNI");
		when(pisdR014.executeSignatureConstruction(anyString(),anyString(),anyString(),anyString(),anyString())).thenReturn(new SignatureAWS());

		rbvdR401.executeCalculateRefund(participantDTO);

		Assert.assertEquals(0, context.getAdviceList().size());
	}

	@Test
	public void testDataHandlergetProductInformationDB(){
		LOGGER.info(" RBVDR401Test executeTest Start");
		Map<String,Object> response = new HashMap<>();
		response.put("INSURANCE_PRODUCT_ID",new BigDecimal("1245"));
		when(this.pisdr350.executeGetASingleRow(anyString(),anyMap())).thenReturn(response);
		BigDecimal insuranceProductId = dataHandler.getProductInformationDB("841");
		Assert.assertNotNull(insuranceProductId);
		Assert.assertEquals(new BigDecimal("1245"),insuranceProductId);
		Assert.assertEquals(0, context.getAdviceList().size());
		verify(pisdr350, times(1)).executeGetASingleRow(anyString(), anyMap());
	}

	@Test
	public void testDataHandlerGetCumulo(){
		LOGGER.info(" RBVDR401Test executeTest Start");
		Map<String,Object> response = new HashMap<>();
		List<Map<String,Object>> lisMap = new ArrayList<>();
		Map<String,Object> mapArgument = new HashMap<>();
		mapArgument.put("INSURED_AMOUNT",new BigDecimal("14"));
		Map<String,Object> mapArgument2 = new HashMap<>();
		mapArgument2.put("INSURED_AMOUNT",new BigDecimal("14"));
		lisMap.add(mapArgument);
		lisMap.add(mapArgument2);
		response.put("dtoInsurance",lisMap);
		when(this.pisdr350.executeGetASingleRow(anyString(),anyMap())).thenReturn(response);
		BigDecimal insuranceProductId = dataHandler.getCumulo(new BigDecimal("123456"),"9784568","70221978");

		Assert.assertNotNull(insuranceProductId);
		Assert.assertEquals(new BigDecimal("28"),insuranceProductId);
		Assert.assertEquals(0, context.getAdviceList().size());
		verify(pisdr350, times(1)).executeGetASingleRow(anyString(), anyMap());
	}



	
}
