/**
 * Initially wasn't my code, but I refactored heavily. To see the basis
 * of my design, refer to here:
 * http://stackoverflow.com/questions/21142686/making-a-robust-resizable-swing-chess-gui
 */

package src.com.lhumphr2.chess.view;

import src.com.lhumphr2.chess.controller.TournamentController;
import src.com.lhumphr2.chess.model.Coord;
import src.com.lhumphr2.chess.model.Tournament;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Stack;

/**
 * Created by lawrencehumphrey on 9/20/15.
 */
public class TournamentView {
    private static TournamentView _instance;
    // Dimensions of the board. Can be configure.
    private int width = 8;
    private int height = 8;

    // For my convenience so I'm not passing these around as params everywhere
    private static String name1;
    private static String name2;

    // The container
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private final JLabel message = new JLabel("Let's play some chess dudes!");

    // Toolbar buttons
    public final static JButton NEW_GAME = new JButton("New Game");
    public final static JButton UNDO     = new JButton("Undo");
    public final static JButton FORFEIT  = new JButton("Forfeit");

    // The chessboard gui
    private JPanel chessBoard;
    private JButton[][] chessBoardSquares = new JButton[width][height];

    // The chess piece sprites
    private Image[][] chessPieceImages = new Image[2][6];

    // Scoreboard fields
    private JLabel turnNumber = new JLabel("Turn: " + 1);
    private JLabel turnPlayer;
    private JLabel p1Score;
    private JLabel p2Score;

    // The controller which will serve as the intermediate between this view and the model
    private static TournamentController controller;

    // Stack of icons for undoing ya know
    private static Stack<State> history;


    public TournamentView(String name1, String name2) {
        _instance = this;
        this.name1 = name1;
        this.name2 = name2;
        history = new Stack<>();
        initController();
        initializeGUI();
    }

    /**
     * This probably shouldn't go here but nothing else works god gui programming
     * is bad.
     */
    private void initController() {
        // View knows about controller
        controller = new TournamentController();

        // Add button listeners for the toolbar
        NEW_GAME.addActionListener(controller);
        UNDO.addActionListener(controller);
        FORFEIT.addActionListener(controller);

        // Controller knows about View
        controller.addTournamentView(this);
    }

    /**
     * Does all of the heavy lifting. All of the view initializations.
     */
    private final void initializeGUI() {
        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            // handle exception
        }
        catch (ClassNotFoundException e) {
            // handle exception
        }
        catch (InstantiationException e) {
            // handle exception
        }
        catch (IllegalAccessException e) {
            // handle exception
        }

