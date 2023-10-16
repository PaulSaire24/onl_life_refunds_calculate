package com.bbva.rbvd.dto.insurancerefunds.refund;

import com.bbva.rbvd.dto.insurancerefunds.commons.HolderDTO;

import java.util.Date;
import java.util.List;

public class ParticipantDTO extends HolderDTO {
    private String middleName;
    private String secondLastName;
    private ParticipantTypeDTO participantType;
    private List<ContractDetailsDTO> contactDetails;
    private Date birthDate;

    public String getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSecondLastName() {
        return this.secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public ParticipantTypeDTO getParticipantType() {
        return this.participantType;
    }

    public void setParticipantType(ParticipantTypeDTO participantType) {
        this.participantType = participantType;
    }

    public List<ContractDetailsDTO> getContactDetails() {
        return this.contactDetails;
    }

    public void setContactDetails(List<ContractDetailsDTO> contactDetails) {
        this.contactDetails = contactDetails;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "ParticipantDTO{" +
                "middleName='" + middleName + '\'' +
                ", secondLastName='" + secondLastName + '\'' +
                ", participantType=" + participantType +
                ", contactDetails=" + contactDetails +
                ", birthDate=" + birthDate +
                '}';
    }
}