package services;

import dao.Implementations.PartnerDao;
import models.entities.Partner;

import java.util.UUID;

public class PartnerService {
    private PartnerDao partnerDao;

    public PartnerService(PartnerDao partnerDao) {
        this.partnerDao = partnerDao;
    }

    public boolean addPartner(Partner partner) {
        return partnerDao.createPartner(partner);
    }

    public boolean viewAllPartners() {
        return partnerDao.viewAllPartners();
    }

    public int updatePartner(Partner partner) {
        return partnerDao.updatePartner(partner);
    }

    public int deletePartner(UUID id) {
        return partnerDao.deletePartner(id);
    }
}
