package com.simple.spring.console.program.event.exit;

import org.springframework.context.ApplicationEvent;

public class ExitRockPaperScissorsEvent extends ApplicationEvent {
    public ExitRockPaperScissorsEvent(Object source) {
        super(source);
    }
}
