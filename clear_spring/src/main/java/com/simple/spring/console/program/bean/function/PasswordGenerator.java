package com.simple.spring.console.program.bean.function;

import com.simple.spring.console.program.event.exit.ExitPasswordGeneratorEvent;
import com.simple.spring.console.program.util.PrinterGeneralMessagesUtils;
import com.simple.spring.console.program.util.ScannerUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;

@Component
public class PasswordGenerator extends Function {

    private static final List<Character> symbols = new ArrayList<>();

    static {
        for(int code = 0x21; code < 0x7E; code++) symbols.add((char)code);
    }

    public PasswordGenerator(@Value("#{'${app.funcs.password-generator}'.split(';')}") String[] funcs, ApplicationEventPublisher publisher) {
        super(funcs, publisher);
    }

    @Override
    @PostConstruct
    protected void postConstruct() {
        PrinterGeneralMessagesUtils.printRedMessage("Password Generator has been started!");
    }

    @Override
    boolean handleUserChoice(Integer option) {
        switch (option) {
            case 1:
                PrinterGeneralMessagesUtils.printRedMessage("Please enter a password length (at least 8, not more than 100)");
                PrinterGeneralMessagesUtils.printYourChoice();
                try {
                    int length = ScannerUtils.getNewIntegerWithLine();
                    PrinterGeneralMessagesUtils.skipText(1);

                    if(length < 8 || length > 100) throw new IllegalArgumentException();

                    PrinterGeneralMessagesUtils.printRedMessage(String.format("Your password: %s", generate(length)));
                } catch (InputMismatchException e) {
                    PrinterGeneralMessagesUtils.printAboutIncorrectInput();
                    ScannerUtils.getNextLine();
                    handleUserChoice(option);
                } catch (IllegalArgumentException e) {
                    PrinterGeneralMessagesUtils.printAboutIncorrectInput();
                    handleUserChoice(option);
                }
                break;
            case 2:
                publisher.publishEvent(new ExitPasswordGeneratorEvent(this));
                return false;
            default:
                PrinterGeneralMessagesUtils.printAboutIncorrectInput();
                break;
        }
        return true;
    }

    private String generate(int length) {
        StringBuilder password = new StringBuilder();
        for(int i = 0; i < length; i++) {
            password.append(symbols.get(new Random().nextInt(0, symbols.size())));
        }
        return password.toString();
    }
}
