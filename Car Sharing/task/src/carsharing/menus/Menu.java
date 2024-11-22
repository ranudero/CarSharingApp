package carsharing.menus;

import carsharing.models.Company;

import java.util.List;

public interface Menu {
    void show();
    void show(List<Company> companies);
    void run();
    void run(List<Company> companies);
    default void exitApplication(){
        System.exit(0);
    }
}
