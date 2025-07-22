package com.simple.spring.console.program.bean.listener.exit;

import com.simple.spring.console.program.event.exit.ExitRandomizerEvent;
import com.simple.spring.console.program.utils.PrinterGeneralMessagesUtils;
import org.springframework.context.event.EventListener;

public class ExitRandomizerEventListener {

    @EventListener(ExitRandomizerEvent.class)
    public void onEvent() {
        PrinterGeneralMessagesUtils.printRedMessage("Randomizer was closed");
    }

}
