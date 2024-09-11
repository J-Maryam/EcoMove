package services.Implementations;

import dao.Interfaces.ICityDao;
import models.entities.City;
import services.Interfaces.ICityService;

import java.util.List;

public class CityService implements ICityService {

    private ICityDao iCityDao;

    public CityService(ICityDao iCityDao) {
        this.iCityDao = iCityDao;
    }

    @Override
    public List<City> getAllCities() {
        return iCityDao.getAllCities();
    }

    @Override
    public City getCityById(int id) {
        return iCityDao.getCityById(id);
    }
}
