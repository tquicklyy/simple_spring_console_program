package com.spring.boot.console_program.event;

import org.springframework.context.ApplicationEvent;

public class ExitRandomizerEvent extends ApplicationEvent {
    public ExitRandomizerEvent(Object source) {
        super(source);
    }
}
