package carsharing.services;

import carsharing.daos.DBCompanyDAO;
import carsharing.models.Company;

import java.util.List;

public class CompanyService {
    private final DBCompanyDAO companyDAO;
    private static CompanyService instance;

    protected CompanyService() {
        companyDAO = DBCompanyDAO.getInstance();
    }

    public static CompanyService getInstance(){
        if (instance == null) {
            instance = new CompanyService();
        }
        return instance;
    }

    public List<Company> companyList() {
        return companyDAO.findAll();

    }

    public void createCompany(String name) {
        Company company = new Company(0, name);
        companyDAO.add(company);
        System.out.println("The company was created!");
    }

    public String getCompanyName(int companyId) {
        return companyDAO.findById(companyId).getName();
    }
}
