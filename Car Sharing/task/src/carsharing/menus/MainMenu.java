package carsharing.menus;

import carsharing.daos.DBCarDAO;
import carsharing.daos.DBCompanyDAO;
import carsharing.daos.DBCustomerDAO;
import carsharing.models.Customer;
import carsharing.services.CustomerService;
import carsharing.utils.KeyboardUtil;

import java.util.List;

public class MainMenu implements Menu {

    private final ManagerMenu managerMenu;
    private DBCompanyDAO companyDAO;
    private CompanyMenu companyMenu;
    private CustomerMenu customerMenu;
    private CustomerService customerService;
    private DBCarDAO carDao;
    private DBCustomerDAO customerDao;

    public MainMenu() {
        companyDAO = DBCompanyDAO.getInstance();
        carDao = DBCarDAO.getInstance();
        customerDao = DBCustomerDAO.getInstance();
        customerService = CustomerService.getInstance();
        companyMenu = new CompanyMenu();
        managerMenu = new ManagerMenu(companyMenu);
        customerMenu = new CustomerMenu();

    }

    @Override
    public void show() {
        System.out.println("1. Log in as a manager");
        System.out.println("2. Log in as a customer");
        System.out.println("3. Create a customer");
        System.out.println("0. Exit");
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
                    managerMenu.run();
                    break;
                case 2:
                    List<Customer> customers = showCustomerList();
                    if (!customers.isEmpty()){
                        customerMenu.run(customers);
                    }
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

    private List<Customer> showCustomerList() {
        return customerService.customerList();
    }

    @Override
    public <T> void run(List<T> items){

    }
}
