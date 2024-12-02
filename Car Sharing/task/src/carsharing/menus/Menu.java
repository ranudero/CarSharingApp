package carsharing.menus;


import java.util.List;

public interface Menu {
    void show();
    <T> void show(List<T> items);
    void run();
    <T> void run(List<T> items);
    default void exitApplication(){
        System.exit(0);
    }
}
