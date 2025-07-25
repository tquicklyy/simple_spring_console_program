package com.simple.spring.console.program.bean.listener;

import com.simple.spring.console.program.event.exit.*;
import com.simple.spring.console.program.util.PrinterGeneralMessagesUtils;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ExitEventListenersLogging {

    @EventListener(ExitCalculatorEvent.class)
    public void onCalculatorEvent() {
        PrinterGeneralMessagesUtils.printRedMessage("Exit the calculator");
    }

    @EventListener(ExitRandomizerEvent.class)
    public void onRandomizerEvent() {
        PrinterGeneralMessagesUtils.printRedMessage("Exit the randomizer");
    }

    @EventListener(ExitHeadAndTailsEvent.class)
    public void onHeadAndTailsEvent() {
        PrinterGeneralMessagesUtils.printRedMessage("Game \"Head and tails\" has been closed");
    }

    @EventListener(ExitRockPaperScissorsEvent.class)
    public void onRockPaperScissorsEvent() {
        PrinterGeneralMessagesUtils.printRedMessage("Game \"Rock, paper, scissors\" has been closed");
    }

    @EventListener(ExitProgramEvent.class)
    public void onProgramEvent() throws InterruptedException {
        PrinterGeneralMessagesUtils.printRedMessage("The input option has been closed");
    }
}