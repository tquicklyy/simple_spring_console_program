package com.simple.spring.console.program.bean.function;

import com.simple.spring.console.program.event.exit.ExitHeadAndTailsEvent;
import com.simple.spring.console.program.util.PrinterGeneralMessagesUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class HeadAndTails extends Function {

    private static final String HEAD = "HEAD";
    private static final String TAIL = "TAIL";

    public HeadAndTails(@Value("#{'${app.funcs.head-and-tails}'.split(';')}") String[] funcs, ApplicationEventPublisher publisher) {
        super(funcs, publisher);
    }

    @Override
    @PostConstruct
    protected void postConstruct() { PrinterGeneralMessagesUtils.printRedMessage("Head and tails has been started"); }

    @Override
    boolean handleUserChoice(Integer option) {
        switch (option) {
            case 1:
                PrinterGeneralMessagesUtils.printRedMessage(String.format("Your side of the coin: %s", flipCoin()));
                break;
            case 2:
                publisher.publishEvent(new ExitHeadAndTailsEvent(this));
                return false;
            default:
                PrinterGeneralMessagesUtils.printAboutIncorrectInput();
                break;
        }

        return true;
    }

    private String flipCoin() {
         return new Random().nextInt(1, 3) == 1 ? HEAD : TAIL;
    }
}
