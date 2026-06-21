import java.util.*;

// Rotated / Special Array Problems
public class SpecialArrayProblems {

    // ─── FOUR SUM ───────────────────────────────────────────────────────────────
    // Find all unique quadruplets that sum to target
    // Sort + fix two elements + two pointers
    // TC: O(n³), SC: O(1)
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // skip duplicates
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue; // skip duplicates
                int left = j + 1, right = n - 1;
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) left++;    // skip duplicates
                        while (left < right && nums[right] == nums[right - 1]) right--; // skip duplicates
                        left++; right--;
                    } else if (sum < target) left++;
                    else right--;
                }
            }
        }
        return result;
    }

    // ─── MONOTONIC ARRAY ───────────────────────────────────────────────────────────────
    public static boolean isMonotonic(int[] arr) {
        if (arr == null || arr.length == 0) return true;
        boolean increasing = true, decreasing = true;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) decreasing = false;
            if (arr[i] < arr[i - 1]) increasing = false;
        }
        return increasing || decreasing;
    }

    // pair sum
    public static boolean pairSum(int[] arr, int target) {
        if (arr == null || arr.length == 0) return false;
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) return true;
            else if (sum < target) left++;
            else right--;
        }
        return false;
    }

    // pair sum 2
    public static boolean pairSum2(int[] arr, int target) {
        if (arr == null || arr.length == 0) return false;
        int bp = -1;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) { bp = i; break; }
        }
        int n = arr.length;
        int left = bp + 1, right = bp;
        while (left != right) {
            int sum = arr[left] + arr[right];
            if (sum == target) return true;
            else if (sum < target) left = (left + 1) % n;
            else right = (right - 1 + n) % n;
        }
        return false;
    }

    // beautiful array
    public static int[] beautifulArray(int n) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        while (list.size() < n) {
            List<Integer> temp = new ArrayList<>();
            for (int i : list) if (2 * i - 1 <= n) temp.add(2 * i - 1);
            for (int i : list) if (2 * i <= n) temp.add(2 * i);
            list = temp;
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        // Four Sum
        System.out.println(fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0)); // [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]

        System.out.println(Arrays.toString(beautifulArray(5)));
    }
}
