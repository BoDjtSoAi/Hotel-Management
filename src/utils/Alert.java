package utils;

public class Alert {
    public static void printError(String message) {
        System.out.println(Color.WHITE_BOLD + Color.RED_BACKGROUND_BRIGHT + message + Color.RESET);
    }

    public static void printSuccess(String message) {
        System.out.println(Color.WHITE_BOLD + Color.GREEN_BACKGROUND_BRIGHT + message + Color.RESET);
    }
}
