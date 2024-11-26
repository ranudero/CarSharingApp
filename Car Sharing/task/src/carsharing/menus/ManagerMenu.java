package carsharing.menus;

import carsharing.models.Company;
import carsharing.daos.DBCompanyDAO;
import carsharing.services.CompanyService;
import carsharing.utils.KeyboardUtil;

import java.util.List;

public class ManagerMenu implements Menu {
    private final DBCompanyDAO companyDAO;
    private CompanyMenu companyMenu;
    private CompanyService companyService;

    public ManagerMenu(DBCompanyDAO companyDAO, CompanyMenu companyMenu, CompanyService companyService) {
        this.companyDAO = companyDAO;
        this.companyMenu = companyMenu;
        this.companyService = companyService;
    }

    @Override
    public void show() {
        System.out.println("\n1. Company list");
        System.out.println("2. Create a company");
        System.out.println("0. Back");
    }

    @Override
    public <T> void show(List<T> items){
    }

    @Override
    public void run() {
        while (true) {
            show();
            int choice = KeyboardUtil.getInputInt();
            switch (choice) {
                case 1:
                    companyMenu.run(companyListMenu());
                    break;
                case 2:
                    createCompanyMenu();
                    break;
                case 0:
                   return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void createCompanyMenu() {
        String name = KeyboardUtil.getInputString("Enter the company name:");
        companyService.createCompany(name);
    }

    private List<Company> companyListMenu() {
       List<Company> companies = companyService.companyList();
       if (companies.isEmpty()) {
           System.out.println("The company list is empty!");
           return null;
         } else {

           return companies;
       }
    }

    @Override
    public <T> void run(List<T> items){

    }
}

