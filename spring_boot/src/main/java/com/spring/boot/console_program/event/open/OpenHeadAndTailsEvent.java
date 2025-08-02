package com.spring.boot.console_program.event.open;

import org.springframework.context.ApplicationEvent;

public class OpenHeadAndTailsEvent extends ApplicationEvent {
    public OpenHeadAndTailsEvent(Object source) {
        super(source);
    }
}
