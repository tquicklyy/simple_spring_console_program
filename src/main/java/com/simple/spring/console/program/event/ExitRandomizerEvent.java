package com.simple.spring.console.program.event;

import org.springframework.context.ApplicationEvent;

public class ExitRandomizerEvent extends ApplicationEvent {

    public ExitRandomizerEvent(Object source) {
        super(source);
    }
}
