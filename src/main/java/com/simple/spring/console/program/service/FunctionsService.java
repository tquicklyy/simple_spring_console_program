package com.simple.spring.console.program.service;

import com.simple.spring.console.program.bean.Calculator;
import com.simple.spring.console.program.bean.Randomizer;
import com.simple.spring.console.program.event.exit.ExitProgramEvent;
import com.simple.spring.console.program.utils.PrinterGeneralMessagesUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class FunctionsService {

    private final ApplicationEventPublisher publisher;
    private final Randomizer randomizer;
    private final Calculator calculator;

    public FunctionsService(ApplicationEventPublisher publisher, Randomizer randomizer, Calculator calculator) {
        this.publisher = publisher;
        this.randomizer = randomizer;
        this.calculator = calculator;
    }

    public boolean execute(int option) {
        try {
            switch (option) {
                case 1:
                    randomizer.getOptions();
                    break;
                case 2:
                    calculator.getOptions();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    exitProgram(this);
                    return false;

                default:
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    private void exitProgram(Object object) throws InterruptedException {
        PrinterGeneralMessagesUtils.printDefaultMessageForEvent();
        publisher.publishEvent(new ExitProgramEvent(object));
    }
}
