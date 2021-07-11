package LuckyMancala.src.model;

/**
 * A position in the board.
 * 
 * Player is the number of the player (the row of the board) and Hole is the
 * number of the hole (the column of the board).
 * 
 * Player {0} -> Holes {0, 1, 2, 3, 4, 5}
 * 
 * Player {1} -> Holes {0, 1, 2, 3, 4, 5}
 */
public class Position {
    private int player;
    private int hole;
    private boolean inPot;

    /**
     * Position of a hole in the board.
     * 
     * @param player the row of the board (0 or 1).
     * @param hole   the column of the board (0 to 5).
     */
    public Position(int player, int hole) {
        this.player = player;
        this.hole = hole;
        this.inPot = false;
    }

    /**
     * Gets the player (the row of the board).
     * 
     * @return 0 or 1.
     */
    public int getPlayer() {
        return this.player;
    }

    /**
     * Gets the hole (the column of the board).
     * 
     * @return 0 to 5.
     */
    public int getHole() {
        return this.hole;
    }

    public boolean isInPot() {
        return this.inPot;
    }

    public void nextHole(int player) {
        if (inPot) {
            this.player = 1 - this.player;
            inPot = false;
        } else {
            if (this.player == 0) {
                if (this.hole > 0) {
                    this.hole--;
                } else {
                    if (player == 0) {
                        inPot = true;
                    } else {
                        this.player = 1;
                    }
                }
            } else {
                if (this.hole < 5) {
                    this.hole++;
                } else {
                    if (player == 1) {
                        inPot = true;
                    } else {
                        this.player = 0;
                    }
                }
            }
        }
    }

    public Position invert() {
        return new Position((1 - this.player), this.hole);
    }
}
