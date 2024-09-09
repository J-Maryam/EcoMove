package dao.Implementations;

import dao.Interfaces.IContractDao;
import models.entities.Contract;
import models.enums.ContractStatus;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContractDao implements IContractDao {

    private Connection connection;

//    public ContractDao() {
//        this.connection = DbFunctions.getInstance().connectToDb("EcoMove", "postgres", "@aahmhmm28");
//    }

    public ContractDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addContract(Contract contract) {
        String sql = "insert into contract (id, startDate, endDate, specialRate, agreementConditions, renewable, contractStatus, partnerId) values(?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setObject(1, contract.getId());
            ps.setObject(2, contract.getStartDate());
            ps.setObject(3, contract.getEndDate());
            ps.setFloat(4, contract.getSpecialRate());
            ps.setString(5, contract.getAgreementConditions());
            ps.setBoolean(6, contract.isRenewable());
            ps.setObject(7, contract.getContractStatus().toString(), java.sql.Types.OTHER);
            ps.setObject(8, contract.getPartnerId());

            int isAdded = ps.executeUpdate();
            return isAdded > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Contract> getAllContracts() {
        String sql = "SELECT * FROM contract";
        List<Contract> contracts = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                UUID id = (UUID) rs.getObject("id");
                LocalDate startDate = rs.getObject("startDate", LocalDate.class);
                LocalDate endDate = rs.getObject("endDate", LocalDate.class);
                float specialRate = rs.getFloat("specialRate");
                String agreementConditions = rs.getString("agreementConditions");
                boolean renewable = rs.getBoolean("renewable");
                ContractStatus contractStatus = ContractStatus.valueOf(rs.getString("contractStatus").toLowerCase());
                UUID partnerId = (UUID) rs.getObject("partnerId");

                Contract contract = new Contract(id, startDate, endDate, specialRate, agreementConditions, renewable, contractStatus, partnerId);
                contracts.add(contract);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contracts;
    }

    @Override
    public boolean updateContract(Contract contract) {
        String sql = "update contract set startDate = ?, endDate = ?, specialRate = ?, agreementConditions = ?, renewable = ?, contractStatus = ?, partnerId = ? where id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setObject(1, contract.getStartDate());
            ps.setObject(2, contract.getEndDate());
            ps.setFloat(3, contract.getSpecialRate());
            ps.setString(4, contract.getAgreementConditions());
            ps.setBoolean(5, contract.isRenewable());
            ps.setObject(6, contract.getContractStatus().toString(), java.sql.Types.OTHER);
            ps.setObject(7, contract.getPartnerId());
            ps.setObject(8, contract.getId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteContract(UUID id) {
        String sql = "DELETE FROM contract WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setObject(1, id);

            int rowDeleted = ps.executeUpdate();
            return rowDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Contract getContractById(UUID id) {
        String sql = "SELECT * FROM contract WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, id, java.sql.Types.OTHER);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                UUID contractId = UUID.fromString(rs.getString("id"));
                LocalDate startDate = rs.getObject("startDate", LocalDate.class);
                LocalDate endDate = rs.getObject("endDate", LocalDate.class);
                float specialRate = rs.getFloat("specialRate");
                String agreementConditions = rs.getString("agreementConditions");
                boolean renewable = rs.getBoolean("renewable");
                ContractStatus contractStatus = ContractStatus.valueOf(rs.getString("contractStatus"));

                return new Contract(
                        contractId,
                        startDate,
                        endDate,
                        specialRate,
                        agreementConditions,
                        renewable,
                        contractStatus,
                        contractId
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
