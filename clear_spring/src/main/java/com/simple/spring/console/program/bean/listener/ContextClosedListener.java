package com.simple.spring.console.program.bean.listener;

import com.simple.spring.console.program.util.PrinterGeneralMessagesUtils;
import com.simple.spring.console.program.util.ThreadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ContextClosedListener {

    private static final Logger LOG = LoggerFactory.getLogger(ContextClosedListener.class);

    @EventListener(ContextClosedEvent.class)
    public void onEvent() throws InterruptedException {
        PrinterGeneralMessagesUtils.printRedMessage("The context has started closing");
        Thread.sleep(ThreadUtils.TIME_TO_SLEEP);
        PrinterGeneralMessagesUtils.printRedMessage("The context is starting to destroy all the beans");
    }
}
