package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";

    // Database credentials
    static final String USER = "";
    static final String PASS = "";
    static Connection conn = null;
    static Statement stmt = null;

    public static void createDatabaseAndTable(String dbUrl) {

        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(dbUrl, USER, PASS);

            // Execute a query
            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS COMPANY " +
                    "(ID INT PRIMARY KEY AUTO_INCREMENT, " +
                    "NAME VARCHAR(255) NOT NULL)";
            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");

            // Clean-up environment
           // stmt.close();
            //conn.close();
        } catch (SQLException | ClassNotFoundException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void closeStatement() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }


}

