package com.spring.boot.console_program.bean.function;

import com.spring.boot.console_program.bean.State;
import com.spring.boot.console_program.util.PrinterGeneralMessagesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Random;

@ShellComponent
@ShellCommandGroup("Randomizer commands")
public class Randomizer extends Lifecycle {

    private int counter = 1;
    private static final Logger LOG = LoggerFactory.getLogger(Randomizer.class);

    public Randomizer(@Value("${app.funcs.randomizer}") String[] funcs, @Value("${app.description.randomizer}") String description) {
        super(funcs, description);
    }

    @Override
    protected void postConstruct() {
        PrinterGeneralMessagesUtils.printRedMessage("Randomizer has been started!");
    }

    @ShellMethod(key = "numbers", value = "Generate random numbers")
    public void getNumbers(
            @ShellOption(defaultValue = "0", help = "The left boundary of the number generation range") int left,
            @ShellOption(defaultValue = "100", help = "The right boundary of the number generation range") int right,
            @ShellOption(defaultValue = "1", help = "The number of numbers to generate (at least 1 and no more than 100)") int limit) {
        if(State.getStatus() == State.Status.RANDOMIZER) {
            getNewNumber(limit, left, right);
        } else PrinterGeneralMessagesUtils.printRedMessage("State is different from «RANDOMIZER»");
    }

    @ShellMethod(key = "randomizer_info", value = "Info about randomizer")
    public void getInfo() {
        if(State.getStatus() == State.Status.RANDOMIZER) {
            PrinterGeneralMessagesUtils.printMessage(description);
            PrinterGeneralMessagesUtils.printOptionsWithFuncs(funcs);
        } else PrinterGeneralMessagesUtils.printRedMessage("State is different from «RANDOMIZER»");
    }

    private void getNewNumber(int limit, int left, int right) {
        try {
            PrinterGeneralMessagesUtils.printYellowMessage("Starting the enumeration of numbers");
            new Random().ints(left, right).limit(limit).forEach(number -> {
                LOG.info("{} number: {}", counter, number);
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

}
