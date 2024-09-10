package models.entities;

import java.util.List;

public class ReservationTicket {

    private int id;
    private List<Reservation> reservations ;
    private List<Ticket> tickets ;

    public ReservationTicket() {}

    public ReservationTicket(int id, List<Reservation> reservations, List<Ticket> tickets) {
        this.id = id;
        this.reservations = reservations;
        this.tickets = tickets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
