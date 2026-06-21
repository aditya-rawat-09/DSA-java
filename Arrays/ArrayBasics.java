package arrays;

import java.util.Arrays;

public class ArrayBasics {

    // ─── TRAVERSE ────────────────────────────────────────────────────────────────
    // TC: O(n), SC: O(1)
    public static void traverse(int[] arr) {
        for (int x : arr) System.out.print(x + " ");
        System.out.println();
    }

    // ─── INSERT AT INDEX ─────────────────────────────────────────────────────────
    // Shift elements right from end to index, then place value.
    // TC: O(n), SC: O(1) — arr must have space at end
    public static void insertAt(int[] arr, int size, int index, int val) {
        for (int i = size - 1; i >= index; i--) arr[i + 1] = arr[i];
        arr[index] = val;
    }

    // ─── DELETE AT INDEX ─────────────────────────────────────────────────────────
    // Shift elements left from index+1 onwards.
    // TC: O(n), SC: O(1)
    public static int deleteAt(int[] arr, int size, int index) {
        for (int i = index; i < size - 1; i++) arr[i] = arr[i + 1];
        return size - 1;
    }

    // ─── REVERSE ─────────────────────────────────────────────────────────────────
    // TC: O(n), SC: O(1)
    public static void reverse(int[] arr) {
        int l = 0, r = arr.length - 1;
        while (l < r) { int t = arr[l]; arr[l++] = arr[r]; arr[r--] = t; }
    }

    // ─── ROTATE LEFT BY K ────────────────────────────────────────────────────────
    // Reverse first k, reverse rest, reverse all.
    // TC: O(n), SC: O(1)
    public static void rotateLeft(int[] arr, int k) {
        k %= arr.length;
        reverseRange(arr, 0, k - 1);
        reverseRange(arr, k, arr.length - 1);
        reverseRange(arr, 0, arr.length - 1);
    }

    private static void reverseRange(int[] arr, int l, int r) {
        while (l < r) { int t = arr[l]; arr[l++] = arr[r]; arr[r--] = t; }
    }

    // ─── MAX AND MIN ─────────────────────────────────────────────────────────────
    // TC: O(n), SC: O(1)
    public static int max(int[] arr) {
        int m = arr[0];
        for (int x : arr) if (x > m) m = x;
        return m;
    }

    public static int min(int[] arr) {
        int m = arr[0];
        for (int x : arr) if (x < m) m = x;
        return m;
    }

    // ─── LINEAR SEARCH ───────────────────────────────────────────────────────────
    // TC: O(n), SC: O(1)
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) if (arr[i] == target) return i;
        return -1;
    }

    // ─── CHECK SORTED ────────────────────────────────────────────────────────────
    // TC: O(n), SC: O(1)
    public static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) if (arr[i] < arr[i - 1]) return false;
        return true;
    }

    // ─── SECOND LARGEST ──────────────────────────────────────────────────────────
    // TC: O(n), SC: O(1)
    public static int secondLargest(int[] arr) {
        int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
        for (int x : arr) {
            if (x > first) { second = first; first = x; }
            else if (x > second && x != first) second = x;
        }
        return second;
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6};

        System.out.print("Original: "); traverse(arr);

        System.out.println("Max: " + max(arr));           // 9
        System.out.println("Min: " + min(arr));           // 1
        System.out.println("Sorted: " + isSorted(arr));   // false
        System.out.println("Search 5: " + linearSearch(arr, 5)); // 4
        System.out.println("Second largest: " + secondLargest(arr)); // 6

        reverse(arr);
        System.out.print("Reversed: "); traverse(arr);    // 6 2 9 5 1 4 1 3

        int[] arr2 = {1, 2, 3, 4, 5};
        rotateLeft(arr2, 2);
        System.out.print("Rotate left 2: "); traverse(arr2); // 3 4 5 1 2

        // insert / delete on a fixed-size array
        int[] arr3 = new int[6];
        arr3[0]=10; arr3[1]=20; arr3[2]=30; arr3[3]=40;
        insertAt(arr3, 4, 2, 25);
        System.out.print("After insert at 2: ");
        for (int i = 0; i < 5; i++) System.out.print(arr3[i] + " ");
        System.out.println(); // 10 20 25 30 40

        int size = deleteAt(arr3, 5, 2);
        System.out.print("After delete at 2: ");
        for (int i = 0; i < size; i++) System.out.print(arr3[i] + " ");
        System.out.println(); // 10 20 30 40
    }
}
