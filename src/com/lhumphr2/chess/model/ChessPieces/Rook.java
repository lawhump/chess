package src.com.lhumphr2.chess.model.ChessPieces;

import src.com.lhumphr2.chess.model.Coord;
import src.com.lhumphr2.chess.model.Player;

/**
 * This is the class for the Rook piece.
 */

public class Rook extends Queen {
	private boolean hasCastled;

    /**
     * This will be the legit constructor
     * */
    public Rook(Player p, Coord coord) {
        super(p, coord);
        this.hasCastled = false;
        this.name = ChessPieceName.ROOK;
    }

    /**
     * This will be another legit constructor
     */
    public Rook(Player p, int xCoord, int yCoord) {
        super(p, xCoord, yCoord);
        this.hasCastled = false;
        this.name = ChessPieceName.ROOK;
    }

    public boolean hasCastled() {
        return hasCastled;
    }
}
