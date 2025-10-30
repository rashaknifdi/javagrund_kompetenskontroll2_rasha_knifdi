package com.rasha.util;

import java.util.Scanner;

/**
 * Validerar användarens inmatning, t.ex. namn, ålder, bransch, erfarenhetsår och heltal
 * Håller valideringslogik separat från affärslogik – följer SRP.
 */

public class InputValidator {

    public static boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            LoggerUtil.logWarn("Tomt namn angavs.");
            System.out.println("Namnet får inte vara tomt.");
            return false;
        }
        return true;
    }

    public static boolean isValidAge(int age) {
        if (age < 0 || age > 120) {
            LoggerUtil.logWarn("Ogiltig ålder angavs: " + age);
            System.out.println(" Ålder måste vara mellan 0 och 120.");
            return false;
        }
        return true;
    }
    public static boolean isValidBranch(String branch) {
        if (branch == null || branch.trim().isEmpty()) {
            LoggerUtil.logWarn("Tom eller ogiltig bransch angavs.");
            System.out.println("Branschen får inte vara tom. Ange en giltig bransch.");
            return false;
        }
        return true;
    }

    public static boolean isValidExperience(int years) {
        if (years < 0 || years > 100) {
            LoggerUtil.logWarn("Ogiltigt antal erfarenhetsår: " + years);
            System.out.println("Erfarenhet måste vara mellan 0 och 100 år.");
            return false;
        }
        return true;
    }

    public static int readInt(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                LoggerUtil.logWarn("Ogiltigt heltal: " + input);
                System.out.print("Ogiltigt värde. Ange ett heltal: ");
            }
        }
    }

}
