package sorting;

import java.util.Arrays;

public class MergeSort {
    // ─── MERGE SORT ──────────────────────────────────────────────────────────────
    // Divide array into halves, sort each half, then merge them back
    // TC: O(n log n) always
    // SC: O(n) for temporary arrays
    public static void mergeSort(int[] arr, int l, int r) {
        if (l >= r) return;
        int mid = l + (r - l) / 2;
        mergeSort(arr, l, mid);       // sort left half
        mergeSort(arr, mid + 1, r);   // sort right half
        merge(arr, l, mid, r);        // merge both halves
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] left  = Arrays.copyOfRange(arr, l, mid + 1);
        int[] right = Arrays.copyOfRange(arr, mid + 1, r + 1);
        int i = 0, j = 0, k = l;
        while (i < left.length && j < right.length)
            arr[k++] = (left[i] <= right[j]) ? left[i++] : right[j++];
        while (i < left.length)  arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }
}
