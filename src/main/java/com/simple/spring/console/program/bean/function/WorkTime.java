package com.simple.spring.console.program.bean.function;

import com.simple.spring.console.program.util.PrinterGeneralMessagesUtils;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class WorkTime implements BeanNameAware {

    private String beanName;

    ExecutorService executor = Executors.newSingleThreadExecutor();

    private static int timeWork = 1;

    @Override
    public void setBeanName(@NonNull String name) {
        this.beanName = name;
    }

    @PostConstruct
    private void startTimeWork() {
        executor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    timeWork++;
                } catch (InterruptedException e) {
                    PrinterGeneralMessagesUtils.printRedMessage("The timer is notified about the stop!");
                    break;
                }
            }
        });
    }

    @PreDestroy
    private void stopWorkTime() {
        PrinterGeneralMessagesUtils.printRedMessage("Starting to notify the timer about the stop!");
        executor.shutdownNow();
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
