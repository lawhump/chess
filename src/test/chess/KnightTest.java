package src.test.chess;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import src.com.lhumphr2.chess.model.ChessBoard;
import src.com.lhumphr2.chess.model.ChessGameUtils;
import src.com.lhumphr2.chess.model.ChessPieces.ChessPiece;
import src.com.lhumphr2.chess.model.ChessPieces.Knight;
import src.com.lhumphr2.chess.model.Coord;
import src.com.lhumphr2.chess.model.Player;

/**
 * Created by lawrencehumphrey on 9/6/15.
 */
public class KnightTest extends TestCase {
    Player p1 = new Player("You");
    Player p2 = new Player("Suck");
    ChessBoard chessboard;

    @Before
    public void init() {
        chessboard = ChessBoard.makeEmptyInstance();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testObviousErrors() {
        System.out.println("Beginning obvious errors");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,0);
        Knight myKnight = new Knight(p1, initalPos);

        ChessGameUtils.putPiece(myKnight, initalPos);

        try {
            myKnight.moveTo(-1, -1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKnight.moveTo(-1, 0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKnight.moveTo(3, -1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKnight.moveTo(3, 99);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKnight.moveTo(99, 0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }

    @Test(expected = ChessPiece.OriginEqualsDestination.class)
    public void testStationary() {
        System.out.println("Beginning stationary");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,0);
        Knight myKnight = new Knight(p1, initalPos);

        ChessGameUtils.putPiece(myKnight, initalPos);

        try {
            myKnight.moveTo(3,0);
        } catch (ChessPiece.OriginEqualsDestination oed) {
            System.out.println("You are already there.");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }

    @Test
    public void testAllNormalMovements() {
        System.out.println("Beginning test normal movement");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        Knight myKnight = new Knight(p1, initalPos);

        ChessGameUtils.putPiece(myKnight, initalPos);

        try {
            myKnight.moveTo(4, 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(4,1)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) == null);
        assertEquals(true, chessboard.board.size() == 1);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(4, 1)) == myKnight);

        try {
            myKnight.moveTo(3, 3);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        try {
            myKnight.moveTo(5, 2);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(5,2)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) == null);
        assertEquals(true, chessboard.board.size() == 1);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(5,2)) == myKnight);

        try {
            myKnight.moveTo(3, 3);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        try {
            myKnight.moveTo(5, 4);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(5,4)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) == null);
        assertEquals(true, chessboard.board.size() == 1);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(5,4)) == myKnight);

        try {
            myKnight.moveTo(3, 3);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        try {
            myKnight.moveTo(4, 5);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(4,5)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3, 3)) == null);
        assertEquals(true, chessboard.board.size() == 1);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(4,5)) == myKnight);

        try {
            myKnight.moveTo(3, 3);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) != null);
        assertEquals(true, chessboard.board.size() == 1);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) == myKnight);
    }

    @Test
    public void captureEveryDirection() {
        System.out.println("Beginning test capture every direction");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        Knight myKnight = new Knight(p1, initalPos);

        ChessGameUtils.putPiece(myKnight, initalPos);

        Knight k1, k2, k3, k4, k5, k6, k7, k8;
        Coord  c1, c2, c3, c4, c5, c6, c7, c8;

        c1 = new Coord(4, 1);
        c2 = new Coord(5, 2);
        c3 = new Coord(5, 4);
        c4 = new Coord(4, 5);
        c5 = new Coord(2, 5);
        c6 = new Coord(1, 4);
        c7 = new Coord(1, 2);
        c8 = new Coord(2, 1);

        k1 = new Knight(p2, c1);
        k2 = new Knight(p2, c2);
        k3 = new Knight(p2, c3);
        k4 = new Knight(p2, c4);
        k5 = new Knight(p2, c5);
        k6 = new Knight(p2, c6);
        k7 = new Knight(p2, c7);
        k8 = new Knight(p2, c8);

        ChessGameUtils.putPiece(k1,c1);
        ChessGameUtils.putPiece(k2,c2);
        ChessGameUtils.putPiece(k3,c3);
        ChessGameUtils.putPiece(k4,c4);
        ChessGameUtils.putPiece(k5,c5);
        ChessGameUtils.putPiece(k6,c6);
        ChessGameUtils.putPiece(k7,c7);
        ChessGameUtils.putPiece(k8,c8);

        try {
            myKnight.moveTo(4, 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKnight.moveTo(3, 3);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKnight.moveTo(5, 2);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKnight.moveTo(3, 3);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKnight.moveTo(5, 4);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKnight.moveTo(3, 3);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKnight.moveTo(4, 5);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKnight.moveTo(3, 3);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKnight.moveTo(2, 5);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKnight.moveTo(3, 3);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKnight.moveTo(1, 4);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKnight.moveTo(3, 3);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKnight.moveTo(1, 2);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKnight.moveTo(3, 3);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) != null);
        assertEquals(true, chessboard.board.size() == 1);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) == myKnight);
    }

    @Test (expected = ChessPiece.InvalidMove.class)
    public void blockedEveryDirection() {
        System.out.println("Beginning test capture every direction");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3, 3);
        Knight myKnight = new Knight(p1, initalPos);

        ChessGameUtils.putPiece(myKnight, initalPos);

        Knight k1, k2, k3, k4, k5, k6, k7, k8;
        Coord c1, c2, c3, c4, c5, c6, c7, c8;

        c1 = new Coord(4, 1);
        c2 = new Coord(5, 2);
        c3 = new Coord(5, 4);
        c4 = new Coord(4, 5);
        c5 = new Coord(2, 5);
        c6 = new Coord(1, 4);
        c7 = new Coord(1, 2);
        c8 = new Coord(2, 1);

        k1 = new Knight(p1, c1);
        k2 = new Knight(p1, c2);
        k3 = new Knight(p1, c3);
        k4 = new Knight(p1, c4);
        k5 = new Knight(p1, c5);
        k6 = new Knight(p1, c6);
        k7 = new Knight(p1, c7);
        k8 = new Knight(p1, c8);

        ChessGameUtils.putPiece(k1, c1);
        ChessGameUtils.putPiece(k2, c2);
        ChessGameUtils.putPiece(k3, c3);
        ChessGameUtils.putPiece(k4, c4);
        ChessGameUtils.putPiece(k5, c5);
        ChessGameUtils.putPiece(k6, c6);
        ChessGameUtils.putPiece(k7, c7);
        ChessGameUtils.putPiece(k8, c8);

        try {
            myKnight.moveTo(4, 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKnight.moveTo(3, 3);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKnight.moveTo(5, 2);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKnight.moveTo(3, 3);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKnight.moveTo(5, 4);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKnight.moveTo(3, 3);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKnight.moveTo(4, 5);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKnight.moveTo(3, 3);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKnight.moveTo(2, 5);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKnight.moveTo(3, 3);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKnight.moveTo(1, 4);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKnight.moveTo(3, 3);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKnight.moveTo(1, 2);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKnight.moveTo(3, 3);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3, 3)) != null);
        assertEquals(true, chessboard.board.size() == 9);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3, 3)) == myKnight);
    }
}