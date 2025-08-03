package com.spring.boot.console_program.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrinterGeneralMessagesUtils {
    
    private static final Logger LOG = LoggerFactory.getLogger(PrinterGeneralMessagesUtils.class);

    public static void printMessage(String text) {
        LOG.info(text);
    }

    public static void printRedMessage(String text) {
        LOG.info("{}{}{}", StringDesign.RED_COLOR, text, StringDesign.GREEN_COLOR);
        PrinterGeneralMessagesUtils.skipText(1);
    }

    public static void printYellowMessage(String text) {
        LOG.info("{}{}{}", StringDesign.YELLOW_COLOR, text, StringDesign.GREEN_COLOR);
        PrinterGeneralMessagesUtils.skipText(1);
    }

    public static void printAboutIncorrectInput() {
        printRedMessage("Incorrect input. Try again");
    }

    public static void printDefaultMessageForEvent() throws InterruptedException {
        printRedMessage("The necessary data is being accessed.");
        printDotsAndWaitMessage(5);
    }

    public static void printDotsAndWaitMessage(int countOfDots) throws InterruptedException {
        LOG.info(String.format("%s Please wait", StringDesign.RED_COLOR));
        System.out.print(StringDesign.RED_COLOR);
        for (int i = 0; i < countOfDots; i++) {
            System.out.print(".");
            Thread.sleep(ThreadUtils.TIME_TO_SLEEP);
        }
        System.out.print(StringDesign.DEFAULT_COLOR);
        skipText(1);
    }

    /**
     *
     * The method outputs the specified number of empty lines
     */
    public static void skipText(int count) {
        for (int i = 0; i < count; i++) System.out.println();
    }

    public static void printOptionsWithFuncs(String[] funcs) {
        LOG.info("Available functions:");
        for (String func : funcs) {
            LOG.info("➤ {}",
                    func);
        }
        skipText(1);
    }

}
