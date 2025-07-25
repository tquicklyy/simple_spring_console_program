package com.simple.spring.console.program.bean.function;

import com.simple.spring.console.program.enumeration.ResultOfGame;
import com.simple.spring.console.program.event.exit.ExitRockPaperScissorsEvent;
import com.simple.spring.console.program.util.PrinterGeneralMessagesUtils;
import com.simple.spring.console.program.util.ScannerUtils;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Random;

@Component
public class RockPaperScissors extends Function {

    private enum ChoicesInGame {
        ROCK, PAPER, SCISSORS;

        private static ResultOfGame whoWin(ChoicesInGame player, ChoicesInGame computer) {
            if(player.equals(computer)) return ResultOfGame.DRAW;

            return switch (player) {
                case ChoicesInGame.PAPER -> (computer.equals(ChoicesInGame.ROCK)) ? ResultOfGame.VICTORY : ResultOfGame.LOSE;
                case ChoicesInGame.ROCK -> (computer.equals(ChoicesInGame.SCISSORS)) ? ResultOfGame.VICTORY : ResultOfGame.LOSE;
                case ChoicesInGame.SCISSORS -> (computer.equals(ChoicesInGame.PAPER)) ? ResultOfGame.VICTORY : ResultOfGame.LOSE;
            };
        }
    }

    private static final ChoicesInGame[] CHOICES_IN_GAME = {
            ChoicesInGame.PAPER, ChoicesInGame.ROCK, ChoicesInGame.SCISSORS
    };

    public RockPaperScissors(@Value("#{'${app.rock-paper-scissors-funcs}'.split(';')}") String[] funcs, ApplicationEventPublisher publisher) {
        super(funcs, publisher);
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    @PostConstruct
    public void postConstruct() {
        PrinterGeneralMessagesUtils.printRedMessage("Rock, paper, scissors has been started!");
    }

    @Override
    public void startWork() {
        int option = 0;

        while (true) {
            PrinterGeneralMessagesUtils.printOptionsWithFuncs(funcs);
            try {
                PrinterGeneralMessagesUtils.printYourChoice();
                option = ScannerUtils.getNewIntegerWithLine();
                PrinterGeneralMessagesUtils.skipText(1);

                if(!handleUserChoice(option)) {
                    return;
                }

            } catch (InputMismatchException e) {
                PrinterGeneralMessagesUtils.printAboutIncorrectInput();
                ScannerUtils.skipLine();
            }
        }
    }

    @Override
    boolean handleUserChoice(Integer option) {
        try {
            switch (option) {
                case 1:
                    PrinterGeneralMessagesUtils.printRedMessage("ROCK, PAPER OR SCISSORS?");
                    PrinterGeneralMessagesUtils.printYourChoice();
                    play(ChoicesInGame.valueOf(ScannerUtils.getOneWord().toUpperCase()));
                    break;
                case 2:
                    publisher.publishEvent(new ExitRockPaperScissorsEvent(this));
                    return false;
            }
        } catch (IllegalArgumentException e) {
            PrinterGeneralMessagesUtils.printAboutIncorrectInput();
            handleUserChoice(option);
        }

        return true;
    }

    private void play(ChoicesInGame choice) {
        ChoicesInGame computerChoice = CHOICES_IN_GAME[new Random().nextInt(0,3)];
        PrinterGeneralMessagesUtils.printRedMessage(String.format("Computer made choice: %s", computerChoice));
        ResultOfGame.printResultOfGame(
                ChoicesInGame.whoWin(choice, computerChoice));
    }
}
