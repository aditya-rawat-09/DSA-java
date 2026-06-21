package sorting;

import java.util.Arrays;

public class QuickSort {
    // ─── QUICK SORT ──────────────────────────────────────────────────────────────
    // Pick a pivot, partition array so elements < pivot are left, > pivot are right
    // Recursively sort both partitions
    // TC: O(n log n) average, O(n²) worst (sorted array with bad pivot)
    // SC: O(log n) stack space
    public static void quickSort(int[] arr, int l, int r) {
        if (l >= r) return;
        int pivotIdx = partition(arr, l, r);
        quickSort(arr, l, pivotIdx - 1);
        quickSort(arr, pivotIdx + 1, r);
    }

    private static int partition(int[] arr, int l, int r) {
        int pivot = arr[r]; // choose last element as pivot
        int i = l - 1;
        for (int j = l; j < r; j++) {
            if (arr[j] <= pivot) {
                i++;
                int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
            }
        }
        // place pivot in correct position
        int tmp = arr[i + 1]; arr[i + 1] = arr[r]; arr[r] = tmp;
        return i + 1;
    }
}
