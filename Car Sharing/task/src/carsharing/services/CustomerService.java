package carsharing.services;

import carsharing.daos.DBCustomerDAO;
import carsharing.models.Customer;

import java.util.List;

public class CustomerService {
    private final DBCustomerDAO customerDAO;

    public CustomerService(DBCustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public void createCustomer(String name) {
        Customer customer = new Customer(0, name, 0);
        customerDAO.add(customer);
        System.out.println("The customer was created!");
    }

    public void customerList() {
        List<Customer> customers = customerDAO.findAll();
        if (customers.isEmpty()) {
            System.out.println("The customer list is empty!");
        } else {
            for (int i = 0; i < customers.size(); i++) {
                System.out.println((i + 1) + ". " + customers.get(i).getName());
            }
        }
    }


}
