package org.cis120.twentyfourtyeight;

import javax.swing.*;
import java.awt.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class RunTwentyFourtyEight implements Runnable {
    public void run() {

        final JFrame frame = new JFrame("2048");
        frame.setLocation(500, 500);

        // Status panel
        final JPanel status_panel = new JPanel();
        final JPanel score_panel = new JPanel();

        frame.add(status_panel, BorderLayout.BEFORE_FIRST_LINE);
        final JLabel status = new JLabel("Start Playing |");
        final JLabel score = new JLabel(" Score: 0 Moves: 0");
        status_panel.add(status);
        status_panel.add(score);

        // Main playing area
        final GameBoard court = new GameBoard(status, score);
        frame.add(court, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(
                frame,
                "Here are the instructions:" +
                        "\n1. The goal is to combine tiles to eventually get the number 2048" +
                        "\nOnly tiles of the same value can be combined.\n" +
                        "For example, tiles of value 4, and 4 can be combined but " +
                        "\ntiles 4 and 8 cannot be combined.\n" +
                        "\n2. The tiles can be combined by pressing the up, down, left and right" +
                        "\narrow keys.\nUpon the click of an arrow key, each of the blocks" +
                        "\non the board will move the furthest they can in that direction." +
                        "\nIf, as a result of the key press, the tile is next to another tile" +
                        "\nof the same value, then the two values combine to form a tile" +
                        "\nthat is double the value of the two tiles.\n" +
                        "\n3. The game ends either when you have created a tile " +
                        "with a value of 2048 or"
                        +
                        "\nwhen you are no longer able to move or combine any " +
                        "more tiles because all of "
                        + "\nthe spaces on your grid have been filled with a tile.\n" + "" +
                        "\n4. Your score is calculated from the tiles you combine\n\n" +
                        "\nMy 2048 game also contains a undo button that reverses " +
                        "the previous move and reverts "
                        + "\nthe grid to its previous state before the arrow key " +
                        "was pressed. You can save multiple "
                        +
                        "games" +
                        "\nif you click the Save Game Button and insert a name for " +
                        "your game. But, you must "
                        +
                        "remember" +
                        "\nthe name of the file and input it when prompted by the " +
                        "Play Saved Game Button to "
                        +
                        "play your" +
                        "\nsaved game." + "If you input a non-existent game name" +
                        "the grid will be empty"
        );

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.PAGE_END);

        final JButton saveGame = new JButton("Save Game");
        control_panel.add(saveGame);
        saveGame.addActionListener(e -> {
            String fileName = (String) JOptionPane.showInputDialog(
                    frame,
                    "Name of file you want to store game in: " +
                            "\nPlease input the name without .txt" +
                            "\nNote:" +
                            "\nIf you type a file that you have already " +
                            "stored a game in, the current"
                            +
                            "\ngame will overwrite that file.",
                    JOptionPane.PLAIN_MESSAGE
            );
            if ((fileName != null) && (fileName.length() > 0)) {
                // create file and input value
                String finalFileName = fileName + ".txt";
                try {
                    court.save(finalFileName);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            frame,
                            "You inputted a filename that already exists."
                    );
                }
            }
        });

        final JButton newGame = new JButton("Start New Game");
        newGame.addActionListener(e -> court.reset());
        control_panel.add(newGame);

        final JButton undo = new JButton("Undo Last Move");
        undo.addActionListener(e -> court.undo());
        control_panel.add(undo);

        final JButton playSavedGame = new JButton("Play Saved Game");
        playSavedGame.addActionListener(e -> {
            String fileName = JOptionPane.showInputDialog(
                    frame,
                    "Name of file you want to play",
                    JOptionPane.PLAIN_MESSAGE
            );
            if ((fileName != null) && (fileName.length() > 0)) {
                // create file and input value
                String finalFileName = fileName + ".txt";
                try {
                    court.playSavedGame(finalFileName);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            frame,
                            "You inputted an invalid file name. " +
                                    "Make sure you are inputting the name without .txt."
                    );
                }
            }
        });
        control_panel.add(playSavedGame);

        // newGame.addActionListener(e -> court.reset());
        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        court.reset();
    }
}