package carsharing.menus;

import carsharing.models.Car;
import carsharing.services.CarService;
import carsharing.services.CompanyService;
import carsharing.utils.KeyboardUtil;

import java.util.List;

public class CarMenu implements Menu {
    private final int companyId;
    private final CompanyService companyService;
    private final CarService carService;


    public CarMenu(int companyId) {
        this.companyId = companyId;
        companyService = CompanyService.getInstance();
        carService = CarService.getInstance();
    }


    @Override
    public void show() {
        System.out.println("1. Car list");
        System.out.println("2. Create a car");
        System.out.println("0. Back");
    }

    @Override
    public <T> void show(List<T> items) {
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

    public void carListMenu() {
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
    public <T> void run(List<T> items){
    }
}
