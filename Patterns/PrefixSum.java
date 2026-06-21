package patterns;

public class PrefixSum {
    int[] prefix;

    // build the prefix sum array from input
    public PrefixSum(int[] nums) {
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
