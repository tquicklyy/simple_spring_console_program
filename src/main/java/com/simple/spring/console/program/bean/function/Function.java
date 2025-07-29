package com.simple.spring.console.program.bean.function;

import com.simple.spring.console.program.util.PrinterGeneralMessagesUtils;
import com.simple.spring.console.program.util.ScannerUtils;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationEventPublisher;

import java.util.InputMismatchException;

public abstract class Function implements BeanNameAware {

    protected String beanName;
    protected final String[] funcs;
    protected final ApplicationEventPublisher publisher;

    protected Function(String[] funcs, ApplicationEventPublisher publisher) {
        this.funcs = funcs;
        this.publisher = publisher;
    }

    @PostConstruct
    protected abstract void postConstruct();

    @PreDestroy
    protected void preDestroy() {
        PrinterGeneralMessagesUtils.printRedMessage(String.format("Bean with name «%s» has been closed!", beanName));
    }

    public void startWork() {
        int option;

        while (true) {
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
    };

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    abstract boolean handleUserChoice(Integer option);
}
