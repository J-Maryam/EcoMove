package dao;

import models.entities.Partner;

import java.sql.*;
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

    public boolean viewAllPartners()
    {
        String sql = "SELECT * FROM partner";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                System.out.println("Partner ID: " + rs.getObject("id"));
                System.out.println("Company Name: " + rs.getString("companyName"));
                System.out.println("Contact Person: " + rs.getString("businessContact"));
                System.out.println("Transport Type: " + rs.getString("transportType"));
                System.out.println("Geographic Zone: " + rs.getString("geographicZone"));
                System.out.println("Special Conditions: " + rs.getString("specialConditions"));
                System.out.println("Status: " + rs.getString("partnerStatus"));
                System.out.println("Date Created: " + rs.getDate("creationDate"));
                System.out.println("-----------------------------------");
            }
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int updatePartner(Partner partner)
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
            return rowsAffected;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deletePartner(UUID id)
    {
        String sql = "DELETE FROM partner WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setObject(1, id);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
