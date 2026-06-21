package pattern;

// Pattern: Dynamic Programming (DP)
// Use this when: problem has overlapping subproblems and optimal substructure
// Idea: solve smaller subproblems first, store their results
//       use stored results to build up the final answer (no repeated work)

public class dynamicprogramming {

    // 1D DP — Climbing Stairs
    // How many ways to reach step n if you can climb 1 or 2 steps at a time?
    // Same as Fibonacci: ways(n) = ways(n-1) + ways(n-2)
    public int climbStairs(int n) {
        if (n <= 2) return n;

        int prev2 = 1; // ways to reach step 1
        int prev1 = 2; // ways to reach step 2

        for (int i = 3; i <= n; i++) {
            int curr = prev1 + prev2; // ways to reach step i
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }

    // 2D DP — Longest Common Subsequence (LCS)
    // Find the length of the longest sequence present in both strings
    // dp[i][j] = LCS of first i chars of a and first j chars of b
    public int lcs(String a, String b) {
        int m = a.length(), n = b.length();
        int[][] dp = new int[m + 1][n + 1]; // extra row/col for empty string base case

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1; // chars match → extend LCS
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // take best of skipping either char
                }
            }
        }

        return dp[m][n];
    }
}
