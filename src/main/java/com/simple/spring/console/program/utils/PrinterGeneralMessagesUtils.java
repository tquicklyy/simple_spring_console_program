package com.simple.spring.console.program.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrinterGeneralMessagesUtils {
    
    private static final Logger LOG = LoggerFactory.getLogger(PrinterGeneralMessagesUtils.class);

    public static void printRedMessage(String text) {
        LOG.info("{}{}{}", StringDesign.RED_COLOR, text, StringDesign.GREEN_COLOR);
        PrinterGeneralMessagesUtils.skipText(1);
    }

    public static void printYellowMessage(String text) {
        LOG.info("{}{}{}", StringDesign.YELLOW_COLOR, text, StringDesign.GREEN_COLOR);
        PrinterGeneralMessagesUtils.skipText(1);
    }

    public static void printYourChoice() {
        System.out.printf("%sYour choice: %s", StringDesign.PURPLE_COLOR, StringDesign.GREEN_COLOR);
    }

    public static void printAboutIncorrectInput() {
        printRedMessage("Incorrect input. Try again");
    }

    public static void printDefaultMessageForEvent() throws InterruptedException {
        LOG.info("""
                {}The necessary data is being accessed.{}""", StringDesign.RED_COLOR, StringDesign.GREEN_COLOR);
        printDotsAndWaitMessage(5);
    }

    public static void printDotsAndWaitMessage(int countOfDots) throws InterruptedException {
        LOG.info("{}Please wait", StringDesign.RED_COLOR);
        for (int i = 0; i < countOfDots; i++) {
            System.out.print(".");
            Thread.sleep(ThreadUtils.TIME_TO_SLEEP);
        }
        System.out.println(StringDesign.GREEN_COLOR);
    }

    public static void skipText(int count) {
        for (int i = 0; i < count; i++) System.out.println();
    }

    public static void printOptionsWithFuncs(String[] funcs) {
        for (int i = 0; i < funcs.length; i++) {
            LOG.info("{}{}: {}{}",
                    StringDesign.RED_COLOR,
                    i + 1,
                    funcs[i],
                    StringDesign.GREEN_COLOR);
        }
        skipText(1);
    }

}
