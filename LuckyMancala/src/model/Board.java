package LuckyMancala.src.model;

/**
 * The board of Lucky-Mancala.
 * 
 * Two pots and 12 holes (6 for each player).
 */
public class Board {
    private int[] pots;
    private int[][] board;

    /**
     * Initialisation of the board.
     * 
     * Four beans in each holes and no bean in both pots.
     */
    public Board() {
        this.pots = new int[] { 0, 0 };
        this.board = new int[][] { { 4, 4, 4, 4, 4, 4 }, { 4, 4, 4, 4, 4, 4 } };
    }

    /**
     * Gets the amount of beans in the pot of a player.
     * 
     * @param player the player we want to check.
     * @return the amount of beans in the pot of the player.
     */
    public int getPot(int player) {
        return this.pots[player];
    }

    /**
     * Gets the amount of beans in a Hole.
     * 
     * @param position the Position of the Hole.
     * @return the amount of beans in the Hole.
     */
    public int getBeans(Position position) {
        return this.board[position.getPlayer()][position.getHole()];
    }

    public void clearHole(Position p) {
        this.board[p.getPlayer()][p.getHole()] = 0;
    }

    public void putBean(Position p) {
        if (p.isInPot()) {
            this.pots[p.getPlayer()]++;
        } else {
            this.board[p.getPlayer()][p.getHole()]++;
        }
    }

    public void addToPot(int player, int amount) {
        this.pots[player] += amount;
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
        return this.board[position.getPlayer()][position.getHole()] >= 0;
    }

    /**
     * Checks if a side of the board is empty. If one of the side is empty, then the
     * game is over.
     * 
     * @return true if the game is over, false if not.
     */
    public boolean gameOver() {

        int cpt = 0;

        // Check player 0
        for (int i : this.board[0]) {
            cpt += i;
        }
        if (cpt == 0) {
            return true; // Player 0's side is empty
        }

        cpt = 0;

        // Check player 1
        for (int i : this.board[1]) {
            cpt += i;
        }
        if (cpt == 0) {
            return true; // Player 0's side is empty
        }
        return false; // Both sides aren't empty
    }

    /**
     * Checks if a side of the board is empty. If one of the side is empty, then the
     * game is finished.
     * 
     * This method will return the number of the player who's side is empty (0 or
     * 1), or it will return -1 if no side is empty.
     * 
     * @return a player number (or -1 if no side is empty = error).
     */
    public int emptySide() {

        int cpt = 0;

        // Check player 0
        for (int i : this.board[0]) {
            cpt += i;
        }
        if (cpt == 0) {
            return 0; // Player 0's side is empty
        }

        cpt = 0;

        // Check player 1
        for (int i : this.board[1]) {
            cpt += i;
        }
        if (cpt == 0) {
            return 1; // Player 0's side is empty
        }
        return -1; // Both sides aren't empty
    }
}
