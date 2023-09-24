package com.bbva.rbvd.lib.r401.mapper;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.rbvd.dto.insuranceroyal.refund.ParticipantDTO;
import com.bbva.rbvd.dto.insuranceroyal.rimac.calculate.RefundCalculatedPayloadBO;
import com.bbva.rbvd.dto.insuranceroyal.rimac.calculate.RefundRequestBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MapperMap {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapperMap.class);
    private final ApplicationConfigurationService applicationConfigurationService;

    public MapperMap(ApplicationConfigurationService applicationConfigurationService) {
        this.applicationConfigurationService = applicationConfigurationService;
    }

    public void contructionRequestRimac(List<ParticipantDTO> participantDTOList, RefundRequestBO requesRimac){
        LOGGER.info("contructionRequestRimac start");
        LOGGER.info("contructionRequestRimac participantDTO: {}",participantDTOList);
        RefundCalculatedPayloadBO payload = new RefundCalculatedPayloadBO();

        //payload.setFechaNacimiento(String.valueOf(participantDTOList.get(0).getBirthDate()));
        payload.setFechaNacimiento("1989-05-12");
        payload.setNroDocumento(participantDTOList.get(0).getIdentityDocument().getDocumentNumber());
        payload.setProducto("VIDADINAMICO");
        payload.setTipoDocumento(this.applicationConfigurationService.getProperty(participantDTOList.get(0).getIdentityDocument().getDocumentType().getId()));

        requesRimac.setPayload(payload);
        LOGGER.info("contructionRequestRimac RequestRimac {}",requesRimac);
        LOGGER.info("contructionRequestRimac end");
    }
}
