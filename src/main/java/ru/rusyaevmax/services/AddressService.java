package ru.rusyaevmax.services;

import ru.rusyaevmax.dao.AddressDAO;
import ru.rusyaevmax.models.Address;

public class AddressService {
    private final AddressDAO addressDAO;

    public AddressService(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    public void save(Address address) {
        addressDAO.save(address);
    }
}
