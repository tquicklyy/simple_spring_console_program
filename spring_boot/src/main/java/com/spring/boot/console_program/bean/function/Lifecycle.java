package com.spring.boot.console_program.bean.function;

import com.spring.boot.console_program.util.PrinterGeneralMessagesUtils;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.BeanNameAware;


public abstract class Lifecycle implements BeanNameAware {

    protected String beanName;
    protected String[] funcs;
    protected String description;

    protected Lifecycle() {}

    protected Lifecycle(String beanName) {
        this.beanName = beanName;
    }

    protected Lifecycle(String[] funcs, String description) {
        this.funcs = funcs;
        this.description = description;
    }

    @PostConstruct
    protected abstract void postConstruct();

    @PreDestroy
    protected void preDestroy() {
        PrinterGeneralMessagesUtils.printRedMessage(String.format("Bean with name «%s» has been closed!", beanName));
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
