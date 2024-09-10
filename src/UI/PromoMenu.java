package UI;

import models.entities.Promotion;
import models.enums.DiscountType;
import models.enums.OfferStatus;
import services.Implementations.PromoService;
import services.Interfaces.IPromoService;

import java.time.LocalDate;
import java.util.*;

public class PromoMenu {

    private IPromoService iPromoService;
    private Scanner scanner = new Scanner(System.in);
    int choice;

    public PromoMenu(IPromoService iPromoService) {
        this.iPromoService = iPromoService;
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
                    addPromo();
                    break;
                case 2:
                    getAllPromotions();
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

    public void addPromo() {

        System.out.println("Enter offer name: ");
        String offerName = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter start date (yyyy-mm-dd): ");
        String startDateStr = scanner.nextLine();
        LocalDate startDate = LocalDate.parse(startDateStr);

        System.out.print("Enter end date (yyyy-mm-dd): ");
        String endDateStr = scanner.nextLine();
        LocalDate endDate = LocalDate.parse(endDateStr);

        System.out.print("Enter discount type (pourcentage, fixe): ");
        String discountType = scanner.nextLine();

        System.out.print("Enter discount value : ");
        float discountValue = Float.parseFloat(scanner.nextLine());

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
                DiscountType.valueOf(discountType.toUpperCase()),
                discountValue,
                conditions,
                OfferStatus.valueOf(offerStatus.toUpperCase()),
                contractId
        );

        boolean isAdded = iPromoService.addPromo(promotion);
        if (isAdded) {
            System.out.println("Promotion added successfully.");
        } else {
            System.out.println("Promotion not added.");
        }

    }

    public void getAllPromotions(){

        List<Promotion> promotions = iPromoService.getAllPromotions();

        if (promotions.isEmpty()) {
            System.out.println("There are no promotions.");
        }

        for (Promotion promotion : promotions) {
            System.out.println("Promotion ID: " + promotion.getId());
            System.out.println("Offer name: " + promotion.getOfferName());
            System.out.println("Description: " + promotion.getDescription());
            System.out.println("Start Date: " + promotion.getStartDate());
            System.out.println("End Date: " + promotion.getEndDate());
            System.out.println("Discount Type: " + promotion.getDiscountType());
            System.out.println("Discount Value: " + promotion.getDiscountValue());
            System.out.println("Conditions: " + promotion.getConditions());
            System.out.println("Offer Status: " + promotion.getOfferStatus());
            System.out.println("Contract ID: " + promotion.getContractId());
            System.out.println("===============================");
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
        LocalDate startDate = LocalDate.parse(newStartDate);

        System.out.print("Enter new end date (yyyy-mm-dd): ");
        String newEndDate = scanner.nextLine();
        LocalDate endDate = LocalDate.parse(newEndDate);

        System.out.print("Enter new discount type (pourcentage, fixed price): ");
        String newDiscountType = scanner.nextLine();
        DiscountType discountType = DiscountType.valueOf(newDiscountType.toUpperCase());

        System.out.print("Enter new discount value : ");
        float newDiscountValue = Float.parseFloat(scanner.nextLine());

        System.out.print("Enter new conditions: ");
        String newConditions = scanner.nextLine();

        System.out.print("Enter new offer status (active, expired, suspended): ");
        String newOfferStatus = scanner.nextLine();
        OfferStatus offerStatus = OfferStatus.valueOf(newOfferStatus.toUpperCase());

        System.out.print("Enter new contract ID: ");
        UUID newContractId = UUID.fromString(scanner.nextLine());

        Promotion updatedPromo = new Promotion(
                promoId,
                newOfferName,
                newDescription,
                startDate,
                endDate,
                discountType,
                newDiscountValue,
                newConditions,
                offerStatus,
                newContractId
        );

        boolean updated = iPromoService.updatePromo(updatedPromo);
        if (updated) {
            System.out.println("Promotion updated successfully.");
        }else {
            System.out.println("Promotion not updated.");
        }
    }

    public void deletePromotion(){

        System.out.println("Enter the ID of the promotion to delete: ");
        UUID promoId = UUID.fromString(scanner.nextLine());

        boolean deleted =  iPromoService.deletePromo(promoId);
        if (deleted) {
            System.out.println("Promotion deleted successfully.");
        }else {
            System.out.println("Promotion not deleted.");
        }

    }


}
