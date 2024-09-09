package dao.Interfaces;

import models.entities.Client;

public interface IClientDao {

    int addClient(Client client);
    void viewAllClients();
    int updateProfile(Client client);
    int deleteClient(int id);
    void searchClient(Client client);
    void seConnect(Client client);

}
