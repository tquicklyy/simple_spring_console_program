package com.simple.spring.console.program.event.exit;

import org.springframework.context.ApplicationEvent;

public class ExitPasswordGeneratorEvent extends ApplicationEvent {
    public ExitPasswordGeneratorEvent(Object source) {
        super(source);
    }
}
