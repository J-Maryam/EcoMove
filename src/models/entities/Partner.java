package models.entities;

import models.enums.PartnerStatus;
import models.enums.TransportType;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class Partner {

    private UUID id;
    private String companyName;
    private String businessContact;
    private TransportType transportType ;
    private String geographicZone;
    private String specialConditions;
    private PartnerStatus partnerStatus;
    private LocalDate creationDate;

    public Partner() {}

    public Partner(UUID id, String companyName, String businessContact, TransportType transportType, String geographicZone, String specialConditions, PartnerStatus partnerStatus, LocalDate creationDate) {
        this.id = id;
        this.companyName = companyName;
        this.businessContact = businessContact;
        this.partnerStatus = partnerStatus;
        this.geographicZone = geographicZone;
        this.specialConditions = specialConditions;
        this.transportType = transportType;
        this.creationDate = creationDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBusinessContact() {
        return businessContact;
    }

    public void setBusinessContact(String businessContact) {
        this.businessContact = businessContact;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public String getGeographicZone() {
        return geographicZone;
    }

    public void setGeographicZone(String geographicZone) {
        this.geographicZone = geographicZone;
    }

    public String getSpecialConditions() {
        return specialConditions;
    }

    public void setSpecialConditions(String specialConditions) {
        this.specialConditions = specialConditions;
    }

    public PartnerStatus getPartnerStatus() {
        return partnerStatus;
    }

    public void setPartnerStatus(PartnerStatus partnerStatus) {
        this.partnerStatus = partnerStatus;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
