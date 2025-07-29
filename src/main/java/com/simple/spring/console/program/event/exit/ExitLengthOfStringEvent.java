package com.simple.spring.console.program.event.exit;

import org.springframework.context.ApplicationEvent;

public class ExitLengthOfStringEvent extends ApplicationEvent {
    public ExitLengthOfStringEvent(Object source) {
        super(source);
    }
}
