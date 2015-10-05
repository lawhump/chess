package src.com.lhumphr2.chess.model.ChessPieces;

import src.com.lhumphr2.chess.model.ChessGameUtils;
import src.com.lhumphr2.chess.model.Coord;
import src.com.lhumphr2.chess.model.Player;

/**
 * Created by lawrencehumphrey on 9/10/15.
 */
public class Knight extends ChessPiece{
    /**
     * This will be the legit constructor
     * */
    public Knight(Player p, Coord coord) {
        this.currentPosition = coord;
        this.player = p;
        this.name = ChessPieceName.KNIGHT;
        this.origin = new Coord(coord);
    }

    /**
     * This will be another legit constructor
     * */
    public Knight(Player p, int xCoord, int yCoord) {
        this.currentPosition = new Coord(xCoord, yCoord);
        this.player = p;
        this.name = ChessPieceName.KNIGHT;
        this.origin = new Coord(xCoord, yCoord);
    }

    @Override
    public boolean canMoveTo(int col, int row) {
        Coord currPos = this.getCurrentPosition();

        int origX = currPos.getX();
        int origY = currPos.getY();

        int diffX = Math.abs(col - origX);
        int diffY = Math.abs(row - origY);

        // Not an L
        if (!this.isAnLShape(diffX, diffY)) {
            return false;
        }

        Coord dest = new Coord(col, row);

        // Only other way you can't move there is if there's an ally there
        if (ChessGameUtils.isAllyHere(dest, this.getPlayer())) {
            return false;
        }

        return true;
    }

    /**
     * The knight can only move L-shaped patterns
     * @param diffX Absolute value (currX - destX)
     * @param diffY Absolute value (currY - destY)
     * @return True if the movement is an L-shape
     */
    private boolean isAnLShape(int diffX, int diffY) {
        return (diffX == 2 && diffY == 1) || (diffX == 1 && diffY == 2);
    }

    public ChessPieceName getName() {
        return name;
    }
}
