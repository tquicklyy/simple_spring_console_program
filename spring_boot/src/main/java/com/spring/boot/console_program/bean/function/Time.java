package com.spring.boot.console_program.bean.function;

import com.spring.boot.console_program.util.PrinterGeneralMessagesUtils;
import jakarta.annotation.Nonnull;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ShellComponent
@ShellCommandGroup("Time Commands")
public class Time implements BeanNameAware {

    private String beanName;
    CountDownLatch latch = new CountDownLatch(1);

    ExecutorService executor = Executors.newSingleThreadExecutor(runnable -> {
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        return thread;
    });

    private static int timeWork = 1;

    @ShellMethod(key = "time_work", value = "Get work time")
    public void getWorkTime() {
        printTimeOfWork();
    }

    @ShellMethod(key = "time_current", value = "Get current local time")
    public void getCurrentTime() {
        PrinterGeneralMessagesUtils.printMessage(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalTime.now()));
    }

    @Override
    public void setBeanName(@Nonnull String name) {
        this.beanName = name;
    }

    @PostConstruct
    private void startTimeWork() {
        PrinterGeneralMessagesUtils.printRedMessage("Work time has been started!");
        executor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    timeWork++;
                } catch (InterruptedException e) {
                    PrinterGeneralMessagesUtils.printRedMessage("The timer is notified about the stop!");
                    latch.countDown();
                    break;
                }
            }
        });
    }

    @PreDestroy
    private void stopWorkTime() throws InterruptedException {
        PrinterGeneralMessagesUtils.printRedMessage("Starting to notify the timer about the stop!");
        executor.shutdownNow();
        latch.await();
        PrinterGeneralMessagesUtils.printRedMessage(String.format("Bean with name «%s» has been closed!", beanName));
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
