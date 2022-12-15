package ru.rusyaevmax.dao;

import org.hibernate.SessionFactory;
import ru.rusyaevmax.models.Payment;

public class PaymentDAO extends AbstractDAO<Payment> {
    public PaymentDAO(SessionFactory sessionFactory) {
        super(Payment.class, sessionFactory);
    }
}
