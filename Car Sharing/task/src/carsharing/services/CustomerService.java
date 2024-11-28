package carsharing.services;

import carsharing.daos.DBCarDAO;
import carsharing.daos.DBCustomerDAO;
import carsharing.menus.CarMenu;
import carsharing.menus.CompanyMenu;
import carsharing.models.Car;
import carsharing.models.Company;
import carsharing.models.Customer;
import carsharing.utils.KeyboardUtil;

import java.util.List;

public class CustomerService {
    private final DBCustomerDAO customerDAO;
    private static CustomerService instance;
    private final DBCarDAO carDAO;
    private final CompanyMenu companyMenu;
    private final CompanyService companyService;
    private final CarService carService;


    public CustomerService() {
        customerDAO = DBCustomerDAO.getInstance();
        carDAO = DBCarDAO.getInstance();
        companyMenu = CompanyMenu.getInstance();
        companyService = CompanyService.getInstance();
        carService = CarService.getInstance();
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

    public void getCustomerCar(int customerId) {
        int carID = customerDAO.findRentedCar(customerId);
        if (carID == 0) {
            System.out.println("You didn't rent a car!");
        } else {
            Car car = carDAO.findById(carID);
            System.out.println("Your rented car:");
            System.out.println(car.getName());
            System.out.println("Company:");
            System.out.println(companyService.getCompanyName(car.getCompanyId()));
        }
    }


//    public void rentACar(int customerId) {
//        if (customerDAO.findRentedCar(customerId) != 0) {
//            System.out.println("You've already rented a car!");
//        } else {
//            companyMenu.show(companyService.companyList());
//            int companyID = KeyboardUtil.getInputInt();
//            //show cars of the company
//            carService.listAvailableCars(companyID);
//            //select car
//            int carNumber = KeyboardUtil.getInputInt();
//            Car car = carService.availableCarList(companyID).get(carNumber - 1);
//            String carName = car.getName();
//            System.out.println("You rented " + "'" + carName + "'");
//            car = carService.findCarByName(carName, companyID);
//            customerDAO.updateRentedCar(customerId, car.getId());
//        }
//    }

    public void rentACar(int customerId) {
        if (customerDAO.findRentedCar(customerId) != 0) {
            System.out.println("You've already rented a car!");
        } else {
            List<Company> companies = companyService.companyList();
            companyMenu.show(companies);
            int companyID = KeyboardUtil.getInputInt();

            List<Car> availableCars = carService.availableCarList(companyID);
            if (availableCars.isEmpty()) {
                System.out.println("No available cars in the selected company.");
                return;
            }

            carService.listAvailableCars(companyID);
            int carNumber = KeyboardUtil.getInputInt();

            if (carNumber < 1 || carNumber > availableCars.size()) {
                System.out.println("Invalid car selection.");
                return;
            }

            Car car = availableCars.get(carNumber - 1);
            String carName = car.getName();
            System.out.println("You rented " + "'" + carName + "'");
            car = carService.findCarByName(carName, companyID);
            customerDAO.updateRentedCar(customerId, car.getId());
        }
    }

    public void returnRentedCar(int customerId) {
        if (customerDAO.findRentedCar(customerId) == 0) {
            System.out.println("You didn't rent a car!");
        } else {
            customerDAO.returnCar(customerId);
            System.out.println("You've returned a rented car!");
        }
    }
}
