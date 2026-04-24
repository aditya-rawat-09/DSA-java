import java.util.*;
import java.util.logging.Logger;

// Rotated / Special Array Problems
public class ArrayList {

    private static final Logger logger = Logger.getLogger(SpecialArrayProblems.class.getName());

    // monotonic array
    public static boolean isMonotonic(int[] arr) {
        if (arr == null || arr.length == 0) return true;
        boolean increasing = true, decreasing = true;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1])
                decreasing = false;
            if (arr[i] < arr[i - 1])
                increasing = false;
        }
        return increasing || decreasing;
    }

    // pair sum
    public static boolean pairSum(int[] arr, int target) {
        if (arr == null || arr.length == 0) return false;
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target)
                return true;
            else if (sum < target)
                left++;
            else
                right--;
        }
        return false;
    }

    // pair sum 2
    public static boolean pairSum2(int[] arr, int target) {
        if (arr == null || arr.length == 0) return false;
        int bp = -1;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                bp = i;
                break;
            }
        }
        int n = arr.length;

        int left = bp + 1, right = bp;
        while (left != right) {
            int sum = arr[left] + arr[right];
            if (sum == target)
                return true;
            else if (sum < target) {
                left = (left + 1) % n;
            } else
                right = (right - 1 + n) % n;
        }
        return false;
    }


    //beautiful array
    public static int[] beautifulArray(int n) {
        List<Integer> list = new java.util.ArrayList<>();
        list.add(1);
        while (list.size() < n) {
            List<Integer> temp = new java.util.ArrayList<>();
            for (int i : list) {
                if (2 * i - 1 <= n)
                    temp.add(2 * i - 1);
            }
            for (int i : list) {
                if (2 * i <= n)
                    temp.add(2 * i);
            }
            list = temp;
        }
        return list.stream().mapToInt(i -> i).toArray();
    }


    public static void main(String[] args) {
        //beautiful array
        int n = 5;
        int[] result = beautifulArray(n);
        logger.info(Arrays.toString(result));
    }
}
