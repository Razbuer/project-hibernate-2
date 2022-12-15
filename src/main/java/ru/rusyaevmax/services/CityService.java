package ru.rusyaevmax.services;

import ru.rusyaevmax.dao.CategoryDAO;
import ru.rusyaevmax.dao.CityDAO;
import ru.rusyaevmax.models.City;

public class CityService {
    private final CityDAO cityDAO;

    public CityService(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    public City getById(int id) {
        return cityDAO.getById(id);
    }
}
