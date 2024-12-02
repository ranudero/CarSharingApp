package carsharing.daos;

import carsharing.models.Company;
import carsharing.utils.DBClient;

import java.util.List;

public class DBCompanyDAO implements CompanyDAO {
    private static final String CREATE_DB = "CREATE TABLE IF NOT EXISTS COMPANY (" +
            "ID INT PRIMARY KEY AUTO_INCREMENT, " +
            "NAME VARCHAR(255) NOT NULL UNIQUE" +
            ");";
    private static final String SELECT_ALL = "SELECT * FROM COMPANY";
    private static final String INSERT_DATA = "INSERT INTO COMPANY (NAME) VALUES (?)";

    private static DBClient dbClient;
    private static DBCompanyDAO instance;

    protected DBCompanyDAO() {
        dbClient = DBClient.getInstance();
        dbClient.run(CREATE_DB);
        System.out.println("Table created");
    }

    public static DBCompanyDAO getInstance() {
        if (instance == null) {
            instance = new DBCompanyDAO();
        }
        return instance;
    }

    @Override
    public List<Company> findAll() {
        return dbClient.selectForList(SELECT_ALL, rs -> new Company(rs.getInt("ID"), rs.getString("NAME")));
    }

    @Override
    public void add(Company company) {
       dbClient.run(INSERT_DATA.replace("?", "'" + company.getName() + "'"));
    }

    public void closeDatabase() {
        dbClient.closeDatabase();
    }

    public Company findById(int companyId) {
        return dbClient.selectForCompany("SELECT * FROM COMPANY WHERE ID = " + companyId);
    }
}
