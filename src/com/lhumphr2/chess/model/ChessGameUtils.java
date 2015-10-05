package src.com.lhumphr2.chess.model;

import src.com.lhumphr2.chess.model.ChessPieces.ChessPieceName;
import src.com.lhumphr2.chess.model.ChessPieces.King;
import src.com.lhumphr2.chess.model.ChessPieces.ChessPiece;

/**
 * Created by lawrencehumphrey on 9/6/15.
 */
public class ChessGameUtils {
    private static ChessGameUtils _instance = new ChessGameUtils();

    public static ChessGameUtils getInstance() {
        return _instance;
    }

    private ChessGameUtils() { }

    public static void clearBoard() {
        ChessBoard.board.clear();
    }

    public static void newGame() {
        clearBoard();
        ChessBoard.initBoard();
        ChessGame.resetPlayers();
    }

    public static void gameOver() {
        // Print winner and scores
        // Prompt for new game, change players, quit, etc
    }

    /**
     * Get the player who is not p
     * Assumption of only two players
     * @param p This is me. Don't return me.
     * @return Return the player who is not me
     */
    public static Player getOtherPlayer(Player p) {
        return (p.getID() == 1 ?  ChessGame.getP2(): ChessGame.getP1());
    }

    /**
     * Helpful function to get this player's king. Only limited to two players right now
     * I don't care leave me alone
     * @param p This player
     * @return Player p's king
     */
    public static King getMyKing(Player p) {
        return (p.getID() == 1 ? ChessBoard.k1 : ChessBoard.k2);
    }


    /**
     * TODO implement
     * Returns the point values of each player.
     * Pawns - 1, Bishops - 3, Knights - 3, Rooks - 5, Queen - 9
     * @return array where the ith score corresponds to the score of the ith player
     */
    public static int[] scoreBoard() {
        int[] scores = {0, 0};
        return scores;
    }

    /**
     * True if currPlayer is in check
     * @param currPlayer Player to be determined whether or not in check
     * @return True if currPlayer in check
     */
    public static boolean inCheck(Player currPlayer) {
        return inCheck(getMyKing(currPlayer));
    }

    /**
     * True if currPlayer is in checkmate. Sorry bro.
     * @param currPlayer Player to be determined whether or not in checkmate
     * @return True if currPlayer in checkmate
     */
    public static boolean inCheckmate(Player currPlayer) {
        return false;
    }

    /**
     * Useful in mapping pieces to places. Collapsing a 2D array to 1 dimension.
     * @param c The intuitive index on the board. Think of it as indexing a 2D array.
     * @return The coordinate in one dimensional form
     */
    public static int to1DCoord(Coord c) {
        int xIdx = c.getX();
        int yIdx = c.getY();
        int boardWidth = (ChessBoard.getInstance()).getWidth();

        return yIdx * boardWidth + xIdx;
    }

    /**
     * Useful in mapping pieces to places. Collapsing a 2D array to 1 dimension.
     * @param col Desired destination column — x value
     * @param row Desired destination row — y value
     * @return The coordinate in one dimensional form
     */
    public static int to1DCoord(int col, int row) {
        int boardWidth = (ChessBoard.getInstance()).getWidth();

        return row * boardWidth + col;
    }

    /**
     * Takes a point in 1 dimension to 2 dimensions. Builds a 2D array index from idx.
     * @param idx
     * @return A coord corresponding to the 2D index.
     */
    public static Coord to2DCoord(int idx) {
        int boardWidth = (ChessBoard.getInstance()).getWidth();

        int xIdx = idx % boardWidth;
        int yIdx = (int) Math.floor(idx / boardWidth);

        Coord cOut = new Coord(xIdx, yIdx);
        return cOut;
    }

    /**
     * Helper to place said piece in said position. No checks to see if it's successfully been
     * placed. Sorry bro.
     * @param cp Chess piece to be places
     * @param c Coordinate to place said piece.
     */
    public static void putPiece(ChessPiece cp, Coord c) {
        ChessBoard.board.put(to1DCoord(c), cp);
    }

    /**
     * Checks to see if you attempted to move to the position you are currently in
     * @param diffX Absolute value (currX - destX)
     * @param diffY Absolute value (currY - destY)
     * @return True if diffX and diffY equal 0
     */
    public static boolean movedToCurrentPosition(int diffX, int diffY) {
        return diffX == 0 && diffY == 0;
    }

    /**
     * Don't accept negative values. Period.
     * @param col Desired destination column — x value
     * @param row Desired destination row — y value
     * @return True if neither value is negative
     */
    public static boolean isPositive(int col, int row) {
        return col >= 0 && row >= 0;
    }

