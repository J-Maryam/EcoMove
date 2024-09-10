package models.entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class Reservation {

    private UUID id;
    private Client client;
    private LocalDate date;
    private float price;
    private boolean cancelled;

    public Reservation() {}

    public Reservation(UUID id, Client client, LocalDate date, float price, boolean cancelled) {
        this.id = id;
        this.client = client;
        this.date = date;
        this.price = price;
        this.cancelled = cancelled;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
