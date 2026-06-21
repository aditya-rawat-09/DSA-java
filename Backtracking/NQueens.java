package backtracking;

import java.util.*;

public class NQueens {
    // ─── Q.3 N-QUEENS ────────────────────────────────────────────────────────────
    // Place n queens on n×n board so no two queens attack each other
    // TC: O(n!), SC: O(n²)
    public static void nQueens(int col, boolean[] rows, boolean[] diag1, boolean[] diag2, int n, int[] board) {
        if (col == n) {
            System.out.println(Arrays.toString(board));
            return;
        }
        for (int row = 0; row < n; row++) {
            if (!rows[row] && !diag1[row - col + n - 1] && !diag2[row + col]) {
                board[col] = row;
                rows[row] = diag1[row - col + n - 1] = diag2[row + col] = true;
                nQueens(col + 1, rows, diag1, diag2, n, board);
                rows[row] = diag1[row - col + n - 1] = diag2[row + col] = false;
            }
        }
    }
}
