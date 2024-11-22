package carsharing.menus;

import carsharing.daos.DBCompanyDAO;
import carsharing.models.Company;
import carsharing.utils.KeyboardUtil;

import java.util.List;

public class CompanyMenu implements Menu {
    private final DBCompanyDAO companyDAO;

    public CompanyMenu(DBCompanyDAO companyDAO) {
        this.companyDAO = companyDAO;
    }

    @Override
    public void run() {

    }

    @Override
    public void show() {

    }

    @Override
    public void show(List<Company> companies){
        System.out.println("\nChoose a company:");
        companies.forEach(company -> System.out.println(company.getId() + ". " + company.getName()));
    }

    @Override
    public void run(List<Company> companies) {
        while (true) {
            show(companies);
            int choice = KeyboardUtil.getInputInt();
            if (choice == 0) {
                return;
            } else {
                System.out.println("Not implemented");
            }
        }
    }
}
