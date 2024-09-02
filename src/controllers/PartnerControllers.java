package controllers;

import dao.PartnerDao;
import models.entities.Partner;
import models.enums.PartnerStatus;
import models.enums.TransportType;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class PartnerControllers {

    private PartnerDao partnerDao;
    private Scanner scanner;

    public PartnerControllers(Connection connection) {
        this.partnerDao = new PartnerDao(connection);
        this.scanner = new Scanner(System.in);
    }

//    public void createPartner(){
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
//        partnerDao.createPartner(partner);
//        System.out.println("Partner added successfully");
//    }
}
