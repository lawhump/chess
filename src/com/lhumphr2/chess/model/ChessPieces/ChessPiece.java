package src.com.lhumphr2.chess.model.ChessPieces;

import src.com.lhumphr2.chess.model.*;

import java.util.Stack;

public abstract class ChessPiece extends Piece {
    public ChessPieceName name;
    Coord currentPosition;
    Coord origin;

    public ChessPiece() { /* yeah yeah yeah */ }

    public ChessPiece(Coord coord) {
        this.currentPosition = new Coord(coord);
        this.origin = new Coord(coord);
    }

    /**
     * This is the main method. The bread and butter of movement, if you will.
     * @param col Desired destination column with range [0, boardWidth)
     * @param row Desired destination row with range [0, boardHeight)
     */
    public void moveTo(int col, int row) throws IndexOutOfBoundsException,
            GetOutOfCheckException, PutsYouInCheckException,
            OriginEqualsDestination, InvalidMove {
        try {
            checkIfAnObviousInvalidMove(col, row);
        }

        catch (OriginEqualsDestination originEqualsDestination) {
            originEqualsDestination.printStackTrace();
            throw originEqualsDestination;
        }

        // Now we have to check the non-obvious cases.
        Player currPlayer = this.getPlayer();
        King myKing = ChessGameUtils.getMyKing(currPlayer);
        // If they aren't in check, move. Allow unless it puts them in check.
        if (currPlayer.isNotInCheck()) {
            currentlyNotInCheckMovement(col, row, currPlayer, myKing);
        }

        // They are in check.
        else {
            currentlyInCheckMovement(col, row, myKing);
        }
    }

    // TODO Include history for this method. Not bothering with it now.
    private void currentlyInCheckMovement(int col, int row, King myKing) throws GetOutOfCheckException {
        if (myKing.getNumThreats() > 1) {
            if (ChessGameUtils.pieceName(this) != ChessPieceName.KING) {
                throw new GetOutOfCheckException();
            }

            if (canMoveOutOfCheck(this, col, row)) {
                // move
            }

            ChessGameUtils.gameOver();
        }

        else {
            boolean move  = canMoveOutOfCheck(this, col, row);
            boolean kill  = canKillOutOfCheck(this, col, row);
            boolean block = canBlockOutOfCheck(this, col, row);

            if (move || kill || block) {
                // Accept the move
                int idx = ChessGameUtils.to1DCoord(col, row);
                ChessBoard.board.put(idx, this);
                ChessBoard.board.remove(ChessGameUtils.to1DCoord(this.getCurrentPosition()));
                this.currentPosition.setX(col);
                this.currentPosition.setY(row);
            }

            if(!move && !kill && !block) {
                ChessGameUtils.gameOver();
            }

            // There are still options to get out of check.
            // Make the user get out of check.
            throw new GetOutOfCheckException();
        }
    }

    private void currentlyNotInCheckMovement(int col, int row, Player currPlayer, King myKing) throws PutsYouInCheckException, InvalidMove {
        if (this.canMoveTo(col, row)) {
            int idx1 = ChessGameUtils.to1DCoord(currentPosition.getX(), currentPosition.getY());
            int idx2 = ChessGameUtils.to1DCoord(col, row);

            State s1 = new State(idx1, this);
            State s2 = new State(idx2, ChessBoard.board.get(idx2));

            ChessBoard.pushHistory(s1);
            ChessBoard.pushHistory(s2);

            // If this move put me in check, restore the board and don't allow it
            if(ChessGameUtils.inCheck(myKing)) {
                ChessBoard.undo();
                throw new PutsYouInCheckException();
            }

            // The move didn't put me in check
            this.currentPosition.setX(col);
            this.currentPosition.setY(row);
            isOpponentInCheck(currPlayer);

            return;
        }

        // Not necessarily accurate. It could be that they can't move in that specified manner.
        else {
            throw new InvalidMove();
        }
    }

