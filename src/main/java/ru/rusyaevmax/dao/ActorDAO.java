package ru.rusyaevmax.dao;

import org.hibernate.SessionFactory;
import ru.rusyaevmax.models.Actor;
import ru.rusyaevmax.models.Category;

import java.util.List;
import java.util.Set;

public class ActorDAO extends AbstractDAO<Actor> {
    public ActorDAO(SessionFactory sessionFactory) {
        super(Actor.class, sessionFactory);
    }

    public List<Actor> getActorsByIds(Set<Short> ids) {
        String HQL = "SELECT a FROM Actor AS a WHERE a.id IN :ids";

        return sessionFactory.getCurrentSession()
                .createQuery(HQL, Actor.class)
                .setParameter("ids", ids)
                .list();
    }
}
