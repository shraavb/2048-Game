package org.cis120.twentyfourtyeight;

import java.io.File;
import java.io.*;
import java.util.*;

/**
 * CIS 120 HW09 - 2048
 * Created by Shraavasti
 */

/**
 * This class is a model for 2048.
 * 
 * This game adheres to a Model-View-Controller design framework.
 *
 * This model is completely independent of the view and controller.
 * The game can be played from start to finish without ever drawing anything
 * on a screen or instantiating a Java Swing object.
 * 
 * Run this file to see the main method play a game of 2048,
 * visualized with Strings printed to the console.
 */
public class GameModel {

    private int[][] board;
    private int numMoves = 0;
    private int score;
    private static int height;
    private static int width;
    private LinkedList<GameModel> gameQueue = new LinkedList<GameModel>();
    private boolean winning = false;
    private boolean losing = false;

    /**
     * Constructor sets up game state.
     */
    public GameModel() {
        reset();
        gameQueue.add(this);
    }

    public GameModel(int[][] board) {
        reset();
        this.board = board;
        gameQueue.add(this);
    }

    public GameModel copyGameModel() {

        int[][] copyOfBoard = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                copyOfBoard[i][j] = this.board[i][j];
            }
        }
        GameModel copy = new GameModel(copyOfBoard);
        copy.numMoves = this.numMoves;
        copy.score = this.score;
        return copy;
    }

    /**
     * copyOfBoard gets the copy of the current board of the game
     * to maintain encapsulation
     *
     * @return a copy of the 2D array representing the current state of the board
     */
    public int[][] copyOfBoard() {

        int[][] copyOfBoard = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                copyOfBoard[i][j] = this.board[i][j];
            }
        }
        return copyOfBoard;
    }

    /**
     * playMove allows players to play a move. Completes the move if the input is
     * valid.
     * If the key input is invalid nothing happens and the player can continue to
     * play
     * the game.
     *
     * @param //direc Direction of key
     * @return void
     */

    public void playerMove(Direction direc) {
        int[][] prevBoard = copyOfBoard();
        gameQueue.add(this.copyGameModel());
        if (!winning && !losing) {
            if (direc == Direction.UP) {
                numMoves++;
                for (int j = 0; j < board.length; j++) { // column
                    for (int i = 0; i < board.length; i++) { // row
                        // Start at r = 0, look at r+1 if same value
                        // combine and set r+1 to empty. Then for next
                        // row look if r-1 is empty and if it is take its spot
                        if (i + 1 < 4) {
                            int k = i + 1;
                            while (k < 4) {
                                if (board[i][j] != 0 && board[i][j] == board[k][j]
                                        && board[i][j] == prevBoard[i][j]) {
                                    prevBoard = copyOfBoard();
                                    board[i][j] = 2 * board[i][j];
                                    board[k][j] = 0;
                                    score = score + board[i][j];
                                } else if (board[k][j] != 0 && board[k][j] != board[i][j]) {
                                    k = 4;
                                }
                                k++;
                            }
                        }
                        // Set p = i. While (p > 0) check board row p-1
                        // is empty. If so move to that spot. Else check
                        // board row p-1 is same value. If so combine. Update p = p-1
                        int p = i;
                        while (p > 0) {
                            if (board[p - 1][j] == 0) {
                                board[p - 1][j] = board[p][j];
                                board[p][j] = 0;
                            } else if (board[p - 1][j] == board[p][j]
                                    && board[p][j] == prevBoard[p][j]) {
                                prevBoard = copyOfBoard();
                                board[p - 1][j] = 2 * board[p][j];
                                board[p][j] = 0;
                                score = score + board[p - 1][j];
                            }
                            p--;
                        }

                    }
                }

            }
            if (direc == Direction.DOWN) {
                numMoves++;
                for (int j = 0; j < board.length; j++) { // column
                    for (int i = board.length - 1; i > -1; i--) { // row
                        // Start at r = 3, look at r-1 if same value
                        // combine and set r-1 to empty. Then for next
                        // row look if r+1 is empty and if it is take its spot
                        if (i - 1 > -1) {
                            int k = i - 1;
                            while (k > -1) {
                                if (board[i][j] != 0 && board[i][j] == board[k][j]
                                        && board[i][j] == prevBoard[i][j]) {
                                    prevBoard = copyOfBoard();
                                    board[i][j] = 2 * board[k][j];
                                    board[k][j] = 0;
                                    score = score + board[i][j];

                                } else if (board[k][j] != 0 && board[i][j] != board[k][j]) {
                                    k = -1;
                                }
                                k--;
                            }
                        }
                        // Set p = i. While (p < 3) check board row p + 1
                        // is empty. If so move to that spot. Else check
                        // board row p+1 is same value. If so combine. Update p = p+1
                        int p = i;
                        while (p < 3) {
                            if (board[p + 1][j] == 0) {
                                board[p + 1][j] = board[p][j];
                                board[p][j] = 0;
                            } else if (board[p + 1][j] == board[p][j]
                                    && board[p][j] == prevBoard[p][j]) {
                                prevBoard = copyOfBoard();
                                board[p + 1][j] = 2 * board[p][j];
                                board[p][j] = 0;
                                score = score + board[p + 1][j];
                            }
                            p++;
                        }

                    }
                }
            }

            if (direc == Direction.LEFT) {
                numMoves++;
                for (int i = 0; i < board.length; i++) { // row
                    for (int j = 0; j < board.length; j++) { // column

                        // Start at r = 0, look at r+1 if same value
                        // combine and set r+1 to empty. Then for next
                        // row look if r+1 is empty and if it is take its spot
                        if (j + 1 < 4) {
                            int k = j + 1;
                            while (k < 4) {
                                if (board[i][j] != 0 && board[i][j] == board[i][k]
                                        && board[i][j] == prevBoard[i][j]) {
                                    prevBoard = copyOfBoard();
                                    board[i][j] = 2 * board[i][k];
                                    board[i][k] = 0;
                                    score = score + board[i][j];

                                } else if (board[i][k] != 0 && board[i][j] != board[i][k]) {
                                    k = 4;
                                }
                                k++;
                            }
                        }
                        // Set p = j. While (p > 0) check board row p - 1
                        // is empty. If so move to that spot. Else check
                        // board row p - 1 is same value. If so combine. Update p = p-1
                        int p = j;
                        while (p > 0) {
                            if (board[i][p - 1] == 0) {
                                board[i][p - 1] = board[i][p];
                                board[i][p] = 0;
                            } else if (board[i][p - 1] == board[i][p]
                                    && board[i][p] == prevBoard[i][p]) {
                                prevBoard = copyOfBoard();
                                board[i][p - 1] = 2 * board[i][p];
                                board[i][p] = 0;
                                score = score + board[i][p - 1];
                            }
                            p--;
                        }

                    }
                }

            }

            if (direc == Direction.RIGHT) {
                numMoves++;
                for (int i = 0; i < board.length; i++) { // row
                    for (int j = board.length - 1; j > -1; j--) { // column

                        // Start at r = 3, look at r+1 if same value
                        // combine and set r+1 to empty. Then for next
                        // row look if r+1 is empty and if it is take its spot
                        if (j - 1 > -1) {
                            int k = j - 1;
                            while (k > -1) {
                                if (board[i][j] != 0 && board[i][j] == board[i][k]
                                        && board[i][j] == prevBoard[i][j]) {
                                    prevBoard = copyOfBoard();
                                    board[i][j] = 2 * board[i][k];
                                    board[i][k] = 0;
                                    score = score + board[i][j];
                                } else if (board[i][k] != 0 && board[i][j] != board[i][k]) {
                                    k = -1;
                                }
                                k--;
                            }
                        }
                        // Set p = j. While (p < 3) check board row p + 1
                        // is empty. If so move to that spot. Else check
                        // board row p + 1 is same value. If so combine. Update p = p+1
                        int p = j;
                        while (p < 3) {
                            if (board[i][p + 1] == 0) {
                                board[i][p + 1] = board[i][p];
                                board[i][p] = 0;
                            } else if (board[i][p + 1] == board[i][p]
                                    && board[i][p] == prevBoard[i][p]) {
                                prevBoard = copyOfBoard();
                                board[i][p + 1] = 2 * board[i][p];
                                board[i][p] = 0;
                                score = score + board[i][p + 1];
                            }
                            p++;
                        }

                    }
                }

            }
            if (checkIfBoardContainsEmptySpaces()) {
                insertRandom2();
            }
        }
    }

    /**
     * helper function to insert a 2 tile randomly after a move is played to
     * continue game
     */
    private void insertRandom2() {
        int marker = 0;
        while (marker == 0) {
            int row = (int) (Math.random() * 10);
            int col = (int) (Math.random() * 10);
            if (row < 4 && col < 4) {
                if (board[row][col] == 0) {
                    board[row][col] = 2;
                    marker = 1;

                }
            }
        }
    }

    /**
     * undo the most recent move and return the current board to its previous state
     * before the move
     */
    public void undo() {
        if (!gameQueue.isEmpty()) {
            GameModel previousState = gameQueue.removeLast();
            this.board = previousState.copyOfBoard();
            this.numMoves = previousState.getNumMoves();
            this.score = previousState.getScore();
        }
    }

    /**
     * checkWinner checks whether the game has reached a win condition.
     * The game needs to contain a tile with 2048
     *
     * @return false if the game board does not contain a 2048 tile
     */
    public boolean checkIfWinning() {
        // Check horizontal win
        for (int[] ints : board) {
            for (int ints2 : ints) {
                if (ints2 == 2048) {
                    winning = true;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if game is in a losing state
     */
    public boolean checkIfLosing() {
        // Check horizontal win
        if (!winning) {
            for (int[] ints : board) {
                for (int ints2 : ints) {
                    if (ints2 == 0) {
                        return false;
                    }
                }
            }
            for (int i = 0; i < 4; i++) { // row
                for (int j = 0; j < 4; j++) { // column
                    if (i + 1 < 4 && board[i][j] == board[i + 1][j] && board[i][j] != 0) {
                        return false;
                    }
                    if (j + 1 < 4 && board[i][j] == board[i][j + 1] && board[i][j] != 0) {
                        return false;
                    }
                    if (i - 1 > -1 && board[i][j] == board[i - 1][j] && board[i][j] != 0) {
                        return false;
                    }
                    if (j - 1 > -1 && board[i][j] == board[i][j - 1] && board[i][j] != 0) {
                        return false;
                    }
                }
            }
        }
        losing = true;
        return true;
    }

    /**
     * printGameState prints the current game state
     * for debugging.
     */
    public void printGameState() {
        System.out.println("\n\nNumMoves " + numMoves + ":\n");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (j == 0 && i != 0) {
                    System.out.print("\n" + board[i][j]);
                } else {
                    System.out.print(board[i][j]);
                }
            }
        }
    }

    /**
     * helper method to get the current score of the board
     */
    public int getScore() {
        return this.score;
    }

    private boolean checkIfBoardContainsEmptySpaces() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * helper method to get the current number of moves of the board
     */
    public int getNumMoves() {
        return this.numMoves;
    }

    /**
     * helper method to get the current queue of the board
     */
    public LinkedList<GameModel> getGameQueue() {
        return this.gameQueue;
    }

    /**
     * reset (re-)sets the game state to start a new game.
     */
    public void reset() {
        score = 0;
        numMoves = 0;
        height = 4;
        width = 4;
        board = new int[height][width];
        insertRandom2();
        winning = false;
        losing = false;
    }

    /**
     * getGridValue is a getter for the contents of the cell specified by the method
     * arguments.
     *
     * @param c column to retrieve
     * @param r row to retrieve
     * @return the value stored at that index of the board
     */
    public int getGridValue(int c, int r) {
        return board[r][c];
    }

    /**
     * addGridValue inserts the given value at the index specified by the method
     * arguments.
     *
     * @param column column to retrieve
     * @param row    row to retrieve
     * @param c      the value to be stored at that index of the board
     */
    public void addGridValue(int c, int row, int column) {
        this.board[row][column] = c;
    }

    /**
     * Method to help with testing
     * Sets the number of moves for a game model object
     */
    public void setNumMoves(int moves) {
        this.numMoves = moves;
    }

    /**
     * Method to help with testing
     * Sets the score for a game model object
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Method to help with testing
     * Fills the file that the game model should be stored in
     */
    public void setFileToSave(String fileName) {
        int[][] copy = copyOfBoard();

        BufferedWriter bw;
        try {
            Writer newWriter = new FileWriter(fileName, false);
            bw = new BufferedWriter(newWriter);
            bw.write(score + "\n");
            bw.write(numMoves + "\n");
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    bw.write(copy[i][j] + "\n");
                }
            }

            bw.close();
        } catch (Exception e) {

        }
    }

    /**
     * @param fileName name of file to be processed to play the save game
     *                 Processes the file to set the instance variable and prepare
     *                 the game model for the player
     *                 to play the save game
     */
    public void processFileToPlay(String fileName) {
        int[][] board = new int[4][4];
        int score = 0;
        String scoreString;
        int moves = 0;
        String movesString;
        File game = new File(fileName);
        // if (game.exists())

        Reader fr;
        try {
            fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            scoreString = br.readLine();
            score = Integer.parseInt(scoreString);
            movesString = br.readLine();
            moves = Integer.parseInt(movesString);

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    String val = br.readLine();
                    board[i][j] = Integer.parseInt(val);
                }
            }

            br.close();
        } catch (Exception e) {

        }
        this.board = board;
        this.score = score;
        this.numMoves = moves;
    }

    /**
     * This main method illustrates how the model is completely independent of
     * the view and controller. We can play the game from start to finish
     * without ever creating a Java Swing object.
     *
     * This is modularity in action, and modularity is the bedrock of the
     * Model-View-Controller design framework.
     *
     * Run this file to see the output of this method in your console.
     */
    public static void main(String[] args) {
        int[][] board = new int[4][4];
        GameModel t = new GameModel(board);
        t.addGridValue(2, 2, 3);
        t.addGridValue(2, 1, 3);
        t.addGridValue(4, 0, 3);
        t.printGameState();

        t.playerMove(Direction.UP);
        t.printGameState();

        t.playerMove(Direction.LEFT);
        t.printGameState();

        t.playerMove(Direction.RIGHT);
        t.printGameState();

        t.playerMove(Direction.LEFT);
        t.printGameState();

        t.playerMove(Direction.RIGHT);
        t.printGameState();

        System.out.println("Winner is: " + t.checkIfWinning());
    }
}
