package services;

import dao.Implementations.TicketDao;
import models.entities.Ticket;

import java.util.List;
import java.util.UUID;

public class TicketService {

    private TicketDao ticketDao;

    public TicketService(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public int addTicket(Ticket ticket) {
        return ticketDao.addTicket(ticket);
    }

    public List<Ticket> getAllTickets() {
        return ticketDao.getAllTickets();
    }

    public int updateTicket(Ticket ticket) {
        return ticketDao.updateTicket(ticket);
    }

    public int deleteTicket(UUID id) {
        return ticketDao.deleteTicket(id);
    }
}
