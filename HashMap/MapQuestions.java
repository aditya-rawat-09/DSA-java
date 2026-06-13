import java.util.*;

public class MapQuestions {

    // ─── MAJORITY ELEMENT ─────────────────────────────────────────────────────────
    // Element appearing more than n/2 times.
    // Count frequencies using HashMap; return key with count > n/2.
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
    // Count chars of s, decrement for t; all counts must reach 0.
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
        // Majority Element
        System.out.println("─── Majority Element ───");
        System.out.println(majorityElement(new int[]{3, 2, 3}));              // 3
        System.out.println(majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2})); // 2

        // Valid Anagram
        System.out.println("─── Valid Anagram ───");
        System.out.println(isAnagram("anagram", "nagaram")); // true
        System.out.println(isAnagram("rat", "car"));         // false
        System.out.println(isAnagram("listen", "silent"));   // true
    }
}
