package services.Interfaces;

import models.entities.Ticket;

import java.util.List;
import java.util.UUID;

public interface ITicketService {

    int addTicket(Ticket ticket);
    List<Ticket> getAllTickets();
    int updateTicket(Ticket ticket);
    int deleteTicket(UUID id);
}
