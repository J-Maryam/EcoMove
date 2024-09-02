package dao;

import models.entities.Partner;
import models.enums.PartnerStatus;
import models.enums.TransportType;

import java.sql.*;
import java.time.LocalDate;
import java.util.UUID;

public class PartnerDao {

    private Connection connection;

    public PartnerDao(Connection connection) {
        this.connection = connection;
    }

    public void createPartner(Partner partner)
    {
        String sql = "insert into partner (id, companyName, businessContact, transportType, geographicZone, specialConditions, partnerStatus, creationDate) values (?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setObject(1, partner.getId());
            ps.setString(2, partner.getCompanyName());
            ps.setString(3, partner.getBusinessContact());
            ps.setObject(4, partner.getTransportType().toString(), java.sql.Types.OTHER);
            ps.setString(5, partner.getGeographicZone());
            ps.setString(6, partner.getSpecialConditions());
            ps.setObject(7, partner.getPartnerStatus().toString(), java.sql.Types.OTHER);
            ps.setDate(8, java.sql.Date.valueOf(partner.getCreationDate()));
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void viewAllPartners()
    {
        String sql = "SELECT * FROM partner";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                UUID id = (UUID) rs.getObject("id");
                String companyName = rs.getString("companyName");
                String businessContact = rs.getString("businessContact");
                TransportType transportType = TransportType.valueOf(rs.getString("transportType"));
                String geographicZone = rs.getString("geographicZone");
                String specialConditions = rs.getString("specialConditions");
                PartnerStatus partnerStatus = PartnerStatus.valueOf(rs.getString("partnerStatus"));
                LocalDate creationDate = rs.getDate("creationDate").toLocalDate();

                System.out.println("ID: " + id);
                System.out.println("Company Name: " + companyName);
                System.out.println("Business Contact: " + businessContact);
                System.out.println("Transport Type: " + transportType);
                System.out.println("Geographic Zone: " + geographicZone);
                System.out.println("Special Conditions: " + specialConditions);
                System.out.println("Partner Status: " + partnerStatus);
                System.out.println("Creation Date: " + creationDate);
                System.out.println("==================================");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
