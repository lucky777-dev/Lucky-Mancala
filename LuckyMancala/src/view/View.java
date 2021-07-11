package LuckyMancala.src.view;

import java.util.Scanner;

import LuckyMancala.src.Util;
import LuckyMancala.src.model.*;

public class View {

    static Scanner kb = new Scanner(System.in);

    private Game game;

    public View(Game game) {
        this.game = game;
    }

    public void displayWelcome() {
        Util.fastType("Lucky Mancala " + this.game.getVersion());
        System.out.println();
        Util.fastType("by Sm!le42.");
        System.out.println("\n");
        Util.typeln("Welcome! :)");
        System.out.println();
    }

    public String askName(int player) {
        Util.fastType("Please enter the name of the player " + (player + 1));
        System.out.print("\n>");
        String name = kb.nextLine();
        while (name.length() > 7) {
            System.out.println("! 7 characters maximum. Try again");
            System.out.print(">");
            name = kb.nextLine();
        }
        if (name.equals("")) {
            name = "Player" + (player + 1);
        }
        Util.typeln("Welcome " + name + " !");
        System.out.println();
        return name;
    }

    public void displayGame() {
        Util.clear();
        String display = (this.game.getPlayerName(0) + "\n");
        if (this.game.getCurrentPlayer() == 0) {
            display += "         a    z    e    r    t    y\n";
        } else {
            display += "\n";
        }
        display += ("    " + addSide(0));
        display += "    [" + this.game.getPot(0) + "]                              [" + this.game.getPot(1) + "]\n    ";
        display += addSide(1);
        if (this.game.getCurrentPlayer() == 1) {
            display += "         a    z    e    r    t    y\n";
        } else {
            display += "\n";
        }
        display += ("                                     " + this.game.getPlayerName(1));
        System.out.println(display);
    }

    private String addSide(int player) {
        String result = "   ";
        for (int i = 0; i < 6; i++) {
            result += (String.format("%4s ", ("(" + (this.game.getBeans(new Position(player, i))) + ")")));
        }
        return (result + "\n");
    }

    public int askHole(boolean replay) {
        if (replay) {
            System.out.println(":) Replay !");
        } else {
            Util.fastTypeln("It is your turn, " + this.game.getPlayerName(this.game.getCurrentPlayer()));
            Util.fastTypeln("Please enter the position (azerty)");
        }
        System.out.print(">");
        String azerty = kb.nextLine();
        while (!isAzerty(azerty)) {
            System.out.println("! Invalid position");
            System.out.println("Please enter a, z, e, r, t or y");
            System.out.print(">");
            azerty = kb.nextLine();
        }
        while(!this.game.canBeTaken(new Position(this.game.getCurrentPlayer(), azertyToInt(azerty)))) {
            System.out.println("! No beans there");
            System.out.println("Try again");
            System.out.print(">");
            azerty = kb.nextLine();
            while (!isAzerty(azerty)) {
                System.out.println("! Invalid position");
                System.out.println("Please enter a, z, e, r, t or y");
                System.out.print(">");
                azerty = kb.nextLine();
            }
        }
        return azertyToInt(azerty);
    }

    private int azertyToInt(String azerty) {
        int result = -1;
        switch (azerty) {
            case "a":
                result = 0;
                break;
            case "z":
                result = 1;
                break;
            case "e":
                result = 2;
                break;
            case "r":
                result = 3;
                break;
            case "t":
                result = 4;
                break;
            case "y":
                result = 5;
                break;
        }
        return result;
    }

    private boolean isAzerty(String azerty) {
        azerty = azerty.toLowerCase();
        return (azerty.equals("a") || azerty.equals("z") || azerty.equals("e") || azerty.equals("r")
                || azerty.equals("t") || azerty.equals("y"));
    }

    public void displayTurn(Position p) {
        Util.clear();
        String display = (this.game.getPlayerName(0) + "\n");
        if (p.getPlayer() == 0 && !p.isInPot()) {
            display += "         ";
            for (int i = 0; i < p.getHole(); i++) {
                display += "     ";
            }
            display += ((this.game.canSteal(p)) ? ("*") : (this.game.getPickedBeans())) + "\n";
        } else {
            display += "\n";
        }
        display += ("    " + addSide(0));
        display += " "
                + ((p.isInPot() && p.getPlayer() == 0) ? (String.format("%2s",
                        (this.game.getPickedBeans() == 0) ? ("*") : (this.game.getPickedBeans()))) : ("  "))
                + " [" + this.game.getPot(0) + "]                              [" + this.game.getPot(1) + "]"
                + ((p.isInPot() && p.getPlayer() == 1) ? (String.format("%2s",
                        (this.game.getPickedBeans() == 0) ? ("*") : (this.game.getPickedBeans()))) : (""))
                + "\n";
        display += ("    " + addSide(1));
        if (p.getPlayer() == 1 && !p.isInPot()) {
            display += "         ";
            for (int i = 0; i < p.getHole(); i++) {
                display += "     ";
            }
            display += ((this.game.canSteal(p)) ? ("*") : (this.game.getPickedBeans())) + "\n";
        } else {
            display += "\n";
        }
        display += ("                                     " + this.game.getPlayerName(1));
        System.out.println(display);
        Util.sleep(0.42);
    }

    public void displaySteal(int stolen) {
        if (stolen > 15) {
            Util.typeln("OMGGGGGGGG xD hahahah, " + (this.game.getPlayerName(1 - this.game.getCurrentPlayer()))
                    + " is a big looser <3");
        } else if (stolen > 10) {
            Util.typeln("Wow... " + stolen + " stolen beans! Well done "
                    + this.game.getPlayerName(this.game.getCurrentPlayer()) + " !");
        } else if (stolen > 5) {
            Util.typeln("Hehe :D " + stolen + " stolen beans, not bad !");
        } else if (stolen > 1) {
            Util.type(stolen + " stolen beans");
        } else {
            Util.typeln("+1 bean");
        }
    }

    public void displayEnd() {
        if(this.game.getPot(0) != this.game.getPot(1)) {
            Util.typeln("Well played " + this.game.getPlayerName((this.game.getPot(0) > this.game.getPot(1)) ? (0) : (1)));
        } else {
            Util.typeln("It's a tie, nobody won.");
        }
    }
}
