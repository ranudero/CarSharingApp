package carsharing.daos;

import carsharing.models.Car;
import carsharing.utils.DBClient;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBCarDAO implements CarDAO{
    private static final String CREATE_DB = "CREATE TABLE IF NOT EXISTS CAR (" +
            "ID INT PRIMARY KEY AUTO_INCREMENT, " +
            "NAME VARCHAR(255) NOT NULL UNIQUE, " +
            "COMPANY_ID INT NOT NULL, " +
            "CONSTRAINT COMPANY_ID_FK FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY(ID)" +
            ");";
    private static final String SELECT_ALL = "SELECT * FROM CAR";
    private static final String SELECT_BY_ID = "SELECT * FROM CAR WHERE COMPANY_ID = ?";
    private static final String INSERT_DATA = "INSERT INTO CAR (NAME, COMPANY_ID) VALUES (?, ?)";
    private static final String AVAILABLE_CARS = "SELECT * FROM CAR WHERE COMPANY_ID = %d AND ID NOT IN (SELECT RENTED_CAR_ID FROM CUSTOMER WHERE RENTED_CAR_ID IS NOT NULL)";
    private static final String SELECT_BY_NAME = "SELECT * FROM CAR WHERE NAME = ? AND COMPANY_ID = ?";

    private static DBClient dbClient;
    private static DBCarDAO instance;

    protected DBCarDAO() {
        dbClient = DBClient.getInstance();
        dbClient.run(CREATE_DB);
        System.out.println("Table created");
    }

    public static DBCarDAO getInstance() {
        if (instance == null) {
            instance = new DBCarDAO();
        }
        return instance;
    }

    @Override
    public List<Car> findAll() {
        return dbClient.selectForCars(SELECT_ALL);
    }

    public List<Car> findAll(Integer companyId) {
        return dbClient.selectForCars(SELECT_BY_ID.replace("?", companyId.toString()));
    }
    @Override
    public void add(Car car) {
        try {
            PreparedStatement preparedStatement = dbClient.getConnection().prepareStatement(INSERT_DATA);
            preparedStatement.setString(1, car.getName());
            preparedStatement.setInt(2, car.getCompanyId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Car findById(int carId) {
        return dbClient.selectForCar("SELECT * FROM CAR WHERE ID = " + carId);
    }

    public List<Car> findAvailableCars(Integer companyId) {
        ResultSet set = dbClient.query(AVAILABLE_CARS.formatted(companyId));
        List<Car> availableCars = new ArrayList<>();
        int id = 1;
        try{
            while(set.next()){
                Car car = new Car(id,
                        set.getString("NAME"),
                        set.getInt("COMPANY_ID")
                );
                availableCars.add(car);
                id++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableCars;

    }

    public Car findByName(String name, int companyId) {
        try (PreparedStatement preparedStatement = dbClient.getConnection().prepareStatement(SELECT_BY_NAME)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, companyId);
            ResultSet set = preparedStatement.executeQuery();
            if (set.next()) {
                return new Car(set.getInt("ID"), set.getString("NAME"), set.getInt("COMPANY_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
