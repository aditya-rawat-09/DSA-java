package sorting;

import java.util.Arrays;

public class HeapSort {
    // ─── HEAP SORT ────────────────────────────────────────────────────────────────
    // Build max-heap in O(n), then repeatedly extract max to end of array.
    // TC: O(n log n), SC: O(1)
    public static void heapSort(int[] arr) {
        int n = arr.length;
        // build max-heap (heapify all non-leaf nodes from bottom up)
        for (int i = n / 2 - 1; i >= 0; i--) heapifyDown(arr, n, i);
        // extract max one by one: swap root with last, shrink heap
        for (int i = n - 1; i > 0; i--) {
            int tmp = arr[0]; arr[0] = arr[i]; arr[i] = tmp;
            heapifyDown(arr, i, 0);
        }
    }

    // ─── HEAPIFY DOWN (max-heap) ──────────────────────────────────────────────────
    // Ensure subtree rooted at i satisfies max-heap property within size n.
    // TC: O(log n), SC: O(1)
    private static void heapifyDown(int[] arr, int n, int i) {
        int largest = i, l = 2 * i + 1, r = 2 * i + 2;
        if (l < n && arr[l] > arr[largest]) largest = l;
        if (r < n && arr[r] > arr[largest]) largest = r;
        if (largest != i) {
            int tmp = arr[i]; arr[i] = arr[largest]; arr[largest] = tmp;
            heapifyDown(arr, n, largest);
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 10, 3, 5, 1};
        heapSort(arr);
        System.out.println(Arrays.toString(arr)); // [1, 3, 4, 5, 10]
    }
}
