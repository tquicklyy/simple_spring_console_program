package com.spring.boot.console_program.bean.listener;

import com.spring.boot.console_program.event.exit.*;
import com.spring.boot.console_program.util.PrinterGeneralMessagesUtils;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ExitEventListenersLogging {

    @EventListener(ExitRandomizerEvent.class)
    public void onRandomizerEvent() {
        PrinterGeneralMessagesUtils.printMessage("Exiting the randomizer");
    }

    @EventListener(ExitCalculatorEvent.class)
    public void onCalculatorEvent() {
        PrinterGeneralMessagesUtils.printRedMessage("Exiting the calculator");
    }

    @EventListener(ExitHeadAndTailsEvent.class)
    public void onHeadAndTailsEvent() {
        PrinterGeneralMessagesUtils.printRedMessage("Exiting from head and tails game");
    }

    @EventListener(ExitRockPaperScissors.class)
    public void onRockPaperScissors() {
        PrinterGeneralMessagesUtils.printRedMessage("Exiting from rock, paper, scissors game");
    }

    @EventListener(ExitPasswordGeneratorEvent.class)
    public void onPasswordGenerator() {
        PrinterGeneralMessagesUtils.printRedMessage("Exiting from password generator");
    }

    @EventListener(ExitLengthOfStringEvent.class)
    public void onLengthOfString() {
        PrinterGeneralMessagesUtils.printRedMessage("Exiting the function to get the length of the string");
    }
}