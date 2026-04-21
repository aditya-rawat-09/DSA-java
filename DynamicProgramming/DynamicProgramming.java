import java.util.Arrays;

public class DynamicProgramming {

    // Fibonacci with memoization
    static int[] memo = new int[100];
    static int fib(int n) {
        if (n <= 1) return n;
        if (memo[n] != 0) return memo[n];
        return memo[n] = fib(n - 1) + fib(n - 2);
    }

    // 0/1 Knapsack
    static int knapsack(int[] wt, int[] val, int W, int n) {
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 1; i <= n; i++)
            for (int w = 0; w <= W; w++)
                dp[i][w] = wt[i-1] <= w
                    ? Math.max(dp[i-1][w], val[i-1] + dp[i-1][w - wt[i-1]])
                    : dp[i-1][w];
        return dp[n][W];
    }

    // Coin Change (min coins)
    static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++)
            for (int coin : coins)
                if (coin <= i) dp[i] = Math.min(dp[i], dp[i - coin] + 1);
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        System.out.println("Fib(10):      " + fib(10));  // 55
        System.out.println("Knapsack:     " + knapsack(new int[]{1,3,4}, new int[]{1,4,5}, 6, 3)); // 9
        System.out.println("CoinChange:   " + coinChange(new int[]{1,2,5}, 11)); // 3
    }
}
