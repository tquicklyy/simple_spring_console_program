package com.spring.boot.console_program.bean.listener;

import com.spring.boot.console_program.event.open.*;
import com.spring.boot.console_program.util.PrinterGeneralMessagesUtils;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OpenEventListenersLogging {

    @EventListener(OpenRandomizerEvent.class)
    public void onRandomizerEvent() {
        PrinterGeneralMessagesUtils.printMessage ("Opening the randomizer");
    }

    @EventListener(OpenCalculatorEvent.class)
    public void onCalculatorEvent() {
        PrinterGeneralMessagesUtils.printMessage ("Opening the calculator");
    }

    @EventListener(OpenHeadAndTailsEvent.class)
    public void onHeadAndTailsEvent() {
        PrinterGeneralMessagesUtils.printMessage ("Opening head and tails game");
    }

    @EventListener(OpenRockPaperScissors.class)
    public void onRockPaperScissors() {
        PrinterGeneralMessagesUtils.printMessage ("Opening rock, paper, scissors game");
    }

    @EventListener(OpenPasswordGeneratorEvent.class)
    public void onPasswordGenerator() {
        PrinterGeneralMessagesUtils.printMessage ("Opening from password generator");
    }

    @EventListener(OpenLengthOfStringEvent.class)
    public void onLengthOfString() {
        PrinterGeneralMessagesUtils.printMessage ("Opening the function to get the length of the string");
    }

}
