import java.util.Date;

public class Billet {

    private int id;
    private TransportType transportType;
    private float prixAchat;
    private float prixVente;
    private Date dateVente;
    private BilletStatus billetStatus;

    public Billet(int id, TransportType transportType, float prixAchat, float prixVente, Date dateVente, BilletStatus status) {
        this.id = id;
        this.transportType = transportType;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
        this.dateVente = dateVente;
        this.billetStatus = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public float getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(float prixAchat) {
        this.prixAchat = prixAchat;
    }

    public float getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(float prixVente) {
        this.prixVente = prixVente;
    }

    public Date getDateVente() {
        return dateVente;
    }

    public void setDateVente(Date dateVente) {
        this.dateVente = dateVente;
    }

    public BilletStatus getBilletStatus() {
        return billetStatus;
    }

    public void setBilletStatus(BilletStatus billetStatus) {
        this.billetStatus = billetStatus;
    }

}
