package carsharing.menus;

import carsharing.daos.DBCompanyDAO;
import carsharing.models.Car;
import carsharing.models.Company;
import carsharing.services.CarService;
import carsharing.services.CompanyService;
import carsharing.utils.KeyboardUtil;

import java.util.List;

public class CarMenu implements Menu {
    private final DBCompanyDAO companyDAO;
    private final int companyId;
    private final CompanyService companyService;
    private final CarService carService;

    public CarMenu(DBCompanyDAO companyDAO, int companyId, CompanyService companyService, CarService carService) {
        this.companyDAO = companyDAO;
        this.companyId = companyId;
        this.companyService = companyService;
        this.carService = carService;
    }

    @Override
    public void show() {
        System.out.println("1. Car list");
        System.out.println("2. Create a car");
        System.out.println("0. Back");
    }

    @Override
    public void show(List<Company> companies){
    }

    @Override
    public void run() {
        System.out.println("\n"+companyService.getCompanyName(companyId));
        while(true) {
            show();
            int choice = KeyboardUtil.getInputInt();
            switch (choice) {
                case 1:
                    carListMenu();
                    break;
                case 2:
                    createCarMenu();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    private void carListMenu() {
        List<Car> cars = carService.carList(companyId);
        if (cars.isEmpty()) {
            System.out.println("\nThe car list is empty!\n");
        } else {
            cars.forEach(car -> System.out.println(car.getId() + ". " + car.getName()));
            System.out.println("");
        }
    }

    private void createCarMenu() {
        String name = KeyboardUtil.getInputString("Enter the car name:");
        carService.createCar(companyId, name);
    }

    @Override
    public void run(List<Company> companies){
    }
}
