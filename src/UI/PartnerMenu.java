package UI;

import models.entities.Partner;
import models.enums.PartnerStatus;
import models.enums.TransportType;
import services.Implementations.PartnerService;
import services.Interfaces.IPartnerService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class PartnerMenu {

    private IPartnerService iPartnerService;
    private Scanner scanner = new Scanner(System.in);
    int choice;


    public PartnerMenu(IPartnerService iPartnerService) {
        this.iPartnerService = iPartnerService;
    }

    public void displayPartnerMenu() {

        boolean running = true;
        while (running) {
            System.out.println("======= Partner Menu ========");
            System.out.println("1. Add a new Partner");
            System.out.println("2. View all Partners");
            System.out.println("3. Update an existing Partner");
            System.out.println("4. Delete a Partner");
            System.out.println("0. Back to main menu");
            System.out.println("Choose your choice");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
            switch (choice) {
                case 1:
                    addPartner();
                    break;
                case 2:
                    getAllPartners();
                    break;
                case 3:
                    updatePartner();
                    break;
                case 4:
                    deletePartner();
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

    public void addPartner(){

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
                TransportType.valueOf(transportType.toUpperCase()),
                geographicZone,
                specialConditions,
                PartnerStatus.valueOf(partnerStatus.toUpperCase()),
                LocalDate.now()
        );

        boolean isAdded = iPartnerService.addPartner(partner);
        if (isAdded) {
            System.out.println("Partner added successfully");
        }else {
            System.out.println("Partner not added");
        }

    }

    public void getAllPartners(){
        List<Partner> partners =  iPartnerService.getAllPartners();

        if (partners.isEmpty()){
            System.out.println("No partners found");
        }

        for (Partner partner : partners) {
            System.out.println("Partner ID: " + partner.getId());
            System.out.println("Company Name: " + partner.getCompanyName());
            System.out.println("Contact Person: " + partner.getBusinessContact());
            System.out.println("Transport Type: " + partner.getTransportType());
            System.out.println("Geographic Zone: " + partner.getGeographicZone());
            System.out.println("Special Conditions: " + partner.getSpecialConditions());
            System.out.println("Status: " + partner.getPartnerStatus());
            System.out.println("Date Created: " + partner.getCreationDate());
            System.out.println("-----------------------------------");
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
                TransportType.valueOf(newTransportType.toUpperCase()),
                newGeographicZone,
                newSpecialConditions,
                PartnerStatus.valueOf(newPartnerStatus.toUpperCase()),
                LocalDate.now()
        );

        int updated = iPartnerService.updatePartner(updatedPartner);
        if (updated > 0){
            System.out.println("Partner updated successfully");
        }else {
            System.out.println("Partner not updated");
        }
    }

    public void deletePartner(){

        System.out.println("Enter the ID of the partner to delete: ");
        UUID partnerId = UUID.fromString(scanner.nextLine());

        int deleted = iPartnerService.deletePartner(partnerId);

        if (deleted > 0){
            System.out.println("Partner deleted successfully");
        }else {
            System.out.println("Partner not deleted");
        }
    }


}
