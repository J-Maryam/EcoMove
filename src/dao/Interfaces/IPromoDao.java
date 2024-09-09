package dao.Interfaces;

import models.entities.Promotion;

import java.util.List;
import java.util.UUID;

public interface IPromoDao {

    boolean addPromo(Promotion promo);
    List<Promotion> getAllPromotions();
    boolean updatePromo(Promotion promo);
    boolean deletePromo(UUID id);
}
