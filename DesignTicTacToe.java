/**
 * Design a Tic-tac-toe game that is played between two players on anxngrid.
You may assume the following rules:
A move is guaranteed to be valid and is placed on an empty block.
Once a winning condition is reached, no more moves is allowed.
A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
Example:
Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.

TicTacToe toe = new TicTacToe(3);

toe.move(0, 0, 1); -> Returns 0 (no one wins)
|X| | |
| | | |    // Player 1 makes a move at (0, 0).
| | | |

toe.move(0, 2, 2); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 2 makes a move at (0, 2).
| | | |

toe.move(2, 2, 1); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 1 makes a move at (2, 2).
| | |X|

toe.move(1, 1, 2); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 2 makes a move at (1, 1).
| | |X|

toe.move(2, 0, 1); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 1 makes a move at (2, 0).
|X| |X|

toe.move(1, 0, 2); -> Returns 0 (no one wins)
|X| |O|
|O|O| |    // Player 2 makes a move at (1, 0).
|X| |X|

toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
|X| |O|
|O|O| |    // Player 1 makes a move at (2, 1).
|X|X|X|
Follow up:
Could you do better than O(n^2) permove()operation?
Could you get O(1) per move() operation?
 */
public class DesignTicTacToe {
    private char[][] board;
    private static char X = 'X';
    private static char O = 'O';
    private int size;

    /** Initialize your data structure here. */
    public DesignTicTacToe(int n) {
        board = new char[n][n];
        size = n;
    }

    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        char c;
        if (player == 1) {
            c = X;
        } else {
            c = O;
        }
        if (board[row][col] != 0) {
            // throw error, occupied
            return 0;
        }
        board[row][col] = c;

        if (hasWon(row, col, size, c)) {
            return player;
        }
        return 0;
    }

    private boolean hasWon(int row, int col, int n, char c) {

        // check horizontal
        boolean rowLine = true;
        for (int i = 0; i < n; i++) {
            rowLine = rowLine && (board[i][col] == c);
        }
        // check vertical
        boolean colLine = true;
        for (int j = 0; j < n; j++) {
            colLine = colLine && (board[row][j] == c);
        }
        // check diagonal
        if (row + col == n - 1 || row == col) {
            boolean diagLine1 = true;
            boolean diagLine2 = true;
            for (int j = 0; j < n; j++) {
                diagLine1 = diagLine1 && (board[j][j] == c);
            }
            for (int j = 0; j < n; j++) {
                diagLine2 = diagLine2 && (board[n - 1 - j][j] == c);
            }
            return rowLine || colLine || diagLine1 || diagLine2;
        } else {
            return rowLine || colLine;
        }
    }
}
