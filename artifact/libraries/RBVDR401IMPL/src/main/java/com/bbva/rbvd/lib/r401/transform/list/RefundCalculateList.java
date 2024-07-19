package com.bbva.rbvd.lib.r401.transform.list;

import com.bbva.rbvd.dto.insurancerefunds.refund.AccumulatedTotalAmountDTO;
import com.bbva.rbvd.dto.insurancerefunds.refund.RefundCalculateDTO;
import com.bbva.rbvd.dto.insurancerefunds.refund.RefundsDTO;
import com.bbva.rbvd.dto.insurancerefunds.refund.TermDTO;
import com.bbva.rbvd.dto.insurancerefunds.refund.UnitDTO;
import com.bbva.rbvd.dto.insurancerefunds.refund.ParticipantDTO;

import com.bbva.rbvd.dto.insurancerefunds.rimac.RefundCalculateResponseBO;
import com.bbva.rbvd.dto.insurancerefunds.rimac.RefundCalculatedPayloadBO;
import com.bbva.rbvd.dto.insurancerefunds.utils.Constans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RefundCalculateList {
    private static final Logger LOGGER = LoggerFactory.getLogger(RefundCalculateList.class);
    public static List<RefundCalculateDTO> constructionResponse(RefundCalculateResponseBO reponseRimac, ParticipantDTO participantDTO, BigDecimal cumulo){
        LOGGER.info("constructionResponse start");
        LOGGER.info("constructionResponse reponseRimac {}",reponseRimac);
        LOGGER.info("constructionResponse reponseRimac {}",reponseRimac.getPayload().size());
        LOGGER.info("constructionResponse participant {}",participantDTO);

        List<RefundCalculateDTO> responseRefund = new ArrayList<>();

        for (RefundCalculatedPayloadBO obj:reponseRimac.getPayload()) {
            RefundCalculateDTO dataDTO = new RefundCalculateDTO();
            //participant
            dataDTO.setParticipants(Collections.singletonList(participantDTO));
            //term
            TermDTO termDTO = new TermDTO();
            termDTO.setNumber(obj.getPlazo());
            dataDTO.setTerm(termDTO);
            //refund
            List<RefundsDTO> refundsDTOList = new ArrayList<>();

            for (Integer ob:obj.getPorcentajes()) {
                UnitDTO unitDTO = new UnitDTO();
                unitDTO.setUnitType(Constans.PERCENTAGE);
                unitDTO.setPercentage(BigDecimal.valueOf(ob));
                RefundsDTO refundsDTO = new RefundsDTO();
                refundsDTO.setUnit(unitDTO);
                refundsDTOList.add(refundsDTO);
            }
            dataDTO.setRefunds(refundsDTOList);
            AccumulatedTotalAmountDTO accumulatedTotalAmountDTO = new AccumulatedTotalAmountDTO();
            accumulatedTotalAmountDTO.setAmount(cumulo.doubleValue());
            accumulatedTotalAmountDTO.setCurrency("PEN");
            dataDTO.setAccumulatedTotalAmount(accumulatedTotalAmountDTO);
            responseRefund.add(dataDTO);
        }
        LOGGER.info("constructionResponse end");
        LOGGER.info("constructionResponse response size: {}",responseRefund.size());
        LOGGER.info("constructionResponse response body: {}",responseRefund);
        return responseRefund;
    }
}
