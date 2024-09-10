package UI;

import models.entities.Client;
import services.Implementations.ClientService;
import services.Interfaces.IClientService;

import java.util.Scanner;

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

        System.out.println("Enter your email address: ");
        String email = scanner.nextLine();

        System.out.println("Enter your phone number: ");
        String phone = scanner.nextLine();

        Client client = new Client(firstName, lastName, email, phone);

        int isAdded = iClientService.addClient(client);

        if (isAdded > 0) {
            System.out.println("Client added successfully !");
        }else {
            System.out.println("Client failed to register !");
        }

    }
}
