package dao;


import models.entities.Promotion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PromoDao {

    private Connection connection;

    public PromoDao(Connection connection) {
        this.connection = connection;
    }

    public void addPromotion(Promotion promotion) {

        String sql = "insert into promo (id, offerName, description, startDate, endDate, discountType, conditions, offerStatus) value (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, promotion.getId());
            ps.setString(2, promotion.getOfferName());
            ps.setString(3, promotion.getDescription());
            ps.setDate(3, new java.sql.Date(promotion.getStartDate().getTime()));
            ps.setDate(4, new java.sql.Date(promotion.getEndDate().getTime()));
            ps.setObject(5, promotion.getDiscountType().toString(), java.sql.Types.OTHER);
            ps.setFloat(6, promotion.getDiscountValue());
            ps.setString(7, promotion.getConditions());
            ps.setObject(8, promotion.getOfferStatus().toString(), java.sql.Types.OTHER);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Promotion added successfully");
            }else {
                System.out.println("Promotion could not be added");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
