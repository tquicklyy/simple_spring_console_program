package com.simple.spring.console.program.event.exit;

import org.springframework.context.ApplicationEvent;

public class ExitHeadAndTailsEvent extends ApplicationEvent {
    public ExitHeadAndTailsEvent(Object source) {
        super(source);
    }
}
