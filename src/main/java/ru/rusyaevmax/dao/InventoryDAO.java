package ru.rusyaevmax.dao;

import org.hibernate.SessionFactory;
import ru.rusyaevmax.models.Inventory;

import java.util.List;

public class InventoryDAO extends AbstractDAO<Inventory> {
    public InventoryDAO(SessionFactory sessionFactory) {
        super(Inventory.class, sessionFactory);
    }

    public List<Inventory> getAllByFilmIdAndStoreId(short filmId, byte storeId) {
        String HQL = """
                SELECT i
                  FROM Inventory AS i
                 WHERE i.film.id = :filmId
                   AND i.store.id = :storeId
                """;

        return sessionFactory.getCurrentSession()
                .createQuery(HQL, Inventory.class)
                .setParameter("filmId", filmId)
                .setParameter("storeId", storeId)
                .list();
    }

}
