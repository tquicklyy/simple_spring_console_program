package com.simple.spring.console.program.bean.listener;

import com.simple.spring.console.program.event.ExitProgramEvent;
import com.simple.spring.console.program.utils.StringDesign;
import com.simple.spring.console.program.utils.ThreadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ExitProgramEventListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(ExitProgramEvent.class);

    @EventListener(ExitProgramEvent.class)
    public void onEvent() throws InterruptedException {
        LOG.info("{}The input option is closed {}", StringDesign.RED_COLOR, StringDesign.GREEN_COLOR);
    }

}
