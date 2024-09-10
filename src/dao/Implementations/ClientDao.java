package dao.Implementations;

import dao.Interfaces.IClientDao;
import models.entities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientDao implements IClientDao {

    private Connection connection;

    public ClientDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int addClient(Client client) {
        String sql = "insert into clients (id, firstName, lastName, email, phone) values (?, ?, ?, ?, ?);";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setObject(1, client.getId());
            ps.setString(2, client.getFirstName());
            ps.setString(3, client.getLastName());
            ps.setString(4, client.getEmail());
            ps.setString(5, client.getPhone());

            int rowAffected =  ps.executeUpdate();
            return rowAffected;

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }

    @Override
    public List<Client> getAllClients() {
        String sql = "select * from clients";
        List<Client> clients = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UUID id = UUID.fromString(rs.getString("id"));
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                Client client = new Client(id, firstName, lastName, email, phone);
                clients.add(client);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public int updateProfile(Client client) {
        String sql = "update clients set firstName = ?, lastName = ?, email = ?, phone = ? where id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, client.getFirstName());
            ps.setString(2, client.getLastName());
            ps.setString(3, client.getEmail());
            ps.setString(4, client.getPhone());
            ps.setObject(5, client.getId());

            int rowAffected = ps.executeUpdate();
            return rowAffected;

        }catch (SQLException e) {
            e.printStackTrace();
        }
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
    public Client getClientByDetails(String firstName, String lastName, String email) {
        Client client = null;
        String sql = "select * from clients where firstName = ? and lastName = ? and email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setObject(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, email);

            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    UUID id = UUID.fromString(rs.getString("id"));
                    String phone = rs.getString("phone");

                    return new Client(id, firstName, lastName, email, phone);
                }
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

}
