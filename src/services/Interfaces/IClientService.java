package services.Interfaces;

import models.entities.Client;

import java.util.List;

public interface IClientService {
    int addClient(Client client);
    List<Client> getAllClients();
    int updateProfile(Client client);
    int deleteClient(int id);
    void searchClient(Client client);
    void seConnect(Client client);
}
