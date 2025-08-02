package com.spring.boot.console_program.bean.function;

import com.spring.boot.console_program.bean.State;
import com.spring.boot.console_program.util.PrinterGeneralMessagesUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@ShellCommandGroup("Calculator Commands")
public class Calculator extends Lifecycle {

    private double score;

    public Calculator(
            @Value("${app.funcs.calculator}") String[] funcs,
            @Value("${app.description.calculator}") String description ) {
        super(funcs, description);
    }

    @ShellMethod(key = "cr_sum", value = "Adds a number to the score")
    public void sum(@ShellOption(help = "A number which you want to add to the score") int summand) {
        if(State.getStatus() == State.Status.CALCULATOR) {
            summation(summand);
            printScore();
        } else PrinterGeneralMessagesUtils.printRedMessage("State is different from «CALCULATOR»");
    }

    @ShellMethod(key = "cr_sub", value = "Subtracts a number from the score")
    public void sub(@ShellOption(help = "A number which you want to subtracts from the score") int deductible) {
        if(State.getStatus() == State.Status.CALCULATOR) {
            subtraction(deductible);
            printScore();
        } else PrinterGeneralMessagesUtils.printRedMessage("State is different from «CALCULATOR»");
    }

    @ShellMethod(key = "cr_div", value = "Divides the score by the number")
    public void div(@ShellOption(help = "A number to divide the score by") int divider) {
        if(State.getStatus() == State.Status.CALCULATOR) {
            divide(divider);
            printScore();
        } else PrinterGeneralMessagesUtils.printRedMessage("State is different from «CALCULATOR»");
    }

    @ShellMethod(key = "cr_mul", value = "Multiply the score by the number")
    public void mul(@ShellOption(help = "A number to multiply the score by") int multiplier) {
        if(State.getStatus() == State.Status.CALCULATOR) {
            multiply(multiplier);
            printScore();
        } else PrinterGeneralMessagesUtils.printRedMessage("State is different from «CALCULATOR»");
    }

    @ShellMethod(key = "cr_score", value = "Print the score to the console")
    public void printScore() {
        if(State.getStatus() == State.Status.CALCULATOR) {
            PrinterGeneralMessagesUtils.printYellowMessage(String.format("Your current score: %.2f", score).replace(',', '.'));
        } else PrinterGeneralMessagesUtils.printRedMessage("State is different from «CALCULATOR»");
    }

    @ShellMethod(key = "cr_score_reset", value = "Reset the score to zero")
    public void resetScore() {
        if (State.getStatus() == State.Status.CALCULATOR) {
            score = 0;
            printScore();
        } else PrinterGeneralMessagesUtils.printRedMessage("State is different from «CALCULATOR»");
    }

    @ShellMethod(key = "cr_info", value = "Info about calculator")
    public void getInfo() {
        if (State.getStatus() == State.Status.CALCULATOR) {
            score = 0;
            super.getInfo();
        } else PrinterGeneralMessagesUtils.printRedMessage("State is different from «CALCULATOR»");
    }

    private void summation(double number) {
        score += number;
    }

    private void subtraction(double number) {
        score -= number;
    }

    private void divide(double number) {
        if(number != 0) score /= number;
        else PrinterGeneralMessagesUtils.printAboutIncorrectInput();
    }

    private void multiply(double number) {
        score *= number;
    }
}
