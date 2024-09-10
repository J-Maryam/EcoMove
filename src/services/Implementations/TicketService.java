package services.Implementations;

import dao.Implementations.TicketDao;
import dao.Interfaces.ITicketDao;
import models.entities.Ticket;
import services.Interfaces.ITicketService;

import java.util.List;
import java.util.UUID;

public class TicketService implements ITicketService {

    private ITicketDao iTicketDao;

    public TicketService(TicketDao ticketDao) {
        this.iTicketDao = ticketDao;
    }

    @Override
    public int addTicket(Ticket ticket) {
        return iTicketDao.addTicket(ticket);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return iTicketDao.getAllTickets();
    }

    @Override
    public int updateTicket(Ticket ticket) {
        return iTicketDao.updateTicket(ticket);
    }

    @Override
    public int deleteTicket(UUID id) {
        return iTicketDao.deleteTicket(id);
    }
}
