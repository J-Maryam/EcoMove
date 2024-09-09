package dao.Interfaces;

import models.entities.Client;

import java.util.List;

public interface IClientDao {

    int addClient(Client client);
    List<Client> getAllClients();
    int updateProfile(Client client);
    int deleteClient(int id);
    void searchClient(Client client);
    void seConnect(Client client);

}
