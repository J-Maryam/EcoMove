package dao.Interfaces;

import models.entities.Ticket;

import java.util.List;
import java.util.UUID;

public interface ITicketDao {

    int addTicket(Ticket ticket);
    List<Ticket> getAllTickets();
    int updateTicket(Ticket ticket);
    int deleteTicket(UUID id);

}
