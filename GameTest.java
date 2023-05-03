package org.cis120.twentyfourtyeight;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * You can use this file (and others) to test your
 * implementation.
 */

public class GameTest {

    @Test
    public void leftMergeSameNumberAfterMergeGameModelTest() {
        GameModel t = new GameModel(new int[4][4]);
        t.addGridValue(4, 0, 3);
        t.addGridValue(4, 0, 2);
        t.addGridValue(8, 0, 1);
        t.playerMove(Direction.LEFT);
        int[][] expected = t.copyOfBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (expected[i][j] != 2) {
                    expected[i][j] = 0;
                }
            }
        }
        expected[0][1] = 8;
        expected[0][0] = 8;
        Assertions.assertArrayEquals(expected, t.copyOfBoard());
        assertEquals(t.getNumMoves(), 1);
        assertEquals(t.getScore(), 8);

    }

    @Test
    public void rightMergeSameNumberAfterMergeGameModelTest() {
        GameModel t = new GameModel();
        t.addGridValue(2, 0, 3);
        t.addGridValue(2, 0, 2);
        t.addGridValue(4, 0, 1);
        t.playerMove(Direction.RIGHT);
        int[][] expected = t.copyOfBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (expected[i][j] != 2) {
                    expected[i][j] = 0;
                }
            }
        }
        expected[0][3] = 4;
        expected[0][2] = 4;
        assertTrue(Arrays.deepEquals(expected, t.copyOfBoard()));
        assertEquals(t.getNumMoves(), 1);
        assertEquals(t.getScore(), 4);

    }

    @Test
    public void upMergeSameNumberAfterMergeGameModelTest() {
        GameModel t = new GameModel();
        t.addGridValue(4, 0, 0);
        t.addGridValue(4, 1, 0);
        t.addGridValue(8, 2, 0);
        t.playerMove(Direction.UP);
        int[][] expected = t.copyOfBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (expected[i][j] != 2) {
                    expected[i][j] = 0;
                }
            }
        }
        expected[0][0] = 8;
        expected[1][0] = 8;
        assertTrue(Arrays.deepEquals(expected, t.copyOfBoard()));
        assertEquals(t.getNumMoves(), 1);
        assertEquals(t.getScore(), 8);

    }

    @Test
    public void downMergeSameNumberAfterMergeGameModelTest() {
        GameModel t = new GameModel();
        t.addGridValue(4, 0, 0);
        t.addGridValue(4, 1, 0);
        t.addGridValue(4, 2, 0);
        t.playerMove(Direction.DOWN);
        int[][] expected = t.copyOfBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (expected[i][j] != 2) {
                    expected[i][j] = 0;
                }
            }
        }
        expected[3][0] = 8;
        expected[2][0] = 4;
        assertTrue(Arrays.deepEquals(expected, t.copyOfBoard()));
        assertEquals(t.getNumMoves(), 1);
        assertEquals(t.getScore(), 8);

    }

    @Test
    public void downMoreThanOneEmptySpacesTest() {
        GameModel t = new GameModel(new int[4][4]);
        t.addGridValue(2, 0, 0);
        t.playerMove(Direction.DOWN);
        int[][] expected = t.copyOfBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (expected[i][j] != 2) {
                    expected[i][j] = 0;
                }
            }
        }
        expected[3][0] = 2;
        assertTrue(Arrays.deepEquals(expected, t.copyOfBoard()));
        assertEquals(t.getNumMoves(), 1);
        assertEquals(t.getScore(), 0);

    }

    @Test
    public void leftMoreThanOneEmptySpacesTest() {
        GameModel t = new GameModel(new int[4][4]);
        t.addGridValue(2, 1, 2);
        t.playerMove(Direction.LEFT);
        int[][] expected = t.copyOfBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (expected[i][j] != 2) {
                    expected[i][j] = 0;
                }
            }
        }
        expected[1][0] = 2;
        assertTrue(Arrays.deepEquals(expected, t.copyOfBoard()));
        assertEquals(t.getNumMoves(), 1);
        assertEquals(t.getScore(), 0);

    }

    @Test
    public void rightMoreThanOneEmptySpacesTest() {
        GameModel t = new GameModel(new int[4][4]);
        t.addGridValue(2, 1, 1);
        t.playerMove(Direction.RIGHT);
        int[][] expected = t.copyOfBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (expected[i][j] != 2) {
                    expected[i][j] = 0;
                }
            }
        }
        expected[1][3] = 2;
        assertTrue(Arrays.deepEquals(expected, t.copyOfBoard()));
        assertEquals(t.getNumMoves(), 1);
        assertEquals(t.getScore(), 0);

    }

    @Test
    public void upMoreThanOneEmptySpacesTest() {
        GameModel t = new GameModel();
        t.addGridValue(16, 3, 3);
        t.playerMove(Direction.UP);
        int[][] expected = t.copyOfBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (expected[i][j] != 2) {
                    expected[i][j] = 0;
                }
            }
        }
        expected[0][3] = 16;
        assertTrue(Arrays.deepEquals(expected, t.copyOfBoard()));
        assertEquals(t.getNumMoves(), 1);
        assertEquals(t.getScore(), 0);

    }

    @Test
    public void upMergeCreatesSpaceBetweenTwoTilesTest() {
        GameModel t = new GameModel();
        t.addGridValue(16, 3, 0);
        t.addGridValue(4, 2, 0);
        t.addGridValue(4, 1, 0);
        t.playerMove(Direction.UP);
        int[][] expected = t.copyOfBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (expected[i][j] != 2) {
                    expected[i][j] = 0;
                }
            }
        }
        expected[0][0] = 8;
        expected[1][0] = 16;
        assertTrue(Arrays.deepEquals(expected, t.copyOfBoard()));
        assertEquals(t.getNumMoves(), 1);
        assertEquals(t.getScore(), 8);

    }

    @Test
    public void leftMergeCreatesSpaceBetweenTwoTilesTest() {
        GameModel t = new GameModel();
        t.addGridValue(8, 0, 3);
        t.addGridValue(2, 0, 2);
        t.addGridValue(2, 0, 1);
        t.playerMove(Direction.LEFT);
        int[][] expected = t.copyOfBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (expected[i][j] != 2) {
                    expected[i][j] = 0;
                }
            }
        }
        expected[0][1] = 8;
        expected[0][0] = 4;
        assertTrue(Arrays.deepEquals(expected, t.copyOfBoard()));
        assertEquals(t.getNumMoves(), 1);
        assertEquals(t.getScore(), 4);

    }

    @Test
    public void downMergeCreatesSpaceBetweenTwoTilesTest() {
        GameModel t = new GameModel();
        t.addGridValue(2, 2, 0);
        t.addGridValue(2, 1, 0);
        t.addGridValue(8, 0, 0);
        t.playerMove(Direction.DOWN);
        int[][] expected = t.copyOfBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (expected[i][j] != 2) {
                    expected[i][j] = 0;
                }
            }
        }
        expected[2][0] = 8;
        expected[3][0] = 4;
        assertTrue(Arrays.deepEquals(expected, t.copyOfBoard()));
        assertEquals(t.getNumMoves(), 1);
        assertEquals(t.getScore(), 4);

    }

    @Test
    public void rightMergeCreatesSpaceBetweenTwoTilesTest() {
        GameModel t = new GameModel();
        t.addGridValue(2, 0, 2);
        t.addGridValue(2, 0, 1);
        t.addGridValue(8, 0, 0);
        t.playerMove(Direction.RIGHT);
        int[][] expected = t.copyOfBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (expected[i][j] != 2) {
                    expected[i][j] = 0;
                }
            }
        }
        expected[0][3] = 4;
        expected[0][2] = 8;
        assertTrue(Arrays.deepEquals(expected, t.copyOfBoard()));
        assertEquals(t.getNumMoves(), 1);
        assertEquals(t.getScore(), 4);

    }

    @Test
    public void reached2048StopGameGameModelTest() {
        GameModel t = new GameModel();
        t.addGridValue(2, 0, 2);
        t.addGridValue(2, 0, 1);
        t.addGridValue(2048, 0, 0);
        int[][] board = t.copyOfBoard();
        assertTrue(t.checkIfWinning());
        assertTrue(Arrays.deepEquals(t.copyOfBoard(), board));
    }

    @Test
    public void notReached2048ButNotLosingGameModelTest() {
        GameModel t = new GameModel();
        t.addGridValue(2, 0, 2);
        t.addGridValue(2, 0, 1);
        assertFalse(t.checkIfWinning());
        assertFalse(t.checkIfLosing());

    }

    @Test
    public void losingAllTilesFilledButAllDifferentGameModelTest() {
        int[][] board = new int[4][4];
        int num = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = (int) (Math.pow(2, num));
                num++;
            }
        }
        GameModel t = new GameModel(board);
        assertTrue(t.checkIfLosing());
    }

    @Test
    public void notLosingAllTilesFilledButAllSameGameModelTest() {
        int[][] board = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = 2;
            }
        }
        GameModel t = new GameModel(board);
        assertFalse(t.checkIfWinning());
        assertFalse(t.checkIfLosing());
    }

    @Test
    public void processFileToPlayGameModelTest() {
        GameModel t = new GameModel(new int[4][4]);
        t.processFileToPlay("output1.txt");
        assertEquals(0, t.getScore());
        assertEquals(1, t.getNumMoves());
        int[][] expected = t.copyOfBoard();
        expected[1][2] = 2;
        expected[3][3] = 2;
        assertTrue(Arrays.deepEquals(expected, t.copyOfBoard()));
    }

    @Test
    public void undoGameModelTest() {
        GameModel t = new GameModel();
        t.playerMove(Direction.UP);
        int[][] board1 = t.copyOfBoard();
        int score1 = t.getScore();
        int numMoves1 = t.getNumMoves();
        t.playerMove(Direction.DOWN);
        LinkedList<GameModel> queue = t.getGameQueue();
        t.undo();
        GameModel removed = new GameModel(board1);
        removed.setScore(score1);
        removed.setNumMoves(numMoves1);
        assertFalse(queue.contains(removed));
        assertTrue(Arrays.deepEquals(board1, t.copyOfBoard()));
    }

    @Test
    public void multipleMovesGameModelTest() {
        GameModel t = new GameModel(new int[4][4]);
        t.addGridValue(2, 1, 0);
        t.addGridValue(2, 1, 1);
        t.addGridValue(4, 1, 3);
        t.addGridValue(2, 3, 2);

        t.playerMove(Direction.UP);
        int[][] actual = t.copyOfBoard();
        int[][] expected = t.copyOfBoard();
        expected[0][0] = 2;
        expected[0][1] = 2;
        expected[0][2] = 2;
        expected[0][3] = 4;
        assertTrue(Arrays.deepEquals(actual, t.copyOfBoard()));
        assertEquals(t.getNumMoves(), 1);
        assertEquals(t.getScore(), 0);

        t.playerMove(Direction.LEFT);
        actual = t.copyOfBoard();
        expected = t.copyOfBoard();
        expected[0][0] = 4;
        expected[0][1] = 2;
        expected[0][2] = 4;
        assertTrue(Arrays.deepEquals(expected, t.copyOfBoard()));
        assertEquals(t.getNumMoves(), 2);
        assertEquals(t.getScore(), 4);
    }

    @Test
    public void downMergeWithSpaceGameModelTest() {
        GameModel t = new GameModel(new int[4][4]);
        t.addGridValue(4, 1, 0);
        t.addGridValue(4, 3, 0);

        t.playerMove(Direction.DOWN);
        int[][] expected = t.copyOfBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (expected[i][j] != 2) {
                    expected[i][j] = 0;
                }
            }
        }
        expected[3][0] = 8;
        assertTrue(Arrays.deepEquals(expected, t.copyOfBoard()));
        assertEquals(t.getNumMoves(), 1);
        assertEquals(t.getScore(), 8);
    }

    @Test
    public void upMergeWithSpace3rdColGameModelTest() {
        GameModel t = new GameModel(new int[4][4]);
        t.addGridValue(4, 0, 0);
        t.addGridValue(4, 3, 0);
        int[][] expected;
        t.playerMove(Direction.UP);
        expected = t.copyOfBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (expected[i][j] != 2) {
                    expected[i][j] = 0;
                }
            }
        }
        expected[0][0] = 8;
        assertTrue(Arrays.deepEquals(t.copyOfBoard(), expected));
        assertEquals(t.getNumMoves(), 1);
        assertEquals(t.getScore(), 8);
    }

    @Test
    public void leftMergeWithSpace3rdColGameModelTest() {
        GameModel t = new GameModel(new int[4][4]);
        t.addGridValue(4, 0, 0);
        t.addGridValue(4, 0, 3);

        t.playerMove(Direction.LEFT);
        int[][] expected = t.copyOfBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (expected[i][j] != 2) {
                    expected[i][j] = 0;
                }
            }
        }
        expected[0][0] = 8;
        assertTrue(Arrays.deepEquals(expected, t.copyOfBoard()));
        assertEquals(t.getNumMoves(), 1);
        assertEquals(t.getScore(), 8);
    }

    @Test
    public void leftMergeWithSpaceAtEdgeColGameModelTest() {
        GameModel t = new GameModel(new int[4][4]);
        t.addGridValue(4, 0, 0);
        t.addGridValue(2, 0, 1);
        t.addGridValue(2, 0, 3);

        t.playerMove(Direction.LEFT);
        int[][] expected = t.copyOfBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (expected[i][j] != 2) {
                    expected[i][j] = 0;
                }
            }
        }
        expected[0][0] = 4;
        expected[0][1] = 4;
        assertTrue(Arrays.deepEquals(expected, t.copyOfBoard()));
        assertEquals(t.getNumMoves(), 1);
        assertEquals(t.getScore(), 4);
    }

    @Test
    public void rightMergeWithSpace3rdColGameModelTest() {
        GameModel t = new GameModel(new int[4][4]);
        t.addGridValue(4, 0, 3);
        t.addGridValue(4, 0, 1);

        t.playerMove(Direction.RIGHT);
        int[][] expected = t.copyOfBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (expected[i][j] != 2) {
                    expected[i][j] = 0;
                }
            }
        }
        expected[0][3] = 8;
        assertTrue(Arrays.deepEquals(expected, t.copyOfBoard()));
        assertEquals(t.getNumMoves(), 1);
        assertEquals(t.getScore(), 8);
    }

    @Test
    public void movesSingletonUndoGameModelTest() {
        int[][] board = new int[4][4];
        board[0][3] = 2;
        GameModel t = new GameModel(board);
        board = t.copyOfBoard();
        assertEquals(t.getNumMoves(), 0);
        t.playerMove(Direction.DOWN);
        assertEquals(1, t.getNumMoves());
        int[][] board1 = t.copyOfBoard();

        t.playerMove(Direction.LEFT);
        assertEquals(2, t.getNumMoves());

        t.undo();
        assertTrue(Arrays.deepEquals(board1, t.copyOfBoard()));

        t.undo();
        assertTrue(Arrays.deepEquals(board, t.copyOfBoard()));

        assertEquals(0, t.getScore());
        assertEquals(0, t.getNumMoves());

    }

    @Test
    public void saveInFileThatAlreadyExistsGameModelTest() {
        GameModel t = new GameModel(new int[4][4]);
        t.addGridValue(2, 0, 3);
        t.setFileToSave("trial4");
        int moves0 = t.getNumMoves();
        t.playerMove(Direction.DOWN);
        t.playerMove(Direction.LEFT);
        t.undo();
        t.undo();
        assertEquals(moves0, t.getNumMoves());
        assertEquals(0, t.getScore());
    }

    @Test
    public void leftMergeWhenNotNextToEachOtherGameModelTest() {
        GameModel t = new GameModel(new int[4][4]);
        t.addGridValue(2, 0, 3);
        t.addGridValue(2, 0, 1);
        t.addGridValue(4, 0, 0);
        t.playerMove(Direction.LEFT);
        int[][] expected = t.copyOfBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (expected[i][j] != 2) {
                    expected[i][j] = 0;
                }
            }
        }
        expected[0][0] = 4;
        expected[0][1] = 4;
        assertEquals(1, t.getNumMoves());
        assertEquals(4, t.getScore());
        assertTrue(Arrays.deepEquals(expected, t.copyOfBoard()));
    }

    @Test
    public void testEncapsulation() {
        GameModel t = new GameModel();
        t.addGridValue(2, 0, 2);
        t.addGridValue(2, 0, 1);
        assertFalse(t.checkIfWinning());
        assertFalse(t.checkIfLosing());

    }
}
