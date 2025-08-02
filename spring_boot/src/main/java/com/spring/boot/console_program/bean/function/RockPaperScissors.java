package com.spring.boot.console_program.bean.function;

import com.spring.boot.console_program.enumeration.ResultOfGame;
import com.spring.boot.console_program.util.PrinterGeneralMessagesUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Random;

@ShellComponent
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

    public RockPaperScissors(
            @Value("${app.funcs.rock-paper-scissors}") String[] funcs,
            @Value("${app.description.rock-paper-scissors}") String description) {
        super(funcs, description);
    }

    @ShellMethod(key = "rps_play", value = "Play rock, paper, scissors")
    public void play(
            @ShellOption(help = "Your choice in game") String choice
    ) {
        if(State.getStatus() == State.Status.ROCK_PAPER_SCISSORS) {
            ChoicesInGame computerChoice = CHOICES_IN_GAME[new Random().nextInt(0, 3)];
            PrinterGeneralMessagesUtils.printRedMessage(String.format("Computer made choice: %s", computerChoice));
            ResultOfGame.printResultOfGame(ChoicesInGame.whoWin(ChoicesInGame.valueOf(choice.toUpperCase()), computerChoice));
        } else PrinterGeneralMessagesUtils.printRedMessage("State is different from «ROCK_PAPER_SCISSORS»");
    }

    @Override
    @ShellMethod(key = "rps_info", value = "Info about rock, paper, scissors")
    public void getInfo() {
        if(State.getStatus() == State.Status.ROCK_PAPER_SCISSORS) super.getInfo();
        else PrinterGeneralMessagesUtils.printRedMessage("State is different from «ROCK_PAPER_SCISSORS»");
    }
}
