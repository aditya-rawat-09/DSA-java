import java.util.Arrays;

public class Sorting {

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
        bubbleSort(a1);
        System.out.println("Bubble:    " + Arrays.toString(a1));

        int[] a2 = {5, 3, 8, 1, 2};
        selectionSort(a2);
        System.out.println("Selection: " + Arrays.toString(a2));

        int[] a3 = {5, 3, 8, 1, 2};
        insertionSort(a3);
        System.out.println("Insertion: " + Arrays.toString(a3));

        int[] a4 = {5, 3, 8, 1, 2};
        mergeSort(a4, 0, a4.length - 1);
        System.out.println("Merge:     " + Arrays.toString(a4));

        int[] a5 = {5, 3, 8, 1, 2};
        quickSort(a5, 0, a5.length - 1);
        System.out.println("Quick:     " + Arrays.toString(a5));

        int[] a6 = {4, 2, 2, 8, 3, 3, 1};
        countingSort(a6);
        System.out.println("Counting:  " + Arrays.toString(a6));

        int[] a7 = {3, 1, 5, 4, 2};
        cyclicSort(a7);
        System.out.println("Cyclic:    " + Arrays.toString(a7));
    }
}
