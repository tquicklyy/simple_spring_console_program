package com.spring.boot.console_program.event.exit;

import org.springframework.context.ApplicationEvent;

public class ExitCalculatorEvent extends ApplicationEvent {
    public ExitCalculatorEvent(Object source) {
        super(source);
    }
}
