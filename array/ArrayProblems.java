import java.util.*;

// Important 1D Array Interview Questions
public class ArrayProblems {

    // ─── TWO SUM ────────────────────────────────────────────────────────────────
    // Return indices of two numbers that add up to target
    // TC: O(n), SC: O(n)
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) return new int[]{map.get(complement), i};
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    // ─── KADANE'S ALGORITHM ─────────────────────────────────────────────────────
    // Maximum subarray sum
    // TC: O(n), SC: O(1)
    public static int maxSubarraySum(int[] nums) {
        int maxSum = nums[0], curr = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curr = Math.max(nums[i], curr + nums[i]);
            maxSum = Math.max(maxSum, curr);
        }
        return maxSum;
    }

    // ─── DUTCH NATIONAL FLAG ────────────────────────────────────────────────────
    // Sort array of 0s, 1s, 2s in one pass
    // TC: O(n), SC: O(1)
    public static void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;
        while (mid <= high) {
            if (nums[mid] == 0)      { swap(nums, low++, mid++); }
            else if (nums[mid] == 1) { mid++; }
            else                     { swap(nums, mid, high--); }
        }
    }

    // ─── BEST TIME TO BUY AND SELL STOCK ────────────────────────────────────────
    // Maximum profit from one transaction
    // TC: O(n), SC: O(1)
    public static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE, maxProfit = 0;
        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);
        }
        return maxProfit;
    }

    // ─── MOVE ZEROES ────────────────────────────────────────────────────────────
    // Move all 0s to end while maintaining relative order
    // TC: O(n), SC: O(1)
    public static void moveZeroes(int[] nums) {
        int insertPos = 0;
        for (int num : nums) if (num != 0) nums[insertPos++] = num;
        while (insertPos < nums.length) nums[insertPos++] = 0;
    }

    // ─── FIND DUPLICATE ─────────────────────────────────────────────────────────
    // Find the duplicate number in array [1..n] using Floyd's cycle detection
    // TC: O(n), SC: O(1)
    public static int findDuplicate(int[] nums) {
        int slow = nums[0], fast = nums[0];
        do { slow = nums[slow]; fast = nums[nums[fast]]; } while (slow != fast);
        slow = nums[0];
        while (slow != fast) { slow = nums[slow]; fast = nums[fast]; }
        return slow;
    }

    // ─── MAXIMUM PRODUCT SUBARRAY ────────────────────────────────────────────────
    // TC: O(n), SC: O(1)
    public static int maxProduct(int[] nums) {
        int maxProd = nums[0], minProd = nums[0], result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) { int tmp = maxProd; maxProd = minProd; minProd = tmp; }
            maxProd = Math.max(nums[i], maxProd * nums[i]);
            minProd = Math.min(nums[i], minProd * nums[i]);
            result = Math.max(result, maxProd);
        }
        return result;
    }

    // ─── CONTAINER WITH MOST WATER ──────────────────────────────────────────────
    // TC: O(n), SC: O(1)
    public static int maxWater(int[] height) {
        int left = 0, right = height.length - 1, max = 0;
        while (left < right) {
            max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) left++; else right--;
        }
        return max;
    }

    // ─── TRAPPING RAIN WATER ────────────────────────────────────────────────────
    // TC: O(n), SC: O(1)
    public static int trap(int[] height) {
        int left = 0, right = height.length - 1, water = 0;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            if (height[left] <= height[right]) {
                leftMax = Math.max(leftMax, height[left]);
                water += leftMax - height[left++];
            } else {
                rightMax = Math.max(rightMax, height[right]);
                water += rightMax - height[right--];
            }
        }
        return water;
    }

    // ─── NEXT PERMUTATION ───────────────────────────────────────────────────────
    // Rearrange to next lexicographically greater permutation
    // TC: O(n), SC: O(1)
    public static void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) j--;
            swap(nums, i, j);
        }
        reverse(nums, i + 1, nums.length - 1);
    }

    // ─── MERGE INTERVALS ────────────────────────────────────────────────────────
    // TC: O(n log n), SC: O(n)
    public static int[][] mergeIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> result = new ArrayList<>();
        int[] curr = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= curr[1]) curr[1] = Math.max(curr[1], intervals[i][1]);
            else { result.add(curr); curr = intervals[i]; }
        }
        result.add(curr);
        return result.toArray(new int[0][]);
    }

    // ─── LONGEST CONSECUTIVE SEQUENCE ───────────────────────────────────────────
    // TC: O(n), SC: O(n)
    public static int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) set.add(n);
        int longest = 0;
        for (int n : set) {
            if (!set.contains(n - 1)) {
                int len = 1;
                while (set.contains(n + len)) len++;
                longest = Math.max(longest, len);
            }
        }
        return longest;
    }

    // ─── ROTATE ARRAY ───────────────────────────────────────────────────────────
    // Rotate array right by k steps
    // TC: O(n), SC: O(1)
    public static void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    // ─── SUBARRAY SUM EQUALS K ──────────────────────────────────────────────────
    // Count subarrays with sum equal to k
    // TC: O(n), SC: O(n)
    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0, prefixSum = 0;
        for (int num : nums) {
            prefixSum += num;
            count += map.getOrDefault(prefixSum - k, 0);
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }

    // ─── HELPERS ────────────────────────────────────────────────────────────────
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
    }

    private static void reverse(int[] arr, int i, int j) {
        while (i < j) swap(arr, i++, j--);
    }

    public static void main(String[] args) {
        // Two Sum
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));   // [0, 1]

        // Kadane's
        System.out.println(maxSubarraySum(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})); // 6

        // Dutch Flag
        int[] colors = {2, 0, 2, 1, 1, 0};
        sortColors(colors);
        System.out.println(Arrays.toString(colors));  // [0, 0, 1, 1, 2, 2]

        // Stock profit
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));  // 5

        // Trapping Rain Water
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));  // 6

        // Longest Consecutive
        System.out.println(longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));  // 4

        // Subarray Sum = k
        System.out.println(subarraySum(new int[]{1, 1, 1}, 2));  // 2
    }
}
