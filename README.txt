

===================
=: Core Concepts :=
===================


  1. 2D Arrays
  Implements the Game Model by storing the current state of the
  grid. The array stores the value of the tile at the specific index of the grid
  Why:
  A 2D array is mutable and can be updated at a specific index. A 2D is easy to iterate through
  to check if empty or if it contains a specific value. By default all the values in the array
  are 0 making it easy to check if empty or if a value has no been added to the array

  2. Collections
  Implemented through the undo button

  Why:
  A linked list is used to store the history of the 2048 game. A linked list has methods
  that makes it easy to remove the last value in the list (removeLast()).
  A linked list is ordered in the way values are inputted so the order is preserved
  making it idea to store the history of the game

  When the undo button is pressed, the last value in the linked list is removed and
  the model is reverted back to the previous state


  3. File I/O

 Implemented through the saving feature which allows users to save the current game in
 a file. Users can then retrieve the game by inputting the name of the file.
 If the file was previously used to store another game, and the same file is inputted, the
 game will overwrite the file with the current game

 Why:
 A text file makes it easy to store the state of the game. File I/O such as
 the file reader/writer and buffered reader/writer are needed to read and write the contents of the file such as
 the score, number of moves and the contents of each square in the 2048 board.

  4. JUnit testable component

  Implemented through Game Model.

 Why:
  The game 2048 can be played with only game model and without the
  graphics and Java Swing. This ensures a Model-View-Controller design framework is maintained.
  Since all the features of the game are implemented in game model, the game is completely independent of the
  view and controller.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

  Direction: Specifies the different moves/directions possible so an invalid key/move is
  ignored

  GameModel: Creates and manipulates a Game Model Object that contains the current games
  score, the number of moves, the state of the board and the history of moves/boards.
  Contains methods like playerMove(), undo(), checkIfWinning(), checkIfLosing() and playSavedGame()
   to update the Game Model based on the moves the player makes

  GameBoard: Stores the current GameModel object and updates it based on moves made
  by the player. Contains a key listener that takes in the input given by
  the user and then proceeds to update the game based on the input.
  Contains methods reset(), save(), undo(), playSavedGame() and updateStatus() which
  uses the methods in GameModel to update the current game model object, update
  the game's graphics such as the status, number of moves, score. It repaints the board
  based on these changes and prepares the game for the next move.

  RunTwentyFourtyEight: Runs the game and creates the game panel by adding the buttons and creating
  action listeners triggering the appropriate methods in the Game Board class. If the input from
  the user such as the file name is not possible/invalid a warning message is triggered. When a new game
  is started the instructions for the game are shown.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

  Understanding what was happening in the game was hard at first as my test cases
  didn't necessarily show what was going wrong or what the board looked like.
  Therefore, I implemented helper functions such as getCurrentStateOfBoard() to visualize
  what was happening.


- Evaluation: 

  The current state of the board is encapsulated from the user so, they are unable to
  edit the board. I think the current separating of classes and functionality is appropriate
  and if I further seperated functionality It may be difficult to use the methods in multiple
  classes and update the game accordingly.
