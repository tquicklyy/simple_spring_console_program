package com.simple.spring.console.program.bean.listener;

import com.simple.spring.console.program.event.ExitProgramEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ExitProgramEventListener {

    @EventListener(ExitProgramEvent.class)
    public void onEvent() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            System.out.println(i);
            Thread.sleep(1000);
        }
        System.out.println("The input option is closed");
    }

}
