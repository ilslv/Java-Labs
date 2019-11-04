package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    public static String Start() throws Exception {

        int menuItem;
        String[] consoleInput = new String[2];
        Scanner in = new Scanner(System.in);
        System.out.println("\n1. Factorial\n2. String reverse\n3. Substring\n0. Exit");
        try {
            menuItem = in.nextInt();
        } catch (Exception ex) {
            System.out.println("Error occurred while reading input!");
            return null;
        }

            switch (menuItem) {
                case 1:
                    in.nextLine();
                    consoleInput[0] = in.nextLine();
                    return String.valueOf(Factorial(Integer.parseInt(consoleInput[0])));
                case 2:
                    in.nextLine();
                    consoleInput[0] = in.nextLine();
                    StringBuilder buf = new StringBuilder();
                    buf.append(consoleInput[0]);
                    buf.reverse();
                    return buf.toString();
                case 3:
                    in.nextLine();
                    consoleInput[0] = in.nextLine();
                    consoleInput[1] = in.nextLine();
                    return consoleInput[0].contains(consoleInput[1]) ? "Substring found." : "No match for substring.";
                case 0:
                    Exception ex = new Exception("Finish the application");
                    throw ex;
                default:
                    return null;
            }
    }

    private static long Factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result = result * i;
        }
        return result;
    }
}
