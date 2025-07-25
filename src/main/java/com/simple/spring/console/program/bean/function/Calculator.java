package com.simple.spring.console.program.bean.function;

import com.simple.spring.console.program.event.exit.ExitProgramEvent;
import com.simple.spring.console.program.utils.PrinterGeneralMessagesUtils;
import com.simple.spring.console.program.utils.ScannerUtils;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;

@Component
public class Calculator implements Function {

    private double score = 0;

    private final String[] funcs;
    private final ApplicationEventPublisher publisher;

    public Calculator(@Value("#{'${app.calculator-funcs}'.split(';')}") String[] funcs, ApplicationEventPublisher publisher) {
        this.funcs = funcs;
        this.publisher = publisher;
    }


    @Override
    @PostConstruct
    public void postConstruct() {
        PrinterGeneralMessagesUtils.printRedMessage("Calculator has been started!");
    }

    @Override
    @PreDestroy
    public void preDestroy() {
        PrinterGeneralMessagesUtils.printRedMessage("Calculator has been closed!");
    }

    @Override
    public void getOptions() {
        int option;
        double number = 0;

        while (true) {
            PrinterGeneralMessagesUtils.printYellowMessage(String.format("Your current score: %.2f", score).replace(',', '.'));
            PrinterGeneralMessagesUtils.printOptionsWithFuncs(funcs);

            try {
                PrinterGeneralMessagesUtils.printYourChoice();
                option = ScannerUtils.getNewIntegerWithLine();
                PrinterGeneralMessagesUtils.skipText(1);

                switch (option) {
                    case 1:
                        number = printMessageForEnterNumber();
                        sum(number);
                        break;
                    case 2:
                        number = printMessageForEnterNumber();
                        sub(number);
                        break;
                    case 3:
                        number = printMessageForEnterNumber();
                        divide(number);
                        break;
                    case 4:
                        number = printMessageForEnterNumber();
                        multiply(number);
                        break;
                    case 5:
                        PrinterGeneralMessagesUtils.printRedMessage("Score has been reset");
                        score = 0;
                        break;
                    case 6:
                        publisher.publishEvent(ExitProgramEvent.class);
                        return;

                    default:
                        PrinterGeneralMessagesUtils.printAboutIncorrectInput();
                }

            } catch (InputMismatchException e) {
                PrinterGeneralMessagesUtils.printAboutIncorrectInput();
                ScannerUtils.skipLine();
            }
        }
    }

    private double printMessageForEnterNumber() {
        PrinterGeneralMessagesUtils.printRedMessage("Please enter number");
        PrinterGeneralMessagesUtils.printYourChoice();
        return ScannerUtils.getNewIntegerWithLine();
    }

    private void sum(double number) {
        score += number;
    }

    private void sub(double number) {
        score -= number;
    }

    private void divide(double number) {
        score /= number;
    }

    private void multiply(double number) {
        score *= number;
    }
}
