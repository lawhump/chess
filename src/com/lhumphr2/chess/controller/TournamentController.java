package src.com.lhumphr2.chess.controller;

import src.com.lhumphr2.chess.model.ChessPieces.ChessPiece;
import src.com.lhumphr2.chess.model.Coord;
import src.com.lhumphr2.chess.model.Tournament;
import src.com.lhumphr2.chess.view.TournamentView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lawrencehumphrey on 9/21/15.
 */
public class TournamentController implements ActionListener {
    // Model
    private Tournament tModel;
    // View
    private TournamentView tView;
    private static boolean hasStarted = false;

    // True if they clicked on a spot with their designated piece
    // Needed to put piece at endpoint
    private boolean firstClick = false;
    private ChessPiece selectedPiece;

    /**
     * Something was clicked. This figures out what and responds accordingly.
     * @param e An ActionEvent? Who actually knows.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == TournamentView.NEW_GAME) {
            tView.setupNewGame();
            tModel.setupNewGame();
            hasStarted = true;
        }

        else if (e.getSource() == TournamentView.UNDO) {
            Tournament.undo();
            tView.undo();
            reset();
        }

        else if (e.getSource() == TournamentView.FORFEIT){
            int giveUp = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to give up? How will you live with yourself?",
                    "Forfeit",
                    JOptionPane.YES_NO_OPTION);
            if (giveUp == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, Tournament.getOtherPlayer().getName() + " wins.");
                hasStarted = false;
            }
            else {
                // Good choice
                return;
            }
        }

        else {
            if (!hasStarted) {
                JOptionPane.showMessageDialog(null, "Start the game, ya dingus");
                return;
            }

            Coord clicked = parse(e.getActionCommand());

            if (firstClick) {
                movePiece(clicked);
            }

            else {
                selectPiece(clicked);
            }
        }
    }

    /**
     * We have a piece and we wish to move it to the coordinate 'clicked'
     * @param clicked The x,y pair representing the desired destination of selectedPiece
     */
    private void movePiece(Coord clicked) {
        try {
            int origX = selectedPiece.getCurrentPosition().getX();
            int origY = selectedPiece.getCurrentPosition().getY();
            int destX = clicked.getX();
            int destY = clicked.getY();

            selectedPiece.moveTo(clicked.getX(), clicked.getY());

            System.out.println("Moved piece from "+origX+","+origY+" to "+destX+","+destY);

            tView.updateView(origX, origY, destX, destY);
            Tournament.incrementTurn();
            tView.advanceTurn(Tournament.getTurnCount(), Tournament.getWhoseTurn().getName());
            reset();
        }

        catch (ChessPiece.GetOutOfCheckException e) {
            // Tell the View to make user get out of check
            JOptionPane.showMessageDialog(null, "You are in check. You must get out of it.");
            e.printStackTrace();
            reset();
        }

        catch (ChessPiece.PutsYouInCheckException e) {
            // Tell the View to alert user that move puts them in check
            JOptionPane.showMessageDialog(null, "This move puts you in check. Try something else.");
            e.printStackTrace();
            reset();
        }

        catch (ChessPiece.OriginEqualsDestination originEqualsDestination) {
            // That's where you started. Ignore it and make them try again.
            originEqualsDestination.printStackTrace();
            reset();
        }

        catch (ChessPiece.InvalidMove invalidMove) {
            // Either someone in your way or your piece can't behave like that.
            // Ignore it and make them try again.
            JOptionPane.showMessageDialog(null, "Invalid move.");
            invalidMove.printStackTrace();
            reset();
        }
    }

    /**
     * Get the ChessPiece at the coordinate where the user clicked.
     * If it's not a piece owned by the player whose turn it is, the View will yell at them.
     * They still can take their turn though.
     * @param clicked The x,y pair representing the location where the user clicked as a Coord
     */
    // TODO WHAT'S HAPPENING????
    private void selectPiece(Coord clicked) {
        selectedPiece = tModel.getOccupant(clicked);
        if (Tournament.getWhoseTurn().getID() != selectedPiece.getPlayerID()) {
            JOptionPane.showMessageDialog(null, "Select your piece before selecting a destination");
            return;
        }
        firstClick = true;
    }

    /**
     * Takes the String representation of the coordinate and returns the Coord representation
     * @param actionCommand The String representation of the coordinate
     * @return The x,y pair representing the location where the user clicked as a Coord
     */
    private Coord parse(String actionCommand) {
        String[] strArr = actionCommand.split(",");
        int xCoord = Integer.parseInt(strArr[0]);
        int yCoord = Integer.parseInt(strArr[1]);

        return new Coord(xCoord,yCoord);
    }

    /**
     * Tell this Controller about the View
     * @param tView Our Tournament View
     */
    public void addTournamentView(TournamentView tView){
        System.out.println("Controller: adding view");
        this.tView = tView;
    }

    /**
     * Connect this Controller about the Model
     */
    public void addTournamentModel(){
        System.out.println("Controller: adding model");
        this.tModel = Tournament.getInstance();
    }

    /**
     * User input was invalid, so we're forgetting all about it and starting all over
     */
    private void reset() {
        firstClick = false;
        selectedPiece = null;
    }
}