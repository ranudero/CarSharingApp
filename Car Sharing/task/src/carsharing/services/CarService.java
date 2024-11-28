package carsharing.services;

import carsharing.daos.DBCarDAO;
import carsharing.models.Car;

import java.util.List;

public class CarService {
    private final DBCarDAO carDAO;
    private static CarService instance;

   protected CarService() {
       carDAO = DBCarDAO.getInstance();
    }

    public static CarService getInstance(){
        if (instance == null) {
            instance = new CarService();
        }
        return instance;
    }

    public List<Car> carList(Integer companyId) {
        return carDAO.findAll(companyId);

    }

    public List<Car> availableCarList(Integer companyId){
        return carDAO.findAvailableCars(companyId);
    }

    public void listAvailableCars(Integer companyId) {
        List<Car> cars = availableCarList(companyId);
        if (cars.isEmpty()) {
            System.out.println("No available cars");
        } else {
            cars.forEach(car -> System.out.println(car.getId() + ". " + car.getName()));
        }
    }

    public void createCar(Integer companyId, String name) {
        Car car = new Car(0, name, companyId);
        carDAO.add(car);
        System.out.println("The car was created!");
    }

    public Car findCarByName(String name, Integer companyId) {
        return carDAO.findByName(name, companyId);
    }

}
