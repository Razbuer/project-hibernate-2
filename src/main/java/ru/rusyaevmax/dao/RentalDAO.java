package ru.rusyaevmax.dao;

import org.hibernate.SessionFactory;
import ru.rusyaevmax.models.Rental;

import java.util.List;

public class RentalDAO extends AbstractDAO<Rental> {
    public RentalDAO(SessionFactory sessionFactory) {
        super(Rental.class, sessionFactory);
    }

    public List<Rental> getAll() {
        String HQL = """
                SELECT DISTINCT r
                  FROM Rental AS r
                  LEFT JOIN FETCH r.inventory AS i
              """;

        return sessionFactory.getCurrentSession().createQuery(HQL, Rental.class).list();
    }

    public List<Rental> getListByFilmId(short filmId) {
        String HQL = """
                SELECT DISTINCT r
                  FROM Rental AS r
                  LEFT JOIN FETCH r.inventory AS i
                 WHERE i.film.id = :filmId
              """;

        return sessionFactory.getCurrentSession()
                .createQuery(HQL, Rental.class)
                .setParameter("filmId", filmId)
                .list();
    }
}
