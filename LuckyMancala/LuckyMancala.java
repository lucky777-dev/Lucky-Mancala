package LuckyMancala;

import LuckyMancala.src.controller.*;
import LuckyMancala.src.model.*;
import LuckyMancala.src.view.*;

public class LuckyMancala {
    public static void main(String[] args) {
        Game game = new Game();
        Controller controller  = new Controller(game, new View(game));
        controller.play();
    }
}