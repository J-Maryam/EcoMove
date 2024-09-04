package console;

import dao.PartnerDao;
import models.entities.Partner;
import models.enums.PartnerStatus;
import models.enums.TransportType;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class PartnerMenu {

    private PartnerDao partnerDao;
    private Scanner scanner;

    public PartnerMenu(Connection connection) {
        this.partnerDao = new PartnerDao(connection);
        this.scanner = new Scanner(System.in);
    }

    public void displayPartnerMenu() {
        System.out.println("======= Partner Menu ========");
        System.out.println("1. Add a new Partner");
        System.out.println("2. View all Partners");
        System.out.println("3. Update an existing Partner");
        System.out.println("4. Delete a Partner");
        System.out.println("0. Back to main menu");
        System.out.println("Choose your choice");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                createPartner();
                break;
            case 2:
                viewAllPartner();
                break;
            case 3:
                updatePartner();
                break;
            case 4:
                deletePartner();
                break;
            case 0:
                System.out.println("Exiting...!");
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    public void createPartner(){

        System.out.println("Enter company name: ");
        String companyName = scanner.nextLine();

        System.out.print("Enter contact person: ");
        String businessContact = scanner.nextLine();

        System.out.print("Enter type of transport (airplane, train, bus): ");
        String transportType = scanner.nextLine();

        System.out.print("Enter geographical zone: ");
        String geographicZone = scanner.nextLine();

        System.out.print("Enter special conditions: ");
        String specialConditions = scanner.nextLine();

        System.out.print("Enter status (active, inactive, suspended): ");
        String partnerStatus = scanner.nextLine();

        Partner partner = new Partner(
                UUID.randomUUID(),
                companyName,
                businessContact,
                TransportType.valueOf(transportType.toLowerCase()),
                geographicZone,
                specialConditions,
                PartnerStatus.valueOf(partnerStatus.toLowerCase()),
                LocalDate.now()
        );

        boolean isAdded = partnerDao.createPartner(partner);
        if (isAdded) {
            System.out.println("Partner added successfully");
        }else {
            System.out.println("Partner not added");
        }

    }

    public void viewAllPartner(){

        boolean displayed =  partnerDao.viewAllPartners();
        if (displayed){
            System.out.println("Partner list retrieved successfully");
        }else {
            System.out.println("Partner list empty");
        }

    }

    public void updatePartner(){

        System.out.println("Enter the ID of the partner to modify: ");
        UUID partnerId = UUID.fromString(scanner.nextLine());

        System.out.println("Enter new company name: ");
        String newCompanyName = scanner.nextLine();

        System.out.print("Enter new contact person: ");
        String newBusinessContact = scanner.nextLine();

        System.out.print("Enter new type of transport (airplane, train, bus): ");
        String newTransportType = scanner.nextLine();

        System.out.print("Enter new geographical zone: ");
        String newGeographicZone = scanner.nextLine();

        System.out.print("Enter new special conditions: ");
        String newSpecialConditions = scanner.nextLine();

        System.out.print("Enter new status (active, inactive, suspended): ");
        String newPartnerStatus = scanner.nextLine();

        Partner updatedPartner = new Partner(
                partnerId,
                newCompanyName,
                newBusinessContact,
                TransportType.valueOf(newTransportType.toLowerCase()),
                newGeographicZone,
                newSpecialConditions,
                PartnerStatus.valueOf(newPartnerStatus.toLowerCase()),
                LocalDate.now()
        );

        int updated = partnerDao.updatePartner(updatedPartner);
        if (updated > 0){
            System.out.println("Partner updated successfully");
        }else {
            System.out.println("Partner not updated");
        }

        partnerDao.viewAllPartners();

    }

    public void deletePartner(){

        System.out.println("Enter the ID of the partner to delete: ");
        UUID partnerId = UUID.fromString(scanner.nextLine());

        int deleted = partnerDao.deletePartner(partnerId);

        if (deleted > 0){
            System.out.println("Partner deleted successfully");
        }else {
            System.out.println("Partner not deleted");
        }
    }


}
