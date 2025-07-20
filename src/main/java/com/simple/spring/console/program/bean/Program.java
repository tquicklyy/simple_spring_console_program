package com.simple.spring.console.program.bean;

import com.simple.spring.console.program.service.FunctionsService;
import com.simple.spring.console.program.utils.PrinterMessages;
import com.simple.spring.console.program.utils.StringDesign;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Program {

    private final String appName;
    private final FunctionsService service;

    public Program(@Value("${app.name}") String appName, FunctionsService service) {
        this.appName = appName;
        this.service = service;
    }

    public void startProgram() {
        PrinterMessages.printHelloMessage(appName);
        continueProgram();
    }

    public void continueProgram() {
        Scanner scanner = new Scanner(System.in);
        int option;

        while(true) {
            PrinterMessages.printNumbersOfProgramFunctions();
            System.out.printf("%sYour choice: %s", StringDesign.RED_COLOR, StringDesign.GREEN_COLOR);
            option = scanner.nextInt();
            scanner.nextLine();
            if(!service.execute(option, this)) {
                return;
            };
        }
    }
}
