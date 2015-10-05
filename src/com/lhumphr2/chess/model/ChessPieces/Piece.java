package src.com.lhumphr2.chess.model.ChessPieces;

import src.com.lhumphr2.chess.model.Player;

/**
 * Generic version of the Piece class.
 * Ideally should work with any 2D game with pieces
 * i.e. ChessPieceName in this exercisea
 */

public abstract class Piece {
	 Player player;

    /**
     * Every piece can move. Otherwise what are they good for?
     * Makes the assumption that it's a piece in 2 dimensional space.
     * @param col Desired destination column — x value
     * @param row Desired destination row — y value
     * @throws Exception If the move in invalid, what the problem was.
     */
	public abstract void moveTo (int col, int row) throws Exception;

    /**
     * Checks to see if desired move is within the behavior of this piece.
     * @param col Desired destination column — x value
     * @param row Desired destination row — y value
     * @return True if the move is acceptable under the limitations of this piece's moveset.
     */
    public abstract boolean canMoveTo(int col, int row);

    public Player getPlayer() {
        return player;
    }
}
