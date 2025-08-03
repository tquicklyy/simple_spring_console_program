package com.spring.boot.console_program.bean.function;

import com.spring.boot.console_program.util.PrinterGeneralMessagesUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

@ShellComponent
@ShellCommandGroup("Time Commands")
public class Time extends Function {

    public Time(
            @Value("${app.funcs.time}") String[] funcs,
            @Value("${app.description.time}") String description) {
        super(funcs, description);
    }

    private static int timeWork = 1;

    ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor(runnable -> {
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        return thread;
    });

    @Override
    protected void postConstruct() {
        super.postConstruct();
        executor.scheduleAtFixedRate(() -> timeWork++, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    protected void preDestroy() {
        PrinterGeneralMessagesUtils.printRedMessage("Starting to notify the timer about the stop!");
        PrinterGeneralMessagesUtils.printRedMessage("The timer is notified about the stop!");
        super.preDestroy();
    }

    @ShellMethod(key = "time_work", value = "Get work time")
    public void getWorkTime() {
        printTimeOfWork();
    }

    @ShellMethod(key = "time_current", value = "Get current local time")
    public void getCurrentTime() {
        PrinterGeneralMessagesUtils.printMessage(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalTime.now()));
    }

    public void printTimeOfWork() {
        StringBuilder time = new StringBuilder();

        time.append("You've already been working for ");
        if(timeWork < 60) time.append(String.format("%s seconds", timeWork));
        else if(timeWork < 3600) time.append(String.format("%s minutes %s seconds", timeWork / 60, timeWork % 60));
        else time.append(String.format("%s hours %s minutes %s seconds", timeWork/3600, timeWork % 3600 / 60, timeWork % 60));

        PrinterGeneralMessagesUtils.printYellowMessage(time.toString());
    }
}
