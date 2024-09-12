package UI;

import models.entities.Contract;
import models.entities.Journey;
import models.entities.Ticket;
import models.enums.TicketStatus;
import models.enums.TransportType;
import services.Implementations.ContractService;
import services.Implementations.TicketService;
import services.Interfaces.IContractService;
import services.Interfaces.IJourneyService;
import services.Interfaces.ITicketService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class TicketMenu {

    private Scanner scanner = new Scanner(System.in);
    int choice;

    private IContractService iContractService;
    private ITicketService iTicketService;
    private IJourneyService iJourneyService;

    public TicketMenu(ITicketService iTicketService, IContractService iContractService, IJourneyService iJourneyService) {
        this.iTicketService = iTicketService;
        this.iContractService = iContractService;
        this.iJourneyService = iJourneyService;
    }

//    public void displayTicketMenu() {
//        boolean running = true;
//        while (running) {
//            System.out.println("======= Ticket Menu ========");
//            System.out.println("1. Add a new ticket");
//            System.out.println("2. View all tickets");
//            System.out.println("3. Update an existing ticket");
//            System.out.println("4. Delete a ticket");
//            System.out.println("0. Back to main menu");
//            System.out.println("Choose your choice");
//
//            try {
//                choice = Integer.parseInt(scanner.nextLine());
//            } catch (NumberFormatException e) {
//                System.out.println("Invalid input. Please enter a number.");
//            }
//            switch (choice) {
//                case 1:
//                    addTicket();
//                    break;
//                case 2:
//                    getAllTickets();
//                    break;
//                case 3:
//                    updateTicket();
//                    break;
//                case 4:
//                    deleteTicket();
//                    break;
//                case 0:
//                    System.out.println("Exiting...!");
//                    running = false;
//                    break;
//                default:
//                    System.out.println("Invalid choice!");
//            }
//        }
//    }

    public void addTicket() {

        System.out.println("Ajout d'un ticket...");

        System.out.println("Select Transport Type:");
        for (TransportType type : TransportType.values()) {
            System.out.println(type.ordinal() + ". " + type.name());
        }
        int transportTypeIndex = Integer.parseInt(scanner.nextLine());
        TransportType transportType = TransportType.values()[transportTypeIndex];

        System.out.println("Enter Purchase Price: ");
        float purchasePrice = Float.parseFloat(scanner.nextLine());

        System.out.println("Enter Sale Price: ");
        float salePrice = Float.parseFloat(scanner.nextLine());

        System.out.println("Select Ticket Status:");
        for (TicketStatus status : TicketStatus.values()) {
            System.out.println(status.ordinal() + ". " + status.name());
        }
        int statusIndex = Integer.parseInt(scanner.nextLine());
        TicketStatus ticketStatus = TicketStatus.values()[statusIndex];

        System.out.println("Enter Contract ID (UUID): ");
        UUID contractId = UUID.fromString(scanner.nextLine());

        Contract contract = iContractService.getContractById(contractId);

//        if (contract == null) {
//            System.out.println("Invalid Contract ID. Ticket creation failed.");
//            return;
//        }
        List<Journey> journeys = iJourneyService.getAllJourneys();

        System.out.println("Available Journeys:");
        for (int i = 0; i < journeys.size(); i++){
            Journey journey = journeys.get(i);
            System.out.println((i+1) + ". Departure : " + journey.getDepartureCity().getCityName()
                                    + " | Destination: " + journey.getDestinationCity().getCityName() +
                    " | Departure Date: " + journey.getDepartureDate());
        }

        System.out.print("Select a journey (Enter number): ");
        int journeySelection = Integer.parseInt(scanner.nextLine()) - 1;
        if (journeySelection < 0 || journeySelection >= journeys.size()) {
            System.out.println("Invalid selection, please try again.");
            return;
        }
        Journey selectedJourney = journeys.get(journeySelection);

        System.out.println("Enter Departure Date (YYYY-MM-DD): ");
        LocalDate departureDate = LocalDate.parse(scanner.nextLine());


        Ticket ticket = new Ticket(UUID.randomUUID(), transportType, purchasePrice, salePrice, null, ticketStatus, contract, selectedJourney, departureDate);

        int result = iTicketService.addTicket(ticket);

        if (result > 0) {
            System.out.println("Ticket added successfully!");
        } else {
            System.out.println("Failed to add ticket.");
        }
    }

    private void getAllTickets() {
        List<Ticket> tickets = iTicketService.getAllTickets();

        if (tickets.isEmpty()) {
            System.out.println("No tickets found.");
        }

        for (Ticket ticket : tickets) {
            System.out.println("Ticket ID: " + ticket.getId());
            System.out.println("Transport Type: " + ticket.getTransportType());
            System.out.println("Purchase Price: " + ticket.getPurchasePrice());
            System.out.println("Sale Price: " + ticket.getSalePrice());
            System.out.println("Sale Date: " + ticket.getSaleDate());
            System.out.println("Ticket Status: " + ticket.getTicketStatus());
            System.out.println("Contract ID: " + ticket.getContract().getId());
            System.out.println("=================================");
        }
    }

//    private void updateTicket() {
//        System.out.println("Enter Ticket ID to update: ");
//        UUID id = UUID.fromString(scanner.nextLine());
//
//        System.out.println("Enter new Transport Type (airplane, train, bus) : ");
//        TransportType transportType = TransportType.valueOf(scanner.nextLine().toUpperCase());
//
//        System.out.println("Enter new Purchase Price: ");
//        float purchasePrice = Float.parseFloat(scanner.nextLine());
//
//        System.out.println("Enter new Sale Price: ");
//        float salePrice = Float.parseFloat(scanner.nextLine());
//
//        System.out.println("Enter new Sale Date (YYYY-MM-DD): ");
//        LocalDate saleDate = LocalDate.parse(scanner.nextLine());
//
//        System.out.println("Enter new Ticket Status (sold, canceled, pending): ");
//        TicketStatus ticketStatus = TicketStatus.valueOf(scanner.nextLine().toUpperCase());
//
//        System.out.println("Enter new Contract ID (UUID): ");
//        UUID contractId = UUID.fromString(scanner.nextLine());
//
//        Contract contract = iContractService.getContractById(contractId);
//
//
//        Ticket ticket = new Ticket(id, transportType, purchasePrice, salePrice, saleDate, ticketStatus, contract);
//        iTicketService.updateTicket(ticket);
//
//        System.out.println("Ticket updated successfully!");
//    }

    private void deleteTicket() {
        System.out.println("Enter Ticket ID to delete: ");
        UUID id = UUID.fromString(scanner.nextLine());

        iTicketService.deleteTicket(id);

        System.out.println("Ticket deleted successfully!");
    }

}
