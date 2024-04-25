/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.util.Scanner;

/**
 *
 * @author e-default
 */
public class InputUtility {

    private static Scanner scanner = new Scanner(System.in);

    public static String promptInput(String promptMessage) {
        System.out.print(promptMessage);
        return scanner.nextLine();
    }

    public static String promptUpperInput(String promptMessage) {
        System.out.print(promptMessage);
        String UpperCase = scanner.nextLine();
        return UpperCase.toUpperCase();
    }

    public static int promptIntInput(String message) {
        int userInput = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.print(message);

            try {
                userInput = Integer.parseInt(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }

        return userInput;
    }

    public static double promptDoubleInput(String message) {
        double userInput = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.print(message);

            try {
                userInput = Double.parseDouble(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid double.");
            }
        }

        return userInput;
    }

    public static void displaySuccessMessage(String successMessage) {
        System.out.println("Success: " + successMessage);
    }

    public static void displayFailureMessage(String failureMessage) {
        System.out.println("Failure: " + failureMessage);
    }
}
