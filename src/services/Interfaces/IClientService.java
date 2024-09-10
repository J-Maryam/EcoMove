package services.Interfaces;

import models.entities.Client;

import java.util.List;

public interface IClientService {
    boolean isValidEmail(String email);
    int addClient(Client client);
    List<Client> getAllClients();
    int updateProfile(Client client);
    Client getClientByDetails(String firstName, String lastName, String email);
    int deleteClient(int id);
    void searchClient(Client client);
}
