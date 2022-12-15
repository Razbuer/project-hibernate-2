package ru.rusyaevmax.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.rusyaevmax.config.MySessionFactory;
import ru.rusyaevmax.dao.*;
import ru.rusyaevmax.dto.FilmDTO;
import ru.rusyaevmax.models.*;
import ru.rusyaevmax.util.Feature;
import ru.rusyaevmax.util.Rating;

import java.time.Year;
import java.util.HashSet;
import java.util.List;

public class FilmService {
    private final FilmDAO filmDAO;
    private final LanguageDAO languageDAO;
    private final CategoryDAO categoryDAO;
    private final ActorDAO actorDAO;
    private final FilmTextDAO filmTextDAO;
    private final StoreDAO storeDAO;
    private final InventoryDAO inventoryDAO;

    public FilmService(SessionFactory sessionFactory) {
        this.filmDAO = new FilmDAO(sessionFactory);
        this.languageDAO = new LanguageDAO(sessionFactory);
        this.categoryDAO = new CategoryDAO(sessionFactory);
        this.actorDAO = new ActorDAO(sessionFactory);
        this.filmTextDAO = new FilmTextDAO(sessionFactory);
        this.storeDAO = new StoreDAO(sessionFactory);
        this.inventoryDAO = new InventoryDAO(sessionFactory);
    }

    public List<Film> getAll() {
        return filmDAO.getAll();
    }

    public void addNewFilm(FilmDTO filmDTO) {
        try(Session session = MySessionFactory.getInstance().getCurrentSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();

            Film film = new Film();
            film.setTitle(filmDTO.getTitle());
            film.setDescription(filmDTO.getDescription());
            film.setReleaseYear(Year.of(filmDTO.getReleaseYear()));
            film.setLanguage(languageDAO.getById(filmDTO.getLanguageId()));
            List<Category> categories = categoryDAO.getCategoriesByIds(filmDTO.getCategoriesId());
            film.setCategories(new HashSet<>(categories));
            List<Actor> actors = actorDAO.getActorsByIds(filmDTO.getActorsId());
            film.setActors(new HashSet<>(actors));
            film.setRentalDuration(filmDTO.getRentalDuration());
            film.setRentalRate(filmDTO.getRentalRate());
            film.setLength(filmDTO.getLength());
            film.setReplacementCost(filmDTO.getReplacementCost());
            film.setRating(Rating.getByValue(filmDTO.getRating()));
            film.setSpecialFeatures(Feature.getFeaturesByValues(filmDTO.getSpecialFeatures()));

            filmDAO.save(film);

            FilmText filmText = new FilmText();
            filmText.setId(film.getId());
            filmText.setTitle(film.getTitle());
            filmText.setDescription(film.getDescription());
            filmText.setFilm(film);

            filmTextDAO.save(filmText);

            List<Store> stores = storeDAO.getAll();

            for (Store store : stores) {
                Inventory inventory = new Inventory();
                inventory.setFilm(film);
                inventory.setStore(store);

                inventoryDAO.save(inventory);
            }

            transaction.commit();
        }
    }
}
