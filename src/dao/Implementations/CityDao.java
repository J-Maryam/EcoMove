package dao.Implementations;

import dao.Interfaces.ICityDao;
import models.entities.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDao implements ICityDao {

    private Connection connection;

    public CityDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<City> getAllCities() {
        String sql = "select * from cities";
        List<City> cities = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String cityName = rs.getString("cityName");
                City city = new City(id, cityName);

                cities.add(city);
            }


        }catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    @Override
    public City getCityById(int id) {
        String sql = "select * from cities where id = ?";
        City city = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int cityId = rs.getInt("id");
                    String cityName = rs.getString("cityName");
                    city = new City(cityId, cityName);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

}
