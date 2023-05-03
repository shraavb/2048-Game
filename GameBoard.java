package org.cis120.twentyfourtyeight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

/**
 * This class instantiates a TicTacToe object, which is the model for the game.
 * As the user clicks the game board, the model is updated. Whenever the model
 * is updated, the game board repaints itself and updates its status JLabel to
 * reflect the current state of the model.
 * 
 * This game adheres to a Model-View-Controller design framework. This
 * framework is very effective for turn-based games. We STRONGLY
 * recommend you review these lecture slides, starting at slide 8,
 * for more details on Model-View-Controller:
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec37.pdf
 * 
 * In a Model-View-Controller framework, GameBoard stores the model as a field
 * and acts as both the controller (with a MouseListener) and the view (with
 * its paintComponent method and the status JLabel).
 */
@SuppressWarnings("serial")
public class GameBoard extends JPanel {

    private GameModel model; // model for the game
    private JLabel status; // current status text
    private JLabel score;

    // Game constants
    public static final int BOARD_WIDTH = 400;
    public static final int BOARD_HEIGHT = 400;

    /**
     * Initializes the game board.
     */
    public GameBoard(JLabel statusInit, JLabel scoreInit) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.WHITE);

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        model = new GameModel(); // initializes model for the game
        status = statusInit; // initializes the status JLabel
        score = scoreInit;
        /*
         * Listens for mouseclicks. Updates the model, then updates the game
         * board based off of the updated model.
         */
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                Direction input = null;
                if (keyCode == VK_DOWN || keyCode == VK_UP || keyCode == VK_LEFT
                        || keyCode == VK_RIGHT) {
                    if (keyCode == VK_DOWN) {
                        input = Direction.DOWN;
                    } else if (keyCode == VK_UP) {
                        input = Direction.UP;
                    } else if (keyCode == VK_LEFT) {
                        input = Direction.LEFT;
                    } else if (keyCode == VK_RIGHT) {
                        input = Direction.RIGHT;
                    }
                    model.playerMove(input);
                }
                updateStatusAndScore();
                repaint();
                requestFocusInWindow();
            }
        });
    }

    /**
     * (Re-)sets the game to its initial state.
     */
    public void reset() {
        model.reset();
        status.setText("Start Playing");
        score.setText("| Score: 0 Moves: 0");
        repaint();
        requestFocusInWindow();
    }

    public void save(String filename) {
        model.setFileToSave(filename);
        status.setText("Your game has been saved");
        score.setText("| Score: " + model.getScore() + " Moves: " + model.getNumMoves());
        requestFocusInWindow();
    }

    public void undo() {
        model.undo();
        updateStatusAndScore();
        repaint();
        requestFocusInWindow();
    }

    public void playSavedGame(String filename) {
        model.processFileToPlay(filename);
        updateStatusAndScore();
        repaint();
        requestFocusInWindow();
    }

    /**
     * Updates the JLabel to reflect the current state of the game.
     */
    private void updateStatusAndScore() {

        boolean winning = model.checkIfWinning();
        boolean losing = model.checkIfLosing();
        if (winning) {
            status.setText("\n You win!!");
            score.setText(
                    "| Score: " + model.getScore() + " Moves: " + model.getNumMoves()
            );
        } else if (losing) {
            status.setText("\n You have lost the game :(");
            score.setText("| Score: " + model.getScore() + " Moves: " + model.getNumMoves());

        } else {
            status.setText("\n Game is still going on");
            score.setText("| Score: " + model.getScore() + " Moves: " + model.getNumMoves());
            requestFocusInWindow();
        }
        requestFocusInWindow();
    }

    private GameModel getGameModel() {
        return this.model;
    }

    /**
     * Draws the game board.
     * 
     * There are many ways to draw a game board. This approach
     * will not be sufficient for most games, because it is not
     * modular. All of the logic for drawing the game board is
     * in this method, and it does not take advantage of helper
     * methods. Consider breaking up your paintComponent logic
     * into multiple methods or classes, like Mushroom of Doom.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draws board grid
        g.setColor(Color.YELLOW);
        g.fillRoundRect(0, 0, BOARD_HEIGHT, BOARD_WIDTH, 50, 50);
        g.setColor(Color.BLACK);
        g.drawLine(100, 0, 100, 400);
        g.drawLine(200, 0, 200, 400);
        g.drawLine(300, 0, 300, 400);

        g.drawLine(0, 100, 400, 100);
        g.drawLine(0, 200, 400, 200);
        g.drawLine(0, 300, 400, 300);
        g.drawRoundRect(0, 0, 400, 400, 50, 50);

        // Draws tiles
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int tile = model.getGridValue(i, j);
                if (tile != 0) {
                    Color c = Color.orange;
                    g.setColor(c);
                    g.fillRoundRect((i * 100), (j * 100), 100, 100, 50, 50);
                    g.setColor(Color.BLACK);
                    g.drawString(Integer.toString(tile), (50 + (i * 100)), (50 + (j * 100)));
                }
            }
        }
        requestFocusInWindow();
    }

    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}
