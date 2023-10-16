package com.bbva.rbvd.dto.insurancerefunds.refund;

import java.io.Serializable;

public class ContactDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String contactDetailType;
    private String number;
    private String address;

    public String getContactDetailType() {
        return this.contactDetailType;
    }

    public void setContactDetailType(String contactDetailType) {
        this.contactDetailType = contactDetailType;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ContactDTO{");
        sb.append("contactDetailType='").append(this.contactDetailType).append('\'');
        sb.append(", number='").append(this.number).append('\'');
        sb.append(", address='").append(this.address).append('\'');
        sb.append('}');
        return sb.toString();
    }
}