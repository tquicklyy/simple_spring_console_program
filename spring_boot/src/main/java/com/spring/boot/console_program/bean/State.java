package com.spring.boot.console_program.bean;

import com.spring.boot.console_program.bean.function.Calculator;
import com.spring.boot.console_program.event.exit.ExitCalculatorEvent;
import com.spring.boot.console_program.event.exit.ExitHeadAndTailsEvent;
import com.spring.boot.console_program.event.exit.ExitRandomizerEvent;
import com.spring.boot.console_program.event.open.OpenCalculatorEvent;
import com.spring.boot.console_program.event.open.OpenHeadAndTailsEvent;
import com.spring.boot.console_program.event.open.OpenRandomizerEvent;
import com.spring.boot.console_program.util.PrinterGeneralMessagesUtils;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@ShellCommandGroup("State commands")
public class State {

    private static Status status = Status.NONE;
    private static ApplicationEvent exitEvent;

    private final ApplicationEventPublisher publisher;
    private final Calculator calculator;

    public State(ApplicationEventPublisher publisher, Calculator calculator) {
        this.publisher = publisher;
        this.calculator = calculator;
    }

    public enum Status  {
        NONE,
        RANDOMIZER,
        CALCULATOR,
        HEAD_AND_TAILS
    }

    @ShellMethod(key = "reset", value = "Reset the state")
    public void reset() {
        status = Status.NONE;
        PrinterGeneralMessagesUtils.printRedMessage("The state was reset");
        publisher.publishEvent(exitEvent);
    }

    @ShellMethod(key = "randomizer", value = "Change state to «RANDOMIZER»")
    public void randomizer() {
        status = Status.RANDOMIZER;
        PrinterGeneralMessagesUtils.printRedMessage("The state has been changed to «RANDOMIZER»");
        publisher.publishEvent(new OpenRandomizerEvent(this));
        exitEvent = new ExitRandomizerEvent(this);
    }

    @ShellMethod(key = "calculator", value = "Change state to «CALCULATOR»")
    public void calculator() {
        status = Status.CALCULATOR;
        PrinterGeneralMessagesUtils.printRedMessage("The state has been changed to «CALCULATOR»");
        publisher.publishEvent(new OpenCalculatorEvent(this));
        exitEvent = new ExitCalculatorEvent(this);
        calculator.printScore();
    }

    @ShellMethod(key = "head_and_tails", value = "Change state to «HEAD_AND_TAILS»")
    public void headAndTails() {
        status = Status.HEAD_AND_TAILS;
        PrinterGeneralMessagesUtils.printRedMessage("The state has been changed to «HEAD_AND_TAILS»");
        publisher.publishEvent(new OpenHeadAndTailsEvent(this));
        exitEvent = new ExitHeadAndTailsEvent(this);
    }

    public static Status getStatus() {
        return status;
    }
}
