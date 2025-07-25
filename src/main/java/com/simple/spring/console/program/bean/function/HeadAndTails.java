package com.simple.spring.console.program.bean.function;

import com.simple.spring.console.program.event.exit.ExitHeadAndTailsEvent;
import com.simple.spring.console.program.utils.PrinterGeneralMessagesUtils;
import com.simple.spring.console.program.utils.ScannerUtils;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Random;

@Component
public class HeadAndTails implements Function {

    private static final String HEAD = "HEAD";
    private static final String TAIL = "TAIL";

    private final String[] funcs;
    private final ApplicationEventPublisher publisher;

    public HeadAndTails(@Value("#{'${app.head-and-tails-funcs}'.split(';')}") String[] funcs, ApplicationEventPublisher publisher) {
        this.funcs = funcs;
        this.publisher = publisher;
    }

    @Override
    @PostConstruct
    public void postConstruct() { PrinterGeneralMessagesUtils.printRedMessage("Head and tails has been started"); }

    @Override
    @PreDestroy
    public void preDestroy() {
        PrinterGeneralMessagesUtils.printRedMessage("Head and tails has been closed!");
    }

    @Override
    public void getOptions() {
        int option;

        while (true) {
            PrinterGeneralMessagesUtils.printOptionsWithFuncs(funcs);

            try {
                PrinterGeneralMessagesUtils.printYourChoice();
                option = ScannerUtils.getNewIntegerWithLine();
                PrinterGeneralMessagesUtils.skipText(1);

                switch (option) {
                    case 1:
                        PrinterGeneralMessagesUtils.printRedMessage(String.format("Your side of the coin: %s", flipCoin()));
                        break;
                    case 2:
                        publisher.publishEvent(ExitHeadAndTailsEvent.class);
                        return;
                }
            } catch (InputMismatchException e) {
                PrinterGeneralMessagesUtils.printAboutIncorrectInput();
                ScannerUtils.skipLine();
            }
        }
    }

    private String flipCoin() {
         return new Random().nextInt(1, 3) == 1 ? HEAD : TAIL;
    }
}
