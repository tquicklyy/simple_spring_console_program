package com.spring.boot.console_program.bean.function;

import com.spring.boot.console_program.util.PrinterGeneralMessagesUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Random;

@ShellComponent
public class HeadAndTails extends Function {

    private static final String HEAD = "HEAD";
    private static final String TAIL = "TAIL";

    public HeadAndTails(
            @Value("${app.funcs.head-and-tails}") String[] funcs,
            @Value("${app.description.head-and-tails}") String description) {
        super(funcs, description);
    }

    @ShellMethod(key = "hat_flip", value = "Flip the coin")
    public void flipCoin() {
        if(State.getStatus() == State.Status.HEAD_AND_TAILS) {
            PrinterGeneralMessagesUtils.printRedMessage(String.format("Your side of the coin: %s", getResultOfFlip()));
        } else PrinterGeneralMessagesUtils.printRedMessage("State is different from «HEAD_AND_TAILS»");
    }

    @Override
    @ShellMethod(key = "hat_info", value = "Info about head and tails")
    public void getInfo() {
        if(State.getStatus() == State.Status.HEAD_AND_TAILS) super.getInfo();
        else PrinterGeneralMessagesUtils.printRedMessage("State is different from «HEAD_AND_TAILS»");
    }

    private String getResultOfFlip() {
        return new Random().nextInt(1, 3) == 1 ? HEAD : TAIL;
    }
}
