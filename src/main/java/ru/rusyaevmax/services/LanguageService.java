package ru.rusyaevmax.services;

import ru.rusyaevmax.dao.LanguageDAO;

public class LanguageService {
    private final LanguageDAO languageDAO;

    public LanguageService(LanguageDAO languageDAO) {
        this.languageDAO = languageDAO;
    }
}
