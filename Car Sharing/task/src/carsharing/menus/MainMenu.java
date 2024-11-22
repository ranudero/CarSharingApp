package carsharing.menus;

import carsharing.daos.DBCompanyDAO;
import carsharing.models.Company;
import carsharing.utils.KeyboardUtil;

import java.util.List;

public class MainMenu implements Menu {

    private final ManagerMenu managerMenu;
    private DBCompanyDAO companyDAO;
    private CompanyMenu companyMenu;

    public MainMenu() {
        companyDAO = new DBCompanyDAO();
        companyMenu = new CompanyMenu(companyDAO);
        managerMenu = new ManagerMenu(companyDAO, companyMenu);
    }

    @Override
    public void show() {
        System.out.println("1. Log in as a manager");
        System.out.println("0. Exit");
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
                    managerMenu.run();
                    break;
                case 0:
                    companyDAO.closeDatabase();
                    exitApplication();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    @Override
    public void run(List<Company> companies){

    }
}
