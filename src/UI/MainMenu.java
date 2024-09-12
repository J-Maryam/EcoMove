package UI;

import services.Implementations.ContractService;
import services.Implementations.PartnerService;
import services.Implementations.PromoService;
import services.Implementations.TicketService;
import services.Interfaces.*;

import java.sql.Connection;
import java.util.Scanner;

public class MainMenu {
    private Connection connection;
    private Scanner scanner = new Scanner(System.in);;
    int choice;

    IContractService iContractService;
    IPartnerService iPartnerService;
    ITicketService iTicketService;
    IPromoService iPromoService;
    IClientService iClientService;


    public MainMenu(IContractService iContractService, IPartnerService iPartnerService, ITicketService iTicketService, IPromoService iPromoService, IClientService iClientService) {
        this.iContractService = iContractService;
        this.iPartnerService = iPartnerService;
        this.iTicketService = iTicketService;
        this.iPromoService = iPromoService;
        this.iClientService = iClientService;
    }

    public void displayMainMenu() {

        boolean run = true;
        while (run) {
            System.out.println("====== Main Menu ======");
            System.out.println("1. Manage Partners");
            System.out.println("2. Manage Contracts");
            System.out.println("3. Manage Tickets");
            System.out.println("4. Manage Promotions");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
            switch (choice) {
                case 1:
                    PartnerMenu partnerMenu = new PartnerMenu(iPartnerService);
                    partnerMenu.displayPartnerMenu();
                    break;
                case 2:
                    ContractMenu contractMenu = new ContractMenu(iContractService);
                    contractMenu.displayContractMenu();
                    break;
                case 3:
//                    TicketMenu ticketMenu = new TicketMenu(iTicketService);
//                    ticketMenu.displayTicketMenu();
                    break;
                case 4:
                    PromoMenu promoMenu = new PromoMenu(iPromoService);
                    promoMenu.displayPromoMenu();
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

    public void firstMenu() {
        System.out.println("Hi User !");
        System.out.println("=============");
        System.out.println("Are you a client or an admin?");
        System.out.println("1. Client");
        System.out.println("2. Admin");
        System.out.println("Please choose an option (1/2):");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                ClientMenu clientMenu = new ClientMenu(iClientService);
                clientMenu.seConnect();
                break;
            case 2:
                displayMainMenu();
                break;
            default:
                System.out.println("Invalid choice. Please choose 1 or 2.");
                firstMenu();
                break;
        }
    }
}
