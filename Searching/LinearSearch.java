package searching;

public class LinearSearch {

    // ─── LINEAR SEARCH ───────────────────────────────────────────────────────────
    // Scan every element until target is found.
    // TC: O(n), SC: O(1)
    public static int search(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == target) return i;
        return -1;
    }

    // ─── SENTINEL LINEAR SEARCH ──────────────────────────────────────────────────
    // Place target at end (sentinel) to avoid bounds check in the loop.
    // TC: O(n), SC: O(1)
    public static int sentinelSearch(int[] arr, int target) {
        int n = arr.length;
        int last = arr[n - 1];
        arr[n - 1] = target;       // set sentinel
        int i = 0;
        while (arr[i] != target) i++;
        arr[n - 1] = last;         // restore
        if (i < n - 1 || last == target) return i;
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 7, 1, 9, 3};

        System.out.println(search(arr, 7));          // 2
        System.out.println(search(arr, 5));          // -1

        System.out.println(sentinelSearch(arr, 9));  // 4
        System.out.println(sentinelSearch(arr, 5));  // -1
    }
}
