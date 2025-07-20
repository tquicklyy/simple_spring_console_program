package com.simple.spring.console.program.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrinterMessages {
    
    private static final Logger LOG = LoggerFactory.getLogger(PrinterMessages.class);

    public static void printHelloMessage(String appName) {
        skipText();

        LOG.info(""" 
                {}{}
                Hi! This is {}
                with simple interesting functions. Try to
                use some of them and just enjoy! {}
                """, StringDesign.BOLD, StringDesign.RED_COLOR, appName, StringDesign.GREEN_COLOR);
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
            LOG.info("{}{}: {}{}",
                    StringDesign.RED_COLOR,
                    i + 1,
                    funcs[i],
                    StringDesign.GREEN_COLOR);
        }
    }

    public static void printDefaultMessageForEvent() throws InterruptedException {
        LOG.info("""
                {}The necessary data is being accessed.{}""", StringDesign.RED_COLOR, StringDesign.GREEN_COLOR);
        printDotsAndWaitMessage(5);
    }

    public static void printDotsAndWaitMessage(int countOfDots) throws InterruptedException {
        LOG.info("{} Please wait", StringDesign.RED_COLOR);
        for (int i = 0; i < countOfDots; i++) {
            System.out.print(".");
            Thread.sleep(ThreadUtils.TIME_TO_SLEEP);
        }
        System.out.println(StringDesign.GREEN_COLOR);
    }

    public static void skipText() {
        for (int i = 0; i < 1000; i++) System.out.println();
    }
}
