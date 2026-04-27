import java.util.*;

public class Recursion {

    // ─── BASICS ──────────────────────────────────────────────────────────────────

    // Q.1 print 1 to n
    public static void print1ToN(int n) {
        if (n == 0) return;
        print1ToN(n - 1);
        System.out.print(n + " ");
    }

    // Q.2 sum of first n numbers
    // TC: O(n), SC: O(n) stack
    public static int sumOfN(int n) {
        if (n == 0) return 0;
        return n + sumOfN(n - 1);
    }

    // Q.3 fibonacci — return nth fibonacci number
    // TC: O(2^n), SC: O(n)
    public static int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // Q.4 check if array is sorted
    // TC: O(n), SC: O(n)
    public static boolean isSorted(int[] arr, int i) {
        if (i == arr.length - 1) return true;
        if (arr[i] > arr[i + 1]) return false;
        return isSorted(arr, i + 1);
    }

    // Q.5 power — calculate a^b
    // TC: O(log b), SC: O(log b)
    public static long power(long a, int b) {
        if (b == 0) return 1;
        long half = power(a, b / 2);
        if (b % 2 == 0) return half * half;
        return a * half * half;
    }

    // ─── TILING PROBLEM ──────────────────────────────────────────────────────────

    // Q.6 tiling problem — count ways to tile 2×n board with 2×1 tiles
    // TC: O(n), SC: O(n)
    public static int tilingWays(int n) {
        if (n == 0 || n == 1) return 1;
        return tilingWays(n - 1) + tilingWays(n - 2);
    }

    // ─── REMOVE DUPLICATES ───────────────────────────────────────────────────────

    // Q.7 remove duplicate characters from string (keep first occurrence)
    // TC: O(n), SC: O(n)
    public static String removeDuplicates(String s, int i, StringBuilder sb, boolean[] visited) {
        if (i == s.length()) return sb.toString();
        char c = s.charAt(i);
        if (!visited[c - 'a']) {
            visited[c - 'a'] = true;
            sb.append(c);
        }
        return removeDuplicates(s, i + 1, sb, visited);
    }

    // ─── FRIENDS PAIRING ─────────────────────────────────────────────────────────

    // Q.8 friends pairing problem — n friends, each can stay single or pair with another
    // f(n) = f(n-1) + (n-1)*f(n-2)
    // TC: O(n), SC: O(n)
    public static int friendsPairing(int n) {
        if (n <= 2) return n;
        return friendsPairing(n - 1) + (n - 1) * friendsPairing(n - 2);
    }

    // ─── BINARY STRING PROBLEM ───────────────────────────────────────────────────

    // Q.9 count binary strings of length n with no consecutive 1s
    // TC: O(n), SC: O(n)
    public static int countBinaryStrings(int n) {
        if (n == 0) return 1;
        if (n == 1) return 2; // "0" or "1"
        // last char 0: f(n-1) ways, last char 1: must have 0 before it: f(n-2) ways
        return countBinaryStrings(n - 1) + countBinaryStrings(n - 2);
    }

    // ─── DIVIDE AND CONQUER ──────────────────────────────────────────────────────

    // Q.10 tower of hanoi — move n disks from src to dest using helper
    // TC: O(2^n), SC: O(n)
    public static void towerOfHanoi(int n, char src, char dest, char helper) {
        if (n == 0) return;
        towerOfHanoi(n - 1, src, helper, dest);          // move n-1 disks to helper
        System.out.println("Move disk " + n + " from " + src + " to " + dest);
        towerOfHanoi(n - 1, helper, dest, src);          // move n-1 disks from helper to dest
    }

    public static void main(String[] args) {
        // Q.1
        System.out.print("1 to 5: "); print1ToN(5); System.out.println();

        // Q.2
        System.out.println("Sum of 5: " + sumOfN(5));           // 15

        // Q.3
        System.out.println("Fibonacci(6): " + fibonacci(6));    // 8

        // Q.4
        System.out.println("isSorted: " + isSorted(new int[]{1, 2, 3, 4}, 0));  // true
        System.out.println("isSorted: " + isSorted(new int[]{1, 3, 2, 4}, 0));  // false

        // Q.5
        System.out.println("2^10: " + power(2, 10));            // 1024

        // Q.6
        System.out.println("Tiling(4): " + tilingWays(4));      // 5

        // Q.7
        System.out.println("removeDuplicates: " + removeDuplicates("aabbccdd", 0, new StringBuilder(), new boolean[26])); // abcd

        // Q.8
        System.out.println("friendsPairing(4): " + friendsPairing(4)); // 10

        // Q.9
        System.out.println("binaryStrings(3): " + countBinaryStrings(3)); // 5

        // Q.10
        System.out.println("Tower of Hanoi (3 disks):");
        towerOfHanoi(3, 'A', 'C', 'B');
    }
}