    /**
     * If the attempted move is within the confines of the board.
     * @param col Desired destination column — x value
     * @param row Desired destination row — y value
     * @return True if the attempted move is within the board
     */
    public static boolean isWithinBounds(int col, int row) {
        return (col < (ChessBoard.getInstance()).getWidth() && row < (ChessBoard.getInstance()).getHeight());
    }

    /**
     * If the direction of movement is to the left
     * @param origX The x value where we are originating
     * @param destX The x value where we wish to end
     * @return True if we are moving to the left
     */
    public static boolean destinationIsLeft(int origX, int destX) {
        return destX < origX;
    }

    /**
     * If the direction of movement is up
     * @param origY The y value where we are originating
     * @param destY The y value where we wish to end
     * @return True if we are moving to the left
     */
    public static boolean destinationIsUp(int origY, int destY) {
        return destY < origY;
    }

    /**
     * Assuming there's a piece here, is that piece an enemy.
     * @param dest The location to be checked for
     * @param currPlayer The player whose turn it is
     * @return True if the pieces have player IDs which aren't identical
     */
    public static boolean isEnemyHere(Coord dest, Player currPlayer) {
        int idx = to1DCoord(dest);
        Integer key = idx;

        ChessBoard cb = ChessBoard.getInstance();

        ChessPiece p = cb.board.get(key);
        // No piece occupies this spot
        if (p == null) {
            return false;
        }

        Player playerInDestination = p.getPlayer();

        return playerInDestination.getID() != currPlayer.getID();
    }

    /**
     * Assuming there's a piece here, is that piece an ally.
     * @param dest The location to be checked for
     * @param currPlayer The player whose turn it is
     * @return True if the pieces have player IDs which are identical
     */
    public static boolean isAllyHere(Coord dest, Player currPlayer) {
        int idx = to1DCoord(dest);
        Integer key = idx;

        ChessBoard cb = ChessBoard.getInstance();

        ChessPiece p = cb.board.get(key);
        // No piece occupies this spot
        if (p == null) {
            return false;
        }

        Player playerInDestination = p.getPlayer();

        return playerInDestination.getID() == currPlayer.getID();
    }

    /**
     * Checks to see if a piece inhabits this space.
     * @param dest Destination coordinate
     * @return True if anything is here.
     */
    public static boolean isAnyoneHere (Coord dest) {
        return ChessBoard.board.containsKey(to1DCoord(dest));
    }

    /**
     * Checks if king is in check
     * @param k King in question
     * @return True if king in check
     */
    public static boolean inCheck(King k) {
        return checkLinearCapture(k) || checkDiagonalCapture(k) || checkKnightCapture(k);
    }

    /**
     * Tests for check for pieces that move linearly, i.e. vertically and horizontally.
     * @param k King in question
     * @return True if king in check from the north, east, south, or west
     */
    private static boolean checkLinearCapture(King k) {
        return checkN(k) || checkE(k) || checkS(k) || checkW(k);
    }

    /**
     * Tests for check for pieces that move diagonally.
     * @param k King in question
     * @return True if king in check from the northwest, northeast, southwest, or southeast
     */
    private static boolean checkDiagonalCapture(King k) {
        return checkNW(k) || checkNE(k) || checkSW(k) || checkSE(k);
    }

    private static boolean checkKnightCapture(King k) {
        return checkLShapeCapture(k, 1, 2) ||
                checkLShapeCapture(k, 2, 1) ||
                checkLShapeCapture(k, 2, -1) ||
                checkLShapeCapture(k, 1, -2) ||
                checkLShapeCapture(k, -1, -2) ||
                checkLShapeCapture(k, -2, -1) ||
                checkLShapeCapture(k, -2, 1) ||
                checkLShapeCapture(k, 1, -2);
    }

    /**
     * Tests if king being attacked from the north
     * @param k King in question
     * @return True if being attacked from the north
     */
    private static boolean checkN(King k) {
        Coord c = k.getCurrentPosition();
        int xCoord = c.getX();
        int yCoord = c.getY();
        int kingsCoord = yCoord;

        // If we can get off of the board, we're not in check
        while(yCoord >= 0) {
            yCoord--;

            Boolean threat = checkForThreatsLin(k, xCoord, yCoord, kingsCoord);
            if (threat != null) {
                return threat;
            }
        }
        return false;
    }

