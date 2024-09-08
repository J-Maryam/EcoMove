package services;

import dao.ClientDao;
import models.entities.Client;

public class ClientService {

    private ClientDao clientDao;

    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public int addClient(Client client) {
        return clientDao.addClient(client);
    }
}
