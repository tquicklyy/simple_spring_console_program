package com.spring.boot.console_program.bean.function;

import com.spring.boot.console_program.util.PrinterGeneralMessagesUtils;
import com.spring.boot.console_program.util.StringDesign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@ShellCommandGroup("General Commands")
public class GeneralCommands extends Lifecycle{

    private static final Logger LOG = LoggerFactory.getLogger(GeneralCommands.class);
    private final String appName;

    public GeneralCommands(
            @Value("${app.name}") String appName,
            @Value("#{'${app.funcs.general}'.split(';')}") String[] funcs,
            @Value("${app.description.general}") String description ) {
        super(funcs, description);
        this.appName = appName;
    }

    @Override
    protected void postConstruct() {
        PrinterGeneralMessagesUtils.printRedMessage("General commands has been started!");
    }

    @ShellMethod(key = "app_info", value = "Info about application")
    public void printHelloMessage() {
        PrinterGeneralMessagesUtils.printMessage(description);
        PrinterGeneralMessagesUtils.printOptionsWithFuncs(funcs);
    }

}
