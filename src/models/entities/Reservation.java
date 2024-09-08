package models.entities;

import java.util.Date;

public class Reservation {

    private int id;
    private Client client;
    private Date date;
    private float price;
    private boolean cancelled;

    public Reservation(int id, Client client, Date date, float price, boolean cancelled) {
        this.id = id;
        this.client = client;
        this.date = date;
        this.price = price;
        this.cancelled = cancelled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
