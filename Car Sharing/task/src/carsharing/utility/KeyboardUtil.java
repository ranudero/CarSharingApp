package carsharing.utility;

import java.util.Scanner;

public class KeyboardUtil {

    private static final Scanner KEYBOARD = new Scanner(System.in);
    private KeyboardUtil() {
    }

    public static String getInputString(String message) {
        System.out.println(message);
        return KEYBOARD.nextLine();
    }

    public static double getInputDouble(String message) {
        System.out.println(message);
        String inputString = KEYBOARD.nextLine();
        return Double.parseDouble(inputString);
    }

    public static int getInputInt(String message) {
        System.out.println(message);
        String inputString = KEYBOARD.nextLine();
        return Integer.parseInt(inputString);
    }

    public static int getInputInt() {
        String inputString = KEYBOARD.nextLine();
        return Integer.parseInt(inputString);
    }
}
