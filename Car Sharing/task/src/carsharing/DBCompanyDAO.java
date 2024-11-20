package carsharing;

import org.h2.jdbcx.JdbcDataSource;

import java.util.List;

public class DBCompanyDAO implements CompanyDAO {
    private static final String CONNECTION_URL = "jdbc:h2:file:./src/carsharing/db/carsharing";
    private static final String USER = "";
    private static final String PASS = "";
    private static final String CREATE_DB = "CREATE TABLE IF NOT EXISTS COMPANY (" +
            "ID INT PRIMARY KEY AUTO_INCREMENT, " +
            "NAME VARCHAR(255) NOT NULL UNIQUE" +
            ");";
    private static final String SELECT_ALL = "SELECT * FROM COMPANY";
    private static final String INSERT_DATA = "INSERT INTO COMPANY (NAME) VALUES (?)";

    private final DBClient dbClient;

    public DBCompanyDAO() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl(CONNECTION_URL);
        dataSource.setUser(USER);
        dataSource.setPassword(PASS);
        System.out.println("Connection to SQLite has been established.");
        System.out.println(dataSource.getUrl());
        dbClient = new DBClient(dataSource);
        dbClient.run(CREATE_DB);
        System.out.println("Table created");
    }

    @Override
    public List<Company> findAll() {
        return dbClient.selectForList(SELECT_ALL);
    }

    @Override
    public void add(Company company) {
       dbClient.run(INSERT_DATA.replace("?", "'" + company.getName() + "'"));
    }
}
