package dao.Implementations;

import dao.Interfaces.ICityDao;
import dao.Interfaces.IJourneyDao;
import models.entities.City;
import models.entities.Journey;
import services.Implementations.CityService;
import services.Interfaces.ICityService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JourneyDao implements IJourneyDao {

    private Connection connection;

    private ICityService iCityService;

    public JourneyDao(Connection connection, ICityService iCityService) {
        this.connection = connection;
        this.iCityService = iCityService;
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
        String sql = "select * from journeys";
        List<Journey> journeys = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                int departureCityById = rs.getInt("departureCity");
                int destinationCityById = rs.getInt("destinationCity");
                LocalDate departureDate = rs.getDate("departureDate").toLocalDate();

                City departureCity = iCityService.getCityById(departureCityById);
                City destinationCity = iCityService.getCityById(destinationCityById);
                Journey journey = new Journey(id, departureCity, destinationCity, departureDate);

                journeys.add(journey);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return journeys;
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
