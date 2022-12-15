package ru.rusyaevmax.dao;

import org.hibernate.SessionFactory;

import java.util.List;

public abstract class AbstractDAO<T> {
    private final Class<T> clazz;
    protected SessionFactory sessionFactory;

    public AbstractDAO(Class<T> clazz, SessionFactory sessionFactory) {
        this.clazz = clazz;
        this.sessionFactory = sessionFactory;
    }

    public <N extends Number> T getById(N id) {
        return sessionFactory.getCurrentSession().get(clazz, id);
    }

    public List<T> getAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM " + clazz.getName(), clazz).list();
    }

    public List<T> getItems(int from, int count) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM " + clazz.getName(), clazz)
                .setFirstResult(from)
                .setMaxResults(count)
                .list();
    }

    public T save(T t) {
        sessionFactory.getCurrentSession().saveOrUpdate(t);

        return t;
    }

    public T update(T t) {
        return (T) sessionFactory.getCurrentSession().merge(t);
    }

    public void delete(T t) {
        sessionFactory.getCurrentSession().delete(t);
    }

    public <N extends Number> void deleteById(N id) {
        delete(getById(id));
    }
}