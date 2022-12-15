package ru.rusyaevmax.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.rusyaevmax.models.Customer;

public class CustomerDAO extends AbstractDAO<Customer> {
    public CustomerDAO(SessionFactory sessionFactory) {
        super(Customer.class, sessionFactory);
    }

    public Customer getByFirstNameAndLastName(String firstName, String lastName) {
        String HQL = "SELECT c FROM Customer AS c WHERE c.firstName = :firstName AND c.lastName = :lastName";
        Query<Customer> query = sessionFactory.getCurrentSession().createQuery(HQL, Customer.class);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        query.setMaxResults(1);

        return query.getSingleResult();
    }
}
