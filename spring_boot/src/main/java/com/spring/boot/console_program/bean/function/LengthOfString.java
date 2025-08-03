package com.spring.boot.console_program.bean.function;

import com.spring.boot.console_program.util.PrinterGeneralMessagesUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@ShellCommandGroup("Length of string commands")
public class LengthOfString extends Function {
    public LengthOfString(
            @Value("${app.funcs.length-of-string}") String[] funcs,
            @Value("${app.description.length-of-string}") String description) {
        super(funcs, description);
    }

    @Override
    @ShellMethod(key = "los_info", value = "Info about the function to get the length of a string")
    public void getInfo() {
        if(State.getStatus() == State.Status.LENGTH_OF_STRING) super.getInfo();
        else PrinterGeneralMessagesUtils.printRedMessage("State is different from «LENGTH_OF_STRING»");
    }

    @ShellMethod(key = "los_length", value = "Get a length of the string")
    public void length(
            @ShellOption(help = "The string to get the length of") String string
    ) {
        if(State.getStatus() == State.Status.LENGTH_OF_STRING) PrinterGeneralMessagesUtils.printRedMessage(String.format("Length of the string: %s symbols", string.length()));
        else PrinterGeneralMessagesUtils.printRedMessage("State is different from «LENGTH_OF_STRING»");
    }
}
