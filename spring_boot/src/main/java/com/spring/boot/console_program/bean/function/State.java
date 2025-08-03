package com.spring.boot.console_program.bean.function;

import com.spring.boot.console_program.event.exit.*;
import com.spring.boot.console_program.event.open.*;
import com.spring.boot.console_program.util.PrinterGeneralMessagesUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@ShellCommandGroup("State commands")
public class State extends Function {

    private static Status status = Status.NONE;
    private ApplicationEvent emptyEvent = new ApplicationEvent(this) {
        @Override
        public Object getSource() {
            return super.getSource();
        }
    };
    private ApplicationEvent exitEvent = emptyEvent;

    private final ApplicationEventPublisher publisher;
    private final Calculator calculator;

    public State(
            @Value("${app.funcs.state}") String[] funcs,
            @Value("${app.description.state}") String description,
            ApplicationEventPublisher publisher,
            Calculator calculator) {
        super(funcs, description);
        this.publisher = publisher;
        this.calculator = calculator;
    }

    public enum Status  {
        NONE,
        RANDOMIZER,
        CALCULATOR,
        HEAD_AND_TAILS,
        ROCK_PAPER_SCISSORS,
        PASSWORD_GENERATOR,
        LENGTH_OF_STRING
    }

    @Override
    @ShellMethod(key = "state_info", value = "Info about state")
    public void getInfo() {
        super.getInfo();
    }

    @ShellMethod(key = "state_current", value = "Get current state")
    public void getState() {
        PrinterGeneralMessagesUtils.printMessage (String.format("Current state: %s", getStatus()));
    }

    @ShellMethod(key = "state_reset", value = "Reset the state")
    public void reset() {
        if(status == Status.NONE) {
            PrinterGeneralMessagesUtils.printMessage("Already reset");
            return;
        }
        status = Status.NONE;
        publisher.publishEvent(exitEvent);
        PrinterGeneralMessagesUtils.printMessage ("The state was reset");
        exitEvent = emptyEvent;
    }

    @ShellMethod(key = "state_rr", value = "Change state to «RANDOMIZER»")
    public void randomizer() {
        if(status == Status.RANDOMIZER) {
            PrinterGeneralMessagesUtils.printMessage("Already «RANDOMIZER»");
            return;
        }
        status = Status.RANDOMIZER;
        publisher.publishEvent(exitEvent);
        PrinterGeneralMessagesUtils.printMessage ("The state has been changed to «RANDOMIZER»");
        publisher.publishEvent(new OpenRandomizerEvent(this));
        exitEvent = new ExitRandomizerEvent(this);
    }

    @ShellMethod(key = "state_cr", value = "Change state to «CALCULATOR»")
    public void calculator() {
        if(status == Status.CALCULATOR) {
            PrinterGeneralMessagesUtils.printMessage("Already «CALCULATOR»");
            return;
        }
        status = Status.CALCULATOR;
        publisher.publishEvent(exitEvent);
        PrinterGeneralMessagesUtils.printMessage ("The state has been changed to «CALCULATOR»");
        publisher.publishEvent(new OpenCalculatorEvent(this));
        exitEvent = new ExitCalculatorEvent(this);
        calculator.printScore();
    }

    @ShellMethod(key = "state_hat", value = "Change state to «HEAD_AND_TAILS»")
    public void headAndTails() {
        if(status == Status.HEAD_AND_TAILS) {
            PrinterGeneralMessagesUtils.printMessage("Already «HEAD_AND_TAILS»");
            return;
        }
        status = Status.HEAD_AND_TAILS;
        publisher.publishEvent(exitEvent);
        PrinterGeneralMessagesUtils.printMessage ("The state has been changed to «HEAD_AND_TAILS»");
        publisher.publishEvent(new OpenHeadAndTailsEvent(this));
        exitEvent = new ExitHeadAndTailsEvent(this);
    }

    @ShellMethod(key = "state_rps",value = "Change state to «ROCK_PAPER_SCISSORS»")
    public void rockPaperScissors() {
        if(status == Status.ROCK_PAPER_SCISSORS) {
            PrinterGeneralMessagesUtils.printMessage("Already «ROCK_PAPER_SCISSORS»");
            return;
        }
        status = Status.ROCK_PAPER_SCISSORS;
        publisher.publishEvent(exitEvent);
        PrinterGeneralMessagesUtils.printMessage ("The state has been changed to «ROCK_PAPER_SCISSORS»");
        publisher.publishEvent(new OpenRockPaperScissors(this));
        exitEvent = new ExitRockPaperScissors(this);
    }

    @ShellMethod(key = "state_pg", value = "Change state to «PASSWORD_GENERATOR»")
    public void passwordGenerator() {
        if(status == Status.PASSWORD_GENERATOR) {
            PrinterGeneralMessagesUtils.printMessage("Already «PASSWORD_GENERATOR»");
            return;
        }
        status = Status.PASSWORD_GENERATOR;
        publisher.publishEvent(exitEvent);
        PrinterGeneralMessagesUtils.printMessage ("The state has been changed to «PASSWORD_GENERATOR»");
        publisher.publishEvent(new OpenPasswordGeneratorEvent(this));
        exitEvent = new ExitPasswordGeneratorEvent(this);
    }

    @ShellMethod(key = "state_los", value = "Change state to «LENGTH_OF_STRING»")
    public void lengthOfString() {
        if(status == Status.LENGTH_OF_STRING) {
            PrinterGeneralMessagesUtils.printMessage("Already «LENGTH_OF_STRING»");
            return;
        }
        status = Status.LENGTH_OF_STRING;
        publisher.publishEvent(exitEvent);
        PrinterGeneralMessagesUtils.printMessage ("The state has been changed to «LENGTH_OF_STRING»");
        publisher.publishEvent(new OpenLengthOfStringEvent(this));
        exitEvent = new ExitLengthOfStringEvent(this);
    }

    public static Status getStatus() {
        return status;
    }
}
