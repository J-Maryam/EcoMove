package dao.Implementations;

import dao.Interfaces.IJourneyDao;
import models.entities.Journey;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JourneyDao implements IJourneyDao {

    private Connection connection;

    public JourneyDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int addJourney(Journey journey) {
        String sql = "insert into journeys (departureCity, destinationCity, departureDate) values (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setObject(1, journey.getDepartureCity().getId());
            ps.setObject(2, journey.getDestinationCity().getId());
            ps.setObject(3, journey.getDepartureDate());

            return ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Journey> getAllJourneys() {
        return List.of();
    }

    @Override
    public Journey getJourneyById(int id) {
        return null;
    }


    @Override
    public int updateJourney(Journey journey) {
        return 0;
    }

    @Override
    public int deleteJourney(Journey journey) {
        return 0;
    }
}
