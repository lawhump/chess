package src.com.lhumphr2.chess.model;

import src.com.lhumphr2.chess.model.ChessPieces.ChessPiece;

/**
 * This represent the the Model at the highest level.
 * Created by lawrencehumphrey on 9/21/15.
 */
public class Tournament {
    private static Tournament _tournament;
    private ChessGame chessGame;

    private Tournament(String name1, String name2) {
        _tournament = this;
        chessGame = ChessGame.getInstance(name1, name2);
    }

    /**
     * TODO I'm verrrrrrrrrrry scared this will return null one of these days
     * @return NOT NULL FOR THE LOVE OF GOD
     */
    public static Tournament getInstance() {
        return _tournament;
    }

    /**
     * Get the running instance of the tournament Model.
     * More likely one isn't running, so make one.
     * @param name1 Name of p1
     * @param name2 Name of p2
     * @return Tournament with p1 and p2
     */
    public static Tournament getInstance(String name1, String name2) {
        if(_tournament == null) {
            System.out.println("We are in the tournament model. P1 name is " + name1 +
                " and p2 name is " + name2);
            return new Tournament(name1, name2);
        }
        return _tournament;
    }

    /**
     * Whose turn is it.
     * @return The player whose turn it is
     */
    public static Player getWhoseTurn() {
        return ChessGame.getWhoseTurn();
    }

    /**
     * The player whose turn it isn't
     * @return As the title
     */
    public static Player getOtherPlayer() {
        return ChessGame.getOtherPlayer();
    }

    /**
     * There is a piece here. Return it.
     * @param here The coordinate to be queried
     * @return The ChessPiece that resides here
     */
    public ChessPiece getOccupant(Coord here){
        return ChessBoard.board.get(ChessGameUtils.to1DCoord(here));
    }

    /**
     * The integer representation of the turn count
     * @return The number indicating what the turn count is
     */
    public static int getTurnCount() {
        return ChessGame.getTurnCount();
    }

    /**
     * Increase the turn count by one.
     */
    public static void incrementTurn() {
        ChessGame.incrementTurn();
    }

    /**
     * Decrease the turn count by one.
     */
    public static void decrementTurn() {
        ChessGame.decrementTurn();
    }

    /**
     * Start anew
     */
    public void setupNewGame() {
        ChessGame.newGame();
    }

    /**
     * Revert board back to previous state
     */
    public static void undo() {
        ChessBoard.undo();
    }
}