    /**
     * Tests if king being attacked from the east
     * @param k King in question
     * @return True if being attacked from the east
     */
    private static boolean checkE(King k) {
        Coord c = k.getCurrentPosition();
        int xCoord = c.getX();
        int yCoord = c.getY();
        int kingsCoord = xCoord;

        // If we can get off of the board, we're not in check
        while(xCoord < (ChessBoard.getInstance()).getWidth()) {
            xCoord++;

            Boolean threat = checkForThreatsLin(k, xCoord, yCoord, kingsCoord);
            if (threat != null) {
                return threat;
            }
        }
        return false;
    }

    /**
     * Tests if king being attacked from the south
     * @param k King in question
     * @return True if being attacked from the north
     */
    private static boolean checkS(King k) {
        Coord c = k.getCurrentPosition();
        int xCoord = c.getX();
        int yCoord = c.getY();
        int kingsCoord = yCoord;

        // If we can get off of the board, we're not in check
        while(yCoord < (ChessBoard.getInstance()).getHeight()) {
            yCoord++;

            Boolean threat = checkForThreatsLin(k, xCoord, yCoord, kingsCoord);
            if (threat != null) {
                return threat;
            }
        }
        return false;
    }

    /**
     * Tests if king being attacked from the west
     * @param k King in question
     * @return True if being attacked from the west
     */
    private static boolean checkW(King k) {
        Coord c = k.getCurrentPosition();
        int xCoord = c.getX();
        int yCoord = c.getY();
        int kingsCoord = xCoord;

        // If we can get off of the board, we're not in check
        while(xCoord >= 0) {
            xCoord--;

            Boolean threat = checkForThreatsLin(k, xCoord, yCoord, kingsCoord);
            if (threat != null) {
                return threat;
            }
        }
        return false;
    }

    /**
     * Tests if king being attacked from the northwest
     * @param k King in question
     * @return True if being attacked from the northwest
     */
    private static boolean checkNW(King k) {
        Coord c = k.getCurrentPosition();
        int xCoord = c.getX();
        int yCoord = c.getY();

        // If we can get off of the board, we're not in check
        while(xCoord >= 0 && yCoord >= 0) {
            xCoord--;
            yCoord--;

            Boolean threat = checkForThreatsDiag(k, xCoord, yCoord);
            if (threat != null) {
                return threat;
            }
        }
        return false;
    }

    /**
     * Tests if king being attacked from the northeast
     * @param k King in question
     * @return True if being attacked from the northeast
     */
    private static boolean checkNE(King k) {
        Coord c = k.getCurrentPosition();
        int xCoord = c.getX();
        int yCoord = c.getY();

        // If we can get off of the board, we're not in check
        while(xCoord < (ChessBoard.getInstance()).getWidth() &&
                yCoord >= 0) {
            xCoord++;
            yCoord--;

            Boolean threat = checkForThreatsDiag(k, xCoord, yCoord);
            if (threat != null) {
                return threat;
            }
        }
        return false;
    }

    /**
     * Tests if king being attacked from the southwest
     * @param k King in question
     * @return True if being attacked from the southwest
     */
    private static boolean checkSW(King k) {
        Coord c = k.getCurrentPosition();
        int xCoord = c.getX();
        int yCoord = c.getY();

        // If we can get off of the board, we're not in check
        while(yCoord < (ChessBoard.getInstance()).getHeight() &&
                xCoord >= 0) {
            xCoord--;
            yCoord++;

            Boolean threat = checkForThreatsDiag(k, xCoord, yCoord);
            if (threat != null) {
                return threat;
            }
        }
        return false;
    }

    /**
     * Tests if king being attacked from the southeast
     * @param k King in question
     * @return True if being attacked from the southeast
     */
    private static boolean checkSE(King k) {
        Coord c = k.getCurrentPosition();
        int xCoord = c.getX();
        int yCoord = c.getY();

        // If we can get off of the board, we're not in check
        while(xCoord < ChessBoard.getInstance().getWidth() &&
                yCoord < ChessBoard.getInstance().getHeight()) {
            xCoord++;
            yCoord++;

            Boolean threat = checkForThreatsDiag(k, xCoord, yCoord);
            if (threat != null) {
                return threat;
            }
        }
        return false;
    }

