package ru.rusyaevmax.services;

import ru.rusyaevmax.dao.ActorDAO;

public class ActorService {
    private final ActorDAO actorDAO;

    public ActorService(ActorDAO actorDAO) {
        this.actorDAO = actorDAO;
    }
}
