package com.simple.spring.console.program.bean;

import com.simple.spring.console.program.utils.PrinterMessages;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Program {

    private final String appName;

    public Program(@Value("${app.name}") String appName) {
        this.appName = appName;
    }

    public void startProgram() {
        PrinterMessages.printHelloMessage(appName);
        continueProgram();
    }

    public void continueProgram() {
        Scanner scanner = new Scanner(System.in);
        int option;

        while(true) {
            PrinterMessages.printNumbersOfFunctions();
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {

            }
        }
    }
}
