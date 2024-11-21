package carsharing;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;


public class DBClient {
    private final DataSource dataSource;
//    private final String user;
//    private final String pass;

    public DBClient(DataSource dataSource) {
        this.dataSource = dataSource;
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

    public List<Company> selectForList(String query) {
        List<Company> companies = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSetItem = statement.executeQuery(query)
        ) {
            while (resultSetItem.next()) {
                int id = resultSetItem.getInt("ID");
                String name = resultSetItem.getString("NAME");
                Company company = new Company(id, name);
                companies.add(company);
            }
            return companies;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
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
}
