package ru.rusyaevmax.dao;

import org.hibernate.SessionFactory;
import ru.rusyaevmax.models.Film;

import java.util.List;

public class FilmDAO extends AbstractDAO<Film> {
    public FilmDAO(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }

    public List<Film> getAll() {
        return sessionFactory.getCurrentSession().createQuery("SELECT f FROM Film AS f JOIN FETCH f.filmText", Film.class).list();
    }
}
