package com.simple.spring.console.program.bean.function;

import com.simple.spring.console.program.event.exit.ExitCalculatorEvent;
import com.simple.spring.console.program.util.PrinterGeneralMessagesUtils;
import com.simple.spring.console.program.util.ScannerUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;

@Component
public class Calculator extends Function {

    private double score;

    public Calculator(@Value("#{'${app.funcs.calculator}'.split(';')}") String[] funcs, ApplicationEventPublisher publisher) {
        super(funcs, publisher);
    }

    @Override
    @PostConstruct
    protected void postConstruct() {
        PrinterGeneralMessagesUtils.printRedMessage("Calculator has been started!");
    }

    @Override
    public void startWork() {
        int option;

        while (true) {
            PrinterGeneralMessagesUtils.printYellowMessage(String.format("Your current score: %.2f", score).replace(',', '.'));
            PrinterGeneralMessagesUtils.printOptionsWithFuncs(funcs);

            try {
                PrinterGeneralMessagesUtils.printYourChoice();
                option = ScannerUtils.getNewIntegerWithLine();
                PrinterGeneralMessagesUtils.skipText(1);

                if(!handleUserChoice(option)) return;

            } catch (InputMismatchException e) {
                PrinterGeneralMessagesUtils.printAboutIncorrectInput();
                ScannerUtils.getNextLine();
            }
        }
    }

    @Override
    boolean handleUserChoice(Integer option) {
        double number;

        try {
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
                    if (number == 0) throw new IllegalArgumentException();
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
                    publisher.publishEvent(new ExitCalculatorEvent(this));
                    return false;
                default:
                    PrinterGeneralMessagesUtils.printAboutIncorrectInput();
                    break;
            }
        } catch (InputMismatchException e) {
            PrinterGeneralMessagesUtils.printAboutIncorrectInput();
            ScannerUtils.getNextLine();
            handleUserChoice(option);
        } catch (IllegalArgumentException e) {
            PrinterGeneralMessagesUtils.printAboutIncorrectInput();
            handleUserChoice(option);
        }
        return true;
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
