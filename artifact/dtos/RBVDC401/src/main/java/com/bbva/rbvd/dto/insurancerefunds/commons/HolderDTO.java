package com.bbva.rbvd.dto.insurancerefunds.commons;

public class HolderDTO extends CommonsFieldsDTO {

    private String firstName;
    private String lastName;
    private String fullName;
    private IdentityDocumentDTO identityDocument;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public IdentityDocumentDTO getIdentityDocument() { return identityDocument; }
    public void setIdentityDocument(IdentityDocumentDTO identityDocument) { this.identityDocument = identityDocument; }

    @Override
    public String toString() {
        return "HolderDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", identityDocument=" + identityDocument +
                '}';
    }
}