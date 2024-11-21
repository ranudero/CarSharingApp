package carsharing.menus;

import carsharing.models.Company;
import carsharing.daos.DBCompanyDAO;
import carsharing.utils.KeyboardUtil;

public class ManagerMenu implements Menu {
    private final DBCompanyDAO companyDAO;

    public ManagerMenu(DBCompanyDAO companyDAO) {
        this.companyDAO = companyDAO;

    }

    @Override
    public void show() {
        System.out.println("\n1. Company list");
        System.out.println("2. Create a company");
        System.out.println("0. Back");
    }

    @Override
    public void run() {
        while (true) {
            show();
            int choice = KeyboardUtil.getInputInt();
            switch (choice) {
                case 1:
                    companyList();
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

    private void companyList() {
        if (companyDAO.findAll().isEmpty()) {
            System.out.println("The company list is empty!");
            return;
        } else {
            System.out.println("\nCompany list:");
            companyDAO.findAll().forEach(company -> System.out.println(company.getId() + ". " + company.getName()));
        }
    }
}

