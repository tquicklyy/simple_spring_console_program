package com.simple.spring.console.program.bean;

import com.simple.spring.console.program.event.ExitRandomizerEvent;
import com.simple.spring.console.program.utils.PrinterGeneralMessagesUtils;
import com.simple.spring.console.program.utils.ScannerGeneralMessages;
import com.simple.spring.console.program.utils.StringDesign;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Random;

@Component
public class Randomizer {

    private static final Logger LOG = LoggerFactory.getLogger(Randomizer.class);

    private int counter = 1;
    private final String[] funcs;
    private final ApplicationEventPublisher publisher;

    public Randomizer(@Value("#{'${app.calculator-funcs}'.split(';')}") String[] funcs, ApplicationEventPublisher publisher) {
        this.funcs = funcs;
        this.publisher = publisher;
    }

    @PostConstruct
    public void postConstruct() {
        PrinterGeneralMessagesUtils.printRedMessage("Randomizer is starting");
    }

    public void getNewNumber(int limit, int left, int right) {
        try {
            PrinterGeneralMessagesUtils.printYellowMessage("Starting the enumeration of numbers");
            new Random().ints(left, right).limit(limit).forEach(number -> {
                LOG.info("{}{} number: {}{}", StringDesign.RED_COLOR, counter, number, StringDesign.GREEN_COLOR);
                counter++;
            });
            counter = 1;
            PrinterGeneralMessagesUtils.skipText(1);
        } catch (IllegalArgumentException e) {
            PrinterGeneralMessagesUtils.printAboutIncorrectInput();
        } finally {
            PrinterGeneralMessagesUtils.printYellowMessage("Ending the enumeration of numbers");
        }
    }

    public void getOptions() {
        int option;

        while (true) {
            PrinterGeneralMessagesUtils.printOptionsWithFuncs(funcs);
            PrinterGeneralMessagesUtils.printYourChoice();
            option = ScannerGeneralMessages.getNewIntegerWithLine();
            PrinterGeneralMessagesUtils.skipText(1);

            switch (option) {
                case 1:
                    PrinterGeneralMessagesUtils.printRedMessage("Please enter range like: 0 100");
                    PrinterGeneralMessagesUtils.printYourChoice();

                    int left = ScannerGeneralMessages.getNewIntegerWithoutLine();
                    int right = ScannerGeneralMessages.getNewIntegerWithLine();
                    PrinterGeneralMessagesUtils.skipText(1);

                    getNewNumber(1 , left, right);
                    break;
                case 2:
                    PrinterGeneralMessagesUtils.printRedMessage("Please enter number of numbers and range like: 1 0 100");
                    PrinterGeneralMessagesUtils.printYourChoice();

                    option = ScannerGeneralMessages.getNewIntegerWithoutLine();
                    left = ScannerGeneralMessages.getNewIntegerWithoutLine();
                    right = ScannerGeneralMessages.getNewIntegerWithLine();
                    PrinterGeneralMessagesUtils.skipText(1);

                    getNewNumber(option, left, right);
                    break;
                case 3:
                    publisher.publishEvent(new ExitRandomizerEvent(this));
                    return;

                default:
            }
        }
    }

}
