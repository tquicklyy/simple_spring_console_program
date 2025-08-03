package com.spring.boot.console_program.bean.listener;

import com.spring.boot.console_program.util.PrinterGeneralMessagesUtils;
import com.spring.boot.console_program.util.ThreadUtils;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ContextClosedListener {

    @EventListener(ContextClosedEvent.class)
    public void onEvent() throws InterruptedException {
        PrinterGeneralMessagesUtils.printRedMessage("The context has started closing");
        Thread.sleep(ThreadUtils.TIME_TO_SLEEP);
        PrinterGeneralMessagesUtils.printDefaultMessageForEvent();
        PrinterGeneralMessagesUtils.printRedMessage("The context is starting to destroy all the beans");
    }
}
