package src.com.lhumphr2.chess.model.ChessPieces;

import src.com.lhumphr2.chess.model.ChessBoard;
import src.com.lhumphr2.chess.model.ChessGameUtils;
import src.com.lhumphr2.chess.model.Coord;
import src.com.lhumphr2.chess.model.Player;

/**
 * Created by lawrencehumphrey on 9/17/15.
 */
public class Timehopper extends ChessPiece {

    public Timehopper(Player player, Coord coord) {
        this.currentPosition = new Coord(coord);
        this.player = player;
        this.name = ChessPieceName.TIMEHOPPER;
        this.origin = new Coord(coord);
    }

    public Timehopper(Player player, int xCoord, int yCoord) {
        this.currentPosition = new Coord(new Coord(xCoord, yCoord));
        this.player = player;
        this.name = ChessPieceName.TIMEHOPPER;
        this.origin = new Coord(new Coord(xCoord, yCoord));
    }


    @Override
    public boolean canMoveTo(int col, int row) {
        Coord currPos = this.getCurrentPosition();

        int origX = currPos.getX();
        int origY = currPos.getY();

        int diffX = Math.abs(col - origX);
        int diffY = Math.abs(row - origY);

        // Limit movement to two spaces in any direction
        if (diffX > 2 || diffY > 2) {
            return false;
        }

        // Check if a valid diagonal move
        if (diffX > 0 && diffY > 0) {
            if (ChessGameUtils.isNonDiagonalMove(diffX, diffY)) {
                return false;
            }
        }

        // Don't allow them to TP them if someone is at their home.
        if (ChessGameUtils.isAnyoneHere(new Coord(col,row))) {
            ChessPiece inhabitant = ChessBoard.board.get(ChessGameUtils.to1DCoord(col, row));
            if (ChessGameUtils.isAnyoneHere(inhabitant.getOrigin())) {
                return false;
            }
            // Send them home
            ChessBoard.board.put(ChessGameUtils.to1DCoord(inhabitant.getOrigin()), inhabitant);
        }
        return true;
    }

    public ChessPieceName getName() {
        return name;
    }
}
