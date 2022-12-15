package ru.rusyaevmax.dao;

import org.hibernate.SessionFactory;
import ru.rusyaevmax.models.FilmText;

public class FilmTextDAO extends AbstractDAO<FilmText> {
    public FilmTextDAO(SessionFactory sessionFactory) {
        super(FilmText.class, sessionFactory);
    }
}
