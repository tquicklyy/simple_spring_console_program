package com.spring.boot.console_program.bean.function;

import com.spring.boot.console_program.util.PrinterGeneralMessagesUtils;
import jakarta.annotation.Nonnull;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class WorkTime implements BeanNameAware {

    private String beanName;
    CountDownLatch latch = new CountDownLatch(1);

    ExecutorService executor = Executors.newSingleThreadExecutor(runnable -> {
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        return thread;
    });

    private static int timeWork = 1;

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
