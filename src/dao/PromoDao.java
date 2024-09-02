package dao;


import models.entities.Promotion;
import models.enums.DiscountType;
import models.enums.OfferStatus;

import java.sql.*;

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

    public void displayPromotions()
    {
        String sql = "SELECT * FROM promo";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            boolean hasPromotions = false;

            while (rs.next()) {

                hasPromotions = true;

                int id = rs.getInt("id");
                String offerName = rs.getString("offerName");
                String description = rs.getString("description");
                Date startDate = rs.getDate("startDate");
                Date endDate = rs.getDate("endDate");
                DiscountType discountType = DiscountType.valueOf(rs.getString("discountType"));
                String conditions = rs.getString("conditions");
                OfferStatus offerStatus = OfferStatus.valueOf(rs.getString("offerStatus"));

                System.out.println("ID: " + id);
                System.out.println("Offer Name: " + offerName);
                System.out.println("Description: " + description);
                System.out.println("Start Date: " + startDate);
                System.out.println("End Date: " + endDate);
                System.out.println("Discount Type: " + discountType);
                System.out.println("Conditions: " + conditions);
                System.out.println("Offer Status: " + offerStatus);
                System.out.println("==================================");

            }

            if (!hasPromotions) {
                System.out.println("No promotions found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
