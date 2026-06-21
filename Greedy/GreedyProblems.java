package greedy;

import java.util.*;

public class GreedyProblems {

    // ─── ACTIVITY SELECTION ───────────────────────────────────────────────────────
    // Select max number of non-overlapping activities.
    // Sort by end time; greedily pick each activity that starts after the last picked.
    // TC: O(n log n), SC: O(1)
    public static int activitySelection(int[] start, int[] end) {
        int n = start.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> end[a] - end[b]); // sort by end time

        int count = 1, lastEnd = end[idx[0]];
        for (int i = 1; i < n; i++) {
            if (start[idx[i]] >= lastEnd) { count++; lastEnd = end[idx[i]]; }
        }
        return count;
    }

    // ─── JOB SEQUENCING ───────────────────────────────────────────────────────────
    // Maximize profit: each job has a deadline and profit, takes 1 unit of time.
    // Sort by profit desc; assign each job to latest available slot before its deadline.
    // TC: O(n²), SC: O(n)
    public static int jobSequencing(int[] deadlines, int[] profits) {
        int n = deadlines.length;
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> profits[b] - profits[a]);

        int maxDeadline = 0;
        for (int d : deadlines) maxDeadline = Math.max(maxDeadline, d);

        boolean[] slots = new boolean[maxDeadline + 1];
        int totalProfit = 0;
        for (int i : order) {
            for (int j = deadlines[i]; j >= 1; j--) {
                if (!slots[j]) { slots[j] = true; totalProfit += profits[i]; break; }
            }
        }
        return totalProfit;
    }

    // ─── ICE CREAM BARS (LC 1833) ────────────────────────────────────────────────
    // Buy as many ice creams as possible with given coins.
    // Sort by cost; greedily buy cheapest first.
    // TC: O(n log n), SC: O(1)
    public static int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int count = 0;
        for (int cost : costs) {
            if (coins < cost) break;
            coins -= cost;
            count++;
        }
        return count;
    }

    // ─── ASSIGN COOKIES (LC 455) ─────────────────────────────────────────────────
    // Greedily assign smallest sufficient cookie to greediest satisfiable child.
    // TC: O(n log n + m log m), SC: O(1)
    public static int assignCookies(int[] greed, int[] size) {
        Arrays.sort(greed); Arrays.sort(size);
        int child = 0, cookie = 0;
        while (child < greed.length && cookie < size.length) {
            if (size[cookie] >= greed[child]) child++;
            cookie++;
        }
        return child;
    }

    // ─── MINIMUM PLATFORMS (trains) ──────────────────────────────────────────────
    // Find minimum number of platforms needed at a station.
    // Sort arrivals and departures; use two-pointer overlap counting.
    // TC: O(n log n), SC: O(1)
    public static int minPlatforms(int[] arr, int[] dep) {
        Arrays.sort(arr); Arrays.sort(dep);
        int platforms = 1, maxPlatforms = 1, i = 1, j = 0;
        while (i < arr.length && j < dep.length) {
            if (arr[i] <= dep[j]) { platforms++; i++; }
            else { platforms--; j++; }
            maxPlatforms = Math.max(maxPlatforms, platforms);
        }
        return maxPlatforms;
    }

    public static void main(String[] args) {
        // Activity Selection
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] end   = {2, 4, 6, 7, 9, 9};
        System.out.println("Max activities: " + activitySelection(start, end)); // 4

        // Job Sequencing
        int[] deadlines = {2, 1, 2, 1, 3};
        int[] profits   = {100, 19, 27, 25, 15};
        System.out.println("Max profit: " + jobSequencing(deadlines, profits)); // 142

        // Ice Cream Bars
        System.out.println("Ice creams: " + maxIceCream(new int[]{1, 3, 2, 4, 1}, 7)); // 4

        // Assign Cookies
        System.out.println("Content children: " + assignCookies(new int[]{1, 2, 3}, new int[]{1, 1})); // 1

        // Minimum Platforms
        int[] arr2 = {900, 940, 950, 1100, 1500, 1800};
        int[] dep2 = {910, 1200, 1120, 1130, 1900, 2000};
        System.out.println("Min platforms: " + minPlatforms(arr2, dep2)); // 3
    }
}
