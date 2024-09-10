package services.Implementations;

import dao.Implementations.ClientDao;
import dao.Interfaces.IClientDao;
import models.entities.Client;
import services.Interfaces.IClientService;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientService implements IClientService {

    private IClientDao iClientDao;

    public ClientService(IClientDao iClientDao) {
        this.iClientDao = iClientDao;
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9+_.-]+@(.+)$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public int addClient(Client client) {

        if (!isValidEmail(client.getEmail())) {
            System.out.println("Invalid email");
            return -1;
        }
        return iClientDao.addClient(client);
    }

    public List<Client> getAllClients() {
        return iClientDao.getAllClients();
    }

    @Override
    public int updateProfile(Client client) {
        if (!isValidEmail(client.getEmail())) {
            System.out.println("Invalid email");
            return -1;
        }
        return iClientDao.updateProfile(client);
    }

    @Override
    public Client getClientByDetails(String firstName, String lastName, String email) {
        return iClientDao.getClientByDetails(firstName, lastName, email);
    }

    @Override
    public int deleteClient(int id) {
        return 0;
    }

    @Override
    public Client viewMyProfile(UUID id) {
        return iClientDao.viewMyProfile(id);
    }

}
