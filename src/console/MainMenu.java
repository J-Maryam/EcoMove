package console;

import dao.ContractDao;
import dao.PartnerDao;
import models.entities.Contract;
import services.ContractService;
import services.PartnerService;

import java.sql.Connection;
import java.util.Scanner;

public class MainMenu {
    private Connection connection;
    private Scanner scanner;
    int choice;

    ContractDao contractDao = new ContractDao();
    ContractService contractService = new ContractService(contractDao);

    PartnerDao partnerDao = new PartnerDao();
    PartnerService partnerService = new PartnerService(partnerDao);


    public MainMenu() {
        scanner = new Scanner(System.in);
    }

    public void displayMainMenu() {

        boolean run = true;
        while (run) {
            System.out.println("====== Main Menu ======");
            System.out.println("1. Manage Partners");
            System.out.println("2. Manage Promotions");
            System.out.println("3. Manage Tickets");
            System.out.println("4. Manage Contract");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
            switch (choice) {
                case 1:
                    PartnerMenu partnerMenu = new PartnerMenu(partnerService);
                    partnerMenu.displayPartnerMenu();
                    break;
                case 2:
                    PromoMenu promoMenu = new PromoMenu(connection);
                    promoMenu.displayPromoMenu();
                    break;
                case 3:
                    TicketMenu ticketMenu = new TicketMenu(connection);
                    ticketMenu.displayTicketMenu();
                    break;
                case 4:
                    ContractMenu contractMenu = new ContractMenu(contractService);
                    contractMenu.displayContractMenu();
                    break;
                case 0:
                    System.out.println("Exiting...!");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
