package arrays;

import java.util.*;

public class TwoSum {
    // returns indices of two numbers that add up to target
    public int[] solve(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(); // stores number → index

        for (int i = 0; i < nums.length; i++) {
            int rem = target - nums[i]; // what number do we need?

            if (map.containsKey(rem)) {
                return new int[]{map.get(rem), i}; // found the pair
            }

            map.put(nums[i], i); // save current number for future lookups
        }

        return new int[]{-1, -1}; // no pair found
    }
}
