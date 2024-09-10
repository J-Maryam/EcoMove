package services.Implementations;

import dao.Implementations.PartnerDao;
import dao.Interfaces.IPartnerDao;
import models.entities.Partner;
import services.Interfaces.IPartnerService;

import java.util.List;
import java.util.UUID;

public class PartnerService implements IPartnerService {
    private IPartnerDao iPartnerDao;

    public PartnerService(IPartnerDao iPartnerDao) {
        this.iPartnerDao = iPartnerDao;
    }

    @Override
    public boolean addPartner(Partner partner) {
        return iPartnerDao.addPartner(partner);
    }

    @Override
    public List<Partner> getAllPartners() {
        return iPartnerDao.getAllPartners();
    }

    @Override
    public int updatePartner(Partner partner) {
        return iPartnerDao.updatePartner(partner);
    }

    @Override
    public int deletePartner(UUID id) {
        return iPartnerDao.deletePartner(id);
    }
}
