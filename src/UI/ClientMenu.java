package UI;

import models.entities.Client;
import services.Implementations.ClientService;
import services.Interfaces.IClientService;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ClientMenu {

    private IClientService iClientService;
    private Scanner scanner = new Scanner(System.in);

    boolean isEmailValid = false;
    String email;
    private UUID currentClientId;

    public ClientMenu(IClientService iClientService) {
        this.iClientService = iClientService;
    }

    public void displayMenu() {
        boolean running = true;

        int choice;
        while (running) {
            System.out.println("==== Client Menu ====");
            System.out.println("1. View My Profile");
            System.out.println("2. Update Profile");
            System.out.println("3. Search Tickets");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewMyProfile(currentClientId);
                    break;
                case 2:
                    updateProfile();
                    break;
                case 3:
//                    searcheeee ticket;
                    break;
                case 0:
                    System.out.println("Exiting client menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void addClient() {

        System.out.println("Hello User ! Enter your information to get registered !");

        System.out.println("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.println("Enter your last name: ");
        String lastName = scanner.nextLine();

        do {
            System.out.println("Enter your email address: ");
            email = scanner.nextLine();

            if (iClientService.isValidEmail(email)) {
                isEmailValid = true;
            } else {
                System.out.println("Invalid email. Please enter a valid email.");
            }

        } while (!isEmailValid);

        System.out.println("Enter your phone number: ");
        String phone = scanner.nextLine();

        Client client = new Client(UUID.randomUUID() ,firstName, lastName, email, phone);

        int isAdded = iClientService.addClient(client);

        if (isAdded > 0) {
            System.out.println("Client added successfully !");
        } else if (isAdded == -1) {
            System.out.println("Failed to register! Invalid email.");
        } else {
            System.out.println("Client failed to register!");
        }

    }

    public void getAllClients() {
        List<Client> clients = iClientService.getAllClients();

        if(clients.isEmpty()) {
            System.out.println("No clients found!");
        }else {
            System.out.println("======= Clients List =======");
            for (Client client : clients){
                System.out.println("Id : " + client.getId());
                System.out.println("First Name : " + client.getFirstName());
                System.out.println("Last Name : " + client.getLastName());
                System.out.println("Email : " + client.getEmail());
                System.out.println("Phone Number : " + client.getPhone());
                System.out.println("=======================================");
            }
        }

    }

    public void updateProfile() {

        System.out.println("==== Update your profile ====");

        System.out.println("Enter the Id client to update (UUID): ");
        UUID id = UUID.fromString(scanner.nextLine());

        System.out.println("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.println("Enter your last name: ");
        String lastName = scanner.nextLine();

        String email;
        boolean isEmailValid = false;
        do {
            System.out.println("Enter your email address: ");
            email = scanner.nextLine();

            if (iClientService.isValidEmail(email)) {
                isEmailValid = true;
            } else {
                System.out.println("Invalid email. Please enter a valid email.");
            }

        } while (!isEmailValid);

        System.out.println("Enter your phone number: ");
        String phone = scanner.nextLine();

        Client client = new Client(id, firstName, lastName, email, phone);

        int rowAffected = iClientService.updateProfile(client);
        if (rowAffected > 0) {
            System.out.println("Client updated successfully!");
        } else if (rowAffected == -1) {
            System.out.println("Failed to update! Invalid email.");
        } else {
            System.out.println("Failed to update profile! Please try again.");
        }
    }

    public void seConnect(){
        System.out.println("==== Sign In ====");
        System.out.println("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.println("Enter your last name: ");
        String lastName = scanner.nextLine();

        do {
            System.out.println("Enter your email address: ");
            email = scanner.nextLine();
            if (iClientService.isValidEmail(email)) {
                isEmailValid = true;
            } else {
                System.out.println("Invalid email. Please enter a valid email.");
            }
        } while (!isEmailValid);

        Client client = iClientService.getClientByDetails(firstName, lastName, email);

        if (client != null) {
            this.currentClientId = client.getId();
            System.out.println("You are authenticated successfully !");
        }else {
            System.out.println("You are not registered yet!");
            System.out.println("Do you want to register? (yes/no)");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("yes")) {

                System.out.println("Enter your phone number: ");
                String phone = scanner.nextLine();

                client = new Client(UUID.randomUUID() ,firstName, lastName, email, phone);

                int result = iClientService.addClient(client);

                if (result > 0) {
                    System.out.println("Client registered successfully !");
                } else {
                    System.out.println("Failed to register! Please try again.");
                }
            }else {
                System.out.println("Registration canceled");
            }
        }
    }

    public void viewMyProfile(UUID id) {
        if (currentClientId != null) {
            System.out.println("==== My Profile ====");

            Client client = iClientService.viewMyProfile(id);

            if (client != null) {
                System.out.println("Id : " + client.getId());
                System.out.println("First Name : " + client.getFirstName());
                System.out.println("Last Name : " + client.getLastName());
                System.out.println("Email : " + client.getEmail());
                System.out.println("Phone Number : " + client.getPhone());
            } else {
                System.out.println("Profile not found!");
            }
        }else {
            System.out.println("You are not registered yet!");
        }
    }
}
