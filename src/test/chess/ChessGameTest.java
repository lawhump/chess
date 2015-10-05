package src.test.chess;

import junit.framework.TestCase;
import src.com.lhumphr2.chess.model.*;

/**
 * Created by lawrencehumphrey on 9/6/15.
 */
public class ChessGameTest extends TestCase {

    public void testInitialization() {
        System.out.println("Beginnning testInitialization().");
        ChessGame game = ChessGame.getInstance("Lawrence", "Archy");

        Player p1 = game.getP1();
        Player p2 = game.getP2();

        assertEquals((p1.getName()).equals("Lawrence"), true);
        assertEquals((p2.getName()).equals("Archy"), true);

        assertEquals(p1.getID() == 1, true);
        assertEquals(p2.getID() == 2, true);

        assertEquals(game.getBoardSize() == 32, true);

        ChessBoard.printBoard();

        System.out.println("Ending testInitialization().");
    }
}
