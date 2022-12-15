package ru.rusyaevmax.services;

import ru.rusyaevmax.dao.StaffDAO;

public class StaffService {
    private final StaffDAO staffDAO;

    public StaffService(StaffDAO staffDAO) {
        this.staffDAO = staffDAO;
    }
}
