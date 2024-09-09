package services.Implementations;

import dao.Implementations.TicketDao;
import models.entities.Ticket;
import services.Interfaces.ITicketService;

import java.util.List;
import java.util.UUID;

public class TicketService implements ITicketService {

    private TicketDao ticketDao;

    public TicketService(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    public int addTicket(Ticket ticket) {
        return ticketDao.addTicket(ticket);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketDao.getAllTickets();
    }

    @Override
    public int updateTicket(Ticket ticket) {
        return ticketDao.updateTicket(ticket);
    }

    @Override
    public int deleteTicket(UUID id) {
        return ticketDao.deleteTicket(id);
    }
}
