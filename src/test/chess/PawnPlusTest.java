package src.test.chess;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import src.com.lhumphr2.chess.model.ChessBoard;
import src.com.lhumphr2.chess.model.ChessGameUtils;
import src.com.lhumphr2.chess.model.ChessPieces.ChessPiece;
import src.com.lhumphr2.chess.model.ChessPieces.PawnPlus;
import src.com.lhumphr2.chess.model.Coord;
import src.com.lhumphr2.chess.model.Player;

/**
 * Created by lawrencehumphrey on 9/6/15.
 */
public class PawnPlusTest extends TestCase {
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
        PawnPlus myPawnPlus = new PawnPlus(p1, initalPos);

        ChessGameUtils.putPiece(myPawnPlus, initalPos);

        try {
            myPawnPlus.moveTo(-1, -1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myPawnPlus.moveTo(-1, 0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myPawnPlus.moveTo(3, -1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myPawnPlus.moveTo(3, 99);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Destination out of bounds");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        try {
            myPawnPlus.moveTo(99, 0);
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
        PawnPlus myPawnPlus = new PawnPlus(p1, initalPos);

        ChessGameUtils.putPiece(myPawnPlus, initalPos);

        try {
            myPawnPlus.moveTo(3,0);
        } catch (ChessPiece.OriginEqualsDestination oed) {
            System.out.println("You are already there.");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }


}
