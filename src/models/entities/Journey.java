package models.entities;

import java.time.LocalDate;
import java.util.List;

public class Journey {

    private int id;
    private City departureCity;
    private City destinationCity;
    private float distance;
    private List<Ticket> tickets;

    public Journey() {}

    public Journey(int id,City departureCity, City destinationCity, float distance) {
        this.id = id;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public City getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(City departureCity) {
        this.departureCity = departureCity;
    }

    public City getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(City destinationCity) {
        this.destinationCity = destinationCity;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

}
