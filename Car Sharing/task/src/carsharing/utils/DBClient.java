package carsharing.utils;

import carsharing.models.Car;
import carsharing.models.Company;
import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBClient {
    private final JdbcDataSource dataSource;
    private static DBClient instance;
    private static final String CONNECTION_URL = "jdbc:h2:file:./src/carsharing/db/carsharing";

    protected DBClient() {
        this.dataSource = new JdbcDataSource();
        dataSource.setUrl(CONNECTION_URL);
    }

    public static DBClient getInstance() {
        if (instance == null) {
            instance = new DBClient();
        }
        return instance;
    }

    public void run(String str) {
        try(Connection con = dataSource.getConnection();

            Statement statement = con.createStatement()
        ){
            statement.executeUpdate(str);
        } catch (SQLException e) {;
            e.printStackTrace();
        }
    }

    public <T> List<T> selectForList(String query, RowMapper<T> rowMapper) {
        List<T> result = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                result.add(rowMapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void closeConnection() {
        try {
            if (dataSource != null) {
                dataSource.getConnection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeStatement() {
        try {
            if (dataSource != null) {
                dataSource.getConnection().createStatement().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeDatabase() {
        closeStatement();
        closeConnection();
    }

    public Company selectForCompany(String s) {
        Company company = null;
        try (Connection con = dataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSetItem = statement.executeQuery(s)
        ) {
            while (resultSetItem.next()) {
                int id = resultSetItem.getInt("ID");
                String name = resultSetItem.getString("NAME");
                company = new Company(id, name);
            }
            return company;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return company;
    }

    public Car selectForCar(String s) {
        Car car = null;
        try (Connection con = dataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSetItem = statement.executeQuery(s)
        ) {
            while (resultSetItem.next()) {
                int id = resultSetItem.getInt("ID");
                String name = resultSetItem.getString("NAME");
                int companyId = resultSetItem.getInt("COMPANY_ID");
                car = new Car(id, name, companyId);
            }
            return car;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }

    public List<Car> selectForCars(String replace) {
        List<Car> cars = new ArrayList<>();
        int id = 1;
        try (Connection con = dataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(replace)) {
            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                int companyId = resultSet.getInt("COMPANY_ID");
                cars.add(new Car(id, name, companyId));
                id += 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return cars;
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
