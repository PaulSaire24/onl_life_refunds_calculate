package com.bbva.rbvd.dto.insurancerefunds.refund;

import com.bbva.rbvd.dto.insurancerefunds.commons.CommonsFieldsDTO;

public class ParticipantTypeDTO extends CommonsFieldsDTO {
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ParticipantTypeDTO{");
        sb.append("id='").append(getId()).append('\'');
        sb.append('}');
        return sb.toString();
    }
}