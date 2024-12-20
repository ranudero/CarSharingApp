package carsharing.menus;

import carsharing.daos.DBCompanyDAO;
import carsharing.models.Company;
import carsharing.services.CarService;
import carsharing.services.CompanyService;
import carsharing.utils.KeyboardUtil;

import java.util.List;

public class CompanyMenu implements Menu {
    private final DBCompanyDAO companyDAO;
    private final CompanyService companyService;
    private final CarService carService;

    public CompanyMenu(DBCompanyDAO companyDAO, CompanyService companyService, CarService carService) {
        this.companyDAO = companyDAO;
        this.companyService = companyService;
        this.carService = carService;
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
        } else {
            System.out.println("The company list is empty!");
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

    private void createCarMenu(int choice) {
        CarMenu carMenu = new CarMenu(companyDAO, choice, companyService, carService);
        carMenu.run();
    }
}
