package src.com.lhumphr2.chess.model.ChessPieces;

import src.com.lhumphr2.chess.model.ChessGameUtils;
import src.com.lhumphr2.chess.model.Coord;
import src.com.lhumphr2.chess.model.Player;

/**
 * Created by lawrencehumphrey on 9/14/15.
 */
public class Bishop extends Queen {

    /**
     * This will be the legit constructor
     * */
    public Bishop(Player p, Coord coord) {
        super(p, coord);
        this.name = ChessPieceName.BISHOP;
    }

    /**
     * This will be another legit constructor
     */
    public Bishop(Player p, int xCoord, int yCoord) {
        super(p, xCoord, yCoord);
        this.name = ChessPieceName.BISHOP;
    }

    // canMoveTo(int col, int row) implemented by Queen
}
