package carsharing.menu;

import carsharing.utility.KeyboardUtil;

public class ManagerMenu implements Menu {
    public ManagerMenu() {
    }

    @Override
    public void show() {
        System.out.println("1. Company list");
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
                    // companyList();
                    break;
                case 2:
                    // createCompany();
                    break;
                case 0:
                   return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
