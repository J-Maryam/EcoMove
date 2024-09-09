package services.Implementations;

import dao.Implementations.ContractDao;
import models.entities.Contract;

import java.util.List;
import java.util.UUID;

public class ContractService {

    private ContractDao contractDao;

    public ContractService(ContractDao contractDao) {
        this.contractDao = contractDao;
    }

    public boolean addContract(Contract contract) {
        if (contract.getStartDate().after(contract.getEndDate())) {
            throw new IllegalArgumentException("La date de début ne peut pas être après la date de fin.");
        }
        return contractDao.addContract(contract);
    }

    public List<Contract> getAllContracts() {
        return contractDao.getAllContracts();
    }

    public boolean updateContract(Contract contract) {
        return contractDao.updateContract(contract);
    }

    public boolean deleteContract(UUID id) {
        return contractDao.deleteContract(id);
    }

    public Contract getContractById(UUID id) {
        return contractDao.getContractById(id);
    }
}
