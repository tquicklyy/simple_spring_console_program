package com.simple.spring.console.program.bean.listener;

import com.simple.spring.console.program.util.PrinterGeneralMessagesUtils;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStarterEventListener {

    @EventListener(ContextRefreshedEvent.class)
    public void onEvent() {
        PrinterGeneralMessagesUtils.printRedMessage("The application is now running");
    }

}
