package src.com.lhumphr2.chess.model.ChessPieces;

import src.com.lhumphr2.chess.model.ChessGameUtils;
import src.com.lhumphr2.chess.model.Coord;
import src.com.lhumphr2.chess.model.Player;

/**
 * This is the class for the pawn piece.
 */

public class Pawn extends ChessPiece {
	private boolean hasMoved;

    public Pawn(Player p, Coord coord) {
        this.hasMoved = false;
        this.currentPosition = coord;
        this.player = p;
        this.name = ChessPieceName.PAWN;
        this.origin = new Coord(coord);
    }

	public Pawn(Player p, int xCoord, int yCoord) {
		this.hasMoved = false;
		this.player = p;
        this.currentPosition = new Coord(xCoord, yCoord);
        this.name = ChessPieceName.PAWN;
        this.origin = new Coord(xCoord, yCoord);
	}


    public ChessPieceName getName() {
        return name;
    }

    /**
	 * This is the main method which makes use of addNum method.
   	 * @param col Desired destination column with range [0, boardWidth)
   	 * @param row Desired destination row with range [0, boardHeight)
   	 * @return true if the desired move is valid
   	 *		   i.e. if not out of bounds or movement not obstructed
   	 */
	public boolean canMoveTo(int col, int row) {
        // Check for traveling in the wrong direction
        if ((this.getPlayerID() % 2 == 1) && (row > this.getCurrentPosition().getY())) {
            return false;
        }

        if ((this.getPlayerID() % 2 == 0) && (row < this.getCurrentPosition().getY())) {
            return false;
        }

        Coord currPos = this.getCurrentPosition();

        int diffX = Math.abs(col - currPos.getX());
        int diffY = Math.abs(row - currPos.getY());

        // We can't move horizontally unless we're capturing someone
        if (diffX > 0) {
            return this.checkDiagonals(this);
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
    private boolean checkDiagonals(Pawn pawn) {
        if(pawn.getPlayerID()%2 == 1) {
            Coord leftDiag = new Coord(pawn.getCurrentPosition().getX()-1, pawn.getCurrentPosition().getY()-1);
            Coord rightDiag = new Coord(pawn.getCurrentPosition().getX()+1, pawn.getCurrentPosition().getY()-1);
            return (ChessGameUtils.isEnemyHere(leftDiag, pawn.getPlayer()) ||
                    ChessGameUtils.isEnemyHere(rightDiag, pawn.getPlayer()));
        }

        else {
            Coord leftDiag = new Coord(pawn.getCurrentPosition().getX()-1, pawn.getCurrentPosition().getY()+1);
            Coord rightDiag = new Coord(pawn.getCurrentPosition().getX()+1, pawn.getCurrentPosition().getY()+1);
            return (ChessGameUtils.isEnemyHere(leftDiag, pawn.getPlayer()) ||
                    ChessGameUtils.isEnemyHere(rightDiag, pawn.getPlayer()));
        }
    }

    /**
	 * Indicative of whether or not this pawn can move two spaces.
	 * @return True if the piece hasn't moved yet
	 */
    public boolean isFirstMove() {
        return !hasMoved;
    }

    /**
     * We can no longer move two spaces.
     */
    public void moved() {
        hasMoved = true;
    }


}