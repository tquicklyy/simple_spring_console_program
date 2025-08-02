package com.spring.boot.console_program.bean.function;

import com.spring.boot.console_program.util.PrinterGeneralMessagesUtils;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.BeanNameAware;


public abstract class Function implements BeanNameAware {

    protected String beanName;
    protected final String[] funcs;
    protected final String description;

    protected Function(String[] funcs, String description) {
        this.funcs = funcs;
        this.description = description;
    }

    @PostConstruct
    protected void postConstruct() {
        PrinterGeneralMessagesUtils.printRedMessage(String.format("Bean with name «%s» has been created!", beanName));
    };

    @PreDestroy
    protected void preDestroy() {
        PrinterGeneralMessagesUtils.printRedMessage(String.format("Bean with name «%s» has been closed!", beanName));
    }

    public void getInfo() {
        PrinterGeneralMessagesUtils.printMessage(description);
        PrinterGeneralMessagesUtils.printOptionsWithFuncs(funcs);
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
