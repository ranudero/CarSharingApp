package carsharing.menus;

import carsharing.daos.DBCustomerDAO;
import carsharing.services.CustomerService;
import carsharing.utils.KeyboardUtil;

public class RentMenu implements Menu {

    private final DBCustomerDAO customerDao;
    private final CustomerService customerService;
    private final int customerId;

    public RentMenu(DBCustomerDAO customerDao, CustomerService customerService, int customerId) {
        this.customerDao = customerDao;
        this.customerService = customerService;
        this.customerId = customerId;
    }

    @Override
    public void show() {
        System.out.println("\n1. Rent a car");
        System.out.println("2. Return a rented car");
        System.out.println("3. My rented car");
        System.out.println("0. Back");
    }

    @Override
    public <T> void show(java.util.List<T> items) {
        System.out.println("Rent menu");
    }

    @Override
    public void run() {
        show();
        int choice = KeyboardUtil.getInputInt();
        switch (choice) {
            case 1:
                System.out.println("Rent a car");
                rentACarMenu();
                break;
            case 2:
                System.out.println("Return a rented car");
                break;
            case 3:
                System.out.println("My rented car");
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice");
        }
    }

    private void rentACarMenu() {
        //if customer already has rented a car, print "You've already rented a car!"
        //list out all the available companies and let the user pick one
        //if company list is empty, print "The company list is empty!"
        //if user picks a company, list out all the cars available cars of that company
        //if no cars are available, print "No available cars"
        //if user picks a car, rent the car to the customer



    }

    @Override
    public <T> void run(java.util.List<T> items) {
        System.out.println("Rent menu");
    }
}
