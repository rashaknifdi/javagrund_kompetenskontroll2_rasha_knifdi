package com.rasha.util;

import java.util.Scanner;

/**
 * Validerar användarens inmatning, t.ex. heltal och namn.
 * Håller valideringslogik separat från affärslogik – följer SRP.
 */

public class InputValidator {

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

    public static boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            LoggerUtil.logWarn("Tomt namn angavs.");
            System.out.println("Namnet får inte vara tomt.");
            return false;
        }
        return true;
    }
}
