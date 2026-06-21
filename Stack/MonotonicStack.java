package stack;

import java.util.*;

public class MonotonicStack {
    // for each element, find the next greater element to its right
    // if none exists, result stays -1
    public int[] nextGreater(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1); // default: no greater element found

        Deque<Integer> stack = new ArrayDeque<>(); // stores indices (not values)

        for (int i = 0; i < n; i++) {
            // current element is greater than element at stack top
            // so current is the "next greater" for that index
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                res[stack.pop()] = nums[i]; // found the answer for that index
            }

            stack.push(i); // push current index, waiting for its next greater
        }

        return res;
    }
}
