package carsharing.menus;

import carsharing.models.Company;
import carsharing.daos.DBCompanyDAO;
import carsharing.utils.KeyboardUtil;

import java.util.ArrayList;
import java.util.List;

public class ManagerMenu implements Menu {
    private final DBCompanyDAO companyDAO;
    private CompanyMenu companyMenu;

    public ManagerMenu(DBCompanyDAO companyDAO, CompanyMenu companyMenu) {
        this.companyDAO = companyDAO;
        this.companyMenu = companyMenu;
    }

    @Override
    public void show() {
        System.out.println("\n1. Company list");
        System.out.println("2. Create a company");
        System.out.println("0. Back");
    }

    @Override
    public void show(List<Company> companies){
    }

    @Override
    public void run() {
        while (true) {
            show();
            int choice = KeyboardUtil.getInputInt();
            switch (choice) {
                case 1:
                    companyMenu.run(companyList());
                    break;
                case 2:
                    createCompany();
                    break;
                case 0:
                   return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void createCompany() {
        String name = KeyboardUtil.getInputString("Enter the company name:");
        companyDAO.add(new Company(0, name));
        System.out.println("The company was created!");
    }

    private List<Company> companyList() {
        if (companyDAO.findAll().isEmpty()) {
            System.out.println("The company list is empty!");
            return null;
        } else {
            List<Company> companies = new ArrayList<>();
            companyDAO.findAll().forEach(company -> companies.add(company));
            return companies;
        }
    }

    @Override
    public void run(List<Company> companies){

    }
}

