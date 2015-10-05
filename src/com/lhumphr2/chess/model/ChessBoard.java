package src.com.lhumphr2.chess.model;


import src.com.lhumphr2.chess.model.ChessPieces.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Singleton chess game class.
 * P1 is the side closest to us i.e. height-1
 */

public class ChessBoard {
	private static ChessBoard _instance = null;
	// Rules.txt

	private static int height = 8;
	private static int width = 8;
	// Only care about the tiles with pieces on them
	public static Map<Integer, ChessPiece> board;

    // Stack of moves that were made. For undo.
    private static Stack<State> history;

    // So I don't have to look for the kings every time
    static King k1;
    static King k2;

    /**
     * Classic chess setup with 8x8 board and two players
     */
    private ChessBoard() {
        _instance = this;
        board = new HashMap<>();
        history = new Stack<>();
        initBoard();
	}

    /**
     * Custom board size with width w and height h
     * @param h Desired height of the board
     * @param w Desired width of the board
     */
    private ChessBoard(int h, int w) {
        _instance = this;
        height = h;
		width = w;
        board = new HashMap<>();
        history = new Stack<>();
        initBoard();
	}

    /**
	 * There can only be one... chess board! This checks to see if there
	 * already exists an instance of ChessBoard. If not, make and return it,
	 * if so return the existing one.
     * This is the standard 8x8 board with traditional pieces.
   	 * @return ChessGames instance
   	 */
	public static ChessBoard getInstance() {
		if (_instance == null) {
			return new ChessBoard();
		}
		return _instance;
	}


    /**
     * In the event that I extend this one day
     * @param h Custom desired height
     * @param w Custom desired width
     * @return The new chess board instance
     */
    public static ChessBoard getInstance(int h, int w) {
        if (_instance == null) {
            return new ChessBoard(h, w);
        }
        return _instance;
    }

    /**
     * Kind of like getInstance(), but it clears the board. Useful for testing.
     * @return A fresh ChessBoard instance
     */
    public static ChessBoard makeEmptyInstance() {
        if (_instance == null) {
            return new ChessBoard();
        }
        ChessGameUtils.clearBoard();
        return _instance;
    }

	/**
	 * Initializes the game board (puts pieces in correct positions).
   	 */
	static void initBoard() {
        Player p1 = ChessGame.getP1();
        Player p2 = ChessGame.getP2();
		// P1 Pawns
		for (int col=0; col<width; col++) {
            Pawn p = new Pawn(p1, col, height-2);
            board.put(ChessGameUtils.to1DCoord(col, height-2), p);
        }

        // P2 Pawns
        for (int col=0; col<width; col++) {
            Pawn p = new Pawn(p2, col, 1);
            board.put(ChessGameUtils.to1DCoord(col, 1), p);
        }

        // P1 Rooks
        Rook p1R1 = new Rook(p1, 0, height-1);
        Rook p1R2 = new Rook(p1, width-1, height-1);
        board.put(ChessGameUtils.to1DCoord(p1R1.getCurrentPosition()), p1R1);
        board.put(ChessGameUtils.to1DCoord(p1R2.getCurrentPosition()), p1R2);

        // P2 Rooks
        Rook p2R1 = new Rook(p2, 0, 0);
        Rook p2R2 = new Rook(p2, width-1, 0);
        board.put(ChessGameUtils.to1DCoord(p2R1.getCurrentPosition()), p2R1);
        board.put(ChessGameUtils.to1DCoord(p2R2.getCurrentPosition()), p2R2);

        // P1 Knights
        Knight p1K1 = new Knight(p1, 1, height-1);
        Knight p1K2 = new Knight(p1, width-2, height-1);
        board.put(ChessGameUtils.to1DCoord(p1K1.getCurrentPosition()), p1K1);
        board.put(ChessGameUtils.to1DCoord(p1K2.getCurrentPosition()), p1K2);

        // P2 Knights
        Knight p2K1 = new Knight(p2, 1, 0);
        Knight p2K2 = new Knight(p2, width-2, 0);
        board.put(ChessGameUtils.to1DCoord(p2K1.getCurrentPosition()), p2K1);
        board.put(ChessGameUtils.to1DCoord(p2K2.getCurrentPosition()), p2K2);

        // P1 Bishops
        Bishop p1B1 = new Bishop(p1, 2, height-1);
        Bishop p1B2 = new Bishop(p1, width-3, height-1);
        board.put(ChessGameUtils.to1DCoord(p1B1.getCurrentPosition()), p1B1);
        board.put(ChessGameUtils.to1DCoord(p1B2.getCurrentPosition()), p1B2);

        // P2 Bishops
        Bishop p2B1 = new Bishop(p2, 2, 0);
        Bishop p2B2 = new Bishop(p2, width-3, 0);
        board.put(ChessGameUtils.to1DCoord(p2B1.getCurrentPosition()), p2B1);
        board.put(ChessGameUtils.to1DCoord(p2B2.getCurrentPosition()), p2B2);

        // Queens
        Queen p1Q = new Queen(p1, 3, height-1);
        Queen p2Q = new Queen(p2, 3, 0);
        board.put(ChessGameUtils.to1DCoord(p1Q.getCurrentPosition()), p1Q);
        board.put(ChessGameUtils.to1DCoord(p2Q.getCurrentPosition()), p2Q);

        // Kings
        k1 = new King(p1, 4, height-1);
        k2 = new King(p2, 4, 0);
        board.put(ChessGameUtils.to1DCoord(k1.getCurrentPosition()), k1);
        board.put(ChessGameUtils.to1DCoord(k2.getCurrentPosition()), k2);
	}

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public static void pushHistory(State st) {
        history.push(st);
    }

    public static State popHistory() {
        return history.pop();
    }

    public static void undo() {
        // Don't allow user to pop from empty stack
        if (history.empty()) {
            return;
        }

        State s1 = history.pop();
        State s2 = history.pop();

        board.put(s1.idx, s1.piece);
        board.put(s2.idx, s2.piece);
    }

    /**
     * Helper for printing the state of the board.
     */
    public static void printBoard() {
        for (int row=0; row<height; row++) {
            System.out.print("|");
            for (int col=0; col<width; col++) {
                // Check for piece type
                    // Check for player
                ChessPiece cp = board.get(ChessGameUtils.to1DCoord(col, row));

                if(cp != null) {
                    // We have a piece
                    switch (cp.getClass().getSimpleName()) {
                        case "Pawn":
                            if (cp.getPlayer().getID() == 1) {
                                System.out.print("p|");
                            } else {
                                System.out.print("P|");
                            }
                            break;

                        case "Bishop":
                            if (cp.getPlayer().getID() == 1) {
                                System.out.print("b|");
                            } else {
                                System.out.print("B|");
                            }
                            break;

                        case "Knight":
                            if (cp.getPlayer().getID() == 1) {
                                System.out.print("n|");
                            } else {
                                System.out.print("N|");
                            }
                            break;

                        case "Rook":
                            if (cp.getPlayer().getID() == 1) {
                                System.out.print("r|");
                            } else {
                                System.out.print("R|");
                            }
                            break;

                        case "Queen":
                            if (cp.getPlayer().getID() == 1) {
                                System.out.print("q|");
                            } else {
                                System.out.print("Q|");
                            }
                            break;

                        case "King":
                            if (cp.getPlayer().getID() == 1) {
                                System.out.print("k|");
                            } else {
                                System.out.print("K|");
                            }
                            break;
                    }
                }
            }
            System.out.print("\n");
        }
    }
}