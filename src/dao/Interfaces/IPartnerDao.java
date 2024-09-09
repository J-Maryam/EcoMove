package dao.Interfaces;

import models.entities.Partner;

import java.util.List;
import java.util.UUID;

public interface IPartnerDao {

    boolean addPartner(Partner partner);
    List<Partner> getAllPartners();
    int updatePartner(Partner partner);
    int deletePartner(UUID id);
}
