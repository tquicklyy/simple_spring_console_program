package com.spring.boot.console_program.bean.listener;

import com.spring.boot.console_program.event.exit.ExitCalculatorEvent;
import com.spring.boot.console_program.event.exit.ExitRandomizerEvent;
import com.spring.boot.console_program.util.PrinterGeneralMessagesUtils;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ExitEventListenersLogging {

    @EventListener(ExitRandomizerEvent.class)
    public void onRandomizerEvent() {
        PrinterGeneralMessagesUtils.printMessage("Exiting the randomizer");
    }

    @EventListener(ExitCalculatorEvent.class)
    public void onCalculatorEvent() {
        PrinterGeneralMessagesUtils.printRedMessage("Exiting the calculator");
    }
}