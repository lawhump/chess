package src.test.chess;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import src.com.lhumphr2.chess.model.ChessBoard;
import src.com.lhumphr2.chess.model.ChessGameUtils;
import src.com.lhumphr2.chess.model.ChessPieces.ChessPiece;
import src.com.lhumphr2.chess.model.ChessPieces.Queen;
import src.com.lhumphr2.chess.model.Coord;
import src.com.lhumphr2.chess.model.Player;

/**
 * Created by lawrencehumphrey on 9/6/15.
 */
public class QueenTest extends TestCase {
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
        Queen myQueen = new Queen(p1, initalPos);

        ChessGameUtils.putPiece(myQueen, initalPos);

        try {
            myQueen.moveTo(-1, -1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myQueen.moveTo(-1, 0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myQueen.moveTo(3, -1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myQueen.moveTo(3, 99);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myQueen.moveTo(99, 0);
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
        Queen myQueen = new Queen(p1, initalPos);

        ChessGameUtils.putPiece(myQueen, initalPos);

        try {
            myQueen.moveTo(3,0);
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
        Queen myQueen = new Queen(p1, initalPos);

        ChessGameUtils.putPiece(myQueen, initalPos);

        try {
            myQueen.moveTo(3,3);
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

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,chessboard.getHeight()-1)) == null);
    }

    @Test
    public void testMoveDown() {
        System.out.println("Beginning move down");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,1);
        Queen myQueen = new Queen(p1, initalPos);

        ChessGameUtils.putPiece(myQueen, initalPos);

        try {
            myQueen.moveTo(3,3);
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

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,1)) == null);
    }

    @Test
    public void testMoveLeft() {
        System.out.println("Beginning move left");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,0);
        Queen myQueen = new Queen(p1, initalPos);

        ChessGameUtils.putPiece(myQueen, initalPos);

        try {
            myQueen.moveTo(0,0);
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

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(0,0)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) == null);
    }

    @Test
    public void testMoveRight() {
        System.out.println("Beginning move right");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,0);
        Queen myQueen = new Queen(p1, initalPos);

        ChessGameUtils.putPiece(myQueen, initalPos);

        try {
            myQueen.moveTo(6,0);
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

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(6,0)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,0)) == null);
    }

    @Test
    public void testMoveNW() {
        System.out.println("Beginning move nw");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        Queen myQueen = new Queen(p1, initalPos);

        ChessGameUtils.putPiece(myQueen, initalPos);

        try {
            myQueen.moveTo(0,0);
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

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(0,0)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) == null);
    }

    @Test
    public void testMoveNE() {
        System.out.println("Beginning move ne");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        Queen myQueen = new Queen(p1, initalPos);

        ChessGameUtils.putPiece(myQueen, initalPos);

        try {
            myQueen.moveTo(5,1);
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

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(5,1)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) == null);
    }

    @Test
    public void testMoveSE() {
        System.out.println("Beginning move se");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        Queen myQueen = new Queen(p1, initalPos);

        ChessGameUtils.putPiece(myQueen, initalPos);

        try {
            myQueen.moveTo(5,5);
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

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(5,5)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) == null);
    }

    @Test
    public void testMoveSW() {
        System.out.println("Beginning move sw");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        Queen myQueen = new Queen(p1, initalPos);

        ChessGameUtils.putPiece(myQueen, initalPos);

        try {
            myQueen.moveTo(1,5);
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

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(1,5)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) == null);
    }

    @Test(expected = ChessPiece.InvalidMove.class)
    public void testBlockedMoveUp() {
        System.out.println("Beginning blocked move up");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,chessboard.getHeight()-1);
        Queen myQueen = new Queen(p1, initalPos);

        Coord initialPos2 = new Coord(3,chessboard.getHeight()-2);
        Queen otherQueen = new Queen(p1, initialPos2);

        ChessGameUtils.putPiece(myQueen, initalPos);
        ChessGameUtils.putPiece(otherQueen, initialPos2);

        try {
            myQueen.moveTo(3,3);
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
        Queen myQueen = new Queen(p1, initalPos);

        Coord initialPos2 = new Coord(3,4);
        Queen otherQueen = new Queen(p2, initialPos2);

        ChessGameUtils.putPiece(myQueen, initalPos);
        ChessGameUtils.putPiece(otherQueen, initialPos2);

        try {
            myQueen.moveTo(3,5);
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
        Queen myQueen = new Queen(p1, initalPos);

        Coord initialPos2 = new Coord(2,3);
        Queen otherQueen = new Queen(p2, initialPos2);

        ChessGameUtils.putPiece(myQueen, initalPos);
        ChessGameUtils.putPiece(otherQueen, initialPos2);

        try {
            myQueen.moveTo(0,3);
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
        Queen myQueen = new Queen(p1, initalPos);

        Coord initialPos2 = new Coord(4,3);
        Queen otherQueen = new Queen(p2, initialPos2);

        ChessGameUtils.putPiece(myQueen, initalPos);
        ChessGameUtils.putPiece(otherQueen, initialPos2);

        try {
            myQueen.moveTo(6,3);
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
        Queen myQueen = new Queen(p1, initalPos);

        Coord initialPos2 = new Coord(2,2);
        Queen otherQueen = new Queen(p2, initialPos2);

        ChessGameUtils.putPiece(myQueen, initalPos);
        ChessGameUtils.putPiece(otherQueen, initialPos2);


        try {
            myQueen.moveTo(0,0);
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
        Queen myQueen = new Queen(p1, initalPos);

        Coord initialPos2 = new Coord(4,2);
        Queen otherQueen = new Queen(p2, initialPos2);

        ChessGameUtils.putPiece(myQueen, initalPos);
        ChessGameUtils.putPiece(otherQueen, initialPos2);

        try {
            myQueen.moveTo(5,1);
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
        Queen myQueen = new Queen(p1, initalPos);

        Coord initialPos2 = new Coord(4,4);
        Queen otherQueen = new Queen(p2, initialPos2);

        ChessGameUtils.putPiece(myQueen, initalPos);
        ChessGameUtils.putPiece(otherQueen, initialPos2);

        try {
            myQueen.moveTo(5,5);
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
        Queen myQueen = new Queen(p1, initalPos);

        Coord initialPos2 = new Coord(2,4);
        Queen otherQueen = new Queen(p2, initialPos2);

        ChessGameUtils.putPiece(myQueen, initalPos);
        ChessGameUtils.putPiece(otherQueen, initialPos2);

        try {
            myQueen.moveTo(1,5);
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
        Queen myQueen = new Queen(p1, initalPos);

        Coord initialPos2 = new Coord(3,chessboard.getHeight()-2);
        Queen otherQueen = new Queen(p1, initialPos2);

        ChessGameUtils.putPiece(myQueen, initalPos);
        ChessGameUtils.putPiece(otherQueen, initialPos2);

        try {
            myQueen.moveTo(3,chessboard.getHeight()-2);
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
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,chessboard.getHeight()-2)) == myQueen);
    }

    @Test
    public void testCaptureDown() {
        System.out.println("Beginning down capture");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        Queen myQueen = new Queen(p1, initalPos);

        Coord initialPos2 = new Coord(3,5);
        Queen otherQueen = new Queen(p2, initialPos2);

        ChessGameUtils.putPiece(myQueen, initalPos);
        ChessGameUtils.putPiece(otherQueen, initialPos2);

        try {
            myQueen.moveTo(3,5);
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

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,5)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) == null);
        assertEquals(true, chessboard.board.size() == 1);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,5)) == myQueen);
    }

    @Test
    public void testCaptureLeft() {
        System.out.println("Beginning left capture");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        Queen myQueen = new Queen(p1, initalPos);

        Coord initialPos2 = new Coord(0,3);
        Queen otherQueen = new Queen(p2, initialPos2);

        ChessGameUtils.putPiece(myQueen, initalPos);
        ChessGameUtils.putPiece(otherQueen, initialPos2);

        try {
            myQueen.moveTo(0,3);
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

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(0,3)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) == null);
        assertEquals(true, chessboard.board.size() == 1);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(0,3)) == myQueen);
    }

    @Test
    public void testCaptureRight() {
        System.out.println("Beginning right capture");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        Queen myQueen = new Queen(p1, initalPos);

        Coord initialPos2 = new Coord(5,3);
        Queen otherQueen = new Queen(p2, initialPos2);

        ChessGameUtils.putPiece(myQueen, initalPos);
        ChessGameUtils.putPiece(otherQueen, initialPos2);

        try {
            myQueen.moveTo(5,3);
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

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(5,3)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) == null);
        assertEquals(true, chessboard.board.size() == 1);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(5,3)) == myQueen);
    }

    @Test
    public void testCaptureNW() {
        System.out.println("Beginning captured nw");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        Queen myQueen = new Queen(p1, initalPos);

        Coord initialPos2 = new Coord(2,2);
        Queen otherQueen = new Queen(p2, initialPos2);

        ChessGameUtils.putPiece(myQueen, initalPos);
        ChessGameUtils.putPiece(otherQueen, initialPos2);


        try {
            myQueen.moveTo(2,2);
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
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(2,2)) == myQueen);
    }

    @Test
    public void testCaptureNE() {
        System.out.println("Beginning captured ne");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        Queen myQueen = new Queen(p1, initalPos);

        Coord initialPos2 = new Coord(5,1);
        Queen otherQueen = new Queen(p2, initialPos2);

        ChessGameUtils.putPiece(myQueen, initalPos);
        ChessGameUtils.putPiece(otherQueen, initialPos2);


        try {
            myQueen.moveTo(5,1);
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

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(5,1)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) == null);
        assertEquals(true, chessboard.board.size() == 1);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(5,1)) == myQueen);
    }

    @Test
    public void testCaptureSE() {
        System.out.println("Beginning captured se");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        Queen myQueen = new Queen(p1, initalPos);

        Coord initialPos2 = new Coord(6,6);
        Queen otherQueen = new Queen(p2, initialPos2);

        ChessGameUtils.putPiece(myQueen, initalPos);
        ChessGameUtils.putPiece(otherQueen, initialPos2);


        try {
            myQueen.moveTo(6,6);
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

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(6,6)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) == null);
        assertEquals(true, chessboard.board.size() == 1);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(6,6)) == myQueen);
    }

    @Test
    public void testCaptureSW() {
        System.out.println("Beginning captured sw");
        chessboard = ChessBoard.makeEmptyInstance();

        Coord initalPos = new Coord(3,3);
        Queen myQueen = new Queen(p1, initalPos);

        Coord initialPos2 = new Coord(0,6);
        Queen otherQueen = new Queen(p2, initialPos2);

        ChessGameUtils.putPiece(myQueen, initalPos);
        ChessGameUtils.putPiece(otherQueen, initialPos2);


        try {
            myQueen.moveTo(0,6);
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

        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(0,6)) != null);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(3,3)) == null);
        assertEquals(true, chessboard.board.size() == 1);
        assertEquals(true, ChessBoard.board.get(ChessGameUtils.to1DCoord(0,6)) == myQueen);
    }
}