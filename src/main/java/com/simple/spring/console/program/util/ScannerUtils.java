package com.simple.spring.console.program.util;

import java.util.Scanner;

public class ScannerUtils {

    public static Scanner SCANNER = new Scanner(System.in);

    public static int getNewIntegerWithLine() {
        int option = SCANNER.nextInt();
        SCANNER.nextLine();
        return option;
    }

    public static int getNewIntegerWithoutLine() {
        return SCANNER.nextInt();
    }

    public static String getOneWord() {
        return SCANNER.nextLine().replace(" ", "");
    }

    public static String getNextLine() {
        return SCANNER.nextLine();
    }

}
