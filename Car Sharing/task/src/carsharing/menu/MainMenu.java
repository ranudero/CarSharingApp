package carsharing.menu;

import carsharing.CompanyDAO;
import carsharing.DBCompanyDAO;
import carsharing.utility.KeyboardUtil;

public class MainMenu implements Menu {

    private final ManagerMenu managerMenu;
    private CompanyDAO companyDAO;

    public MainMenu() {
        managerMenu = new ManagerMenu();
        companyDAO = new DBCompanyDAO();
    }

    @Override
    public void show() {
        System.out.println("1. Log in as a manager");
        System.out.println("0. Exit");
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
                    exitApplication();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