        createImages();

        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        addToolBar();
        addChessBoard(width, height);
        initScoreBoard();
    }

    /**
     * Grabs the images from online and puts them in my array
     */
    private final void createImages() {
        // TODO Make it such that i have the assets offline
        try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);
            for (int ii = 0; ii < 2; ii++) {
                for (int jj = 0; jj < 6; jj++) {
                    chessPieceImages[ii][jj] = bi.getSubimage(
                            jj * 64, ii * 64, 64, 64);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Adds the GUI representation of the toolbar, which has the buttons
     * for new game, undo, and forfeit.
     */
    private void addToolBar() {
        // Initialize the toolbar container
        // Most of this code isn't mine
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);

        tools.add(NEW_GAME);
        tools.addSeparator();
        tools.add(UNDO);
        tools.addSeparator();
        tools.add(FORFEIT);
        tools.addSeparator();
        tools.add(message);
    }

    /**
     * Adds the section for the actual game, including the chess board itself.
     * Params are so I can hopefully generalize this one day.
     * @param width Desired width
     * @param height Desired height
     */
    private void addChessBoard(int width, int height) {
        // Not my code.
        chessBoard = new JPanel(new GridLayout(0, 9)) {

            /**
             * Override the preferred size to return the largest it can, in
             * a square shape.  Must (must, must) be added to a GridBagLayout
             * as the only component (it uses the parent as a guide to size)
             * with no GridBagConstaint (so it is centered).
             */
            @Override
            public final Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                Dimension prefSize = null;
                Component c = getParent();
                if (c == null) {
                    prefSize = new Dimension(
                            (int)d.getWidth(),(int)d.getHeight());
                } else if (c!=null &&
                        c.getWidth()>d.getWidth() &&
                        c.getHeight()>d.getHeight()) {
                    prefSize = c.getSize();
                } else {
                    prefSize = d;
                }
                int w = (int) prefSize.getWidth();
                int h = (int) prefSize.getHeight();
                // the smaller of the two sizes
                int s = (w>h ? h : w);
                return new Dimension(s,s);
            }
        };
        chessBoard.setBorder(new CompoundBorder(
                new EmptyBorder(8, 8, 8, 8),
                new LineBorder(Color.BLACK)
        ));
        Color c = new Color(30,144,255);
        chessBoard.setBackground(c);
        JPanel boardConstrain = new JPanel(new GridBagLayout());
        boardConstrain.setBackground(c);
        boardConstrain.add(chessBoard);
        gui.add(boardConstrain);

        createChessBoardSquares();
        fillChessBoard();
    }

    /**
     * Creates the actual squares, big surprise, I know
     */
    private void createChessBoardSquares() {
        // create the chess board squares
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                b.setOpaque(true);
                b.addActionListener(controller);
                b.setActionCommand(jj + "," + ii);
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if ((jj % 2 == 1 && ii % 2 == 1)
                        //) {
                        || (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.BLACK);
                }
                chessBoardSquares[jj][ii] = b;
            }
        }

    }

    private void fillChessBoard() {
        String COLS = "ABCDEFGH";

        chessBoard.add(new JLabel(""));
        // fill the top row
        for (int ii = 0; ii < 8; ii++) {
            chessBoard.add(
                    new JLabel(COLS.substring(ii, ii + 1),
                            SwingConstants.CENTER));
        }
        // fill the black non-pawn piece row
        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                switch (jj) {
                    case 0:
                        chessBoard.add(new JLabel("" + (9-(ii + 1)),
                                SwingConstants.CENTER));
                    default:
                        chessBoard.add(chessBoardSquares[jj][ii]);
                }
            }
        }

    }

    /**
     * The right panel you see? That's all this guy.
     */
    private void initScoreBoard() {
        JPanel scoreBoard = new JPanel();
        scoreBoard.setLayout(new BoxLayout(scoreBoard, BoxLayout.Y_AXIS));
        scoreBoard.setMinimumSize(new Dimension(200, 10));

        turnPlayer = new JLabel(name1 + "'s Turn");

        turnNumber.setBorder(new EmptyBorder(2, 30, 10, 30));
        turnPlayer.setBorder(new EmptyBorder(2, 30, 20, 30));

        p1Score = new JLabel(name1 + "'s Score: 0");
        p2Score = new JLabel(name2 + "'s Score: 0");

        p1Score.setBorder(new EmptyBorder(20, 30, 2, 30));
        p2Score.setBorder(new EmptyBorder(10, 30, 2, 30));

        turnNumber.setAlignmentX(Component.CENTER_ALIGNMENT);
        turnPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);

        p1Score.setAlignmentX(Component.CENTER_ALIGNMENT);
        p2Score.setAlignmentX(Component.CENTER_ALIGNMENT);

        scoreBoard.add(turnNumber);
        scoreBoard.add(turnPlayer);
        scoreBoard.add(p1Score);
        scoreBoard.add(p2Score);

        gui.add(scoreBoard, BorderLayout.LINE_END);
    }
    /**
     * Called after the New Game button is pressed. Puts the new pieces on the board.
     */
    public void setupNewGame() {
        // Change view to reflect new game (i.e. populate with pieces)

        int QUEEN = 0, KING = 1, ROOK = 2, KNIGHT = 3, BISHOP = 4, PAWN = 5;

        int[] STARTING_ROW = {
                ROOK, KNIGHT, BISHOP, KING, QUEEN, BISHOP, KNIGHT, ROOK
        };

        int BLACK = 0, WHITE = 1;


        message.setText("Make your move!");
        // set up the black pieces
        for (int ii = 0; ii < STARTING_ROW.length; ii++) {
            chessBoardSquares[ii][0].setIcon(new ImageIcon(
                    chessPieceImages[BLACK][STARTING_ROW[ii]]));
        }
        for (int ii = 0; ii < STARTING_ROW.length; ii++) {
            chessBoardSquares[ii][1].setIcon(new ImageIcon(
                    chessPieceImages[BLACK][PAWN]));
        }
        // set up the white pieces
        for (int ii = 0; ii < STARTING_ROW.length; ii++) {
            chessBoardSquares[ii][6].setIcon(new ImageIcon(
                    chessPieceImages[WHITE][PAWN]));
        }
        for (int ii = 0; ii < STARTING_ROW.length; ii++) {
            chessBoardSquares[ii][7].setIcon(new ImageIcon(
                    chessPieceImages[WHITE][STARTING_ROW[ii]]));
        }

        for(int jj=2; jj<6; jj++) {
            for(int ii=0; ii<8; ii++) {
                chessBoardSquares[ii][jj].setIcon(null);
            }
        }
        resetScoreBoard();
    }

    private void resetScoreBoard() {
        turnNumber = new JLabel("Turn: " + 1);
        turnPlayer = new JLabel(name1 + "'s Turn");
        p1Score = new JLabel(name1 + "'s Score: 0");
        p2Score = new JLabel(name2 + "'s Score: 0");
    }

    public static TournamentView getInstance() {
        return _instance;
    }

    /**
     * This is the bamf that is all as strong as main() just without the title
     */
    public static void go() {
        Runnable r = () -> {
            TournamentView tView = getInstance();

            JFrame f = new JFrame("Chess");
            f.add(tView.getGui());
            // Ensures JVM closes after frame(s) closed and
            // all non-daemon threads are finished
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setLocationByPlatform(true);
            // ensures the frame is the minimum size it needs to be
            // in order display the components within it
            f.pack();
            // ensures the minimum size is enforced.
            f.setMinimumSize(f.getSize());
            f.setVisible(true);
        };
        SwingUtilities.invokeLater(r);
    }

    public JPanel getGui() {
        return gui;
    }

    public static TournamentController getController() {
        return controller;
    }

    /**
     * Updates the view to reflect the new movement.
     * @param origX Original x coordinate
     * @param origY Original y coordinate
     * @param destX Destination x coordinate
     * @param destY Destination y coordinate
     */
    public void updateView(int origX, int origY, int destX, int destY) {
        State s1 = new State(new Coord(origX, origY), chessBoardSquares[origX][origY].getIcon());
        State s2 = new State(new Coord(destX, destY), chessBoardSquares[destX][destY].getIcon());

        Icon icon = chessBoardSquares[origX][origY].getIcon();

        history.push(s1);
        history.push(s2);
        chessBoardSquares[origX][origY].setIcon(null);
        chessBoardSquares[destX][destY].setIcon(icon);
    }

    /**
     * Update the scoreboard to reflect the new turn
     * @param turn New turn count
     * @param name Name of player whose turn it is
     */
    public void advanceTurn(int turn, String name) {
        turnNumber.setText("Turn: " + turn);
        turnPlayer.setText(name + "'s Turn");
    }

    /**
     * Revert board back to previous state
     */
    public void undo() {
        // Don't allow user to pop from empty stack
        if (history.empty()) {
            return;
        }

        State s2 = history.pop();
        State s1 = history.pop();

        chessBoardSquares[s2.pos.getX()][s2.pos.getY()].setIcon(s2.icon);
        chessBoardSquares[s1.pos.getX()][s1.pos.getY()].setIcon(s1.icon);

        Tournament.decrementTurn();
    }
}
