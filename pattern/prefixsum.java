package pattern;

// Pattern: Prefix Sum
// Use this when: you need to answer multiple "sum from index l to r" queries fast
// Idea: pre-compute a running total array
//       then any range sum = prefix[r+1] - prefix[l]  (no loop needed!)
// Build time: O(n) | Query time: O(1)

public class prefixsum {
    int[] prefix;

    // build the prefix sum array from input
    public prefixsum(int[] nums) {
        prefix = new int[nums.length + 1]; // one extra space, prefix[0] = 0

        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i]; // running total
        }
    }

    // returns sum of elements from index l to r (both inclusive)
    public int rangeSum(int l, int r) {
        return prefix[r + 1] - prefix[l]; // subtract to get just that range
    }
}
