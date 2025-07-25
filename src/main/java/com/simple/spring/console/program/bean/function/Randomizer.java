package com.simple.spring.console.program.bean.function;

import com.simple.spring.console.program.event.exit.ExitRandomizerEvent;
import com.simple.spring.console.program.util.PrinterGeneralMessagesUtils;
import com.simple.spring.console.program.util.ScannerUtils;
import com.simple.spring.console.program.util.StringDesign;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Random;

@Component
public class Randomizer extends Function {

    private static final Logger LOG = LoggerFactory.getLogger(Randomizer.class);

    private int counter = 1;

    public Randomizer(@Value("#{'${app.randomizer-funcs}'.split(';')}") String[] funcs, ApplicationEventPublisher publisher) {
        super(funcs, publisher);
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    @PostConstruct
    public void postConstruct() {
        PrinterGeneralMessagesUtils.printRedMessage("Randomizer has been started!");
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

    @Override
    public void startWork() {
        int option;

        while (true) {
            PrinterGeneralMessagesUtils.printOptionsWithFuncs(funcs);
            try {
                PrinterGeneralMessagesUtils.printYourChoice();
                option = ScannerUtils.getNewIntegerWithLine();
                PrinterGeneralMessagesUtils.skipText(1);

                if(!handleUserChoice(option)) {
                    return;
                }

            } catch (InputMismatchException e) {
                PrinterGeneralMessagesUtils.printAboutIncorrectInput();
                ScannerUtils.skipLine();
            }
        }
    }

    @Override
    boolean handleUserChoice(Integer option) {
        switch (option) {
            case 1:
                PrinterGeneralMessagesUtils.printRedMessage("Please enter range like: 0 100");
                PrinterGeneralMessagesUtils.printYourChoice();

                int left = ScannerUtils.getNewIntegerWithoutLine();
                int right = ScannerUtils.getNewIntegerWithLine();
                PrinterGeneralMessagesUtils.skipText(1);

                getNewNumber(1 , left, right);
                break;
            case 2:
                PrinterGeneralMessagesUtils.printRedMessage("Please enter number of numbers and range like: 1 0 100");
                PrinterGeneralMessagesUtils.printYourChoice();

                option = ScannerUtils.getNewIntegerWithoutLine();
                left = ScannerUtils.getNewIntegerWithoutLine();
                right = ScannerUtils.getNewIntegerWithLine();
                PrinterGeneralMessagesUtils.skipText(1);

                getNewNumber(option, left, right);
                break;
            case 3:
                publisher.publishEvent(new ExitRandomizerEvent(this));
                return false;
        }
        return true;
    }
}
