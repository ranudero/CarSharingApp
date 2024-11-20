package carsharing.menu;

public interface Menu {
    void show();
    void run();
    default void exitApplication(){
        System.exit(0);
    }
}
