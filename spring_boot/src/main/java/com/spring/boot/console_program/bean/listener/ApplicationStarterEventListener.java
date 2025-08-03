package com.spring.boot.console_program.bean.listener;

import com.spring.boot.console_program.bean.function.GeneralCommands;
import com.spring.boot.console_program.util.PrinterGeneralMessagesUtils;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStarterEventListener {

    private final GeneralCommands generalCommands;

    public ApplicationStarterEventListener(GeneralCommands generalCommands) {
        this.generalCommands = generalCommands;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void onEvent() {
        PrinterGeneralMessagesUtils.printRedMessage("The application is now running");
        generalCommands.getInfo();
    }

}
