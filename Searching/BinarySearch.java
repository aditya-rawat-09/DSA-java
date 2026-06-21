package searching;

public class BinarySearch {
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
