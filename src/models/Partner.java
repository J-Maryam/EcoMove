import java.util.Date;

public class Partner {

    private int id;
    private String companyName;
    private String businessContact;
    private PartnerStatus partnerStatus;
    private String geographicZone;
    private String specialConditions;
    private TransportType transportType ;
    private Date creationDate;

    public Partner(int id, String companyName, String businessContact, PartnerStatus partnerStatus, String geographicZone, String specialConditions, TransportType transportType, Date creationDate) {
        this.id = id;
        this.companyName = companyName;
        this.businessContact = businessContact;
        this.partnerStatus = partnerStatus;
        this.geographicZone = geographicZone;
        this.specialConditions = specialConditions;
        this.transportType = transportType;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public PartnerStatus getPartnerStatus() {
        return partnerStatus;
    }

    public void setPartnerStatus(PartnerStatus partnerStatus) {
        this.partnerStatus = partnerStatus;
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

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

}
