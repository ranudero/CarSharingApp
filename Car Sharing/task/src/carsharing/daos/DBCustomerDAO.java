package carsharing.daos;

import carsharing.models.Car;
import carsharing.models.Customer;
import carsharing.utils.DBClient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


public class DBCustomerDAO {
    private static final String CREATE_DB = "CREATE TABLE IF NOT EXISTS CUSTOMER (" +
            "ID INT PRIMARY KEY AUTO_INCREMENT, " +
            "NAME VARCHAR(255) NOT NULL UNIQUE, " +
            "RENTED_CAR_ID INT DEFAULT NULL," +
            "CONSTRAINT RENTED_CAR_ID_FK FOREIGN KEY (RENTED_CAR_ID) REFERENCES CAR(ID)" +
            ");";
    private static final String SELECT_BY_ID = "SELECT * FROM CUSTOMER WHERE RENTED_CAR_ID = ?";
    private static final String INSERT_DATA = "INSERT INTO CUSTOMER (NAME) VALUES (?)";
    private static final String SELECT_ALL = "SELECT * FROM CUSTOMER";
    private static final String SELECT_CUSTOMER = "SELECT * FROM CUSTOMER WHERE ID = %d";
    private static final String UPDATE_RENTED_CAR = "UPDATE CUSTOMER SET RENTED_CAR_ID = ? WHERE ID = ?";
    private static final String RETURN_CAR = "UPDATE CUSTOMER SET RENTED_CAR_ID = NULL WHERE ID = ?";

    private static DBClient dbClient;
    private static DBCustomerDAO instance;

    protected DBCustomerDAO() {
        dbClient = DBClient.getInstance();
        dbClient.run(CREATE_DB);
        System.out.println("Table created");
    }

    public static DBCustomerDAO getInstance() {
        if (instance == null) {
            instance = new DBCustomerDAO();
        }
        return instance;
    }

    public void add(Customer customer) {
        try {
            PreparedStatement preparedStatement = dbClient.getConnection().prepareStatement(INSERT_DATA);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Customer> findAll() {
        return dbClient.selectForList(SELECT_ALL, rs -> new Customer(rs.getInt("ID"), rs.getString("NAME"), rs.getInt("RENTED_CAR_ID")));
    }

    public int findRentedCar(int customerId) {
        try {
            ResultSet rs = dbClient.query(SELECT_CUSTOMER.formatted(customerId));
            if (rs.next()) {
                return rs.getInt("RENTED_CAR_ID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void updateRentedCar(int customerId, int carId) {
        try (PreparedStatement preparedStatement = dbClient.getConnection().prepareStatement(UPDATE_RENTED_CAR)) {
            preparedStatement.setInt(1, carId);
            preparedStatement.setInt(2, customerId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void returnCar(int customerId) {
        try (PreparedStatement preparedStatement = dbClient.getConnection().prepareStatement(RETURN_CAR)) {
            preparedStatement.setInt(1, customerId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
