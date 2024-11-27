package carsharing.daos;

import carsharing.models.Customer;

import java.util.List;

public interface CustomerDAO {
    List<Customer> findAll();
    void add(Customer customer);
}
