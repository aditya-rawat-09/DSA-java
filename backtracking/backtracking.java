import java.util.*;

public class backtracking {

    // ─── Q.1 FIND ALL SUBSETS ────────────────────────────────────────────────────
    // Generate all 2^n subsets of an array
    // TC: O(2^n), SC: O(n)
    public static void findSubsets(int[] arr, int i, List<Integer> current) {
        if (i == arr.length) {
            System.out.println(current);
            return;
        }
        current.add(arr[i]);           // include arr[i]
        findSubsets(arr, i + 1, current);
        current.remove(current.size() - 1); // exclude arr[i]
        findSubsets(arr, i + 1, current);
    }

    // ─── Q.2 PERMUTATIONS ────────────────────────────────────────────────────────
    // Generate all permutations of a string
    // TC: O(n!), SC: O(n)
    public static void permutations(String s, String ans) {
        if (s.isEmpty()) {
            System.out.println(ans);
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            String remaining = s.substring(0, i) + s.substring(i + 1);
            permutations(remaining, ans + c);
        }
    }

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

    // ─── Q.4 GRID WAYS ───────────────────────────────────────────────────────────
    // Count ways to reach bottom-right of m×n grid moving only right or down
    // TC: O(2^(m+n)), SC: O(m+n)
    public static int gridWays(int row, int col, int m, int n) {
        if (row == m - 1 && col == n - 1) return 1; // reached destination
        if (row >= m || col >= n) return 0;          // out of bounds
        return gridWays(row + 1, col, m, n) + gridWays(row, col + 1, m, n);
    }

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

    // ─── Q.6 RAT IN A MAZE ───────────────────────────────────────────────────────
    // Find all paths from (0,0) to (n-1,n-1) in a binary maze (1=open, 0=blocked)
    // TC: O(4^(n²)), SC: O(n²)
    public static void ratInMaze(int[][] maze, int row, int col, String path, boolean[][] visited) {
        int n = maze.length;
        if (row == n - 1 && col == n - 1) {
            System.out.println(path);
            return;
        }
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        char[] dir = {'D', 'U', 'R', 'L'};
        for (int d = 0; d < 4; d++) {
            int nr = row + dr[d], nc = col + dc[d];
            if (nr >= 0 && nr < n && nc >= 0 && nc < n && maze[nr][nc] == 1 && !visited[nr][nc]) {
                visited[row][col] = true;
                ratInMaze(maze, nr, nc, path + dir[d], visited);
                visited[row][col] = false; // backtrack
            }
        }
    }

    // ─── Q.7 KEYPAD COMBINATIONS ─────────────────────────────────────────────────
    // Generate all letter combinations for a phone number string
    // TC: O(4^n), SC: O(n)
    static char[][] keypad = {
        {}, {},
        {'a','b','c'}, {'d','e','f'}, {'g','h','i'}, {'j','k','l'},
        {'m','n','o'}, {'p','q','r','s'}, {'t','u','v'}, {'w','x','y','z'}
    };

    public static void letterCombinations(String digits, int pos, StringBuilder sb) {
        if (pos == digits.length()) {
            System.out.println(sb.toString());
            return;
        }
        for (char c : keypad[Character.getNumericValue(digits.charAt(pos))]) {
            sb.append(c);
            letterCombinations(digits, pos + 1, sb);
            sb.deleteCharAt(sb.length() - 1); // backtrack
        }
    }

    public static void main(String[] args) {
        // Q.1 subsets
        System.out.println("Subsets of [1,2,3]:");
        findSubsets(new int[]{1, 2, 3}, 0, new ArrayList<>());

        // Q.2 permutations
        System.out.println("\nPermutations of ABC:");
        permutations("ABC", "");

        // Q.3 N-Queens (4×4)
        System.out.println("\n4-Queens solutions:");
        nQueens(0, new boolean[4], new boolean[7], new boolean[7], 4, new int[4]);

        // Q.4 grid ways
        System.out.println("\nGrid ways (3x3): " + gridWays(0, 0, 3, 3)); // 6

        // Q.5 sudoku
        char[][] board = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        solveSudoku(board);
        System.out.println("\nSudoku solved row 0: " + Arrays.toString(board[0]));

        // Q.6 rat in maze
        int[][] maze = {{1,0,0,0},{1,1,0,1},{0,1,0,0},{0,1,1,1}};
        System.out.println("\nRat in maze paths:");
        ratInMaze(maze, 0, 0, "", new boolean[4][4]);

        // Q.7 keypad
        System.out.println("\nKeypad combinations for '23':");
        letterCombinations("23", 0, new StringBuilder());
    }
}
