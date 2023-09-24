package com.bbva.rbvd.lib.r401;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;
import javax.annotation.Resource;

import com.bbva.rbvd.dto.insuranceroyal.commons.DocumentTypeDTO;
import com.bbva.rbvd.dto.insuranceroyal.commons.IdentityDocumentDTO;
import com.bbva.rbvd.dto.insuranceroyal.refund.ParticipantDTO;
import com.bbva.rbvd.dto.insuranceroyal.refund.ParticipantTypeDTO;
import com.bbva.rbvd.dto.insuranceroyal.rimac.calculate.RefundCalculatedBO;
import com.bbva.rbvd.dto.insuranceroyal.rimac.calculate.RefundCalculatedPayloadBO;
import com.bbva.rbvd.lib.r402.RBVDR402;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/RBVDR401-app.xml",
		"classpath:/META-INF/spring/RBVDR401-app-test.xml",
		"classpath:/META-INF/spring/RBVDR401-arc.xml",
		"classpath:/META-INF/spring/RBVDR401-arc-test.xml" })
public class RBVDR401Test {

	private static final Logger LOGGER = LoggerFactory.getLogger(RBVDR401Test.class);
	@Spy
	private Context context;

	@Resource(name = "rbvdR401")
	private RBVDR401 rbvdR401;
	@Resource(name = "rbvdR402")
	private RBVDR402 rbvdr402;

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
		LOGGER.info(" RBVDR401Test executeTest Start");

		List<ParticipantDTO> participantDTOList = new ArrayList<>();
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
		participantDTO.setBirthDate(LocalDate.parse("2023-05-15"));
		participantDTO.setId("90008806");
		participantDTO.setTraceId("56468446846");
		participantDTOList.add(participantDTO);


		RefundCalculatedBO refundCalculatedBO = new RefundCalculatedBO();
		RefundCalculatedPayloadBO refundCalculatedPayloadBO = new RefundCalculatedPayloadBO();
		refundCalculatedPayloadBO.setNroDocumento("75489614");
		List<Integer> er = new ArrayList<>();
		er.add(45);
		er.add(78);

		refundCalculatedPayloadBO.setPorcentajes(er);
		refundCalculatedBO.setPayload(Collections.singletonList(refundCalculatedPayloadBO));

		when(rbvdr402.executeCalculateService(anyObject(),anyString())).thenReturn(refundCalculatedBO);
		rbvdR401.executeCalculateRefund(participantDTOList);

		Assert.assertEquals(0, context.getAdviceList().size());
	}


	
}
