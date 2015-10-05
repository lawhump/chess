package src.com.lhumphr2.chess.model;

/**
 * Created by lawrencehumphrey on 9/17/15.
 * Typical chess game between you and a pal or a pal and another pal.
 * Basically just two players and a chess board mentally duking it out.
 * Oh, and only one game at a time. Singleton class. W/e.
 */
public class ChessGame {
    private static ChessGame _instance;
    private static ChessBoard chessBoard;
    private static Player p1;
    private static Player p2;
    private static int turn = 1;

    public static ChessGame getInstance() {
        if (_instance == null) {
            return new ChessGame();
        }
        return _instance;
    }

    public static ChessGame getInstance(String nameP1, String nameP2) {
        if (_instance == null) {
            return new ChessGame(nameP1, nameP2);
        }
        return _instance;
    }

    private ChessGame() {
        _instance = this;
        p1 = new Player();
        p2 = new Player();
        chessBoard = ChessBoard.getInstance();
    }

    private ChessGame(String nameP1, String nameP2) {
        _instance = this;
        p1 = new Player(nameP1);
        p2 = new Player(nameP2);
        chessBoard = ChessBoard.getInstance();
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    /**
     * Get Player 1
     * @return Player object who has ID == 1
     */
    public static Player getP1() {
        return p1;
    }

    /**
     * Get Player 2
     * @return Player object who has ID == 2
     */
    public static Player getP2() {
        return p2;
    }

    /**
     * Integer representing the turn count
     * @return The turn count
     */
    public static int getTurnCount() {
        return turn;
    }

    /**
     * The player whose turn it is
     * @return The player whose turn it currently is
     */
    public static Player getWhoseTurn() {
        return (turn % 2 == 1) ? p1 : p2;
    }

    /**
     * The player whose turn it isn't
     * @return The player whose turn it currently isn't
     */
    public static Player getOtherPlayer() {
        return (turn % 2 == 1) ? p2 : p1;
    }

    /**
     * Increase the turn counter by one
     */
    public static void incrementTurn() {
        turn++;
    }

    /**
     * Decrease the turn counter by one
     */
    public static void decrementTurn() {
        turn--;
    }

    /**
     * Reset the stats of the players
     */
    public static void resetPlayers() {
        p1.reset();
        p2.reset();
    }

    public static int getBoardSize() {
        return chessBoard.board.size();
    }

    /**
     * Resets board
     */
    public static void newGame() {
        turn = 1;
        ChessBoard.initBoard();
    }
}
