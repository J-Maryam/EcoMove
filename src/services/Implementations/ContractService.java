package services.Implementations;

import dao.Implementations.ContractDao;
import models.entities.Contract;
import services.Interfaces.IContractService;

import java.util.List;
import java.util.UUID;

public class ContractService implements IContractService {

    private ContractDao contractDao;

    public ContractService(ContractDao contractDao) {
        this.contractDao = contractDao;
    }

    @Override
    public boolean addContract(Contract contract) {
        if (contract.getStartDate().after(contract.getEndDate())) {
            throw new IllegalArgumentException("La date de début ne peut pas être après la date de fin.");
        }
        return contractDao.addContract(contract);
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractDao.getAllContracts();
    }

    @Override
    public boolean updateContract(Contract contract) {
        return contractDao.updateContract(contract);
    }

    @Override
    public boolean deleteContract(UUID id) {
        return contractDao.deleteContract(id);
    }

    @Override
    public Contract getContractById(UUID id) {
        return contractDao.getContractById(id);
    }
}
