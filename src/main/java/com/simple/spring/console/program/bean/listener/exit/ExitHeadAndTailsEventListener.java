package com.simple.spring.console.program.bean.listener.exit;

import com.simple.spring.console.program.event.exit.ExitHeadAndTailsEvent;
import com.simple.spring.console.program.utils.PrinterGeneralMessagesUtils;
import org.springframework.context.event.EventListener;

public class ExitHeadAndTailsEventListener {

    @EventListener(ExitHeadAndTailsEvent.class)
    public void onEvent() {
        PrinterGeneralMessagesUtils.printRedMessage("Game \"Head and tails\" has been closed");
    }

}
