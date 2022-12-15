package ru.rusyaevmax.services;

import ru.rusyaevmax.dao.InventoryDAO;

public class InventoryService {
    private final InventoryDAO inventoryDAO;

    public InventoryService(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }
}
