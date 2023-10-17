package com.bbva.rbvd.dto.insurancerefunds.commons;

import com.bbva.apx.dto.AbstractDTO;

public class IdentityDocumentDTO extends AbstractDTO {

    private DocumentTypeDTO documentType;
    private String documentNumber;

    public DocumentTypeDTO getDocumentType() { return documentType; }
    public void setDocumentType(DocumentTypeDTO documentType) { this.documentType = documentType; }
    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IdentityDocumentDTO{");
        sb.append("documentType='").append(documentType).append('\'');
        sb.append("documentNumber='").append(documentNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }

}