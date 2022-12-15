package ru.rusyaevmax.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.rusyaevmax.config.MySessionFactory;
import ru.rusyaevmax.dao.AddressDAO;
import ru.rusyaevmax.dao.CityDAO;
import ru.rusyaevmax.dao.CustomerDAO;
import ru.rusyaevmax.dao.StoreDAO;
import ru.rusyaevmax.dto.AddressDTO;
import ru.rusyaevmax.dto.CustomerDTO;
import ru.rusyaevmax.models.Address;
import ru.rusyaevmax.models.Customer;

public class CustomerService {
    private final CustomerDAO customerDAO;
    private final AddressDAO addressDAO;
    private final CityDAO cityDAO;
    private final StoreDAO storeDAO;

    public CustomerService(SessionFactory sessionFactory) {
        this.customerDAO = new CustomerDAO(sessionFactory);
        this.addressDAO = new AddressDAO(sessionFactory);
        this.cityDAO = new CityDAO(sessionFactory);
        this.storeDAO = new StoreDAO(sessionFactory);
    }

    public Customer save(Customer customer) {
        return customerDAO.save(customer);
    }

    public Customer getByFirstNameAndLastName(String firstName, String lastName) {
        return customerDAO.getByFirstNameAndLastName(firstName, lastName);
    }

    public Customer getById(int i) {
        return customerDAO.getById((short)i);
    }

    public void addCustomer(AddressDTO addressDTO, CustomerDTO customerDTO) {
        try(Session session = MySessionFactory.getInstance().getCurrentSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();

            Address address = new Address();
            address.setAddress(addressDTO.getAddress());
            address.setAddress2(addressDTO.getAddress2());
            address.setCity(cityDAO.getById(addressDTO.getCityId()));
            address.setPhone(addressDTO.getPhone());
            address.setDistrict(addressDTO.getDistrict());
            address.setPostalCode(addressDTO.getPostalCode());

            addressDAO.save(address);

            Customer customer = new Customer();
            customer.setStore(storeDAO.getById(customerDTO.getStoreId()));
            customer.setFirstName(customerDTO.getFirstName());
            customer.setLastName(customerDTO.getLastName());
            customer.setEmail(customerDTO.getEmail());
            customer.setIsActive(customerDTO.getIsActive());
            customer.setAddress(address);

            this.save(customer);

            transaction.commit();
        }
    }
}
