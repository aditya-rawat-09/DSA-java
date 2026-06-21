import java.util.*;
import java.util.logging.Logger;

// Important 2D Array Interview Questions
public class Array2D {

    private static final Logger logger = Logger.getLogger(Array2D.class.getName());

    // ─── DIAGONAL SUM ────────────────────────────────────────────────────────────
    // TC: O(n), SC: O(1)
    public static int diagonalSum(int[][] matrix) {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[i][i];
            if (i != matrix.length - 1 - i)
                sum += matrix[i][matrix.length - 1 - i];
        }
        return sum;
    }

    // ─── SPIRAL ORDER ────────────────────────────────────────────────────────────
    // TC: O(m*n), SC: O(1)
    public static void printSpiral(int[][] matrix) {
        int startRow = 0, startCol = 0;
        int endRow = matrix.length - 1, endCol = matrix[0].length - 1;
        StringBuilder sb = new StringBuilder();
        while (startRow <= endRow && startCol <= endCol) {
            for (int j = startCol; j <= endCol; j++) sb.append(matrix[startRow][j]).append(" ");
            for (int i = startRow + 1; i <= endRow; i++) sb.append(matrix[i][endCol]).append(" ");
            for (int j = endCol - 1; j >= startCol; j--) { if (startRow == endRow) break; sb.append(matrix[endRow][j]).append(" "); }
            for (int i = endRow - 1; i >= startRow + 1; i--) { if (startCol == endCol) break; sb.append(matrix[i][startCol]).append(" "); }
            startRow++; startCol++; endRow--; endCol--;
        }
        logger.info(sb.toString().trim());
    }

    // ─── ROTATE IMAGE 90° CLOCKWISE ──────────────────────────────────────────────
    // Transpose then reverse each row — in-place
    // TC: O(n²), SC: O(1)
    public static void rotate90(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++) { int tmp = matrix[i][j]; matrix[i][j] = matrix[j][i]; matrix[j][i] = tmp; }
        for (int[] row : matrix) {
            int l = 0, r = n - 1;
            while (l < r) { int tmp = row[l]; row[l++] = row[r]; row[r--] = tmp; }
        }
    }

    // ─── SET MATRIX ZEROES ───────────────────────────────────────────────────────
    // If element is 0, set its entire row and column to 0 — in-place
    // TC: O(m*n), SC: O(1)
    public static void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean firstRowZero = false, firstColZero = false;
        for (int j = 0; j < n; j++) if (matrix[0][j] == 0) firstRowZero = true;
        for (int i = 0; i < m; i++) if (matrix[i][0] == 0) firstColZero = true;
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                if (matrix[i][j] == 0) { matrix[i][0] = 0; matrix[0][j] = 0; }
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                if (matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;
        if (firstRowZero) Arrays.fill(matrix[0], 0);
        if (firstColZero) for (int i = 0; i < m; i++) matrix[i][0] = 0;
    }

    // ─── TRANSPOSE OF MATRIX ─────────────────────────────────────────────────────
    // Flip matrix along its diagonal — in-place
    // TC: O(n²), SC: O(1)
    public static void transpose(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++) { int tmp = matrix[i][j]; matrix[i][j] = matrix[j][i]; matrix[j][i] = tmp; }
    }

    // ─── SEARCH IN ROW-COL SORTED MATRIX ────────────────────────────────────────
    // Each row and column is sorted — start from top-right corner
    // TC: O(m+n), SC: O(1)
    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = 0, col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] > target) col--;
            else row++;
        }
        return false;
    }

    // ─── WORD SEARCH ─────────────────────────────────────────────────────────────
    // Check if word exists in grid using DFS
    // TC: O(m*n*4^L), SC: O(L) where L = word length
    public static boolean wordSearch(char[][] board, String word) {
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                if (dfs(board, word, i, j, 0)) return true;
        return false;
    }

    private static boolean dfs(char[][] board, String word, int i, int j, int k) {
        if (k == word.length()) return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(k)) return false;
        char tmp = board[i][j]; board[i][j] = '#';
        boolean found = dfs(board, word, i+1, j, k+1) || dfs(board, word, i-1, j, k+1)
                     || dfs(board, word, i, j+1, k+1) || dfs(board, word, i, j-1, k+1);
        board[i][j] = tmp;
        return found;
    }

    // ─── NUMBER OF ISLANDS ───────────────────────────────────────────────────────
    // Count connected components of '1's using DFS
    // TC: O(m*n), SC: O(m*n)
    public static int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if (grid[i][j] == '1') { sink(grid, i, j); count++; }
        return count;
    }

    private static void sink(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') return;
        grid[i][j] = '0';
        sink(grid, i+1, j); sink(grid, i-1, j); sink(grid, i, j+1); sink(grid, i, j-1);
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};

        // Diagonal sum
        logger.info("Diagonal sum: " + diagonalSum(matrix));  // 25

        // Spiral
        printSpiral(matrix);  // 1 2 3 6 9 8 7 4 5

        // Rotate 90
        int[][] m2 = {{1,2,3},{4,5,6},{7,8,9}};
        rotate90(m2);
        logger.info("Rotated: " + Arrays.deepToString(m2));  // [[7,4,1],[8,5,2],[9,6,3]]

        // Transpose
        int[][] m3 = {{1,2,3},{4,5,6},{7,8,9}};
        transpose(m3);
        logger.info("Transposed: " + Arrays.deepToString(m3));  // [[1,4,7],[2,5,8],[3,6,9]]

        // Search
        logger.info("Found 5: " + searchMatrix(matrix, 5));  // true

        // Number of islands
        char[][] grid = {{'1','1','0'},{'0','1','0'},{'0','0','1'}};
        logger.info("Islands: " + numIslands(grid));  // 2
    }
}
