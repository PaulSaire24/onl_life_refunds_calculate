package com.bbva.rbvd.lib.r401.dao;

import com.bbva.pisd.dto.insurance.utils.PISDProperties;
import com.bbva.pisd.lib.r350.PISDR350;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.util.CollectionUtils;

import static java.util.stream.Collectors.toList;
import static org.springframework.util.CollectionUtils.isEmpty;

public class DataHandler {

    private PISDR350 pisdr350;

    public DataHandler(PISDR350 pisdr350) {
        this.pisdr350 = pisdr350;
    }

    public BigDecimal getProductInformationDB(String productId){
        Map<String,Object> arguments = new HashMap<>();
        arguments.put("INSURANCE_PRODUCT_TYPE",productId);
        Map<String, Object> responseQueryGetProductInformation =  this.pisdr350.executeGetASingleRow("PISD.GET_PRODUCT_INFORMATION_LIFE", arguments);
        return (BigDecimal) responseQueryGetProductInformation.get("INSURANCE_PRODUCT_ID");
    }

    public BigDecimal getCumulo(BigDecimal insuranceProductId, String customerId, String documentNumber){
        Map<String,Object> arguments = new HashMap<>();
        arguments.put("INSURANCE_PRODUCT_ID",insuranceProductId);
        arguments.put("CUSTOMER_ID",customerId);
        arguments.put("PARTICIPANT_PERSONAL_ID",documentNumber);
        arguments.put("PARTICIPANT_ROLE_ID", new BigDecimal("2"));
        Map<String, Object> responseQueryGetProductInformation =  this.pisdr350.executeGetListASingleRow("PISD.QUERY_GET_INSURANCE_AMOUNT", arguments);


        return this.validateAndReturnCumulo(responseQueryGetProductInformation);
    }

    public BigDecimal validateAndReturnCumulo(Map<String, Object> responseQueryGetCumulus){

        List<Map<String, Object>> rows = (List<Map<String, Object>>) responseQueryGetCumulus.get(PISDProperties.KEY_OF_INSRC_LIST_RESPONSES.getValue());
        BigDecimal sum = BigDecimal.ZERO;
        if(!isEmpty(rows)) {
            List<BigDecimal> listCumulus = rows.stream().map(this::createListCumulus).collect(toList());
            for (BigDecimal amt : listCumulus) {
                sum = sum.add(amt);
            }
        }
        return sum;
    }

    private BigDecimal createListCumulus(Map < String, Object > mapElement){
        return (BigDecimal) mapElement.get(PISDProperties.FIELD_INSURED_AMOUNT.getValue());
    }

}
