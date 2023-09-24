package com.bbva.rbvd.lib.r401.mapper;


import com.bbva.rbvd.dto.insuranceroyal.refund.ParticipantDTO;
import com.bbva.rbvd.dto.insuranceroyal.refund.RefundCalculateDTO;
import com.bbva.rbvd.dto.insuranceroyal.refund.TermDTO;
import com.bbva.rbvd.dto.insuranceroyal.refund.UnitDTO;
import com.bbva.rbvd.dto.insuranceroyal.refund.RefundsDTO;
import com.bbva.rbvd.dto.insuranceroyal.rimac.calculate.RefundCalculatedBO;
import com.bbva.rbvd.dto.insuranceroyal.rimac.calculate.RefundCalculatedPayloadBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MapperBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapperBean.class);
    public static List<RefundCalculateDTO> constructionResponse(RefundCalculatedBO reponseRimac, List<ParticipantDTO> participantDTOList){
        LOGGER.info("constructionResponse start");
        LOGGER.info("constructionResponse reponseRimac {}",reponseRimac);
        LOGGER.info("constructionResponse reponseRimac {}",reponseRimac.getPayload().size());
        LOGGER.info("constructionResponse participant {}",participantDTOList);

        List<RefundCalculateDTO> responseRefund = new ArrayList<>();

        for (RefundCalculatedPayloadBO obj:reponseRimac.getPayload()) {
            RefundCalculateDTO dataDTO = new RefundCalculateDTO();
            //participant
            dataDTO.setParticipants(participantDTOList);
            //term
            TermDTO termDTO = new TermDTO();
            termDTO.setNumber(obj.getPlazo());
            dataDTO.setTerm(termDTO);
            LOGGER.info("Term unit {}",termDTO.getUnit());
            //refund
            List<RefundsDTO> refundsDTOList = new ArrayList<>();

            for (Integer ob:obj.getPorcentajes()) {
                UnitDTO unitDTO = new UnitDTO();
                unitDTO.setUnitType("PERCENTAGE");
                unitDTO.setPercentage(BigDecimal.valueOf(ob));
                RefundsDTO refundsDTO = new RefundsDTO();
                refundsDTO.setUnit(unitDTO);
                refundsDTOList.add(refundsDTO);
            }
            dataDTO.setRefunds(refundsDTOList);
            responseRefund.add(dataDTO);
        }
        LOGGER.info("constructionResponse end");
        LOGGER.info("constructionResponse response size: {}",responseRefund.size());
        LOGGER.info("constructionResponse response body: {}",responseRefund);
        return responseRefund;
    }
}
