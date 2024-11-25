package carsharing.daos;

import carsharing.models.Car;

import java.util.List;

public interface CarDAO {
    List<Car> findAll();
    void add(Car car);
}
