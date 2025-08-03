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
    private static ApplicationEvent exitEvent;

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
        PASSWORD_GENERATOR
    }

    @Override
    @ShellMethod(key = "state_info", value = "Info about state")
    public void getInfo() {
        super.getInfo();
    }

    @ShellMethod(key = "state_reset", value = "Reset the state")
    public void reset() {
        status = Status.NONE;
        PrinterGeneralMessagesUtils.printRedMessage("The state was reset");
        publisher.publishEvent(exitEvent);
    }

    @ShellMethod(key = "state_rr", value = "Change state to «RANDOMIZER»")
    public void randomizer() {
        status = Status.RANDOMIZER;
        PrinterGeneralMessagesUtils.printRedMessage("The state has been changed to «RANDOMIZER»");
        publisher.publishEvent(new OpenRandomizerEvent(this));
        exitEvent = new ExitRandomizerEvent(this);
    }

    @ShellMethod(key = "state_cr", value = "Change state to «CALCULATOR»")
    public void calculator() {
        status = Status.CALCULATOR;
        PrinterGeneralMessagesUtils.printRedMessage("The state has been changed to «CALCULATOR»");
        publisher.publishEvent(new OpenCalculatorEvent(this));
        exitEvent = new ExitCalculatorEvent(this);
        calculator.printScore();
    }

    @ShellMethod(key = "state_hat", value = "Change state to «HEAD_AND_TAILS»")
    public void headAndTails() {
        status = Status.HEAD_AND_TAILS;
        PrinterGeneralMessagesUtils.printRedMessage("The state has been changed to «HEAD_AND_TAILS»");
        publisher.publishEvent(new OpenHeadAndTailsEvent(this));
        exitEvent = new ExitHeadAndTailsEvent(this);
    }

    @ShellMethod(key = "state_rps",value = "Change state to «ROCK_PAPER_SCISSORS»")
    public void rockPaperScissors() {
        status = Status.ROCK_PAPER_SCISSORS;
        PrinterGeneralMessagesUtils.printRedMessage("The state has been changed to «ROCK_PAPER_SCISSORS»");
        publisher.publishEvent(new OpenRockPaperScissors(this));
        exitEvent = new ExitRockPaperScissors(this);
    }

    @ShellMethod(key = "state_pg", value = "Change state to «PASSWORD_GENERATOR»")
    public void passwordGenerator() {
        status = Status.PASSWORD_GENERATOR;
        PrinterGeneralMessagesUtils.printRedMessage("The state has been changed to «PASSWORD_GENERATOR»");
        publisher.publishEvent(new OpenPasswordGeneratorEvent(this));
        exitEvent = new ExitPasswordGeneratorEvent(this);
    }

    public static Status getStatus() {
        return status;
    }
}
