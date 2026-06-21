import java.util.*;

public class Heap {

    // ─── MIN HEAP IMPLEMENTATION ─────────────────────────────────────────────────
    int[] arr;
    int size;

    Heap(int capacity) { arr = new int[capacity]; size = 0; }

    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i)   { return 2 * i + 1; }
    private int right(int i)  { return 2 * i + 2; }
    private void swap(int[] a, int i, int j) { int t = a[i]; a[i] = a[j]; a[j] = t; }

    // ─── INSERT INTO HEAP ─────────────────────────────────────────────────────────
    // Add at end, bubble up (sift up) to restore heap property.
    // TC: O(log n), SC: O(1)
    public void insert(int val) {
        arr[size++] = val;
        int i = size - 1;
        while (i > 0 && arr[parent(i)] > arr[i]) {
            swap(arr, i, parent(i));
            i = parent(i);
        }
    }

    // ─── REMOVE FROM HEAP (remove min) ───────────────────────────────────────────
    // Swap root with last element, reduce size, heapify down from root.
    // TC: O(log n), SC: O(1)
    public int remove() {
        if (size == 0) throw new NoSuchElementException("Heap is empty");
        int min = arr[0];
        arr[0] = arr[--size];
        heapifyDown(arr, size, 0);
        return min;
    }

    // ─── HEAPIFY DOWN (min-heap) ──────────────────────────────────────────────────
    // Sift down node at index i to restore min-heap property.
    // TC: O(log n), SC: O(1)
    private void heapifyDown(int[] a, int n, int i) {
        int smallest = i, l = left(i), r = right(i);
        if (l < n && a[l] < a[smallest]) smallest = l;
        if (r < n && a[r] < a[smallest]) smallest = r;
        if (smallest != i) { swap(a, i, smallest); heapifyDown(a, n, smallest); }
    }

    // ─── BUILD MAX HEAP ───────────────────────────────────────────────────────────
    // Build max-heap in-place from unordered array.
    // TC: O(n), SC: O(1)
    private void buildMaxHeap(int[] a, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) heapifyDownMax(a, n, i);
    }

    private void heapifyDownMax(int[] a, int n, int i) {
        int largest = i, l = 2 * i + 1, r = 2 * i + 2;
        if (l < n && a[l] > a[largest]) largest = l;
        if (r < n && a[r] > a[largest]) largest = r;
        if (largest != i) { swap(a, i, largest); heapifyDownMax(a, n, largest); }
    }

    // ─── HEAP SORT ────────────────────────────────────────────────────────────────
    // Build max-heap, then repeatedly extract max to end of array.
    // TC: O(n log n), SC: O(1)
    public void heapSort(int[] a) {
        int n = a.length;
        buildMaxHeap(a, n);
        for (int i = n - 1; i > 0; i--) {
            swap(a, 0, i);
            heapifyDownMax(a, i, 0);
        }
    }

    public static void main(String[] args) {
        Heap h = new Heap(10);
        for (int v : new int[]{5, 3, 8, 1, 2}) h.insert(v);
        System.out.println(h.remove()); // 1
        System.out.println(h.remove()); // 2

        int[] arr = {4, 10, 3, 5, 1};
        h.heapSort(arr);
        System.out.println(Arrays.toString(arr)); // [1, 3, 4, 5, 10]
    }
}
