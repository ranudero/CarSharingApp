package carsharing.menus;

import carsharing.daos.DBCustomerDAO;
import carsharing.models.Company;
import carsharing.models.Customer;
import carsharing.services.CustomerService;
import carsharing.utils.KeyboardUtil;

import java.util.List;

public class CustomerMenu implements Menu {

    //private final DBCustomerDAO customerDao;
    //private final CustomerService customerService;

    public CustomerMenu() {
        //customerDao = DBCustomerDAO.getInstance();
        //customerService = CustomerService.getInstance();
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
        }
    }

    @Override
    public <T> void run(List<T> items) {
        List<Customer> customers = (List<Customer>) items;
        while (customers != null) {
            show(customers);
            int customer_selected = KeyboardUtil.getInputInt();
            if (customer_selected == 0) {
                return;
            } else if (customer_selected > 0 && customer_selected <= customers.size()) {
                createRentMenu(customer_selected);
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }

    private void createRentMenu(int customerSelected) {
        RentMenu rentMenu = new RentMenu(customerSelected);
        rentMenu.run();
    }


}
