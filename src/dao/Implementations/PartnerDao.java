package dao.Implementations;

import dao.Interfaces.IPartnerDao;
import models.entities.Partner;
import models.enums.PartnerStatus;
import models.enums.TransportType;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PartnerDao implements IPartnerDao {

    private Connection connection;

    public PartnerDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addPartner(Partner partner)
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
            ps.setObject(8, partner.getCreationDate());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public List<Partner> getAllPartners() {
        String sql = "SELECT * FROM partner";
        List<Partner> partners = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                UUID id = UUID.fromString(rs.getString("id"));
                String companyName = rs.getString("companyName");
                String businessContact = rs.getString("businessContact");
                TransportType transportType = TransportType.valueOf(rs.getString("transportType").toUpperCase());
                String geographicZone = rs.getString("geographicZone");
                String specialConditions = rs.getString("specialConditions");
                PartnerStatus partnerStatus = PartnerStatus.valueOf(rs.getString("partnerStatus").toUpperCase());
                LocalDate creationDate = LocalDate.parse(rs.getString("creationDate"));

                Partner partner = new Partner(id, companyName, businessContact, transportType, geographicZone, specialConditions, partnerStatus, creationDate);
                partners.add(partner);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return partners;
    }

    @Override
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
            ps.setObject(7, partner.getCreationDate());
            ps.setObject(8, partner.getId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
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
