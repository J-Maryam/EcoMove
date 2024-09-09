package console;

import models.entities.Contract;
import models.enums.ContractStatus;
import services.ContractService;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ContractMenu {

    private ContractService contractService;
    private Scanner scanner = new Scanner(System.in);
    int choice;
    boolean validDates = false;

    public ContractMenu(ContractService contractService) {
        this.contractService = contractService;
    }

    public void displayContractMenu() {

        boolean running = true;
        while (running) {
            System.out.println("======= Contract Menu ========");
            System.out.println("1. Add a new contract");
            System.out.println("2. View all contracts");
            System.out.println("3. Update an existing contract");
            System.out.println("4. Delete a contract");
            System.out.println("0. Back to main menu");
            System.out.println("Choose your choice");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
            switch (choice) {
                case 1:
                    createContract();
                    break;
                case 2:
                    displayAllContracts();
                    break;
                case 3:
                    updateContract();
                    break;
                case 4:
                    deleteContract();
                    break;
                case 0:
                    System.out.println("Exiting...!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public void createContract() {
        java.util.Date startDate = null;
        java.util.Date endDate = null;

        while (!validDates) {
            try {
                System.out.println("Enter start date (yyyy-MM-dd): ");
                String startDateInput = scanner.nextLine();
                startDate = java.sql.Date.valueOf(startDateInput);

                System.out.println("Enter end date (yyyy-MM-dd): ");
                String endDateInput = scanner.nextLine();
                endDate = java.sql.Date.valueOf(endDateInput);

                if (startDate.after(endDate)) {
                    System.out.println("La date de début ne peut pas être après la date de fin. Veuillez réessayer.");
                } else {
                    validDates = true;
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Erreur de format de date. Veuillez entrer une date valide au format yyyy-MM-dd.");
            }
        }

        System.out.print("Enter special rate: ");
        float specialRate = Float.parseFloat(scanner.nextLine());

        System.out.print("Enter agreement conditions: ");
        String agreementConditions = scanner.nextLine();

        System.out.print("Enter if the contract is renewable (true/false): ");
        boolean renewable = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Enter contract status (ongoing, terminated, suspended): ");
        String contractStatus = scanner.nextLine();

        System.out.print("Enter partner ID (UUID): ");
        UUID partnerId = UUID.fromString(scanner.nextLine());

        Contract newContract = new Contract(
                UUID.randomUUID(),
                startDate,
                endDate,
                specialRate,
                agreementConditions,
                renewable,
                ContractStatus.valueOf(contractStatus.toLowerCase()),
                partnerId
        );

        boolean isAdded = contractService.addContract(newContract);
        if (isAdded) {
            System.out.println("Contract added successfully.");
        } else {
            System.out.println("Contract could not be added.");
        }
    }

    public void displayAllContracts() {
        List<Contract> contracts = contractService.getAllContracts();

        if (contracts.isEmpty()) {
            System.out.println("No contracts found.");
        } else {
            for (Contract contract : contracts) {
                System.out.println("Contract ID: " + contract.getId());
                System.out.println("Start Date: " + contract.getStartDate());
                System.out.println("End Date: " + contract.getEndDate());
                System.out.println("Special Rate: " + contract.getSpecialRate());
                System.out.println("Agreement Conditions: " + contract.getAgreementConditions());
                System.out.println("Renewable: " + (contract.isRenewable() ? "Yes" : "No"));
                System.out.println("Contract Status: " + contract.getContractStatus());
                System.out.println("Partner ID: " + contract.getPartnerId());
                System.out.println("-------------------------------");
            }
        }
    }

    public void updateContract() {

        System.out.println("Enter the ID of the contract to modify (UUID): ");
        UUID contractId = UUID.fromString(scanner.nextLine());

        System.out.print("Enter new start date (yyyy-mm-dd): ");
        String startDateStr = scanner.nextLine();
        Date startDate = Date.valueOf(startDateStr); // Conversion de String à Date

        System.out.print("Enter new end date (yyyy-mm-dd): ");
        String endDateStr = scanner.nextLine();
        Date endDate = Date.valueOf(endDateStr); // Conversion de String à Date

        System.out.print("Enter new special rate: ");
        float specialRate = Float.parseFloat(scanner.nextLine());

        System.out.print("Enter new agreement conditions: ");
        String agreementConditions = scanner.nextLine();

        System.out.print("Enter if the contract is renewable (true/false): ");
        boolean renewable = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Enter new contract status (ongoing, terminated, suspended): ");
        String contractStatusStr = scanner.nextLine();
        ContractStatus contractStatus = ContractStatus.valueOf(contractStatusStr.toLowerCase());

        System.out.print("Enter new partner ID (UUID): ");
        UUID partnerId = UUID.fromString(scanner.nextLine());


        Contract updatedContract = new Contract(
                contractId,
                startDate,
                endDate,
                specialRate,
                agreementConditions,
                renewable,
                contractStatus,
                partnerId
        );

        boolean isUpdated = contractService.updateContract(updatedContract);
        if (isUpdated) {
            System.out.println("Contract updated successfully.");
            displayAllContracts();
        }else {
            System.out.println("Contract not updated.");
        }

    }

    public void deleteContract() {
        System.out.println("Enter the ID of the contract to delete (UUID): ");
        UUID contractId = UUID.fromString(scanner.nextLine());

        boolean isDeleted = contractService.deleteContract(contractId);

        if (isDeleted) {
            System.out.println("Contract deleted successfully.");
            displayAllContracts();
        } else {
            System.out.println("Contract could not be deleted or does not exist.");
        }
    }






}
