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

    public boolean createPartner(Partner partner)
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

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public void viewAllPartners()
    {
        String sql = "SELECT * FROM partner";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);

            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                System.out.println("Partner ID: " + rs.getObject("id"));
                System.out.println("Company Name: " + rs.getString("nomCompagnie"));
                System.out.println("Contact Person: " + rs.getString("contactCommercial"));
                System.out.println("Transport Type: " + rs.getString("typeTransport"));
                System.out.println("Geographic Zone: " + rs.getString("zoneGeographique"));
                System.out.println("Special Conditions: " + rs.getString("conditionsSpeciales"));
                System.out.println("Status: " + rs.getString("statutPartenaire"));
                System.out.println("Date Created: " + rs.getDate("dateCreation"));
                System.out.println("-----------------------------------");
            }

            if (!hasResults) {
                System.out.println("No partners found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePartner(Partner partner)
    {
        String sql = "UPDATE partner SET companyName = ?, businessContact = ?, transportType = ?, geographicZone = ?, specialConditions = ?, partnerStatus = ?, creationDate = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setObject(1, partner.getCompanyName());
            ps.setString(2, partner.getBusinessContact());
            ps.setObject(3, partner.getTransportType().toString(), java.sql.Types.OTHER);
            ps.setString(4, partner.getGeographicZone());
            ps.setString(5, partner.getSpecialConditions());
            ps.setObject(6, partner.getPartnerStatus().toString(), java.sql.Types.OTHER);
            ps.setDate(7, java.sql.Date.valueOf(partner.getCreationDate()));
            ps.setObject(8, partner.getId());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Partner updated successfully.");
            } else {
                System.out.println("No partner found with the provided ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePartner(UUID id)
    {
        String sql = "DELETE FROM partner WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setObject(1, id);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Partner deleted successfully.");
            }else {
                System.out.println("No partner found with the provided ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
