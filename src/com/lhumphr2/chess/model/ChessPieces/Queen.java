package src.com.lhumphr2.chess.model.ChessPieces;

import src.com.lhumphr2.chess.model.ChessGameUtils;
import src.com.lhumphr2.chess.model.Coord;
import src.com.lhumphr2.chess.model.Player;

/**
 * Created by lawrencehumphrey on 9/14/15.
 */
public class Queen extends ChessPiece {
    private final int SUCCESS = 0;
    private final int FAIL = 1;

    public Queen() {
        this.name = ChessPieceName.QUEEN;
        this.currentPosition = new Coord(0, 0);
        this.origin = new Coord(0, 0);
    }

    /**
     * This will be the legit constructor
     * */
    public Queen(Player p, Coord coord) {
        this.currentPosition = coord;
        this.player = p;
        this.name = ChessPieceName.QUEEN;
        this.origin = new Coord(coord);
    }

    /**
     * This will be another legit constructor
     */
    public Queen(Player p, int xCoord, int yCoord) {
        this.currentPosition = new Coord(xCoord, yCoord);
        this.player = p;
        this.name = ChessPieceName.QUEEN;
        this.origin = new Coord(xCoord, yCoord);
    }

    /**
     * This is the main method which checks if the movement of this piece is valid.
     * Tests movement unique to this piece
     * @param col Desired destination column with range [0, boardWidth)
     * @param row Desired destination row with range [0, boardHeight)
     * @return True if the desired move is valid
     *		   i.e. if not out of bounds or movement not obstructed
     */
    @Override
    public boolean canMoveTo(int col, int row){
        Coord currPos = this.getCurrentPosition();

        int origX = currPos.getX();
        int origY = currPos.getY();

        int diffX = Math.abs(col - origX);
        int diffY = Math.abs(row - origY);

        boolean travelingLinearly = false;
        boolean travelingDiagonally = false;

        // Maybe moving diagonally
        if (diffX > 0 && diffY > 0) {
            if (ChessGameUtils.isNonDiagonalMove(diffX, diffY)) {
                return false;
            }

            // Ok, definitely moving diagonally. Only allow if it's a bishop or queen.
            if (this.getName() == ChessPieceName.ROOK) {
                return false;
            }

            travelingDiagonally = true;
        }

        else {
            // Ok, definitely moving linearly. Only allow if it's a rook or queen.
            if (this.getName() == ChessPieceName.BISHOP) {
                return false;
            }

            travelingLinearly = true;
        }

        // Return value from movement
        int success = -1;
        // Let's move then pal
        if (travelingLinearly) {
            success = travelLinearly(this, col, row);
        }

        if (travelingDiagonally) {
            success = travelDiagonally(this, col, row);
        }

        if (success != SUCCESS) {
            return false;
        }

        return true;
    }

    /**
     * Handles the nondiagonal/horizontal and vertical movement.
     * Checks for collisions along the way.
     * @param cp This chess piece.
     * @param col Desired destination column — x value
     * @param row Desired destination row — y value
     * @return Integer representing if it made it to the destination without
     *          difficulty/collisions.
     *          0 - Successful arrival
     */
    private int travelLinearly(ChessPiece cp, int col, int row) {
        Coord c = cp.getCurrentPosition();
        int xCoord = c.getX();
        int yCoord = c.getY();

        while (xCoord != col && yCoord != row) {
            // Traveling up
            if (row < yCoord) {
                yCoord--;
            }
            // Traveling right
            else if (col > xCoord) {
                xCoord++;
            }
            // Traveling down
            else if (row > yCoord) {
                yCoord++;
            }
            // Traveling left
            else {
                xCoord--;
            }

            Boolean collides = checkForCollision(xCoord, yCoord, col, row);
            if (collides != null) {
                return (!collides ? SUCCESS : FAIL);
            }
        }

        return SUCCESS;
    }

    /**
     * Handles the diagonal movement. Checks for collisions along the way.
     * @param cp This chess piece.
     * @param col Desired destination column — x value
     * @param row Desired destination row — y value
     * @return Integer representing if it made it to the destination without
     *          difficulty/collisions.
     *          0 - Successful arrival
     */
    private int travelDiagonally(ChessPiece cp, int col, int row) {
        Coord c = cp.getCurrentPosition();
        int xCoord = c.getX();
        int yCoord = c.getY();

        while (xCoord != col && yCoord != row) {
            // Traveling NW
            if (row < yCoord && col < xCoord) {
                xCoord--;
                yCoord--;
            }
            // Traveling NE
            else if (col > xCoord && row < yCoord) {
                xCoord++;
                yCoord--;
            }
            // Traveling SE
            else if (row > yCoord && col > xCoord) {
                xCoord++;
                yCoord++;
            }
            // Traveling SW
            else {
                xCoord--;
                yCoord++;
            }

            Boolean collides = checkForCollision(xCoord, yCoord, col, row);
            if (collides != null) {
                return (!collides ? SUCCESS : FAIL);
            }
        }

        return SUCCESS;
    }

    /**
     * Checks if a piece obstructs the movement of this piece.
     * @param xCoord Current x position of this piece
     * @param yCoord Current y position of this piece
     * @param destX Destination x value
     * @param destY Destination y value
     * @return True if a collision occurs.
     *          i.e. if at any point you encounter an ally, or if you encounter
     *          an enemy with position not equal to your destination.
     */
    private Boolean checkForCollision(int xCoord, int yCoord, int destX, int destY) {
        Coord currPos = new Coord(xCoord, yCoord);
        boolean allyPieceHere  = ChessGameUtils.isAllyHere(currPos, this.getPlayer());
        boolean enemyPieceHere = ChessGameUtils.isEnemyHere(currPos, this.getPlayer());

        // Collides if at any point you see an ally
        if (allyPieceHere) {
            return true;
        }

        // If not an ally, an enemy
        else if (enemyPieceHere) {
            // Not a collision if we are at destination
            return destX != xCoord || destY != yCoord;
        }

        // No one
        else {
            return false;
        }
    }

    public ChessPieceName getName() {
        return name;
    }
}
