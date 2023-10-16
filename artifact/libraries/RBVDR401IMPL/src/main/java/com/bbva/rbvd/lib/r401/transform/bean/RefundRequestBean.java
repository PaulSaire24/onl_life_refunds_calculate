package com.bbva.rbvd.lib.r401.transform.bean;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.rbvd.dto.insurancerefunds.refund.ParticipantDTO;
import com.bbva.rbvd.dto.insurancerefunds.rimac.RefundCalculatedPayloadBO;
import com.bbva.rbvd.dto.insurancerefunds.rimac.RefundRequestBO;
import com.bbva.rbvd.dto.insurancerefunds.utils.Constans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class RefundRequestBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(RefundRequestBean.class);
    private final ApplicationConfigurationService applicationConfigurationService;

    public RefundRequestBean(ApplicationConfigurationService applicationConfigurationService) {
        this.applicationConfigurationService = applicationConfigurationService;
    }

    public RefundRequestBO contructionRequestRimac(ParticipantDTO participantDTO){
        LOGGER.info("contructionRequestRimac start");
        LOGGER.info("contructionRequestRimac participantDTO: {}",participantDTO);
        RefundRequestBO requesRimac = new RefundRequestBO();
        RefundCalculatedPayloadBO payload = new RefundCalculatedPayloadBO();

        if(Objects.nonNull(participantDTO.getBirthDate())){
            LOGGER.info("Fecha de nacimiento: {}",toLocalDate(participantDTO.getBirthDate()));
            payload.setFechaNacimiento(String.valueOf(toLocalDate(participantDTO.getBirthDate())));
        }
        payload.setNroDocumento(participantDTO.getIdentityDocument().getDocumentNumber());
        payload.setProducto(Constans.PRODUCTO);
        payload.setTipoDocumento(this.applicationConfigurationService.getProperty(participantDTO.getIdentityDocument().getDocumentType().getId()));

        requesRimac.setPayload(payload);
        LOGGER.info("contructionRequestRimac RequestRimac {}",requesRimac);
        LOGGER.info("contructionRequestRimac end");
        return  requesRimac;
    }

    public static LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(Constans.ZONE_ID).toLocalDate();
    }
}
