package com.simple.spring.console.program.event.exit;

import org.springframework.context.ApplicationEvent;

public class ExitCalculatorEvent extends ApplicationEvent {
    public ExitCalculatorEvent(Object source) {
        super(source);
    }
}
