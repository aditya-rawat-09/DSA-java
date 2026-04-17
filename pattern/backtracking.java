package pattern;

import java.util.*;

// Pattern: Backtracking
// Use this when: you need to explore all possible combinations / subsets / permutations
// Idea: at each step, make a choice → go deeper → undo the choice (backtrack)
//       this way you explore every possibility without missing any
// Time: O(2^n) for subsets

public class backtracking {
    // returns all possible subsets of the given array
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] nums, int start, List<Integer> curr, List<List<Integer>> res) {
        res.add(new ArrayList<>(curr)); // save current subset (even empty one)

        for (int i = start; i < nums.length; i++) {
            curr.add(nums[i]);              // make a choice: include nums[i]
            backtrack(nums, i + 1, curr, res); // explore further with this choice
            curr.remove(curr.size() - 1);   // undo the choice (backtrack)
        }
    }
}
