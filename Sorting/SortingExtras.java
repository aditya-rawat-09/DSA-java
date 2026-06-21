package sorting;

import java.util.Arrays;

// Bonus: selection, insertion, counting and cyclic sort
public class SortingExtras {
    // ─── SELECTION SORT ──────────────────────────────────────────────────────────
    // Find the minimum element in unsorted part and place it at the beginning
    // TC: O(n²) always
    // SC: O(1)
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j] < arr[minIdx]) minIdx = j;
            // swap minimum with first unsorted element
            int tmp = arr[minIdx]; arr[minIdx] = arr[i]; arr[i] = tmp;
        }
    }

    // ─── INSERTION SORT ──────────────────────────────────────────────────────────
    // Pick each element and insert it into its correct position in the sorted part
    // Like sorting playing cards in hand
    // TC: O(n²) average/worst, O(n) best
    // SC: O(1)
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            // shift elements greater than key one position ahead
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // ─── COUNTING SORT ───────────────────────────────────────────────────────────
    // Count frequency of each element, then reconstruct sorted array
    // Only works for non-negative integers with known range
    // TC: O(n + k) where k = max value
    // SC: O(k)
    public static void countingSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();
        int[] count = new int[max + 1];
        for (int x : arr) count[x]++;
        int i = 0;
        for (int v = 0; v <= max; v++)
            while (count[v]-- > 0) arr[i++] = v;
    }

    // ─── CYCLIC SORT ─────────────────────────────────────────────────────────────
    // Works when array contains numbers in range [1, n]
    // Place each number at its correct index (num-1)
    // TC: O(n), SC: O(1)
    public static void cyclicSort(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            int correct = arr[i] - 1; // correct index for arr[i]
            if (arr[i] != arr[correct]) {
                int tmp = arr[i]; arr[i] = arr[correct]; arr[correct] = tmp;
            } else {
                i++;
            }
        }
    }

    public static void main(String[] args) {
        int[] a1 = {5, 3, 8, 1, 2};
        BubbleSort.bubbleSort(a1);
        System.out.println("Bubble:    " + Arrays.toString(a1));

        int[] a2 = {5, 3, 8, 1, 2};
        selectionSort(a2);
        System.out.println("Selection: " + Arrays.toString(a2));

        int[] a3 = {5, 3, 8, 1, 2};
        insertionSort(a3);
        System.out.println("Insertion: " + Arrays.toString(a3));

        int[] a4 = {5, 3, 8, 1, 2};
        MergeSort.mergeSort(a4, 0, a4.length - 1);
        System.out.println("Merge:     " + Arrays.toString(a4));

        int[] a5 = {5, 3, 8, 1, 2};
        QuickSort.quickSort(a5, 0, a5.length - 1);
        System.out.println("Quick:     " + Arrays.toString(a5));

        int[] a6 = {4, 2, 2, 8, 3, 3, 1};
        countingSort(a6);
        System.out.println("Counting:  " + Arrays.toString(a6));

        int[] a7 = {3, 1, 5, 4, 2};
        cyclicSort(a7);
        System.out.println("Cyclic:    " + Arrays.toString(a7));
    }
}
