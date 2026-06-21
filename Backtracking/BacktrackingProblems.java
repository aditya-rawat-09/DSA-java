package backtracking;

import java.util.*;

public class BacktrackingProblems {

    // ─── Q.1 FIND ALL SUBSETS ────────────────────────────────────────────────────
    // Generate all 2^n subsets of an array
    // TC: O(2^n), SC: O(n)
    public static void findSubsets(int[] arr, int i, List<Integer> current) {
        if (i == arr.length) {
            System.out.println(current);
            return;
        }
        current.add(arr[i]);
        findSubsets(arr, i + 1, current);
        current.remove(current.size() - 1);
        findSubsets(arr, i + 1, current);
    }

    // ─── Q.2 PERMUTATIONS ────────────────────────────────────────────────────────
    // Generate all permutations of a string
    // TC: O(n!), SC: O(n)
    public static void permutations(String s, String ans) {
        if (s.isEmpty()) { System.out.println(ans); return; }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            String remaining = s.substring(0, i) + s.substring(i + 1);
            permutations(remaining, ans + c);
        }
    }

    // ─── Q.3 ALL SUBSETS (return list) ───────────────────────────────────────────
    // TC: O(2^n), SC: O(n)
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] nums, int start, List<Integer> curr, List<List<Integer>> res) {
        res.add(new ArrayList<>(curr));
        for (int i = start; i < nums.length; i++) {
            curr.add(nums[i]);
            backtrack(nums, i + 1, curr, res);
            curr.remove(curr.size() - 1);
        }
    }

    // ─── Q.4 GRID WAYS ───────────────────────────────────────────────────────────
    // Count ways to reach bottom-right of m×n grid moving only right or down
    // TC: O(2^(m+n)), SC: O(m+n)
    public static int gridWays(int row, int col, int m, int n) {
        if (row == m - 1 && col == n - 1) return 1;
        if (row >= m || col >= n) return 0;
        return gridWays(row + 1, col, m, n) + gridWays(row, col + 1, m, n);
    }

    // ─── Q.5 RAT IN A MAZE ───────────────────────────────────────────────────────
    // Find all paths from (0,0) to (n-1,n-1) in a binary maze (1=open, 0=blocked)
    // TC: O(4^(n²)), SC: O(n²)
    public static void ratInMaze(int[][] maze, int row, int col, String path, boolean[][] visited) {
        int n = maze.length;
        if (row == n - 1 && col == n - 1) { System.out.println(path); return; }
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        char[] dir = {'D', 'U', 'R', 'L'};
        for (int d = 0; d < 4; d++) {
            int nr = row + dr[d], nc = col + dc[d];
            if (nr >= 0 && nr < n && nc >= 0 && nc < n && maze[nr][nc] == 1 && !visited[nr][nc]) {
                visited[row][col] = true;
                ratInMaze(maze, nr, nc, path + dir[d], visited);
                visited[row][col] = false;
            }
        }
    }

    // ─── Q.6 KEYPAD COMBINATIONS ─────────────────────────────────────────────────
    // Generate all letter combinations for a phone number string
    // TC: O(4^n), SC: O(n)
    static char[][] keypad = {
        {}, {},
        {'a','b','c'}, {'d','e','f'}, {'g','h','i'}, {'j','k','l'},
        {'m','n','o'}, {'p','q','r','s'}, {'t','u','v'}, {'w','x','y','z'}
    };

    public static void letterCombinations(String digits, int pos, StringBuilder sb) {
        if (pos == digits.length()) { System.out.println(sb.toString()); return; }
        for (char c : keypad[Character.getNumericValue(digits.charAt(pos))]) {
            sb.append(c);
            letterCombinations(digits, pos + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println("Subsets of [1,2,3]:");
        findSubsets(new int[]{1, 2, 3}, 0, new ArrayList<>());

        System.out.println("\nPermutations of ABC:");
        permutations("ABC", "");

        System.out.println("\n4-Queens solutions:");
        NQueens.nQueens(0, new boolean[4], new boolean[7], new boolean[7], 4, new int[4]);

        System.out.println("\nGrid ways (3x3): " + gridWays(0, 0, 3, 3)); // 6

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
        SudokuSolver.solveSudoku(board);
        System.out.println("\nSudoku solved row 0: " + Arrays.toString(board[0]));

        int[][] maze = {{1,0,0,0},{1,1,0,1},{0,1,0,0},{0,1,1,1}};
        System.out.println("\nRat in maze paths:");
        ratInMaze(maze, 0, 0, "", new boolean[4][4]);

        System.out.println("\nKeypad combinations for '23':");
        letterCombinations("23", 0, new StringBuilder());
    }
}
