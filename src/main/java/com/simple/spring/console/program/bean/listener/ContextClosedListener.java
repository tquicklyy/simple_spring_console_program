package com.simple.spring.console.program.bean.listener;

import com.simple.spring.console.program.utils.StringDesign;
import com.simple.spring.console.program.utils.ThreadUtils;
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
        LOG.info("{}Starting context closing!{}", StringDesign.RED_COLOR, StringDesign.GREEN_COLOR);
        Thread.sleep(ThreadUtils.TIME_TO_SLEEP);
        LOG.info("{}Context is closed. Goodbye!{}", StringDesign.RED_COLOR, StringDesign.GREEN_COLOR);
    }
}
