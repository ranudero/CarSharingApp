package carsharing;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    // write your code here
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";

    //  Database credentials
    static final String USER = "";
    static final String PASS = "";

    public static void main(String[] args) {

        String dbName = "default";
        if (args.length > 1 && args[0].equals("-databaseFileName")) {
            dbName = args[1];
        }
        String dbUrl = "jdbc:h2:./src/carsharing/db/" + dbName;

        DatabaseManager.createDatabaseAndTable(dbUrl);
        DatabaseManager.closeStatement();
        DatabaseManager.closeConnection();
    }
}