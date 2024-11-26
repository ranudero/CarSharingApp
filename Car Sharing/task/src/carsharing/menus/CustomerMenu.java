package carsharing.menus;

import carsharing.daos.DBCustomerDAO;
import carsharing.models.Company;
import carsharing.models.Customer;
import carsharing.services.CustomerService;
import carsharing.utils.KeyboardUtil;

import java.util.List;

public class CustomerMenu implements Menu {

    private final DBCustomerDAO customerDao;
    private final CustomerService customerService;

    public CustomerMenu(DBCustomerDAO customerDao, CustomerService customerService) {
        this.customerDao = customerDao;
        this.customerService = customerService;
    }

    @Override
    public void run() {

    }

    @Override
    public void show() {

    }

    @Override
    public <T> void show(List<T> items){
        if(items != null && !items.isEmpty() && items.get(0) instanceof Customer) {
            List<Customer> customers = (List<Customer>) items;

            System.out.println("\nChoose a customer:");
            customers.forEach(customer -> System.out.println(customer.getId() + ". " + customer.getName()));
            System.out.println("0. Back");
        } else {
            System.out.println("The customer list is empty!");
        }
    }

    @Override
    public <T> void run(List<T> items) {
        List<Customer> customers = (List<Customer>) items;
        while (customers != null) {
            show(customers);
            int choice = KeyboardUtil.getInputInt();
            if (choice == 0) {
                return;
            } else if (choice > 0 && choice <= customers.size()) {
               //createRentMenu(choice);
                System.out.println("Rent menu");
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }
}
