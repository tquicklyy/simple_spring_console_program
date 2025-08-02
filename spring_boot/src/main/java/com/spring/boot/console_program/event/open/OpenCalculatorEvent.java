package com.spring.boot.console_program.event.open;

import org.springframework.context.ApplicationEvent;

public class OpenCalculatorEvent extends ApplicationEvent {
    public OpenCalculatorEvent(Object source) {
        super(source);
    }
}
