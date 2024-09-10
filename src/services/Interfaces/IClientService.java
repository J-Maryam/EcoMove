package services.Interfaces;

import models.entities.Client;

import java.util.List;
import java.util.UUID;

public interface IClientService {
    boolean isValidEmail(String email);
    int addClient(Client client);
    List<Client> getAllClients();
    int updateProfile(Client client);
    Client getClientByDetails(String firstName, String lastName, String email);
    int deleteClient(int id);
    Client viewMyProfile(UUID id);
}
