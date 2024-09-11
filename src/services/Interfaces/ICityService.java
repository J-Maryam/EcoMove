package services.Interfaces;

import models.entities.City;

import java.util.List;

public interface ICityService {

    List<City> getAllCities();
    City getCityById(int id);

}
