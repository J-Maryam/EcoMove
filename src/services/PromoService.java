package services;

import dao.PromoDao;
import models.entities.Promotion;

import java.util.UUID;

public class PromoService {

    private PromoDao promoDao;

    public PromoService(PromoDao promoDao) {
        this.promoDao = promoDao;
    }

    public boolean addPromo(Promotion promo) {
        return promoDao.addPromotion(promo);
    }

    public boolean viewAllPromos() {
        return promoDao.displayPromotions();
    }

    public boolean updatePromo(Promotion promo) {
        return promoDao.updatePromo(promo);
    }

    public boolean deletePromo(UUID id) {
        return promoDao.deletePromo(id);
    }
}
