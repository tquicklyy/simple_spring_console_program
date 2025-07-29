package com.simple.spring.console.program.bean.function;

import com.simple.spring.console.program.util.PrinterGeneralMessagesUtils;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationEventPublisher;

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

    public abstract void startWork();

    abstract boolean handleUserChoice(Integer option);
}
