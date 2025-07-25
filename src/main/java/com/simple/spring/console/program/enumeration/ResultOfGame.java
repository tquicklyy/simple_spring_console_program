package com.simple.spring.console.program.enumeration;

import com.simple.spring.console.program.util.PrinterGeneralMessagesUtils;

public enum ResultOfGame {
    VICTORY,
    LOSE,
    DRAW;

    public static void printResultOfGame(ResultOfGame result) {
        switch (result) {
            case VICTORY -> PrinterGeneralMessagesUtils.printRedMessage("Congratulations! You've won!");
            case LOSE -> PrinterGeneralMessagesUtils.printRedMessage("Unfortunately, you've lost!");
            case DRAW -> PrinterGeneralMessagesUtils.printRedMessage("It looks like a draw!");
        }
    }
}
