package dao.Implementations;


import dao.Interfaces.IPromoDao;
import models.entities.Promotion;
import models.enums.DiscountType;
import models.enums.OfferStatus;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PromoDao implements IPromoDao {

    private Connection connection;

    public PromoDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addPromo(Promotion promotion) {

        String sql = "insert into promo (id, offerName, description, startDate, endDate, discountType, discountValue, conditions, offerStatus, contractId) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setObject(1, promotion.getId());
            ps.setString(2, promotion.getOfferName());
            ps.setString(3, promotion.getDescription());
            ps.setObject(4, promotion.getStartDate());
            ps.setObject(5, promotion.getEndDate());
            ps.setObject(6, promotion.getDiscountType().toString(), java.sql.Types.OTHER);
            ps.setFloat(7, promotion.getDiscountValue());
            ps.setString(8, promotion.getConditions());
            ps.setObject(9, promotion.getOfferStatus().toString(), java.sql.Types.OTHER);
            ps.setObject(10, promotion.getContractId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Promotion> getAllPromotions() {

        String sql = "SELECT * FROM promo";
        List<Promotion> promos = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                UUID id = (UUID) rs.getObject("id");
                String offerName = rs.getString("offerName");
                String description = rs.getString("description");
                LocalDate startDate = LocalDate.parse(rs.getString("startDate"));
                LocalDate endDate = LocalDate.parse(rs.getString("endDate"));
                DiscountType discountType = DiscountType.valueOf(rs.getString("discountType"));
                Float discountValue = rs.getFloat("discountValue");
                String conditions = rs.getString("conditions");
                OfferStatus offerStatus = OfferStatus.valueOf(rs.getString("offerStatus"));
                UUID contractId = (UUID) rs.getObject("contractId");

                Promotion promotion = new Promotion(id, offerName, description, startDate, endDate, discountType, discountValue, conditions, offerStatus, contractId);
                promos.add(promotion);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updatePromo(Promotion promotion) {
        String sql = "UPDATE promo SET offerName = ?, description = ?, startDate = ?, endDate = ?, discountType = ?, discountValue = ?, conditions = ?, offerStatus = ?, contractId = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1, promotion.getOfferName());
            ps.setString(2, promotion.getDescription());
            ps.setObject(3, promotion.getStartDate());
            ps.setObject(4, promotion.getEndDate());
            ps.setObject(5, promotion.getDiscountType().toString(), java.sql.Types.OTHER);
            ps.setFloat(6, promotion.getDiscountValue());
            ps.setString(7, promotion.getConditions());
            ps.setObject(8, promotion.getOfferStatus().toString(), java.sql.Types.OTHER);
            ps.setObject(9, promotion.getContractId());
            ps.setObject(10, promotion.getId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deletePromo(UUID id) {
        String sql = "DELETE FROM promo WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setObject(1, id);

            int rowDeleted = ps.executeUpdate();
            return rowDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
