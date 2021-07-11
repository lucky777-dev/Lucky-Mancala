package LuckyMancala.src.model;

import LuckyMancala.src.Util;

/**
 * The Game class for Lucky-Mancala.
 */
public class Game {
    private String version = "v0.1";
    private String[] playersName;
    private int currentPlayer;
    private int pickedBeans;
    private Board board;

    /**
     * Initialisation of the game with currentPlayer = (0 or 1), pickedBeans = 0 and
     * a new Board.
     */
    public Game() {
        this.playersName = new String[] {"Player1", "Player2"};
        this.currentPlayer = ((int) (Math.random() * 2)); // 0 or 1
        this.pickedBeans = 0;
        this.board = new Board();
    }

    public String getVersion() {
        return this.version;
    }

    public void setPlayerName(int player, String name) {
        this.playersName[player] = name;
    }

    public String getPlayerName(int player) {
        return this.playersName[player];
    }

    /**
     * Gets the current player who is playing.
     * 
     * @return 0 or 1 (player0 or player1)
     */
    public int getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * Gets the amount of beans which are currently picked.
     * 
     * @return the amount of beans in hand.
     */
    public int getPickedBeans() {
        return this.pickedBeans;
    }

    public void setPickedBeans(int beans) {
        this.pickedBeans = beans;
    }

    public void putBean(Position p) {
        this.board.putBean(p);
        this.pickedBeans--;
    }

    /**
     * Gets the amount of beans in a Hole.
     * 
     * @param position the Position of the Hole.
     * @return the amount of beans in the Hole.
     */
    public int getBeans(Position position) {
        return this.board.getBeans(position);
    }

    public void clearHole(Position p) {
        this.board.clearHole(p);
    }

    public int getPot(int player) {
        return this.board.getPot(player);
    }

    public boolean canSteal(Position p) {
        return ((currentPlayer == p.getPlayer())
             && (this.board.getBeans(p) == 1)
             && (this.board.getBeans(p.invert()) > 0)
             && (this.pickedBeans == 0));
    }

    public int steal(Position p) {
        this.board.addToPot(this.currentPlayer, this.board.getBeans(p));
        clearHole(p);
        this.board.addToPot(this.currentPlayer, this.board.getBeans(p.invert()));
        int stolen = this.board.getBeans(p.invert());
        clearHole(p.invert());
        return stolen;
    }

    /**
     * Change player.
     * 
     * player0 -> player1
     * 
     * player1 -> player0
     */
    public void nextPlayer() {
        this.currentPlayer = (1 - this.currentPlayer);
    }

    /**
     * Checks if a Position is inside the board.
     * 
     * Player must be 0 or 1.
     * 
     * Hole must be between 0 and 5.
     * 
     * @param position the Position we want to check.
     * @return true if the Position is inside, false if not.
     */
    public boolean isInside(Position position) {
        return ((position.getPlayer() == 0 || position.getPlayer() == 1)
                && (position.getHole() >= 0 && position.getHole() <= 5));
    }

    /**
     * Checks if we can play a Hole.
     * 
     * We can play a hole only if there is at least one bean in it.
     * 
     * @param position the Position where we want to check.
     * @return true if the Hole isn't empty, false if it is.
     */
    public boolean canBeTaken(Position position) {
        return this.board.canBeTaken(position);
    }

    public void takeHole(Position p) {
        this.pickedBeans = getBeans(p);
        this.board.clearHole(p);
        while (pickedBeans > 0) {
            p.nextHole(this.currentPlayer);
            this.board.putBean(p);
        }
    }

    public boolean isGameOver() {
        return this.board.gameOver();
    }

    /**
     * Checks who won the game (player0 or player1).
     * 
     * @return 0 or 1. (-1 if no winner = error)
     */
    public void endGame() {
        int clean = (1 - this.board.emptySide());
        for (int i = 0; i < 6; i++) {
            this.board.addToPot(clean, this.board.getBeans(new Position(clean, i)));
            this.board.clearHole(new Position(clean, i));
        }
    }
}
