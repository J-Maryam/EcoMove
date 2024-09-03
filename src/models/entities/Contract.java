package models.entities;

import models.enums.ContractStatus;

import java.util.Date;
import java.util.UUID;

public class Contract {

    private UUID id;
    private Date startDate;
    private Date endDate;
    private float specialRate;
    private String agreementConditions;
    private boolean renewable;
    private ContractStatus contractStatus;
    private UUID partnerId;

    public Contract(UUID id, Date startDate, Date endDate, float specialRate, String agreementConditions, boolean renewable, ContractStatus contractStatus, UUID partnerId) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.specialRate = specialRate;
        this.agreementConditions = agreementConditions;
        this.renewable = renewable;
        this.contractStatus = contractStatus;
        this.partnerId = partnerId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public float getSpecialRate() {
        return specialRate;
    }

    public void setSpecialRate(float specialRate) {
        this.specialRate = specialRate;
    }

    public String getAgreementConditions() {
        return agreementConditions;
    }

    public void setAgreementConditions(String agreementConditions) {
        this.agreementConditions = agreementConditions;
    }

    public boolean isRenewable() {
        return renewable;
    }

    public void setRenewable(boolean renewable) {
        this.renewable = renewable;
    }

    public ContractStatus getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(ContractStatus contractStatus) {
        this.contractStatus = contractStatus;
    }

    public UUID getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(UUID partnerId) {
        this.partnerId = partnerId;
    }

}
