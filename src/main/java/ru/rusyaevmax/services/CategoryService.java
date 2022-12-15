package ru.rusyaevmax.services;

import ru.rusyaevmax.dao.CategoryDAO;

public class CategoryService {
    private final CategoryDAO categoryDAO;

    public CategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }
}
