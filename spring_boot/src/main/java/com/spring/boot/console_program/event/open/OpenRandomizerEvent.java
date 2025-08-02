package com.spring.boot.console_program.event.open;

import org.springframework.context.ApplicationEvent;

public class OpenRandomizerEvent extends ApplicationEvent {
    public OpenRandomizerEvent(Object source) {
        super(source);
    }
}
