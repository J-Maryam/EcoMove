package services.Interfaces;

import models.entities.Contract;

import java.util.List;
import java.util.UUID;

public interface IContractService {

    boolean addContract(Contract contract);
    List<Contract> getAllContracts();
    boolean updateContract(Contract contract);
    boolean deleteContract(UUID id);
    Contract getContractById(UUID id);
}
