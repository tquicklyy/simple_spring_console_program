package com.spring.boot.console_program.bean.listener;

import com.spring.boot.console_program.event.ExitRandomizerEvent;
import com.spring.boot.console_program.util.PrinterGeneralMessagesUtils;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ExitEventListenersLogging {

    @EventListener(ExitRandomizerEvent.class)
    public void onRandomizerEvent() {
        PrinterGeneralMessagesUtils.printRedMessage("Exiting the randomizer");
    }
}