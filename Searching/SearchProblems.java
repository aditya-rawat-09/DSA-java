package searching;

public class SearchProblems {

    // ─── LC 33 SEARCH IN ROTATED SORTED ARRAY ────────────────────────────────────
    // One half is always sorted; check which half and decide where to go.
    // TC: O(log n), SC: O(1)
    public static int searchRotated(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) return mid;
            if (nums[l] <= nums[mid]) {                   // left half sorted
                if (nums[l] <= target && target < nums[mid]) r = mid - 1;
                else l = mid + 1;
            } else {                                       // right half sorted
                if (nums[mid] < target && target <= nums[r]) l = mid + 1;
                else r = mid - 1;
            }
        }
        return -1;
    }

    // ─── LC 34 FIND FIRST AND LAST POSITION ──────────────────────────────────────
    // Two binary searches: one for first occurrence, one for last.
    // TC: O(log n), SC: O(1)
    public static int[] searchRange(int[] nums, int target) {
        return new int[]{firstPos(nums, target), lastPos(nums, target)};
    }

    private static int firstPos(int[] nums, int target) {
        int l = 0, r = nums.length - 1, pos = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) { pos = mid; r = mid - 1; } // keep searching left
            else if (nums[mid] < target) l = mid + 1;
            else r = mid - 1;
        }
        return pos;
    }

    private static int lastPos(int[] nums, int target) {
        int l = 0, r = nums.length - 1, pos = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) { pos = mid; l = mid + 1; } // keep searching right
            else if (nums[mid] < target) l = mid + 1;
            else r = mid - 1;
        }
        return pos;
    }

    // ─── LC 278 FIRST BAD VERSION ────────────────────────────────────────────────
    // Binary search on answer: find smallest version where isBadVersion is true.
    // TC: O(log n), SC: O(1)
    static int BAD_VERSION = 4; // simulated
    private static boolean isBadVersion(int v) { return v >= BAD_VERSION; }

    public static int firstBadVersion(int n) {
        int l = 1, r = n;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (isBadVersion(mid)) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    // ─── LC 875 KOKO EATING BANANAS ──────────────────────────────────────────────
    // Binary search on answer (speed k); check if Koko finishes within h hours.
    // TC: O(n log max), SC: O(1)
    public static int minEatingSpeed(int[] piles, int h) {
        int lo = 1, hi = 0;
        for (int p : piles) hi = Math.max(hi, p);
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (canFinish(piles, mid, h)) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    private static boolean canFinish(int[] piles, int k, int h) {
        int hours = 0;
        for (int p : piles) hours += (p + k - 1) / k;
        return hours <= h;
    }

    // ─── LC 153 FIND MINIMUM IN ROTATED SORTED ARRAY ─────────────────────────────
    // Minimum is at the inflection point where order breaks.
    // TC: O(log n), SC: O(1)
    public static int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > nums[r]) l = mid + 1;
            else r = mid;
        }
        return nums[l];
    }

    public static void main(String[] args) {
        // Search Rotated
        System.out.println(searchRotated(new int[]{4,5,6,7,0,1,2}, 0)); // 4
        System.out.println(searchRotated(new int[]{4,5,6,7,0,1,2}, 3)); // -1

        // Search Range
        int[] arr = {5,7,7,8,8,10};
        java.util.Arrays.stream(searchRange(arr, 8)).forEach(x -> System.out.print(x + " "));
        System.out.println(); // 3 4

        // First Bad Version
        System.out.println(firstBadVersion(5)); // 4

        // Koko
        System.out.println(minEatingSpeed(new int[]{3,6,7,11}, 8)); // 4

        // Find Min
        System.out.println(findMin(new int[]{3,4,5,1,2})); // 1
        System.out.println(findMin(new int[]{4,5,6,7,0,1,2})); // 0
    }
}
