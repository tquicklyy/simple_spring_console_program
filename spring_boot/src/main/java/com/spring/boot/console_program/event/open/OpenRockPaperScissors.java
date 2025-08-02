package com.spring.boot.console_program.event.open;

import org.springframework.context.ApplicationEvent;

public class OpenRockPaperScissors extends ApplicationEvent {
    public OpenRockPaperScissors(Object source) {
        super(source);
    }
}
