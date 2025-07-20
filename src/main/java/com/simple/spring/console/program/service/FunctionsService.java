package com.simple.spring.console.program.service;

import com.simple.spring.console.program.event.ExitProgramEvent;
import com.simple.spring.console.program.utils.PrinterMessages;
import com.simple.spring.console.program.utils.ThreadUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class FunctionsService {

    private final ApplicationEventPublisher publisher;

    public FunctionsService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public boolean execute(int option, Object object) {
        try {
            switch (option) {
                case 1:

                case 2:

                case 3:

                case 4:

                case 5:

                case 6:

                case 7:
                    exitProgram(object);
                    return false;

                default:
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    private void exitProgram(Object object) throws InterruptedException {
        PrinterMessages.printDefaultMessageForEvent();
        publisher.publishEvent(new ExitProgramEvent(object));
    }
}
