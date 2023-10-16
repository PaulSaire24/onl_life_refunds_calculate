package com.bbva.rbvd.dto.insurancerefunds.commons;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.bbva.apx.dto.AbstractDTO;

public class CommonsFieldsDTO extends AbstractDTO {

    private String id;
    private String name;
    private BigDecimal amount;
    private String currency;
    private String description;
    private boolean isRecommended;
    private LocalDate maturityDate;
    private String traceId;
    private String saleChannelId;
    private String creationUser;
    private String userAudit;
    private String customerId;
    private TierDTO tier;
    private Double bankingFactor;
    private boolean isPrincipal;
    private Long validityDays;
    private String aap;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRecommended() {
        return isRecommended;
    }

    public void setRecommended(boolean recommended) {
        isRecommended = recommended;
    }

    public LocalDate getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getSaleChannelId() {
        return saleChannelId;
    }

    public void setSaleChannelId(String saleChannelId) {
        this.saleChannelId = saleChannelId;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    public String getUserAudit() {
        return userAudit;
    }

    public void setUserAudit(String userAudit) {
        this.userAudit = userAudit;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public TierDTO getTier() {
        return tier;
    }

    public void setTier(TierDTO tier) {
        this.tier = tier;
    }

    public Double getBankingFactor() {
        return bankingFactor;
    }

    public void setBankingFactor(Double bankingFactor) {
        this.bankingFactor = bankingFactor;
    }

    public boolean isPrincipal() {
        return isPrincipal;
    }

    public void setPrincipal(boolean principal) {
        isPrincipal = principal;
    }

    public Long getValidityDays() {
        return validityDays;
    }

    public void setValidityDays(Long validityDays) {
        this.validityDays = validityDays;
    }

    public String getAap() {
        return aap;
    }

    public void setAap(String aap) {
        this.aap = aap;
    }
}
