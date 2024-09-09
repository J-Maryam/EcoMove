package services;

import dao.ClientDao;
import models.entities.Client;
import models.entities.Partner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientService {

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
}
