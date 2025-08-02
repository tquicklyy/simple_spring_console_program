package com.spring.boot.console_program.event.exit;

import org.springframework.context.ApplicationEvent;

public class ExitRockPaperScissors extends ApplicationEvent {
    public ExitRockPaperScissors(Object source) {
        super(source);
    }
}
