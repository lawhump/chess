package src.com.lhumphr2.chess.model.ChessPieces;

import src.com.lhumphr2.chess.model.ChessGameUtils;
import src.com.lhumphr2.chess.model.Coord;
import src.com.lhumphr2.chess.model.Player;

/**
 * Created by lawrencehumphrey on 9/24/15.
 */
public class PawnPlus extends Pawn {
    private boolean hasMoved;

    public PawnPlus(Player p, Coord coord) {
        super(p, coord);
        hasMoved = false;
        this.name = ChessPieceName.PAWNPLUS;
    }

    public PawnPlus(Player p, int xCoord, int yCoord) {
        super(p, xCoord, yCoord);
        hasMoved = false;
        this.name = ChessPieceName.PAWNPLUS;
    }

    /**
     * This is the main method which makes use of addNum method.
     * @param col Desired destination column with range [0, boardWidth)
     * @param row Desired destination row with range [0, boardHeight)
     * @return true if the desired move is valid
     *		   i.e. if not out of bounds or movement not obstructed
     */
    public boolean canMoveTo(int col, int row) {
        Coord currPos = this.getCurrentPosition();

        int diffX = Math.abs(col - currPos.getX());
        int diffY = Math.abs(row - currPos.getY());

        // We can't move horizontally unless we're capturing someone
        if (diffX > 0) {
            return this.checkLR(this);
        }

        else {
            if (diffY > 2) {
                return false;
            }

            else if (diffY == 2) {
                if (this.isFirstMove()) {
                    this.moved();
                    return true;
                }
                return this.isFirstMove();
            }

            else if (diffY == 1) {
                return true;
            }

            else {
                return false;
            }
        }
    }


    /**
     * Modulo arithmatic so that it can eventually be expanded to a tournament.
     * @return True if there is an enemy on my immediate diagonal.
     */
    private boolean checkLR(Pawn pawn) {
        if(pawn.getPlayerID()%2 == 1) {
            Coord left  = new Coord(pawn.getCurrentPosition().getX()-1, pawn.getCurrentPosition().getY());
            Coord right = new Coord(pawn.getCurrentPosition().getX()+1, pawn.getCurrentPosition().getY());
            return (ChessGameUtils.isEnemyHere(left, pawn.getPlayer()) ||
                    ChessGameUtils.isEnemyHere(right, pawn.getPlayer()));
        }

        else {
            Coord left = new Coord(pawn.getCurrentPosition().getX()-1, pawn.getCurrentPosition().getY());
            Coord right = new Coord(pawn.getCurrentPosition().getX()+1, pawn.getCurrentPosition().getY());
            return (ChessGameUtils.isEnemyHere(left, pawn.getPlayer()) ||
                    ChessGameUtils.isEnemyHere(right, pawn.getPlayer()));
        }
    }
}
