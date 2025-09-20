package util;

import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
    private static Scanner scanner = new Scanner(System.in);

    // Helper method to get user input
    public static String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // Helper method to get integer input
    public static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    // Helper method to validate email format
    public static boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    // Helper method to validate phone number
    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("\\d{10,15}");
    }

    // Helper method to generate unique ID
    public static String generateId(String prefix, int currentCount) {
        return prefix + String.format("%03d", currentCount + 1);
    }

    // Helper method to print separator line
    public static void printSeparator() {
        System.out.println("=".repeat(50));
    }

    // Helper method to print header
    public static void printHeader(String title) {
        printSeparator();
        System.out.println("  " + title);
        printSeparator();
    }

    // Helper method to clear screen (cross-platform)
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // If clearing fails, just print some newlines
            System.out.println("\n".repeat(20));
        }
    }

    // Helper method to pause execution
    public static void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    // Helper method to print list with numbering
    public static void printNumberedList(ArrayList<?> list) {
        if (list.isEmpty()) {
            System.out.println("No items found.");
            return;
        }
        
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

    // Helper method to confirm action
    public static boolean confirmAction(String message) {
        String input = getInput(message + " (y/n): ").toLowerCase();
        return input.equals("y") || input.equals("yes");
    }
}
