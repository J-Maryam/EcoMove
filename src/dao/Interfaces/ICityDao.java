package dao.Interfaces;

import models.entities.City;

import java.util.List;

public interface ICityDao {

    List<City> getAllCities();
    City getCityById(int id);
}
