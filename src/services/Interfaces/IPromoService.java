package services.Interfaces;

import models.entities.Promotion;

import java.util.List;
import java.util.UUID;

public interface IPromoService {

    boolean addPromo(Promotion promotion);
    List<Promotion> getAllPromotions();
    boolean updatePromo(Promotion promotion);
    boolean deletePromo(UUID id);
}
