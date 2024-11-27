package carsharing.services;

import carsharing.daos.DBCustomerDAO;
import carsharing.models.Customer;

import java.util.List;

public class CustomerService {
    private final DBCustomerDAO customerDAO;
    private static CustomerService instance;

    public CustomerService() {
        customerDAO = DBCustomerDAO.getInstance();
    }

    public static CustomerService getInstance(){
        if (instance == null) {
            instance = new CustomerService();
        }
        return instance;
    }

    public void createCustomer(String name) {
        Customer customer = new Customer(0, name, 0);
        customerDAO.add(customer);
        System.out.println("The customer was created!");
    }

    public List<Customer> customerList() {
        List<Customer> customers = customerDAO.findAll();
        if (customers.isEmpty()) {
            System.out.println("The customer list is empty!");
        }
        return customers;
    }


}
