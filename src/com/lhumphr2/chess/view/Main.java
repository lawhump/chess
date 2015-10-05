package src.com.lhumphr2.chess.view;

import src.com.lhumphr2.chess.model.Tournament;
import src.com.lhumphr2.chess.controller.TournamentController;

import javax.swing.*;

/**
 * Created by lawrencehumphrey on 9/20/15.
 */
public class Main {
    public static void main(String[] args) {

        // Get the users' names
        // That's all you need to fire up a new game.
        String player1Name = JOptionPane.showInputDialog(null,
                "Player 1 Name?",
                "Input Dialog Box", JOptionPane.INFORMATION_MESSAGE);
        String player2Name = JOptionPane.showInputDialog(null,
                "Player 2 Name?",
                "Input Dialog Box", JOptionPane.INFORMATION_MESSAGE);

        // Model - initialization
        Tournament tModel    = Tournament.getInstance(player1Name, player2Name);
        // View - initialization
        TournamentView tView = new TournamentView(player1Name, player2Name);
        // Controller - retrieval
        // EWWWWW sorry you have to read this pretend it's not here
        TournamentController controller = TournamentView.getController();

        // Tell the controller about the model - the last link
        controller.addTournamentModel();

        // Fire up the GUI brah
        tView.go();
    }

}
