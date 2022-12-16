package ru.rusyaevmax.services;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.rusyaevmax.config.MySessionFactory;
import ru.rusyaevmax.dao.*;
import ru.rusyaevmax.models.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class RentalService {
    private final RentalDAO rentalDAO;
    private final InventoryDAO inventoryDAO;
    private final CustomerDAO customerDAO;
    private final StaffDAO staffDAO;
    private final PaymentDAO paymentDAO;

    public RentalService(SessionFactory sessionFactory) {
        this.rentalDAO = new RentalDAO(sessionFactory);
        this.inventoryDAO = new InventoryDAO(sessionFactory);
        this.customerDAO = new CustomerDAO(sessionFactory);
        this.staffDAO = new StaffDAO(sessionFactory);
        this.paymentDAO = new PaymentDAO(sessionFactory);
    }

    public List<Rental> getAll() {
        return rentalDAO.getAll();
    }

    public Rental getById(int idRental) {
        return rentalDAO.getById(idRental);
    }

    public void returnFilm(int idRental) {
        try(Session session = MySessionFactory.getInstance().getCurrentSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();

            Rental rental = this.getById(idRental);

            rental.setReturnDate(LocalDateTime.now());

            transaction.commit();
        }
    }

    public String newRentalFilm(short filmId, byte storeId, short customerId, byte staffId) {
        try(Session session = MySessionFactory.getInstance().getCurrentSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();

            List<Inventory> inventories = inventoryDAO.getAllByFilmIdAndStoreId(filmId, storeId);

            Inventory inventory = null;
            for (Inventory inventoryTemp : inventories) {
                if (checkCanBeRentalFilm(inventoryTemp.getId())) {
                    inventory = inventoryTemp;
                    break;
                }
            }

            if (Objects.isNull(inventory))
                return "Фильм не может быть арендован";

            Customer customer = customerDAO.getById(customerId);
            Staff staff = staffDAO.getById(staffId);

            Rental rental = new Rental();
            rental.setInventory(inventory);
            rental.setCustomer(customer);
            rental.setStaff(staff);

            rentalDAO.save(rental);

            Payment payment = new Payment();
            payment.setCustomer(customer);
            payment.setStaff(staff);
            payment.setRental(rental);
            payment.setAmount(inventory.getFilm().getReplacementCost());

            paymentDAO.save(payment);

            transaction.commit();
        }

        return "200";
    }

    public boolean checkCanBeRentalFilm(int inventoryId) {
        List<Rental> listByInventoryId = rentalDAO.getListByInventoryId(inventoryId);

        if (!listByInventoryId.isEmpty())
            for (Rental rental : listByInventoryId)
                if (Objects.isNull(rental.getReturnDate()))
                    return false;

        return true;
    }
}
