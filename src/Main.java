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
//
        PartnerDao partnerDao = new PartnerDao(connection);
//        partnerDao.createPartner(partner);
//        System.out.println("Partner added successfully");

        partnerDao.viewAllPartners();

    }
}