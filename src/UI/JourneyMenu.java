package UI;

import models.entities.City;
import models.entities.Journey;
import services.Implementations.JourneyService;
import services.Interfaces.ICityService;
import services.Interfaces.IJourneyService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class JourneyMenu {

    private Scanner scanner = new Scanner(System.in);
    private IJourneyService iJourneyService;
    private ICityService iCityService;

    public JourneyMenu(IJourneyService iJourneyService, ICityService iCityService) {
        this.iJourneyService = iJourneyService;
        this.iCityService = iCityService;
    }


    public void displayMenu() {
        boolean running = true;

        int choice;
        while (running) {
            System.out.println("=== Journey Menu ===");
            System.out.println("1. Add Journey");
            System.out.println("2. Update Journey");
            System.out.println("3. Delete Journey");
            System.out.println("4. View All Journeys");
            System.out.println("5. View Journey by ID");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addTrajet();
                    break;
                case 2:
                    getAllJourneys();
                    break;
                case 3:
                    ;
                    break;
                case 4:
                    ;
                    break;
                case 5:
//                    searcheeee ticket;
                    break;
                case 0:
                    System.out.println("Exiting client menu...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void addTrajet() {
        System.out.println("=== Add Journey ===");

        List<City> cities = iCityService.getAllCities();

        System.out.println("Available Cities:");
        for (City city : cities) {
            System.out.println(city.getId() + ". " + city.getCityName() + "    ");
        }
        System.out.println();

        System.out.print("Select Departure City (Enter City ID): ");
        int departureCityId = Integer.parseInt(scanner.nextLine());

        int destinationCityId;
        do {
            System.out.print("Select Destination City (Enter City ID): ");
            destinationCityId = Integer.parseInt(scanner.nextLine());

            if (departureCityId == destinationCityId) {
                System.out.println("Error: Departure city and destination city cannot be the same. Please choose a different destination city.");
            }
        } while (departureCityId == destinationCityId);

        LocalDate departureDate = null;
        while (departureDate == null) {
            System.out.print("Enter Departure Date (YYYY-MM-DD): ");
            String dateInput = scanner.nextLine();
            try {
                departureDate = LocalDate.parse(dateInput, DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
            }
        }

        City departureCity = iCityService.getCityById(departureCityId);
        City destinationCity = iCityService.getCityById(destinationCityId);

        Journey journey = new Journey(departureCity, destinationCity, departureDate);

        int result = iJourneyService.addJourney(journey);
        if (result > 0) {
            System.out.println("Journey added successfully!");
        } else {
            System.out.println("Failed to add Journey.");
        }
    }

    // Dans JourneyUI.java

    public void getAllJourneys() {
        System.out.println("=== All Journeys ===");

        List<Journey> journeys = iJourneyService.getAllJourneys();

        if (journeys.isEmpty()) {
            System.out.println("No journeys available.");
        } else {
            for (Journey journey : journeys) {
                System.out.println("ID: " + journey.getId());
                System.out.println("Departure City: " + journey.getDepartureCity().getCityName());
                System.out.println("Destination City: " + journey.getDestinationCity().getCityName());
                System.out.println("Departure Date: " + journey.getDepartureDate());
                System.out.println("-----------------------------");
            }
        }
    }


}
