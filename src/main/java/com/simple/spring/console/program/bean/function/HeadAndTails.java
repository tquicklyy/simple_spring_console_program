package com.simple.spring.console.program.bean.function;

import com.simple.spring.console.program.event.exit.ExitHeadAndTailsEvent;
import com.simple.spring.console.program.util.PrinterGeneralMessagesUtils;
import com.simple.spring.console.program.util.ScannerUtils;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Random;

@Component
public class HeadAndTails extends Function {

    private static final String HEAD = "HEAD";
    private static final String TAIL = "TAIL";

    public HeadAndTails(@Value("#{'${app.head-and-tails-funcs}'.split(';')}") String[] funcs, ApplicationEventPublisher publisher) {
        super(funcs, publisher);
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    @PostConstruct
    public void postConstruct() { PrinterGeneralMessagesUtils.printRedMessage("Head and tails has been started"); }

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
                PrinterGeneralMessagesUtils.printRedMessage(String.format("Your side of the coin: %s", flipCoin()));
                break;
            case 2:
                publisher.publishEvent(new ExitHeadAndTailsEvent(this));
                return false;
        }

        return true;
    }

    private String flipCoin() {
         return new Random().nextInt(1, 3) == 1 ? HEAD : TAIL;
    }
}
