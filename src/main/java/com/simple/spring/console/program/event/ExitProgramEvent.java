package com.simple.spring.console.program.event;

import org.springframework.context.ApplicationEvent;

public class ExitProgramEvent extends ApplicationEvent {

    public ExitProgramEvent(Object source) {
        super(source);
    }
}
