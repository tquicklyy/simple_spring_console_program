package com.simple.spring.console.program.service;

import com.simple.spring.console.program.bean.function.Calculator;
import com.simple.spring.console.program.bean.function.HeadAndTails;
import com.simple.spring.console.program.bean.function.Randomizer;
import com.simple.spring.console.program.event.exit.ExitProgramEvent;
import com.simple.spring.console.program.utils.PrinterGeneralMessagesUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class FunctionsService {

    private final ApplicationEventPublisher publisher;
    private final Randomizer randomizer;
    private final Calculator calculator;
    private final HeadAndTails headAndTails;

    public FunctionsService(ApplicationEventPublisher publisher, Randomizer randomizer, Calculator calculator, HeadAndTails headAndTails) {
        this.publisher = publisher;
        this.randomizer = randomizer;
        this.calculator = calculator;
        this.headAndTails = headAndTails;
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
                    headAndTails.getOptions();
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
