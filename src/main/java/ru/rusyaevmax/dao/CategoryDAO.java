package ru.rusyaevmax.dao;

import org.hibernate.SessionFactory;
import ru.rusyaevmax.models.Category;

import java.util.List;
import java.util.Set;

public class CategoryDAO extends AbstractDAO<Category> {
    public CategoryDAO(SessionFactory sessionFactory) {
        super(Category.class, sessionFactory);
    }

    public List<Category> getCategoriesByIds(Set<Byte> ids) {
        String HQL = "SELECT c FROM Category AS c WHERE c.id IN :ids";

        return sessionFactory.getCurrentSession()
                .createQuery(HQL, Category.class)
                .setParameter("ids", ids)
                .list();
    }
}
