package ru.rusyaevmax.dao;

import org.hibernate.SessionFactory;
import ru.rusyaevmax.models.City;

public class CityDAO extends AbstractDAO<City> {
    public CityDAO(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }
}
