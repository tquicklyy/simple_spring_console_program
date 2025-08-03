package com.spring.boot.console_program.event.open;

import org.springframework.context.ApplicationEvent;

public class OpenPasswordGeneratorEvent extends ApplicationEvent {
    public OpenPasswordGeneratorEvent(Object source) {
        super(source);
    }
}
