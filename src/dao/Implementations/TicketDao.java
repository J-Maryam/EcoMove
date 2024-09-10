package dao.Implementations;

import dao.Interfaces.ITicketDao;
import models.entities.Contract;
import models.entities.Ticket;
import models.enums.ContractStatus;
import models.enums.TicketStatus;
import models.enums.TransportType;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TicketDao implements ITicketDao {

    private Connection connection;

    public TicketDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int addTicket(Ticket ticket) {
        String sql = "insert into tickets (id, transportType, purchasePrice, salePrice, saleDate, ticketStatus, contractId) values(?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setObject(1, ticket.getId(), java.sql.Types.OTHER);
            ps.setObject(2, ticket.getTransportType().toString(), java.sql.Types.OTHER);
            ps.setFloat(3, ticket.getPurchasePrice());
            ps.setFloat(4, ticket.getSalePrice());
            ps.setObject(5, ticket.getSaleDate());
            ps.setObject(6, ticket.getTicketStatus().toString(), java.sql.Types.OTHER);
            ps.setObject(7, ticket.getContract().getId(), java.sql.Types.OTHER);

            int isAdded = ps.executeUpdate();
            return isAdded;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public List<Ticket> getAllTickets() {
        String sql = "SELECT t.id, t.transportType, t.purchasePrice, t.salePrice, t.saleDate, t.ticketStatus, c.id as contractId, c.startDate, c.endDate, c.specialRate, c.agreementConditions, c.renewable, c.contractStatus FROM tickets t LEFT JOIN contracts c ON t.contractId = c.id";

        List<Ticket> tickets = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UUID ticketId = UUID.fromString(rs.getString("id"));
                TransportType transportType = TransportType.valueOf(rs.getString("transportType"));
                float purchasePrice = rs.getFloat("purchasePrice");
                float salePrice = rs.getFloat("salePrice");
                LocalDate saleDate = LocalDate.parse(rs.getString("saleDate"));
                TicketStatus ticketStatus = TicketStatus.valueOf(rs.getString("ticketStatus"));

                UUID contractId = UUID.fromString(rs.getString("contractId"));
                Contract contract = new Contract(
                        contractId,
                        rs.getObject("startDate", LocalDate.class),
                        rs.getObject("endDate", LocalDate.class),
                        rs.getFloat("specialRate"),
                        rs.getString("agreementConditions"),
                        rs.getBoolean("renewable"),
                        ContractStatus.valueOf(rs.getString("contractStatus")),
                        contractId
                );

                Ticket ticket = new Ticket(
                        ticketId,
                        transportType,
                        purchasePrice,
                        salePrice,
                        saleDate,
                        ticketStatus,
                        contract
                );
                tickets.add(ticket);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public int updateTicket(Ticket ticket) {
        String sql = "UPDATE tickets SET transportType = ?, purchasePrice = ?, salePrice = ?, saleDate = ?, ticketStatus = ?, contractId = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, ticket.getTransportType().toString(), java.sql.Types.OTHER);
            ps.setFloat(2, ticket.getPurchasePrice());
            ps.setFloat(3, ticket.getSalePrice());
            ps.setObject(4, ticket.getSaleDate());
            ps.setObject(5, ticket.getTicketStatus().toString(), java.sql.Types.OTHER);
            ps.setObject(6, ticket.getContract().getId());
            ps.setObject(7, ticket.getId());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int deleteTicket(UUID ticketId) {
        String sql = "DELETE FROM tickets WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, ticketId);
            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
