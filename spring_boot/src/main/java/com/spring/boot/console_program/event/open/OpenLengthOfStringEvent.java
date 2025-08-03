package com.spring.boot.console_program.event.open;

import org.springframework.context.ApplicationEvent;

public class OpenLengthOfStringEvent extends ApplicationEvent {
    public OpenLengthOfStringEvent(Object source) {
        super(source);
    }
}