    public Coord getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Coord currentPosition) {
        this.currentPosition = currentPosition;
    }

    /**
     * Called when this player is in check and is attempting to move this piece.
     * Checks if the movement obstructs the path.
     * @param chessPiece This chess piece
     * @param col Desired destination column — x value
     * @param row Desired destination row — y value
     * @return True if this piece obstructs path
     */
    private boolean canBlockOutOfCheck(ChessPiece chessPiece, int col, int row) {
        return false;
    }

    /**
     * Called when this player is in check and is attempting to move this piece.
     * Checks if this piece captures the attacking piece.
     * @param chessPiece This chess piece
     * @param col Desired destination column — x value
     * @param row Desired destination row — y value
     * @return True if this piece obstructs path
     */
    private boolean canKillOutOfCheck(ChessPiece chessPiece, int col, int row) {
        return false;
    }

    /**
     * Called when this player is in check and is attempting to move this piece.
     * Checks if this is the king and can move out of check.
     * @param chessPiece This chess piece
     * @param col Desired destination column — x value
     * @param row Desired destination row — y value
     * @return True if this piece obstructs path
     */
    private boolean canMoveOutOfCheck(ChessPiece chessPiece, int col, int row) {
        if (chessPiece.getClass().getSimpleName() != "King") {
            return false;
        }

        // Check if by moving here, they are no longer in check.
        return false;
    }

    /**
     * Called after the current player successfully moves. Checks if the opponent
     * is now in check and updates inCheck accordingly.
     * @param currPlayer The current player
     */
    private void isOpponentInCheck(Player currPlayer) {
        Player opponent = ChessGameUtils.getOtherPlayer(currPlayer);
        King otherKing  = ChessGameUtils.getMyKing(opponent);
        if(ChessGameUtils.inCheck(otherKing)) {
            opponent.setInCheck(true);
        }
    }

    /**
     * TODO
     * Coupled with setStateOfBoard(). Called if the attempted move is invalid
     * and resets the board to its configuration before the move.
     * @param history Moves that happened this turn
     */
    private void restoreStateOfBoard(Stack<State> history) {
        // Re-map things in the history
    }

    private void setStateOfBoard(int col, int row, Stack<State> history) {
        State origin = new State(ChessGameUtils.to1DCoord(this.getCurrentPosition()), this);
        int destIdx  = ChessGameUtils.to1DCoord(col, row);
        State dest   = new State(destIdx, ChessBoard.board.get(destIdx));

        history.push(origin);
        history.push(dest);
    }

    /**
     * Does all of the obvious error handling, i.e. indexoutofbounds and moving
     * to current position.
     * @param col Desired destination column — x value
     * @param row Desired destination row — y value
     * @throws Exception
     */
    private void checkIfAnObviousInvalidMove(int col, int row) throws IndexOutOfBoundsException, OriginEqualsDestination {
        // I.e. don't move to a negative position.
        if (!ChessGameUtils.isPositive(col, row)) {
            throw new IndexOutOfBoundsException();
        }

        // IndexOutOfBounds isn't cool man
        if (!(ChessGameUtils.isWithinBounds(col, row))){
            throw new IndexOutOfBoundsException();
        }

        Coord currPos = this.getCurrentPosition();

        int origX = currPos.getX();
        int origY = currPos.getY();

        int diffX = Math.abs(col - origX);
        int diffY = Math.abs(row - origY);

        // You have to move somewhere, pal.
        if (ChessGameUtils.movedToCurrentPosition(diffX, diffY)) {
            throw new OriginEqualsDestination();
        }
    }

    public int getPlayerID() {
        return this.getPlayer().getID();
    }

    public Coord getOrigin() {
        return this.origin;
    }

    public class GetOutOfCheckException extends Exception {
        public GetOutOfCheckException() {
            super();
        }
    }

    public class PutsYouInCheckException extends Exception {
        public PutsYouInCheckException() {
            super();
        }
    }

    public class InvalidMove extends Exception {
        public InvalidMove() {
            super();
        }
    }

    public class OriginEqualsDestination extends Exception {
        public OriginEqualsDestination() {
            super();
        }
    }
}
