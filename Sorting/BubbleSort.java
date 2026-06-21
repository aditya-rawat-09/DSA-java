package sorting;

import java.util.Arrays;

public class BubbleSort {
    // ─── BUBBLE SORT ─────────────────────────────────────────────────────────────
    // Repeatedly swap adjacent elements if they are in wrong order
    // After each pass, the largest unsorted element bubbles to its correct position
    // TC: O(n²) average/worst, O(n) best (already sorted)
    // SC: O(1)
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j]; arr[j] = arr[j + 1]; arr[j + 1] = tmp;
                    swapped = true;
                }
            }
            if (!swapped) break; // already sorted, early exit
        }
    }
}
