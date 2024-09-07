package dao;


import config.DbFunctions;
import models.entities.Promotion;
import models.enums.DiscountType;
import models.enums.OfferStatus;

import java.sql.*;
import java.util.UUID;

public class PromoDao {

    private Connection connection;

    public PromoDao(Connection connection) {
        this.connection = connection;
    }

    public boolean addPromotion(Promotion promotion) {

        String sql = "insert into promo (id, offerName, description, startDate, endDate, discountType, conditions, offerStatus, contractId) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setObject(1, promotion.getId());
            ps.setString(2, promotion.getOfferName());
            ps.setString(3, promotion.getDescription());
            ps.setDate(4, new java.sql.Date(promotion.getStartDate().getTime()));
            ps.setDate(5, new java.sql.Date(promotion.getEndDate().getTime()));
            ps.setObject(6, promotion.getDiscountType().toString(), java.sql.Types.OTHER);
            ps.setString(7, promotion.getConditions());
            ps.setObject(8, promotion.getOfferStatus().toString(), java.sql.Types.OTHER);
            ps.setObject(9, promotion.getContractId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean displayPromotions() {
        String sql = "SELECT * FROM promo";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println("Promotion ID: " + rs.getObject("id"));
                System.out.println("Offer name: " + rs.getString("offerName"));
                System.out.println("Description: " + rs.getString("description"));
                System.out.println("Start Date: " + rs.getDate("startDate"));
                System.out.println("End Date: " + rs.getDate("endDate"));
                System.out.println("Discount Type: " + DiscountType.valueOf(rs.getString("discountType")));
                System.out.println("Conditions: " + rs.getString("conditions"));
                System.out.println("Offer Status: " + OfferStatus.valueOf(rs.getString("offerStatus")));
                System.out.println("Contract ID: " + rs.getObject("contractID"));
                System.out.println("===============================");
            }
            return true;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePromo(Promotion promotion) {
        String sql = "UPDATE promo SET offerName = ?, description = ?, startDate = ?, endDate = ?, discountType = ?, conditions = ?, offerStatus = ?, contractId = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1, promotion.getOfferName());
            ps.setString(2, promotion.getDescription());
            ps.setDate(3, new java.sql.Date(promotion.getStartDate().getTime()));
            ps.setDate(4, new java.sql.Date(promotion.getEndDate().getTime()));
            ps.setObject(5, promotion.getDiscountType().toString(), java.sql.Types.OTHER);
            ps.setString(6, promotion.getConditions());
            ps.setObject(7, promotion.getOfferStatus().toString(), java.sql.Types.OTHER);
            ps.setObject(8, promotion.getContractId());
            ps.setObject(9, promotion.getId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

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
