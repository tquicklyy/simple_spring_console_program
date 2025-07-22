package com.simple.spring.console.program.bean.listener;

import com.simple.spring.console.program.event.ExitProgramEvent;
import com.simple.spring.console.program.utils.PrinterGeneralMessagesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ExitProgramEventListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(ExitProgramEvent.class);

    @EventListener(ExitProgramEvent.class)
    public void onEvent() throws InterruptedException {
        PrinterGeneralMessagesUtils.printRedMessage("The input option is closed");
    }

}
