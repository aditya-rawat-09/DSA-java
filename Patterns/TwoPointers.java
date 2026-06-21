package patterns;

public class TwoPointers {
    // returns true if any two numbers in sorted array add up to target
    public boolean solve(int[] arr, int target) {
        int l = 0, r = arr.length - 1;

        while (l < r) {
            int sum = arr[l] + arr[r];

            if (sum == target) return true;  // found the pair
            else if (sum < target) l++;      // sum too small, move left pointer right
            else r--;                        // sum too big, move right pointer left
        }

        return false; // no pair found
    }
}
