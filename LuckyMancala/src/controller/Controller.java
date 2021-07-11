package LuckyMancala.src.controller;

import LuckyMancala.src.model.*;
import LuckyMancala.src.view.*;

public class Controller {
    private Game game;
    private View view;

    public Controller(Game game, View view) {
        this.game = game;
        this.view = view;
    }

    public void play() {
        this.view.displayWelcome();
        this.game.setPlayerName(0, this.view.askName(0));
        this.game.setPlayerName(1, this.view.askName(1));

        boolean replay = false;

        while (!this.game.isGameOver()) {
            this.view.displayGame();
            Position position = new Position(this.game.getCurrentPlayer(), this.view.askHole(replay));
            this.game.setPickedBeans(this.game.getBeans(position));
            this.game.clearHole(position);
            while (this.game.getPickedBeans() > 0) {
                position.nextHole(this.game.getCurrentPlayer());
                this.game.putBean(position);
                this.view.displayTurn(position);
            }
            replay = true;
            if (!position.isInPot()) {
                if (this.game.canSteal(position)) {
                    int stolen = this.game.steal(position);
                    this.view.displaySteal(stolen);
                }
                this.game.nextPlayer();
                replay = false;
            }
        }
        this.game.endGame();
        this.view.displayGame();
        this.view.displayEnd();
        System.out.println("bye.");
    }
}
