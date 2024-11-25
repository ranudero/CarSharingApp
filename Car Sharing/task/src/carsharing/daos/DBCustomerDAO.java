package carsharing.daos;

import carsharing.models.Customer;
import carsharing.utils.DBClient;

import java.sql.PreparedStatement;
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

    private final DBClient dbClient;

    public DBCustomerDAO() {
        dbClient = new DBClient();
        dbClient.run(CREATE_DB);
        System.out.println("Table created");
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

}
