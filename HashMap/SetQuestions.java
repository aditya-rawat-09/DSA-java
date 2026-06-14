import java.util.*;

public class SetQuestions {

    // ─── UNION OF TWO ARRAYS ────────────────────────────────────────────────────
    // Add both arrays into a HashSet; duplicates auto-removed.
    // TC: O(m+n), SC: O(m+n)
    public static List<Integer> union(int[] a, int[] b) {
        Set<Integer> set = new LinkedHashSet<>();
        for (int n : a) set.add(n);
        for (int n : b) set.add(n);
        return new ArrayList<>(set);
    }

    // ─── INTERSECTION OF TWO ARRAYS ───────────────────────────────────────────────
    // Add first array to HashSet; iterate second — add to result if present in set.
    // TC: O(m+n), SC: O(m)
    public static List<Integer> intersection(int[] a, int[] b) {
        Set<Integer> setA  = new HashSet<>();
        Set<Integer> seen  = new HashSet<>(); // avoid duplicates in result
        List<Integer> res  = new ArrayList<>();
        for (int n : a) setA.add(n);
        for (int n : b) {
            if (setA.contains(n) && seen.add(n)) res.add(n);
        }
        return res;
    }

    // ─── COUNT DISTINCT ELEMENTS ─────────────────────────────────────────────────
    // Add all elements to a HashSet; size = count of distinct elements.
    // TC: O(n), SC: O(n)
    public static int countDistinct(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int n : arr) set.add(n);
        return set.size();
    }

    // ─── COUNT DISTINCT IN EACH WINDOW OF SIZE K ──────────────────────────────────
    // Sliding window with a frequency map; distinct count = map size.
    // When element leaves window, decrement freq; remove if freq hits 0.
    // TC: O(n), SC: O(k)
    public static int[] countDistinctInWindows(int[] arr, int k) {
        int n = arr.length;
        int[] res = new int[n - k + 1];
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            freq.put(arr[i], freq.getOrDefault(arr[i], 0) + 1);
            if (i >= k) {
                int out = arr[i - k];
                freq.put(out, freq.get(out) - 1);
                if (freq.get(out) == 0) freq.remove(out);
            }
            if (i >= k - 1) res[i - k + 1] = freq.size();
        }
        return res;
    }

    public static void main(String[] args) {
        // Union
        System.out.println("─── Union ───");
        System.out.println(union(
            new int[]{1, 2, 3, 4},
            new int[]{3, 4, 5, 6}
        )); // [1, 2, 3, 4, 5, 6]

        // Intersection
        System.out.println("─── Intersection ───");
        System.out.println(intersection(
            new int[]{1, 2, 3, 4},
            new int[]{3, 4, 5, 6}
        )); // [3, 4]

        // Count Distinct
        System.out.println("─── Count Distinct ───");
        System.out.println(countDistinct(new int[]{1, 2, 3, 2, 1, 4})); // 4

        // Count Distinct in Each Window
        System.out.println("─── Count Distinct in Windows ───");
        System.out.println(Arrays.toString(
            countDistinctInWindows(new int[]{1, 2, 1, 3, 4, 2, 3}, 4)
        )); // [3, 4, 4, 3]
    }
}
