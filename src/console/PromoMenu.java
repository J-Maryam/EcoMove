package console;

import dao.PartnerDao;
import dao.PromoDao;
import models.entities.Partner;
import models.entities.Promotion;
import models.enums.DiscountType;
import models.enums.OfferStatus;
import models.enums.PartnerStatus;
import models.enums.TransportType;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class PromoMenu {

    private PromoDao promoDao;
    private Scanner scanner;
    int choice;

    public PromoMenu(Connection connection) {
        this.promoDao = new PromoDao(connection);
        this.scanner = new Scanner(System.in);
    }

    public void displayPromoMenu() {

        boolean running = true;
        while (running) {
            System.out.println("======= Promotion Menu ========");
            System.out.println("1. Add a new Promotion");
            System.out.println("2. View all Promotions");
            System.out.println("3. Update an existing Promotion");
            System.out.println("4. Delete a Promotion");
            System.out.println("0. Back to main menu");
            System.out.println("Choose your choice");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
            switch (choice) {
                case 1:
                    createPromotion();
                    break;
                case 2:
                    viewAllPromotions();
                    break;
                case 3:
                    updatePromotion();
                    break;
                case 4:
                    deletePromotion();
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

    public void createPromotion() {

        System.out.println("Enter offer name: ");
        String offerName = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter start date (yyyy-mm-dd): ");
        String startDateStr = scanner.nextLine();
        java.sql.Date startDate = java.sql.Date.valueOf(startDateStr);

        System.out.print("Enter end date (yyyy-mm-dd): ");
        String endDateStr = scanner.nextLine();
        java.sql.Date endDate = java.sql.Date.valueOf(endDateStr);

        System.out.print("Enter discount type (pourcentage, fixe): ");
        String discountType = scanner.nextLine();

        System.out.print("Enter conditions : ");
        String conditions = scanner.nextLine();

        System.out.print("Enter offer status (active, expired, suspended): ");
        String offerStatus = scanner.nextLine();

        System.out.print("Enter contract ID : ");
        UUID contractId = UUID.fromString(scanner.nextLine());

        Promotion promotion = new Promotion(
                UUID.randomUUID(),
                offerName,
                description,
                startDate,
                endDate,
                DiscountType.valueOf(discountType.toLowerCase()),
                conditions,
                OfferStatus.valueOf(offerStatus.toLowerCase()),
                contractId
        );

        boolean isAdded = promoDao.addPromotion(promotion);
        if (isAdded) {
            System.out.println("Promotion added successfully.");
        } else {
            System.out.println("Promotion not added.");
        }

    }

    public void viewAllPromotions(){

        boolean displayed =  promoDao.displayPromotions();
        if (displayed) {
            System.out.println("Promotions displayed successfully.");
        }else {
            System.out.println("No promotions found.");
        }

    }

    public void updatePromotion() {

        System.out.println("Enter the ID of the promotion to modify: ");
        UUID promoId = UUID.fromString(scanner.nextLine());

        System.out.println("Enter new offer name: ");
        String newOfferName = scanner.nextLine();

        System.out.println("Enter new description: ");
        String newDescription = scanner.nextLine();

        System.out.print("Enter new start date (yyyy-mm-dd): ");
        String newStartDate = scanner.nextLine();
        java.sql.Date startDate = java.sql.Date.valueOf(newStartDate);

        System.out.print("Enter new end date (yyyy-mm-dd): ");
        String newEndDate = scanner.nextLine();
        java.sql.Date endDate = java.sql.Date.valueOf(newEndDate);

        System.out.print("Enter new discount type (pourcentage, fixed price): ");
        String newDiscountType = scanner.nextLine();
        DiscountType discountType = DiscountType.valueOf(newDiscountType.toLowerCase());

        System.out.print("Enter new conditions: ");
        String newConditions = scanner.nextLine();

        System.out.print("Enter new offer status (active, expired, suspended): ");
        String newOfferStatus = scanner.nextLine();
        OfferStatus offerStatus = OfferStatus.valueOf(newOfferStatus.toLowerCase());

        System.out.print("Enter new contract ID: ");
        UUID newContractId = UUID.fromString(scanner.nextLine());

        Promotion updatedPromo = new Promotion(
                promoId,
                newOfferName,
                newDescription,
                startDate,
                endDate,
                discountType,
                newConditions,
                offerStatus,
                newContractId
        );

        boolean updated = promoDao.updatePromo(updatedPromo);
        if (updated) {
            System.out.println("Promotion updated successfully.");
        }else {
            System.out.println("Promotion not updated.");
        }
        promoDao.displayPromotions();
    }

    public void deletePromotion(){

        System.out.println("Enter the ID of the promotion to delete: ");
        UUID promoId = UUID.fromString(scanner.nextLine());

        boolean deleted =  promoDao.deletePromo(promoId);
        if (deleted) {
            System.out.println("Promotion deleted successfully.");
        }else {
            System.out.println("Promotion not deleted.");
        }

    }


}
