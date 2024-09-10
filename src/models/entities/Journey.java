package models.entities;

public class Journey {

    private int id;
    private String departureCity;
    private String destinationCity;

    public Journey() {}

    public Journey(int id, String departureCity, String destinationCity) {
        this.id = id;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

}
