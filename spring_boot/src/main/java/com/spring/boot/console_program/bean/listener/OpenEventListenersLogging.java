package com.spring.boot.console_program.bean.listener;

import com.spring.boot.console_program.event.exit.ExitHeadAndTailsEvent;
import com.spring.boot.console_program.event.exit.ExitRockPaperScissors;
import com.spring.boot.console_program.event.open.OpenCalculatorEvent;
import com.spring.boot.console_program.event.open.OpenHeadAndTailsEvent;
import com.spring.boot.console_program.event.open.OpenRandomizerEvent;
import com.spring.boot.console_program.event.open.OpenRockPaperScissors;
import com.spring.boot.console_program.util.PrinterGeneralMessagesUtils;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OpenEventListenersLogging {

    @EventListener(OpenRandomizerEvent.class)
    public void onRandomizerEvent() {
        PrinterGeneralMessagesUtils.printRedMessage("Opening the randomizer");
    }

    @EventListener(OpenCalculatorEvent.class)
    public void onCalculatorEvent() {
        PrinterGeneralMessagesUtils.printRedMessage("Opening the calculator");
    }

    @EventListener(OpenHeadAndTailsEvent.class)
    public void onHeadAndTailsEvent() {
        PrinterGeneralMessagesUtils.printRedMessage("Opening head and tails game");
    }

    @EventListener(OpenRockPaperScissors.class)
    public void onRockPaperScissors() {
        PrinterGeneralMessagesUtils.printRedMessage("Opening rock, paper, scissors game");
    }

}
