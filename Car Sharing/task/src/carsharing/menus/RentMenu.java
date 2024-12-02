package carsharing.menus;

import carsharing.services.CustomerService;
import carsharing.utils.KeyboardUtil;

public class RentMenu implements Menu {

    private final CustomerService customerService;
    private final int customerId;

    public RentMenu(int customerId) {

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

        customerService.rentACar(customerId);

    }

    private void myRentedCarMenu() {

        customerService.getCustomerCar(customerId);
    }

    @Override
    public <T> void run(java.util.List<T> items) {
        System.out.println("Rent menu");
    }
}
