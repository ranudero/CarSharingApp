package carsharing.menus;

import carsharing.daos.DBCustomerDAO;
import carsharing.services.CustomerService;
import carsharing.utils.KeyboardUtil;

public class RentMenu implements Menu {

    private final DBCustomerDAO customerDao;
    private final CustomerService customerService;
    private final int customerId;

    public RentMenu(int customerId) {
        customerDao = DBCustomerDAO.getInstance();
        customerService = CustomerService.getInstance();
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
        while(true) {
            show();
            int choice = KeyboardUtil.getInputInt();
            switch (choice) {
                case 1:
                    rentACarMenu();
                    break;
                case 2:
                    returnRentedCarMenu();
                    break;
                case 3:
                    myRentedCarMenu();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void returnRentedCarMenu() {
        customerService.returnRentedCar(customerId);
    }

    private void rentACarMenu() {
        //if customer already has rented a car, print "You've already rented a car!"
        //list out all the available companies and let the user pick one
        //if company list is empty, print "The company list is empty!"
        //if user picks a company, list out all the cars available cars of that company
        //if no cars are available, print "No available cars"
        //if user picks a car, rent the car to the customer
        customerService.rentACar(customerId);

    }

    private void myRentedCarMenu() {
        //if customer has rented a car, print the car name
        //if customer has not rented a car, print "You didn't rent a car!"
        customerService.getCustomerCar(customerId);
    }

    @Override
    public <T> void run(java.util.List<T> items) {
        System.out.println("Rent menu");
    }
}
