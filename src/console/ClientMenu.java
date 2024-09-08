package console;

import models.entities.Client;
import services.ClientService;

import java.util.Scanner;

public class ClientMenu {

    private ClientService clientService;
    private Scanner scanner = new Scanner(System.in);

    public ClientMenu(ClientService clientService) {
        this.clientService = clientService;
    }


    public void addClient() {

        System.out.println("Hello User ! Enter your information to get registered !");

        System.out.println("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.println("Enter your last name: ");
        String lastName = scanner.nextLine();

        System.out.println("Enter your email address: ");
        String email = scanner.nextLine();

        System.out.println("Enter your phone number: ");
        String phone = scanner.nextLine();

        Client client = new Client(firstName, lastName, email, phone);

        int isAdded = clientService.addClient(client);

        if (isAdded > 0) {
            System.out.println("Client added successfully !");
        }else {
            System.out.println("Client failed to register !");
        }

    }
}
