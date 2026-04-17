package pattern;

// Pattern: Binary Search
// Use this when: array is sorted and you need to find a target
// Idea: instead of checking every element, cut the search space in half each time
// Time: O(log n)

public class binarysearch {
    public int search(int[] arr, int target) {
        int l = 0, r = arr.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2; // find middle index (avoids overflow)

            if (arr[mid] == target) return mid;       // found it
            else if (arr[mid] < target) l = mid + 1; // target is in right half
            else r = mid - 1;                         // target is in left half
        }

        return -1; // target not found
    }
}
