package dao;

import config.DbFunctions;
import models.entities.Contract;
import models.enums.ContractStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContractDao {

    private Connection connection;

    public ContractDao() {
        this.connection = DbFunctions.getInstance().connectToDb("EcoMove", "postgres", "@aahmhmm28");
    }

    public boolean addContract(Contract contract) {
        String sql = "insert into contract (id, startDate, endDate, specialRate, agreementConditions, renewable, contractStatus, partnerId) values(?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setObject(1, contract.getId());
            ps.setDate(2, new java.sql.Date(contract.getStartDate().getTime()));
            ps.setDate(3, new java.sql.Date(contract.getEndDate().getTime()));
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

    public List<Contract> getAllContracts() {
        String sql = "SELECT * FROM contract";
        List<Contract> contracts = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                UUID id = (UUID) rs.getObject("id");
                Date startDate = rs.getDate("startDate");
                Date endDate = rs.getDate("endDate");
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

    public boolean updateContract(Contract contract) {
        String sql = "update contract set startDate = ?, endDate = ?, specialRate = ?, agreementConditions = ?, renewable = ?, contractStatus = ?, partnerId = ? where id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setDate(1, new java.sql.Date(contract.getStartDate().getTime())); // Conversion en java.sql.Date
            ps.setDate(2, new java.sql.Date(contract.getEndDate().getTime())); // Conversion en java.sql.Date
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

    public Contract getContractById(UUID id) {
        String sql = "SELECT * FROM contract WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, id, java.sql.Types.OTHER);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                UUID contractId = UUID.fromString(rs.getString("id"));
                java.util.Date startDate = rs.getDate("startDate");
                java.util.Date endDate = rs.getDate("endDate");
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
