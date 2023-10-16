package com.bbva.rbvd.dto.insurancerefunds.rimac;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponseBO extends CommonFieldsBO {
    private String code;
    private String message;
    private List<String> details;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}
