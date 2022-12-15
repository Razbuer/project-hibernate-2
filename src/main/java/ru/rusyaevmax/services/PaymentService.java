package ru.rusyaevmax.services;

import ru.rusyaevmax.dao.PaymentDAO;

public class PaymentService {
    private final PaymentDAO paymentDAO;

    public PaymentService(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }
}
