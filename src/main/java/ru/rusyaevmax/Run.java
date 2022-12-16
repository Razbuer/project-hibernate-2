package ru.rusyaevmax;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.rusyaevmax.config.MySessionFactory;
import ru.rusyaevmax.dao.*;
import ru.rusyaevmax.dto.AddressDTO;
import ru.rusyaevmax.dto.CustomerDTO;
import ru.rusyaevmax.dto.FilmDTO;
import ru.rusyaevmax.models.*;
import ru.rusyaevmax.services.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class Run {
    private final CustomerService customerService;
    private final FilmService filmService;
    private final RentalService rentalService;

    public Run(SessionFactory sessionFactory) {
        this.customerService = new CustomerService(sessionFactory);
        this.filmService = new FilmService(sessionFactory);
        this.rentalService = new RentalService(sessionFactory);
    }

    public static void main(String[] args) {
        Run run = new Run(MySessionFactory.getInstance());

//        run.addCustomer();

//        run.returnFilm();

        run.newRentalFilm();

//        run.addNewFilm();
    }

    /**
     * Добавить метод, который умеет создавать нового покупателя (таблица customer)
     * со всеми зависимыми полями. Не забудь сделать чтоб метод был транзакционным
     * (чтоб не попасть в ситуацию что адрес покупателя записали в БД, а самого покупателя – нет).
     * */
    public void addCustomer() {
        // Допустим с контроллера пришёл адрес и данные о покупателе
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setPostalCode("430000");
        addressDTO.setDistrict("Republic of Mordovia");
        addressDTO.setCityId((short) 1);
        addressDTO.setAddress("1 Lenina");
        addressDTO.setPhone("89270707000");

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setStoreId((byte) 1);
        customerDTO.setFirstName("Maxim");
        customerDTO.setLastName("Rusyaev");
        customerDTO.setEmail("rusyaevmax@yandex.ru");
        customerDTO.setIsActive(true);

        // транзакционный метод
        customerService.addCustomer(addressDTO, customerDTO);
    }

    /**
     * Добавить транзакционный метод, который описывает событие «покупатель пошел и вернул ранее арендованный фильм».
     * Покупателя и событие аренды выбери любое на свое усмотрение.
     * Рейтинг фильма пересчитывать не нужно.
     */
    public void returnFilm() {
        // С контроллера пришёл id записи для возврата
        int idRental = 12001;

        rentalService.returnFilm(idRental);
    }

    /**
     * Добавить транзакционный метод, который описывает событие «покупатель сходил в магазин (store) и
     * арендовал (rental) там инвентарь (inventory). При этом он сделал оплату (payment) у продавца (staff)».
     * Фильм (через инвентарь) выбери на свое усмотрение. Единственное ограничение – фильм должен быть доступен для аренды.
     * То есть либо в rental не должно быть вообще записей по инвентарю,
     * либо должна быть заполнена колонка return_date таблицы rental для последней аренды этого инвентаря.
     */
    public void newRentalFilm() {
        // С контроллера пришла информация по арендованному фильму и магазину
        short filmId = 1;
        byte storeId = 1;
        short customerId = 1;
        byte staffId = 1;

        String resultMSG = rentalService.newRentalFilm(filmId, storeId, customerId, staffId);
    }

    /**
     * Добавить транзакционный метод, который описывает событие «сняли новый фильм,
     * и он стал доступен для аренды».
     * Фильм, язык, актеров, категории и т д выбери на свое усмотрение.
     */
    public void addNewFilm() {
        // С контроллера пришла информация по новому фильму
        FilmDTO filmDTO = new FilmDTO();

        filmDTO.setTitle("New Film");
        filmDTO.setDescription("It`s beautiful film");
        filmDTO.setReleaseYear(2022);
        filmDTO.setLanguageId((byte)1);
        filmDTO.setCategoriesId(Set.of((byte)1, (byte)2));
        filmDTO.setActorsId(Set.of((short)1, (short)2, (short)3));
        filmDTO.setRentalDuration((byte)6);
        filmDTO.setRentalRate(BigDecimal.valueOf(4.99));
        filmDTO.setLength((short)120);
        filmDTO.setReplacementCost(BigDecimal.valueOf(99.99));
        filmDTO.setRating("PG-13");
        filmDTO.setSpecialFeatures("Commentaries,Deleted Scenes");

        filmService.addNewFilm(filmDTO);

    }
}
