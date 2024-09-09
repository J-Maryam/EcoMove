package dao.Implementations;

import dao.Interfaces.IClientDao;
import models.entities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientDao implements IClientDao {

    private Connection connection;

    public ClientDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int addClient(Client client) {
        String sql = "insert into clients (id, firstName, lastName, email, phone) values (?, ?, ?, ?, ?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, client.getId());
            ps.setString(2, client.getFirstName());
            ps.setString(3, client.getLastName());
            ps.setString(4, client.getEmail());
            ps.setString(5, client.getPhone());

            return ps.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void viewAllClients() {

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
