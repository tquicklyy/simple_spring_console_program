package com.spring.boot.console_program.event.exit;

import org.springframework.context.ApplicationEvent;

public class ExitLengthOfStringEvent extends ApplicationEvent {
    public ExitLengthOfStringEvent(Object source) {
        super(source);
    }
}
