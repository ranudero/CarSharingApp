package carsharing.daos;

import carsharing.models.Car;
import carsharing.utils.DBClient;
import org.h2.jdbcx.JdbcDataSource;
import java.sql.PreparedStatement;

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

    private final DBClient dbClient;

    public DBCarDAO() {
        dbClient = new DBClient();
        dbClient.run(CREATE_DB);
        System.out.println("Table created");
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
}
