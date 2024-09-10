package services.Implementations;

import dao.Implementations.ContractDao;
import dao.Interfaces.IContractDao;
import models.entities.Contract;
import services.Interfaces.IContractService;

import java.util.List;
import java.util.UUID;

public class ContractService implements IContractService {

    private IContractDao iContractDao;

    public ContractService(IContractDao iContractDao) {
        this.iContractDao = iContractDao;
    }

    @Override
    public boolean addContract(Contract contract) {
        if (contract.getStartDate().isAfter(contract.getEndDate())) {
            throw new IllegalArgumentException("La date de début ne peut pas être après la date de fin.");
        }
        return iContractDao.addContract(contract);
    }

    @Override
    public List<Contract> getAllContracts() {
        return iContractDao.getAllContracts();
    }

    @Override
    public boolean updateContract(Contract contract) {
        return iContractDao.updateContract(contract);
    }

    @Override
    public boolean deleteContract(UUID id) {
        return iContractDao.deleteContract(id);
    }

    @Override
    public Contract getContractById(UUID id) {
        return iContractDao.getContractById(id);
    }
}
