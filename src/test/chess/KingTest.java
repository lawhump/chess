package src.test.chess;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import src.com.lhumphr2.chess.model.ChessBoard;
import src.com.lhumphr2.chess.model.ChessGameUtils;
import src.com.lhumphr2.chess.model.ChessPieces.*;
import src.com.lhumphr2.chess.model.Coord;
import src.com.lhumphr2.chess.model.Player;

/**
 * Created by lawrencehumphrey on 9/6/15.
 */
public class KingTest extends TestCase {
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
        King myKing = new King(p1, initalPos);

        ChessGameUtils.putPiece(myKing, initalPos);

        try {
            myKing.moveTo(-1, -1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKing.moveTo(-1, 0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKing.moveTo(3, -1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKing.moveTo(3, 99);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myKing.moveTo(99, 0);
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
        King myKing = new King(p1, initalPos);

        ChessGameUtils.putPiece(myKing, initalPos);

        try {
            myKing.moveTo(3,0);
        } catch (ChessPiece.OriginEqualsDestination oed) {
            System.out.println("You are already there.");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }

    @Test
    public void testMoveUp() {
        System.out.println("Beginning move up");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,chessboard.getHeight()-1);
        King myKing = new King(p1, initalPos);

        ChessGameUtils.putPiece(myKing, initalPos);

        try {
            myKing.moveTo(3,chessboard.getHeight()-2);
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Destination out of bounds");
        } catch (ChessPiece.OriginEqualsDestination oed) {
            System.out.println("You are already there");
        } catch (ChessPiece.InvalidMove poym) {
            System.out.println("A piece is in your way");
        } catch (ChessPiece.PutsYouInCheckException pyice) {
            System.out.println("You can't move into check");
        } catch (ChessPiece.GetOutOfCheckException gooce) {
            System.out.println("You are in check");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,chessboard.getHeight()-2)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,chessboard.getHeight()-1)) == null);
    }

    @Test
    public void testMoveDown() {
        System.out.println("Beginning move down");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,1);
        King myKing = new King(p1, initalPos);

        ChessGameUtils.putPiece(myKing, initalPos);

