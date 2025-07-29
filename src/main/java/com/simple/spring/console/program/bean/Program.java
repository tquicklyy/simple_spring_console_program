package com.simple.spring.console.program.bean;

import com.simple.spring.console.program.bean.function.WorkTime;
import com.simple.spring.console.program.service.FunctionsService;
import com.simple.spring.console.program.util.PrinterGeneralMessagesUtils;
import com.simple.spring.console.program.util.ScannerUtils;
import com.simple.spring.console.program.util.StringDesign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;


@Component
public class Program {

    private final static Logger LOG = LoggerFactory.getLogger(Program.class);

    private final String appName;
    private final String[] funcs;

    private final WorkTime workTime;
    private final FunctionsService service;

    public Program(@Value("${app.name}") String appName, FunctionsService service, @Value("#{'${app.funcs.main}'.split(';')}") String[] funcs, WorkTime workTime) {
        this.appName = appName;
        this.service = service;
        this.funcs = funcs;
        this.workTime = workTime;
    }

    public void startProgram() {
        printHelloMessage();
        continueProgram();
    }

    public void continueProgram() {
        int option = 0;

        while(true) {
            workTime.printTimeOfWork();
            PrinterGeneralMessagesUtils.printOptionsWithFuncs(funcs);
            PrinterGeneralMessagesUtils.printYourChoice();

            try {
                option = ScannerUtils.getNewIntegerWithLine();

                PrinterGeneralMessagesUtils.skipText(1);

                if(!service.execute(option)) {
                    return;
                }
            } catch (InputMismatchException e) {
                PrinterGeneralMessagesUtils.printAboutIncorrectInput();
                ScannerUtils.skipLine();
            }
        }
    }

    public void endProgram(ConfigurableApplicationContext context) {
        context.close();
    }

    private void printHelloMessage() {
        PrinterGeneralMessagesUtils.skipText(1000);

        LOG.info(""" 
                {}{}
                Hi! This is {}
                with simple interesting functions. Try to
                use some of them and just enjoy! {}
                """, StringDesign.BOLD, StringDesign.RED_COLOR, appName, StringDesign.GREEN_COLOR);
    }
}
