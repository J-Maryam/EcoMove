import config.DbFunctions;
import dao.PartnerDao;
import models.entities.Partner;
import models.enums.PartnerStatus;
import models.enums.TransportType;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        DbFunctions db = new DbFunctions();
        Connection connection = db.connectToDb("EcoMove", "postgres", "@aahmhmm28");

//        addPartner
//        System.out.println("Enter company name: ");
//        String companyName = scanner.nextLine();
//
//        System.out.print("Enter contact person: ");
//        String businessContact = scanner.nextLine();
//
//        System.out.print("Enter type of transport (airplane, train, bus): ");
//        String transportType = scanner.nextLine();
//
//        System.out.print("Enter geographical zone: ");
//        String geographicZone = scanner.nextLine();
//
//        System.out.print("Enter special conditions: ");
//        String specialConditions = scanner.nextLine();
//
//        System.out.print("Enter status (active, inactive, suspended): ");
//        String partnerStatus = scanner.nextLine();
//
//        Partner partner = new Partner(
//                UUID.randomUUID(),
//                companyName,
//                businessContact,
//                TransportType.valueOf(transportType.toLowerCase()),
//                geographicZone,
//                specialConditions,
//                PartnerStatus.valueOf(partnerStatus.toLowerCase()),
//                LocalDate.now()
//        );

        PartnerDao partnerDao = new PartnerDao(connection);
//        partnerDao.createPartner(partner);
//        System.out.println("Partner added successfully");

        partnerDao.viewAllPartners();

//        updatePartner
//        System.out.println("Enter the ID of the partner to modify: ");
//        UUID partnerId = UUID.fromString(scanner.nextLine());
//
//        System.out.println("Enter new company name: ");
//        String newCompanyName = scanner.nextLine();
//
//        System.out.print("Enter new contact person: ");
//        String newBusinessContact = scanner.nextLine();
//
//        System.out.print("Enter new type of transport (airplane, train, bus): ");
//        String newTransportType = scanner.nextLine();
//
//        System.out.print("Enter new geographical zone: ");
//        String newGeographicZone = scanner.nextLine();
//
//        System.out.print("Enter new special conditions: ");
//        String newSpecialConditions = scanner.nextLine();
//
//        System.out.print("Enter new status (active, inactive, suspended): ");
//        String newPartnerStatus = scanner.nextLine();
//
//        Partner updatedPartner = new Partner(
//                partnerId,
//                newCompanyName,
//                newBusinessContact,
//                TransportType.valueOf(newTransportType.toLowerCase()),
//                newGeographicZone,
//                newSpecialConditions,
//                PartnerStatus.valueOf(newPartnerStatus.toLowerCase()),
//                LocalDate.now()
//        );
//
//        partnerDao.updatePartner(updatedPartner);
//        partnerDao.viewAllPartners();


//        deletePartner
        System.out.println("Enter the ID of the partner to delete: ");
        UUID partnerId = UUID.fromString(scanner.nextLine());

        partnerDao.deletePartner(partnerId);
    }
}