    /**
     * Check for attacking pieces on the king's diagonals
     * @param k King in question
     * @param xCoord Current position in the x direction
     * @param yCoord Current position in the y direction
     * @return True if there's a threat on the diagonal
     */
    private static Boolean checkForThreatsDiag(King k, int xCoord, int yCoord) {
        ChessPiece piece = ChessBoard.board.get(ChessGameUtils.to1DCoord(xCoord, yCoord));
        if(piece != null) {
            Player me = k.getPlayer();
            Player occupant = piece.getPlayer();
            // Different teams
            if(occupant.getID() != me.getID()) {
                // Check to see if it's a piece that can capture linearly
                // namely a queen or bishop
                if (piece.name == ChessPieceName.BISHOP || piece.name == ChessPieceName.QUEEN) {
                    k.encounteredThreat();
                    return true;
                }

                // King or pawn could be here and could totally rekk you dude
                else if (piece.name == ChessPieceName.KING || piece.name == ChessPieceName.PAWN) {
                    Coord currPos = k.getCurrentPosition();
                    if (Math.abs(yCoord-currPos.getY()) <= 1) {
                        k.encounteredThreat();
                        return true;
                    }
                }

                // It's some other piece that can't hurt us
                else {
                    return false;
                }
            }
            // C'mon ya dingus we're on the same team
            else {
                return false;
            }
        }
        return null;
    }

    /**
     * Check if king is getting attacked horizontally or vertically
     * @param k King in question
     * @param xCoord Current position in the x direction
     * @param yCoord Current position in the y direction
     * @return True if there's a threat horizontally or vertically
     */
    private static Boolean checkForThreatsLin(King k, int xCoord, int yCoord, int kingsCoord) {
        ChessPiece piece = ChessBoard.board.get(ChessGameUtils.to1DCoord(xCoord, yCoord));
        if(piece != null) {
            Player me = k.getPlayer();
            Player occupant = piece.getPlayer();
            // Different teams
            if(occupant.getID() != me.getID()) {
                // Check to see if it's a piece that can capture linearly namely a queen or rook (or king)
                if (piece.name == ChessPieceName.ROOK || piece.name == ChessPieceName.QUEEN) {
                    k.encounteredThreat();
                    return true;
                }

                // King could be here and could totally rekk you dude
                else if (piece.name == ChessPieceName.KING) {
                    if (Math.abs(yCoord-kingsCoord) <= 1) {
                        k.encounteredThreat();
                        return true;
                    }
                }

                // It's some other piece that can't hurt us
                else {
                    return false;
                }
            }
            // It's our own teammate dude c'mon
            else {
                return false;
            }
        }
        return null;
    }

    /**
     * Check for spooky knights
     * @param k King in question
     * @param xOffset Movement in x direction {-2, -1, 1, 2}
     * @param yOffset Movement in x direction {-2, -1, 1, 2}
     * @return True if there's a spooky knight attacking us
     */
    private static boolean checkLShapeCapture(King k, int xOffset, int yOffset) {
        Coord kingPos = k.getCurrentPosition();
        int kingX = kingPos.getX();
        int kingY = kingPos.getY();

        ChessPiece piece = ChessBoard.board.get(ChessGameUtils.to1DCoord(kingX+xOffset, kingY+yOffset));
        if(piece != null) {
            Player me = k.getPlayer();
            Player occupant = piece.getPlayer();
            // Different teams
            if(occupant.getID() != me.getID()) {
                // Check to see if it's an enemy knight
                if (piece.name == ChessPieceName.KNIGHT) {
                    k.encounteredThreat();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if this piece is attempting to move vertically xor horizontally.
     * @param diffX Absolute value (currX - destX)
     * @param diffY Absolute value (currY - destY)
     * @return True if either diffX or diffY is nonzero
     */
    public static boolean isNonLinearMove(int diffX, int diffY) {
        return diffX > 0 && diffY > 0;
    }

    /**
     * Checks if this piece is attempting to move vertically xor horizontally.
     * @param diffX Absolute value (currX - destX)
     * @param diffY Absolute value (currY - destY)
     * @return True if either diffX or diffY is nonzero
     */
    public static boolean isNonDiagonalMove(int diffX, int diffY) {
        return diffX != diffY;
    }

    public static ChessPieceName pieceName(ChessPiece cp) {
        switch (cp.getClass().getSimpleName()) {
            case "Pawn":
                return ChessPieceName.PAWN;
            case "Bishop":
                return ChessPieceName.BISHOP;
            case "Knight":
                return ChessPieceName.KNIGHT;
            case "Rook":
                return ChessPieceName.ROOK;
            case "Queen":
                return ChessPieceName.QUEEN;
            case "King":
                return ChessPieceName.KING;
            case "Timehopper":
                return ChessPieceName.TIMEHOPPER;
        }
        // Please don't ever get here
        return null;
    }
}
