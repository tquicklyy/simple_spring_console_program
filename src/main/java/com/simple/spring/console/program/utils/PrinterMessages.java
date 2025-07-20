package com.simple.spring.console.program.utils;

public class PrinterMessages {

    public static void printHelloMessage(String appName) {
        System.out.printf(""" 
                %s%sHi! This is %s
                with simple interesting functions. Try to
                use some of them and just enjoy! \n
                """, StringDesign.BOLD, StringDesign.GREEN_COLOR,  appName);
    }

    public static void printNumbersOfProgramFunctions() {
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
            System.out.printf("%s%d: %s%s%s \n",
                    StringDesign.GREEN_COLOR,
                    i + 1,
                    StringDesign.RED_COLOR,
                    funcs[i],
                    StringDesign.GREEN_COLOR);
        }
    }

    public static void printDefaultMessageForEvent() {
        System.out.printf("""
                %sThe necessary data is being accessed.
                Please wait.%s
                """, StringDesign.PURPLE_COLOR, StringDesign.GREEN_COLOR);
    }
}
