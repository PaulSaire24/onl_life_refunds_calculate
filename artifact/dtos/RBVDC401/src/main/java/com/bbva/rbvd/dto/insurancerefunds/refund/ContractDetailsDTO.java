package com.bbva.rbvd.dto.insurancerefunds.refund;

import java.io.Serializable;

public class ContractDetailsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private ContactDTO contact;

    public ContactDTO getContact() {
        return this.contact;
    }

    public void setContact(ContactDTO contact) {
        this.contact = contact;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ContractDetailsDTO{");
        sb.append("contact=").append(this.contact);
        sb.append('}');
        return sb.toString();
    }
}