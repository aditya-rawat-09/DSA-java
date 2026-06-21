package backtracking;

import java.util.*;

public class SudokuSolver {
    // ─── Q.5 SUDOKU SOLVER ───────────────────────────────────────────────────────
    // Fill 9×9 sudoku board using backtracking
    // TC: O(9^(empty cells)), SC: O(1)
    public static boolean solveSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValidSudoku(board, i, j, c)) {
                            board[i][j] = c;
                            if (solveSudoku(board)) return true;
                            board[i][j] = '.'; // backtrack
                        }
                    }
                    return false; // no valid number found
                }
            }
        }
        return true; // all cells filled
    }

    private static boolean isValidSudoku(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == c) return false;                          // check row
            if (board[i][col] == c) return false;                          // check col
            if (board[3*(row/3) + i/3][3*(col/3) + i%3] == c) return false; // check 3×3 box
        }
        return true;
    }
}
