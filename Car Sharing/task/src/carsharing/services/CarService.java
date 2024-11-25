package carsharing.services;

import carsharing.daos.DBCarDAO;
import carsharing.models.Car;
import carsharing.models.Company;

import java.util.List;

public class CarService {
    private final DBCarDAO carDAO;

    public CarService(DBCarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public List<Car> carList(Integer companyId) {
        return carDAO.findAll(companyId);

    }

    public void createCar(Integer companyId, String name) {
        Car car = new Car(0, name, companyId);
        carDAO.add(car);
        System.out.println("The car was created!");
    }

    public String getCarName(int carId) {
        return carDAO.findById(carId).getName();
    }
}
