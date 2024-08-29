import java.util.Date;

public class Partenaire {

    private int id;
    private String companyName;
    private String contactCommercial;
    private PartenaireStatut partenaireStatut;
    private String geographicZone;
    private String conditionsSpeciales;
    private TransportType transportType ;
    private Date dateCreation;

    public Partenaire(int id, String companyName, String contactCommercial, PartenaireStatut partenaireStatut, String geographicZone, String conditionsSpeciales, TransportType transportType, Date dateCreation) {
        this.id = id;
        this.companyName = companyName;
        this.contactCommercial = contactCommercial;
        this.partenaireStatut = partenaireStatut;
        this.geographicZone = geographicZone;
        this.conditionsSpeciales = conditionsSpeciales;
        this.transportType = transportType;
        this.dateCreation = dateCreation;
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

    public String getContactCommercial() {
        return contactCommercial;
    }

    public void setContactCommercial(String contactCommercial) {
        this.contactCommercial = contactCommercial;
    }

    public PartenaireStatut getPartenaireStatut() {
        return partenaireStatut;
    }

    public void setPartenaireStatut(PartenaireStatut partenaireStatut) {
        this.partenaireStatut = partenaireStatut;
    }

    public String getGeographicZone() {
        return geographicZone;
    }

    public void setGeographicZone(String geographicZone) {
        this.geographicZone = geographicZone;
    }

    public String getConditionsSpeciales() {
        return conditionsSpeciales;
    }

    public void setConditionsSpeciales(String conditionsSpeciales) {
        this.conditionsSpeciales = conditionsSpeciales;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

}