        try {
            myKing.moveTo(3,2);
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Destination out of bounds");
        } catch (ChessPiece.OriginEqualsDestination oed) {
            System.out.println("You are already there");
        } catch (ChessPiece.InvalidMove poym) {
            System.out.println("A piece is in your way");
        } catch (ChessPiece.PutsYouInCheckException pyice) {
            System.out.println("You can't move into check");
        } catch (ChessPiece.GetOutOfCheckException gooce) {
            System.out.println("You are in check");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,2)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,1)) == null);
    }

    @Test
    public void testMoveLeft() {
        System.out.println("Beginning move left");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,0);
        King myKing = new King(p1, initalPos);

        ChessGameUtils.putPiece(myKing, initalPos);

        try {
            myKing.moveTo(2,0);
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Destination out of bounds");
        } catch (ChessPiece.OriginEqualsDestination oed) {
            System.out.println("You are already there");
        } catch (ChessPiece.InvalidMove poym) {
            System.out.println("A piece is in your way");
        } catch (ChessPiece.PutsYouInCheckException pyice) {
            System.out.println("You can't move into check");
        } catch (ChessPiece.GetOutOfCheckException gooce) {
            System.out.println("You are in check");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(2,0)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,0)) == null);
    }

    @Test
    public void testMoveRight() {
        System.out.println("Beginning move right");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,0);
        King myKing = new King(p1, initalPos);

        ChessGameUtils.putPiece(myKing, initalPos);

        try {
            myKing.moveTo(4,0);
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Destination out of bounds");
        } catch (ChessPiece.OriginEqualsDestination oed) {
            System.out.println("You are already there");
        } catch (ChessPiece.InvalidMove poym) {
            System.out.println("A piece is in your way");
        } catch (ChessPiece.PutsYouInCheckException pyice) {
            System.out.println("You can't move into check");
        } catch (ChessPiece.GetOutOfCheckException gooce) {
            System.out.println("You are in check");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(4,0)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,0)) == null);
    }

    @Test
    public void testMoveNW() {
        System.out.println("Beginning move nw");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        King myKing = new King(p1, initalPos);

        ChessGameUtils.putPiece(myKing, initalPos);

        try {
            myKing.moveTo(2,2);
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Destination out of bounds");
        } catch (ChessPiece.OriginEqualsDestination oed) {
            System.out.println("You are already there");
        } catch (ChessPiece.InvalidMove poym) {
            System.out.println("A piece is in your way");
        } catch (ChessPiece.PutsYouInCheckException pyice) {
            System.out.println("You can't move into check");
        } catch (ChessPiece.GetOutOfCheckException gooce) {
            System.out.println("You are in check");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(2,2)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) == null);
    }

    @Test
    public void testMoveNE() {
        System.out.println("Beginning move ne");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        King myKing = new King(p1, initalPos);

        ChessGameUtils.putPiece(myKing, initalPos);

        try {
            myKing.moveTo(4,2);
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Destination out of bounds");
        } catch (ChessPiece.OriginEqualsDestination oed) {
            System.out.println("You are already there");
        } catch (ChessPiece.InvalidMove poym) {
            System.out.println("A piece is in your way");
        } catch (ChessPiece.PutsYouInCheckException pyice) {
            System.out.println("You can't move into check");
        } catch (ChessPiece.GetOutOfCheckException gooce) {
            System.out.println("You are in check");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(4,2)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) == null);
    }

    @Test
    public void testMoveSE() {
        System.out.println("Beginning move se");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        King myKing = new King(p1, initalPos);

        ChessGameUtils.putPiece(myKing, initalPos);

        try {
            myKing.moveTo(4,4);
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Destination out of bounds");
        } catch (ChessPiece.OriginEqualsDestination oed) {
            System.out.println("You are already there");
        } catch (ChessPiece.InvalidMove poym) {
            System.out.println("A piece is in your way");
        } catch (ChessPiece.PutsYouInCheckException pyice) {
            System.out.println("You can't move into check");
        } catch (ChessPiece.GetOutOfCheckException gooce) {
            System.out.println("You are in check");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(4,4)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) == null);
    }

    @Test
    public void testMoveSW() {
        System.out.println("Beginning move sw");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        King myKing = new King(p1, initalPos);

        ChessGameUtils.putPiece(myKing, initalPos);

        try {
            myKing.moveTo(2,4);
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Destination out of bounds");
        } catch (ChessPiece.OriginEqualsDestination oed) {
            System.out.println("You are already there");
        } catch (ChessPiece.InvalidMove poym) {
            System.out.println("A piece is in your way");
        } catch (ChessPiece.PutsYouInCheckException pyice) {
            System.out.println("You can't move into check");
        } catch (ChessPiece.GetOutOfCheckException gooce) {
            System.out.println("You are in check");
        } catch (Exception e) {
//            System.out.println("Something went wrong");
        }

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(2,4)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) == null);
    }

    @Test(expected = ChessPiece.InvalidMove.class)
    public void testBlockedMoveUp() {
        System.out.println("Beginning blocked move up");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,chessboard.getHeight()-1);
        King myKing = new King(p1, initalPos);

        Coord initialPos2 = new Coord(3,chessboard.getHeight()-2);
        Queen myQueen = new Queen(p1, initialPos2);

        ChessGameUtils.putPiece(myKing, initalPos);
        ChessGameUtils.putPiece(myQueen, initialPos2);

        try {
            myKing.moveTo(3,chessboard.getHeight()-2);
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Destination out of bounds");
        } catch (ChessPiece.OriginEqualsDestination oed) {
            System.out.println("You are already there");
        } catch (ChessPiece.InvalidMove poym) {
            System.out.println("A piece is in your way");
        } catch (ChessPiece.PutsYouInCheckException pyice) {
            System.out.println("You can't move into check");
        } catch (ChessPiece.GetOutOfCheckException gooce) {
            System.out.println("You are in check");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }

    @Test(expected = ChessPiece.InvalidMove.class)
    public void testBlockedMoveDown() {
        System.out.println("Beginning blocked move down");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        King myKing = new King(p1, initalPos);

        Coord initialPos2 = new Coord(3,4);
        Queen myQueen = new Queen(p1, initialPos2);

        ChessGameUtils.putPiece(myKing, initalPos);
        ChessGameUtils.putPiece(myQueen, initialPos2);

        try {
            myKing.moveTo(3,4);
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Destination out of bounds");
        } catch (ChessPiece.OriginEqualsDestination oed) {
            System.out.println("You are already there");
        } catch (ChessPiece.InvalidMove poym) {
            System.out.println("A piece is in your way");
        } catch (ChessPiece.PutsYouInCheckException pyice) {
            System.out.println("You can't move into check");
        } catch (ChessPiece.GetOutOfCheckException gooce) {
            System.out.println("You are in check");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }

    @Test(expected = ChessPiece.InvalidMove.class)
    public void testBlockedMoveLeft() {
        System.out.println("Beginning blocked move left");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        King myKing = new King(p1, initalPos);

        Coord initialPos2 = new Coord(2,3);
        Queen myQueen = new Queen(p1, initialPos2);

        ChessGameUtils.putPiece(myKing, initalPos);
        ChessGameUtils.putPiece(myQueen, initialPos2);

        try {
            myKing.moveTo(2,3);
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Destination out of bounds");
        } catch (ChessPiece.OriginEqualsDestination oed) {
            System.out.println("You are already there");
        } catch (ChessPiece.InvalidMove poym) {
            System.out.println("A piece is in your way");
        } catch (ChessPiece.PutsYouInCheckException pyice) {
            System.out.println("You can't move into check");
        } catch (ChessPiece.GetOutOfCheckException gooce) {
            System.out.println("You are in check");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }

    @Test(expected = ChessPiece.InvalidMove.class)
    public void testBlockedMoveRight() {
        System.out.println("Beginning blocked move right");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        King myKing = new King(p1, initalPos);

        Coord initialPos2 = new Coord(4,3);
        Queen myQueen = new Queen(p1, initialPos2);

        ChessGameUtils.putPiece(myKing, initalPos);
        ChessGameUtils.putPiece(myQueen, initialPos2);

        try {
            myKing.moveTo(4,3);
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Destination out of bounds");
        } catch (ChessPiece.OriginEqualsDestination oed) {
            System.out.println("You are already there");
        } catch (ChessPiece.InvalidMove poym) {
            System.out.println("A piece is in your way");
        } catch (ChessPiece.PutsYouInCheckException pyice) {
            System.out.println("You can't move into check");
        } catch (ChessPiece.GetOutOfCheckException gooce) {
            System.out.println("You are in check");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }

    @Test(expected = ChessPiece.InvalidMove.class)
    public void testBlockedMoveNW() {
        System.out.println("Beginning blocked move nw");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        King myKing = new King(p1, initalPos);

        Coord initialPos2 = new Coord(2,2);
        Queen myQueen = new Queen(p1, initialPos2);

        ChessGameUtils.putPiece(myKing, initalPos);
        ChessGameUtils.putPiece(myQueen, initialPos2);


        try {
            myKing.moveTo(2,2);
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Destination out of bounds");
        } catch (ChessPiece.OriginEqualsDestination oed) {
            System.out.println("You are already there");
        } catch (ChessPiece.InvalidMove poym) {
            System.out.println("A piece is in your way");
        } catch (ChessPiece.PutsYouInCheckException pyice) {
            System.out.println("You can't move into check");
        } catch (ChessPiece.GetOutOfCheckException gooce) {
            System.out.println("You are in check");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }

    @Test(expected = ChessPiece.InvalidMove.class)
    public void testBlockedMoveNE() {
        System.out.println("Beginning blocked move ne");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        King myKing = new King(p1, initalPos);

        Coord initialPos2 = new Coord(4,2);
        Queen myQueen = new Queen(p1, initialPos2);

        ChessGameUtils.putPiece(myKing, initalPos);
        ChessGameUtils.putPiece(myQueen, initialPos2);

        try {
            myKing.moveTo(4,2);
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Destination out of bounds");
        } catch (ChessPiece.OriginEqualsDestination oed) {
            System.out.println("You are already there");
        } catch (ChessPiece.InvalidMove poym) {
            System.out.println("A piece is in your way");
        } catch (ChessPiece.PutsYouInCheckException pyice) {
            System.out.println("You can't move into check");
        } catch (ChessPiece.GetOutOfCheckException gooce) {
            System.out.println("You are in check");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }

    @Test(expected = ChessPiece.InvalidMove.class)
    public void testBlockedMoveSE() {
        System.out.println("Beginning blocked move se");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        King myKing = new King(p1, initalPos);

        Coord initialPos2 = new Coord(4,4);
        Queen myQueen = new Queen(p1, initialPos2);

        ChessGameUtils.putPiece(myKing, initalPos);
        ChessGameUtils.putPiece(myQueen, initialPos2);

        try {
            myKing.moveTo(4,4);
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Destination out of bounds");
        } catch (ChessPiece.OriginEqualsDestination oed) {
            System.out.println("You are already there");
        } catch (ChessPiece.InvalidMove poym) {
            System.out.println("A piece is in your way");
        } catch (ChessPiece.PutsYouInCheckException pyice) {
            System.out.println("You can't move into check");
        } catch (ChessPiece.GetOutOfCheckException gooce) {
            System.out.println("You are in check");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }

    @Test(expected = ChessPiece.InvalidMove.class)
    public void testBlockedMoveSW() {
        System.out.println("Beginning blocked move sw");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        King myKing = new King(p1, initalPos);

        Coord initialPos2 = new Coord(2,4);
        Queen myQueen = new Queen(p1, initialPos2);

        ChessGameUtils.putPiece(myKing, initalPos);
        ChessGameUtils.putPiece(myQueen, initialPos2);

        try {
            myKing.moveTo(2,4);
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Destination out of bounds");
        } catch (ChessPiece.OriginEqualsDestination oed) {
            System.out.println("You are already there");
        } catch (ChessPiece.InvalidMove poym) {
            System.out.println("A piece is in your way");
        } catch (ChessPiece.PutsYouInCheckException pyice) {
            System.out.println("You can't move into check");
        } catch (ChessPiece.GetOutOfCheckException gooce) {
            System.out.println("You are in check");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }

    @Test
    public void testCaptureUp() {
        System.out.println("Beginning up capture");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,chessboard.getHeight()-1);
        King myKing = new King(p1, initalPos);

        Coord initialPos2 = new Coord(3,chessboard.getHeight()-2);
        Bishop otherBishop = new Bishop(p2, initialPos2);

        ChessGameUtils.putPiece(myKing, initalPos);
        ChessGameUtils.putPiece(otherBishop, initialPos2);

        try {
            myKing.moveTo(3,chessboard.getHeight()-2);
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Destination out of bounds");
        } catch (ChessPiece.OriginEqualsDestination oed) {
            System.out.println("You are already there");
        } catch (ChessPiece.InvalidMove poym) {
            System.out.println("A piece is in your way");
        } catch (ChessPiece.PutsYouInCheckException pyice) {
            System.out.println("You can't move into check");
        } catch (ChessPiece.GetOutOfCheckException gooce) {
            System.out.println("You are in check");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,chessboard.getHeight()-2)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,chessboard.getHeight()-1)) == null);
        assertEquals(true, chessboard.board.size() == 1);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,chessboard.getHeight()-2)) == myKing);
    }

    @Test
    public void testCaptureDown() {
        System.out.println("Beginning down capture");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        King myKing = new King(p1, initalPos);

        Coord initialPos2 = new Coord(3,4);
        Bishop otherBishop = new Bishop(p2, initialPos2);

        ChessGameUtils.putPiece(myKing, initalPos);
        ChessGameUtils.putPiece(otherBishop, initialPos2);

        try {
            myKing.moveTo(3,4);
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Destination out of bounds");
        } catch (ChessPiece.OriginEqualsDestination oed) {
            System.out.println("You are already there");
        } catch (ChessPiece.InvalidMove poym) {
            System.out.println("A piece is in your way");
        } catch (ChessPiece.PutsYouInCheckException pyice) {
            System.out.println("You can't move into check");
        } catch (ChessPiece.GetOutOfCheckException gooce) {
            System.out.println("You are in check");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,4)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) == null);
        assertEquals(true, chessboard.board.size() == 1);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,4)) == myKing);
    }

    @Test
    public void testCaptureLeft() {
        System.out.println("Beginning left capture");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        King myKing = new King(p1, initalPos);

        Coord initialPos2 = new Coord(2,3);
        Bishop otherBishop = new Bishop(p2, initialPos2);

        ChessGameUtils.putPiece(myKing, initalPos);
        ChessGameUtils.putPiece(otherBishop, initialPos2);

        try {
            myKing.moveTo(2,3);
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Destination out of bounds");
        } catch (ChessPiece.OriginEqualsDestination oed) {
            System.out.println("You are already there");
        } catch (ChessPiece.InvalidMove poym) {
            System.out.println("A piece is in your way");
        } catch (ChessPiece.PutsYouInCheckException pyice) {
            System.out.println("You can't move into check");
        } catch (ChessPiece.GetOutOfCheckException gooce) {
            System.out.println("You are in check");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(2,3)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) == null);
        assertEquals(true, chessboard.board.size() == 1);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(2,3)) == myKing);
    }

    @Test
    public void testCaptureRight() {
        System.out.println("Beginning right capture");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        King myKing = new King(p1, initalPos);

        Coord initialPos2 = new Coord(4,3);
        Bishop otherBishop = new Bishop(p2, initialPos2);

        ChessGameUtils.putPiece(myKing, initalPos);
        ChessGameUtils.putPiece(otherBishop, initialPos2);

        try {
            myKing.moveTo(4,3);
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Destination out of bounds");
        } catch (ChessPiece.OriginEqualsDestination oed) {
            System.out.println("You are already there");
        } catch (ChessPiece.InvalidMove poym) {
            System.out.println("A piece is in your way");
        } catch (ChessPiece.PutsYouInCheckException pyice) {
            System.out.println("You can't move into check");
        } catch (ChessPiece.GetOutOfCheckException gooce) {
            System.out.println("You are in check");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(4,3)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) == null);
        assertEquals(true, chessboard.board.size() == 1);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(4,3)) == myKing);
    }

    @Test
    public void testCaptureNW() {
        System.out.println("Beginning captured nw");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        King myKing = new King(p1, initalPos);

        Coord initialPos2 = new Coord(2,2);
        Rook otherRook = new Rook(p2, initialPos2);

        ChessGameUtils.putPiece(myKing, initalPos);
        ChessGameUtils.putPiece(otherRook, initialPos2);


        try {
            myKing.moveTo(2,2);
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Destination out of bounds");
        } catch (ChessPiece.OriginEqualsDestination oed) {
            System.out.println("You are already there");
        } catch (ChessPiece.InvalidMove poym) {
            System.out.println("A piece is in your way");
        } catch (ChessPiece.PutsYouInCheckException pyice) {
            System.out.println("You can't move into check");
        } catch (ChessPiece.GetOutOfCheckException gooce) {
            System.out.println("You are in check");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(2,2)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) == null);
        assertEquals(true, chessboard.board.size() == 1);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(2,2)) == myKing);
    }

    @Test
    public void testCaptureNE() {
        System.out.println("Beginning captured ne");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        King myKing = new King(p1, initalPos);

        Coord initialPos2 = new Coord(4,2);
        Rook otherRook = new Rook(p2, initialPos2);

        ChessGameUtils.putPiece(myKing, initalPos);
        ChessGameUtils.putPiece(otherRook, initialPos2);


        try {
            myKing.moveTo(4,2);
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Destination out of bounds");
        } catch (ChessPiece.OriginEqualsDestination oed) {
            System.out.println("You are already there");
        } catch (ChessPiece.InvalidMove poym) {
            System.out.println("A piece is in your way");
        } catch (ChessPiece.PutsYouInCheckException pyice) {
            System.out.println("You can't move into check");
        } catch (ChessPiece.GetOutOfCheckException gooce) {
            System.out.println("You are in check");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(4,2)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) == null);
        assertEquals(true, chessboard.board.size() == 1);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(4,2)) == myKing);
    }

    @Test
    public void testCaptureSE() {
        System.out.println("Beginning captured se");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        King myKing = new King(p1, initalPos);

        Coord initialPos2 = new Coord(4,4);
        Rook otherRook = new Rook(p2, initialPos2);

        ChessGameUtils.putPiece(myKing, initalPos);
        ChessGameUtils.putPiece(otherRook, initialPos2);


        try {
            myKing.moveTo(4,4);
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Destination out of bounds");
        } catch (ChessPiece.OriginEqualsDestination oed) {
            System.out.println("You are already there");
        } catch (ChessPiece.InvalidMove poym) {
            System.out.println("A piece is in your way");
        } catch (ChessPiece.PutsYouInCheckException pyice) {
            System.out.println("You can't move into check");
        } catch (ChessPiece.GetOutOfCheckException gooce) {
            System.out.println("You are in check");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(4,4)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) == null);
        assertEquals(true, chessboard.board.size() == 1);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(4,4)) == myKing);
    }

    @Test
    public void testCaptureSW() {
        System.out.println("Beginning captured sw");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        King myKing = new King(p1, initalPos);

        Coord initialPos2 = new Coord(2,4);
        Rook otherRook = new Rook(p2, initialPos2);

        ChessGameUtils.putPiece(myKing, initalPos);
        ChessGameUtils.putPiece(otherRook, initialPos2);


        try {
            myKing.moveTo(2,4);
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Destination out of bounds");
        } catch (ChessPiece.OriginEqualsDestination oed) {
            System.out.println("You are already there");
        } catch (ChessPiece.InvalidMove poym) {
            System.out.println("A piece is in your way");
        } catch (ChessPiece.PutsYouInCheckException pyice) {
            System.out.println("You can't move into check");
        } catch (ChessPiece.GetOutOfCheckException gooce) {
            System.out.println("You are in check");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(2,4)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) == null);
        assertEquals(true, chessboard.board.size() == 1);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(2,4)) == myKing);
    }
}