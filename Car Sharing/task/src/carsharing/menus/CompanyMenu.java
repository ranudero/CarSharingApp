package carsharing.menus;

import carsharing.daos.DBCompanyDAO;
import carsharing.models.Company;
import carsharing.services.CarService;
import carsharing.services.CompanyService;
import carsharing.utils.KeyboardUtil;

import java.util.List;

public class CompanyMenu implements Menu {
    //private final DBCompanyDAO companyDAO;
    //private final CompanyService companyService;
    //private final CarService carService;
    private static CompanyMenu instance;

    protected CompanyMenu() {
        //companyDAO = DBCompanyDAO.getInstance();
        //companyService = CompanyService.getInstance();
        //carService = CarService.getInstance();
    }

    public static CompanyMenu getInstance() {
        if (instance == null) {
            instance = new CompanyMenu();
        }
        return instance;
    }

    @Override
    public void run() {

    }

    @Override
    public void show() {

    }

    @Override
    public <T> void show(List<T> items){
        if(items != null && !items.isEmpty() && items.get(0) instanceof Company) {
            List<Company> companies = (List<Company>) items;

            System.out.println("\nChoose a company:");
            companies.forEach(company -> System.out.println(company.getId() + ". " + company.getName()));
            System.out.println("0. Back");
        }
    }

    @Override
    public <T> void run(List<T> items) {
        List<Company> companies = (List<Company>) items;
        while (companies != null) {
            show(companies);
            int choice = KeyboardUtil.getInputInt();
            if (choice == 0) {
                return;
            } else if (choice > 0 && choice <= companies.size()) {
               createCarMenu(choice);
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }

    public void createCarMenu(int choice) {
        CarMenu carMenu = new CarMenu(choice);
        carMenu.run();
    }
}
