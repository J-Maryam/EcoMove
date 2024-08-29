import java.util.Date;

public class Promotion {

    private int id;
    private String promoName;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private ReductionType reductionType;
    private float reductionValue;
    private String conditions;
    private PromoStatus promoStatus;

    public Promotion(int id, String promoName, String description, Date dateDebut, Date dateFin, ReductionType reductionType, float reductionValue, String conditions, PromoStatus promoStatus) {

        this.id = id;
        this.promoName = promoName;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.reductionType = reductionType;
        this.reductionValue = reductionValue;
        this.conditions = conditions;
        this.promoStatus = promoStatus;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public ReductionType getReductionType() {
        return reductionType;
    }

    public void setReductionType(ReductionType reductionType) {
        this.reductionType = reductionType;
    }

    public float getReductionValue() {
        return reductionValue;
    }

    public void setReductionValue(float reductionValue) {
        this.reductionValue = reductionValue;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public PromoStatus getPromoStatus() {
        return promoStatus;
    }

    public void setPromoStatus(PromoStatus promoStatus) {
        this.promoStatus = promoStatus;
    }

}
