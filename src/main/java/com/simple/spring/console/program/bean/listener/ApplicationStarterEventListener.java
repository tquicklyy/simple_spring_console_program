package com.simple.spring.console.program.bean.listener;

import com.simple.spring.console.program.utils.StringDesign;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStarterEventListener {

    @EventListener(ContextRefreshedEvent.class)
    public void onEvent() {
        System.out.println(StringDesign.GREEN_COLOR);
    }

}
