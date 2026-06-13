import java.util.*;

public class MapExamples {

    // ─── HASHMAP ──────────────────────────────────────────────────────────────────
    // No guaranteed order. O(1) avg for put/get/remove.
    public static void hashMapDemo() {
        System.out.println("─── HashMap ───");
        Map<String, Integer> map = new HashMap<>();
        map.put("banana", 2);
        map.put("apple",  5);
        map.put("cherry", 1);
        map.put("apple",  9);          // update existing key

        System.out.println(map.get("apple"));          // 9
        System.out.println(map.containsKey("cherry")); // true
        System.out.println(map.containsValue(2));      // true
        System.out.println(map.size());                // 3
        map.remove("banana");
        System.out.println(map);                       // {apple=9, cherry=1} (unordered)

        // iterate
        for (Map.Entry<String, Integer> e : map.entrySet())
            System.out.println(e.getKey() + " -> " + e.getValue());

        map.getOrDefault("mango", 0);                  // 0 (key not found)
        map.putIfAbsent("apple", 100);                 // won't update, key exists
        System.out.println(map.get("apple"));          // 9
    }

    // ─── LINKED HASHMAP ───────────────────────────────────────────────────────────
    // Maintains insertion order. O(1) avg for put/get/remove.
    public static void linkedHashMapDemo() {
        System.out.println("─── LinkedHashMap ───");
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("banana", 2);
        map.put("apple",  5);
        map.put("cherry", 1);

        System.out.println(map); // {banana=2, apple=5, cherry=1}  ← insertion order preserved

        for (String key : map.keySet())
            System.out.print(key + " "); // banana apple cherry
        System.out.println();
    }

    // ─── TREEMAP ──────────────────────────────────────────────────────────────────
    // Sorted by natural key order (or custom Comparator). O(log n) for put/get/remove.
    public static void treeMapDemo() {
        System.out.println("─── TreeMap ───");
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("banana", 2);
        map.put("apple",  5);
        map.put("cherry", 1);
        map.put("date",   4);

        System.out.println(map);              // {apple=5, banana=2, cherry=1, date=4} sorted
        System.out.println(map.firstKey());   // apple
        System.out.println(map.lastKey());    // date
        System.out.println(map.floorKey("bo"));  // banana (largest key <= "bo")
        System.out.println(map.ceilingKey("bo")); // cherry (smallest key >= "bo")
        System.out.println(map.headMap("cherry")); // {apple=5, banana=2}
        System.out.println(map.tailMap("cherry")); // {cherry=1, date=4}
    }

    // ─── MAJORITY ELEMENT ─────────────────────────────────────────────────────────
    // Element appearing more than n/2 times.
    // Use HashMap to count frequencies; return key with count > n/2.
    // TC: O(n), SC: O(n)
    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) freq.put(n, freq.getOrDefault(n, 0) + 1);
        for (Map.Entry<Integer, Integer> e : freq.entrySet())
            if (e.getValue() > nums.length / 2) return e.getKey();
        return -1;
    }

    // ─── VALID ANAGRAM ────────────────────────────────────────────────────────────
    // Two strings are anagrams if they have the same character frequencies.
    // Use HashMap to count chars in s, decrement for t; all values must be 0.
    // TC: O(n), SC: O(1) — at most 26 keys
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) freq.put(c, freq.getOrDefault(c, 0) + 1);
        for (char c : t.toCharArray()) {
            if (!freq.containsKey(c)) return false;
            freq.put(c, freq.get(c) - 1);
            if (freq.get(c) < 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        hashMapDemo();
        System.out.println();

        linkedHashMapDemo();
        System.out.println();

        treeMapDemo();
        System.out.println();

        // Majority Element
        System.out.println("─── Majority Element ───");
        System.out.println(majorityElement(new int[]{3, 2, 3}));             // 3
        System.out.println(majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2})); // 2

        // Valid Anagram
        System.out.println("─── Valid Anagram ───");
        System.out.println(isAnagram("anagram", "nagaram")); // true
        System.out.println(isAnagram("rat", "car"));         // false
        System.out.println(isAnagram("listen", "silent"));   // true
    }
}
