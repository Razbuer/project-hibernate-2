package ru.rusyaevmax.services;

import ru.rusyaevmax.dao.StoreDAO;
import ru.rusyaevmax.models.Store;

public class StoreService {
    private final StoreDAO storeDAO;

    public StoreService(StoreDAO storeDAO) {
        this.storeDAO = storeDAO;
    }

    public Store getById(int id) {
        return storeDAO.getById(id);
    }
}
