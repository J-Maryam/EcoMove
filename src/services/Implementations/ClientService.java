package services.Implementations;

import dao.Implementations.ClientDao;
import models.entities.Client;
import services.Interfaces.IClientService;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientService implements IClientService {

    private ClientDao clientDao;

    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9+_.-]+@(.+)$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public int addClient(Client client) {
        return clientDao.addClient(client);
    }

    public List<Client> getAllClients() {
        return clientDao.getAllClients();
    }

    @Override
    public int updateProfile(Client client) {
        return 0;
    }

    @Override
    public int deleteClient(int id) {
        return 0;
    }

    @Override
    public void searchClient(Client client) {

    }

    @Override
    public void seConnect(Client client) {

    }


}
