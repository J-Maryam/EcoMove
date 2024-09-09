package services.Implementations;

import dao.Implementations.PromoDao;
import models.entities.Promotion;
import services.Interfaces.IPromoService;

import java.util.List;
import java.util.UUID;

public class PromoService implements IPromoService {

    private PromoDao promoDao;

    public PromoService(PromoDao promoDao) {
        this.promoDao = promoDao;
    }

    @Override
    public boolean addPromo(Promotion promo) {
        return promoDao.addPromo(promo);
    }

    @Override
    public List<Promotion> getAllPromotions() {
        return promoDao.getAllPromotions();
    }

    @Override
    public boolean updatePromo(Promotion promo) {
        return promoDao.updatePromo(promo);
    }

    @Override
    public boolean deletePromo(UUID id) {
        return promoDao.deletePromo(id);
    }
}
