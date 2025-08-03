package com.spring.boot.console_program.bean.function;

import com.spring.boot.console_program.util.PrinterGeneralMessagesUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ShellComponent
@ShellCommandGroup("Password generator commands")
public class PasswordGenerator extends Function {

    private static final List<Character> symbols = new ArrayList<>();

    static {
        for(int code = 0x21; code < 0x7E; code++) symbols.add((char)code);
    }

    public PasswordGenerator(
            @Value("${app.funcs.password-generator}") String[] funcs,
            @Value("${app.description.password-generator}") String description) {
        super(funcs, description);
    }

    @Override
    @ShellMethod(key = "pg_info", value = "Info about password generator")
    public void getInfo() {
        if(State.getStatus() == State.Status.PASSWORD_GENERATOR) super.getInfo();
        else PrinterGeneralMessagesUtils.printRedMessage("State is different from «PASSWORD_GENERATOR»");
    }

    @ShellMethod(key = "pg_generate", value = "Generate password using symbols from the ASCII table with codes from 33 to 126")
    public void generate(
            @ShellOption(defaultValue = "8", help = "The length of the password. It must be at least 8 and no more than 100 characters long") int length
    ) {
        if(State.getStatus() == State.Status.PASSWORD_GENERATOR) {
            if (length < 8 || length > 100) PrinterGeneralMessagesUtils.printAboutIncorrectInput();
            else PrinterGeneralMessagesUtils.printRedMessage(String.format("Your password: %s", getPassword(length)));
        } else PrinterGeneralMessagesUtils.printRedMessage("State is different from «PASSWORD_GENERATOR»");
    }

    private String getPassword(int length) {
        StringBuilder password = new StringBuilder();
        for(int i = 0; i < length; i++) {
            password.append(symbols.get(new Random().nextInt(0, symbols.size())));
        }
        return password.toString();
    }
}
