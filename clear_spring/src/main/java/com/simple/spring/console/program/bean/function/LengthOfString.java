package com.simple.spring.console.program.bean.function;

import com.simple.spring.console.program.event.exit.ExitLengthOfStringEvent;
import com.simple.spring.console.program.util.PrinterGeneralMessagesUtils;
import com.simple.spring.console.program.util.ScannerUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


@Component
public class LengthOfString extends Function {

    protected LengthOfString(@Value("#{'${app.funcs.length-of-string}'.split(';')}") String[] funcs, ApplicationEventPublisher publisher) {
        super(funcs, publisher);
    }

    @Override
    @PostConstruct
    protected void postConstruct() {
        PrinterGeneralMessagesUtils.printRedMessage("Length of string has been started!");
    }

    @Override
    boolean handleUserChoice(Integer option) {
        switch (option) {
            case 1:
                PrinterGeneralMessagesUtils.printRedMessage("Please enter the string");
                PrinterGeneralMessagesUtils.printYourChoice();
                String entered = ScannerUtils.getNextLine();
                PrinterGeneralMessagesUtils.printRedMessage(String.format("Length of the string: %s symbols", entered.length()));
                break;
            case 2:
                publisher.publishEvent(new ExitLengthOfStringEvent(this));
                return false;
            default:
                PrinterGeneralMessagesUtils.printAboutIncorrectInput();
                break;
        }
        return true;
    }
}
