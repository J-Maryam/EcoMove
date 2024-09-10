package services.Implementations;

import dao.Implementations.PromoDao;
import dao.Interfaces.IPromoDao;
import models.entities.Promotion;
import services.Interfaces.IPromoService;

import java.util.List;
import java.util.UUID;

public class PromoService implements IPromoService {

    private IPromoDao iPromoDao;

    public PromoService(PromoDao promoDao) {
        this.iPromoDao = promoDao;
    }

    @Override
    public boolean addPromo(Promotion promo) {
        return iPromoDao.addPromo(promo);
    }

    @Override
    public List<Promotion> getAllPromotions() {
        return iPromoDao.getAllPromotions();
    }

    @Override
    public boolean updatePromo(Promotion promo) {
        return iPromoDao.updatePromo(promo);
    }

    @Override
    public boolean deletePromo(UUID id) {
        return iPromoDao.deletePromo(id);
    }
}
