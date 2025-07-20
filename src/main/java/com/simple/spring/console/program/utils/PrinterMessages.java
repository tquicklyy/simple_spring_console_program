package com.simple.spring.console.program.utils;

public class PrinterMessages {

    public static void printHelloMessage(String appName) {
        System.out.printf(""" 
                %s%sHi! This is %s
                with simple interesting functions. Try to
                use some of them and just enjoy! \n
                """, StringDesign.BOLD, StringDesign.GREEN_COLOR,  appName);
    }

    public static void printNumbersOfFunctions() {
        String[] funcs = {
                "Randomizer",
                "Calculator",
                "Head and Tails",
                "Rock, paper, scissors",
                "Password generator",
                "Get length of string",
                "Exit program"
        };

        for (int i = 0; i < funcs.length; i++) {
            System.out.printf("%s%d: %s%s \n",
                    StringDesign.GREEN_COLOR,
                    i + 1,
                    StringDesign.RED_COLOR,
                    funcs[i]);
        }
    }
}
