package dao;

import config.DbFunctions;
import models.entities.Contract;
import models.entities.Ticket;
import models.enums.ContractStatus;
import models.enums.TicketStatus;
import models.enums.TransportType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TicketDao {

    private Connection connection;

    public TicketDao() {
        this.connection = DbFunctions.getInstance().connectToDb("EcoMove", "postgres", "@aahmhmm28");
    }

    public int addTicket(Ticket ticket) {
        String sql = "insert into ticket (id, transportType, purchasePrice, salePrice, saleDate, ticketStatus, contractId) values(?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setObject(1, ticket.getId(), java.sql.Types.OTHER);
            ps.setObject(2, ticket.getTransportType().toString(), java.sql.Types.OTHER);
            ps.setFloat(3, ticket.getPurchasePrice());
            ps.setFloat(4, ticket.getSalePrice());
            ps.setDate(5, new java.sql.Date(ticket.getSaleDate().getTime()));
            ps.setObject(6, ticket.getTicketStatus().toString(), java.sql.Types.OTHER);
            ps.setObject(7, ticket.getContract().getId(), java.sql.Types.OTHER);

            int isAdded = ps.executeUpdate();
            return isAdded;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public List<Ticket> getAllTickets() {
        String sql = "SELECT t.id, t.transportType, t.purchasePrice, t.salePrice, t.saleDate, t.ticketStatus, c.id as contractId, c.startDate, c.endDate, c.specialRate, c.agreementConditions, c.renewable, c.contractStatus FROM ticket t LEFT JOIN contract c ON t.contractId = c.id";

        List<Ticket> tickets = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UUID ticketId = UUID.fromString(rs.getString("id"));
                TransportType transportType = TransportType.valueOf(rs.getString("transportType"));
                float purchasePrice = rs.getFloat("purchasePrice");
                float salePrice = rs.getFloat("salePrice");
                Date saleDate = rs.getDate("saleDate");
                TicketStatus ticketStatus = TicketStatus.valueOf(rs.getString("ticketStatus"));

                UUID contractId = UUID.fromString(rs.getString("contractId"));
                Contract contract = new Contract(
                        contractId,
                        rs.getDate("startDate"),
                        rs.getDate("endDate"),
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

    public int updateTicket(Ticket ticket) {
        String sql = "UPDATE ticket SET transportType = ?, purchasePrice = ?, salePrice = ?, saleDate = ?, ticketStatus = ?, contractId = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, ticket.getTransportType().toString(), java.sql.Types.OTHER);
            ps.setFloat(2, ticket.getPurchasePrice());
            ps.setFloat(3, ticket.getSalePrice());
            ps.setDate(4, new java.sql.Date(ticket.getSaleDate().getTime()));
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

    public int deleteTicket(UUID ticketId) {
        String sql = "DELETE FROM ticket WHERE id = ?";

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
