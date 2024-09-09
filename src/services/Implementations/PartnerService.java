package services.Implementations;

import dao.Implementations.PartnerDao;
import models.entities.Partner;
import services.Interfaces.IPartnerService;

import java.util.List;
import java.util.UUID;

public class PartnerService implements IPartnerService {
    private PartnerDao partnerDao;

    public PartnerService(PartnerDao partnerDao) {
        this.partnerDao = partnerDao;
    }

    @Override
    public boolean addPartner(Partner partner) {
        return partnerDao.addPartner(partner);
    }

    @Override
    public List<Partner> getAllPartners() {
        return partnerDao.getAllPartners();
    }

    @Override
    public int updatePartner(Partner partner) {
        return partnerDao.updatePartner(partner);
    }

    @Override
    public int deletePartner(UUID id) {
        return partnerDao.deletePartner(id);
    }
}
