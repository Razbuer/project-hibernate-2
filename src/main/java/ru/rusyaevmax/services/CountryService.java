package ru.rusyaevmax.services;

import ru.rusyaevmax.dao.CountryDAO;

public class CountryService {
    private final CountryDAO countryDAO;

    public CountryService(CountryDAO countryDAO) {
        this.countryDAO = countryDAO;
    }
}
