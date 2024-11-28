package carsharing.utils;

import carsharing.models.Car;
import carsharing.models.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBClient {
    private final Connection con    ;
    private static DBClient instance;
    private static final String CONNECTION_URL = "jdbc:h2:file:./src/carsharing/db/carsharing";

    protected DBClient() throws SQLException {

        con = DriverManager.getConnection(CONNECTION_URL);
    }

    public static DBClient getInstance() {
        try {
            if (instance == null) {
                instance = new DBClient();
            }
            return instance;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void run(String str) {
        try {
            con.prepareStatement(str).execute();
        } catch (SQLException e) {;
            e.printStackTrace();
        }
    }

    public void state(String sql) {
        try {
            con.prepareStatement(sql).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet query(String sql) {
        try {
            return con.prepareStatement(sql).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public <T> List<T> selectForList(String query, RowMapper<T> rowMapper) {
        List<T> result = new ArrayList<>();
        try {
             ResultSet resultSet = con.createStatement().executeQuery(query);
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
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeStatement() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeDatabase() {
        closeConnection();
    }

    public Company selectForCompany(String s) {
        Company company = null;
        try{ ResultSet resultSetItem = con.createStatement().executeQuery(s);
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
        try { ResultSet resultSetItem = con.createStatement().executeQuery(s);
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
        try{
            ResultSet resultSet = con.createStatement().executeQuery(replace);
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
            return con;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }
}
