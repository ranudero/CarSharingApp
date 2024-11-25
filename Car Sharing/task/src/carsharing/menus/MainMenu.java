package carsharing.menus;

import carsharing.daos.DBCarDAO;
import carsharing.daos.DBCompanyDAO;
import carsharing.daos.DBCustomerDAO;
import carsharing.models.Company;
import carsharing.services.CarService;
import carsharing.services.CompanyService;
import carsharing.services.CustomerService;
import carsharing.utils.KeyboardUtil;

import java.util.List;

public class MainMenu implements Menu {

    private final ManagerMenu managerMenu;
    private DBCompanyDAO companyDAO;
    private DBCarDAO carDAO;
    private DBCustomerDAO customerDao;
    private CompanyMenu companyMenu;
    private CompanyService companyService;
    private CarService carService;
    private CustomerService customerService;

    public MainMenu() {
        companyDAO = new DBCompanyDAO();
        carDAO = new DBCarDAO();
        customerDao = new DBCustomerDAO();
        companyService = new CompanyService(companyDAO);
        carService = new CarService(carDAO);
        customerService = new CustomerService(customerDao);
        companyMenu = new CompanyMenu(companyDAO, companyService, carService);
        managerMenu = new ManagerMenu(companyDAO, companyMenu, companyService);
    }

    @Override
    public void show() {
        System.out.println("1. Log in as a manager");
        System.out.println("2. Log in as a customer");
        System.out.println("3. Create a customer");
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
                case 2:
                    showCustomerList();
                    break;
                case 3:
                    createCustomer();
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

    private void createCustomer() {
        String name = KeyboardUtil.getInputString("Enter the customer name:");
        customerService.createCustomer(name);
    }

    private void showCustomerList() {
        customerService.customerList();
    }

    @Override
    public void run(List<Company> companies){

    }
}
