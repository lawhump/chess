package src.com.lhumphr2.chess.model.ChessPieces;

import src.com.lhumphr2.chess.model.Coord;
import src.com.lhumphr2.chess.model.Player;

/**
 * Created by lawrencehumphrey on 9/10/15.
 */
public class King extends ChessPiece {
    private int numThreats = 0;

    /**
     * This will be the legit-ass constructor
     * */
    public King(Player p, Coord coord) {
        this.currentPosition = new Coord(coord);
        this.origin = new Coord(coord);
        this.player = p;
        this.name = ChessPieceName.KING;
    }

    /**
     * This will be another legit-ass constructor
     * */
    public King(Player p, int xCoord, int yCoord) {
        this.currentPosition = new Coord(xCoord, yCoord);
        this.origin = new Coord(xCoord, yCoord);
        this.player = p;
        this.name = ChessPieceName.KING;
    }

    @Override
    public boolean canMoveTo(int col, int row) {
        Coord currPos = this.getCurrentPosition();

        int origX = currPos.getX();
        int origY = currPos.getY();

        int diffX = Math.abs(col - origX);
        int diffY = Math.abs(row - origY);

        // More than one step away
        if (this.moreThanOneSpaceAway(diffX, diffY)) {
            return false;
        }

        return true;
    }

    private boolean moreThanOneSpaceAway(int diffX, int diffY) {
        return diffX > 1 || diffY > 1;
    }

    public ChessPieceName getName() {
        return name;
    }

    public int getNumThreats() {
        return numThreats;
    }

    public void setNumThreats(int numThreats) {
        this.numThreats = numThreats;
    }

    public void encounteredThreat() {
        this.numThreats++;
    }
}
