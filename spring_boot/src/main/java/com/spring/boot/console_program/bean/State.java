package com.spring.boot.console_program.bean;

import com.spring.boot.console_program.bean.function.Randomizer;
import com.spring.boot.console_program.event.ExitRandomizerEvent;
import com.spring.boot.console_program.event.OpenRandomizerEvent;
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
    private final Randomizer randomizer;

    public State(ApplicationEventPublisher publisher, Randomizer randomizer) {
        this.publisher = publisher;
        this.randomizer = randomizer;
    }

    public enum Status  {
        NONE,
        RANDOMIZER
    }

    @ShellMethod(key = "reset", value = "Reset the state")
    public void reset() {
        publisher.publishEvent(exitEvent);
        status = Status.NONE;
        PrinterGeneralMessagesUtils.printRedMessage("The state was reset");
    }

    @ShellMethod(key = "randomizer", value = "Change state to «RANDOMIZER»")
    public void randomizer() {
        publisher.publishEvent(new OpenRandomizerEvent(this));
        status = Status.RANDOMIZER;
        exitEvent = new ExitRandomizerEvent(this);
        PrinterGeneralMessagesUtils.printRedMessage("The state has been changed to «RANDOMIZER»");
    }

    public static Status getStatus() {
        return status;
    }
}
