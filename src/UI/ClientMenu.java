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

    public ClientMenu(IClientService iClientService) {
        this.iClientService = iClientService;
    }


    public void addClient() {

        System.out.println("Hello User ! Enter your information to get registered !");

        System.out.println("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.println("Enter your last name: ");
        String lastName = scanner.nextLine();

        boolean isEmailValid = false;
        String email;
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
}
