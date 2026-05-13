import java.util.*;

public class StringBasics {

    // Q.1 check palindrome
    // TC: O(n), SC: O(1)
    public static boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++; r--;
        }
        return true;
    }

    // Q.2 capitalize first letter of each word
    // TC: O(n), SC: O(n)
    public static String capitalizeWords(String s) {
        StringBuilder sb = new StringBuilder();
        for (String word : s.trim().split("\\s+")) {
            sb.append(Character.toUpperCase(word.charAt(0)))
              .append(word.substring(1).toLowerCase())
              .append(" ");
        }
        return sb.toString().trim();
    }

    // Q.3 string compression
    // "aaabbc" -> "a3b2c1"
    // TC: O(n), SC: O(n)
    public static String compress(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            int count = 0;
            while (i < s.length() && s.charAt(i) == c) { i++; count++; }
            sb.append(c).append(count);
        }
        return sb.length() < s.length() ? sb.toString() : s;
    }

    // Q.4 first non-repeating character in a string
    // use LinkedHashMap to preserve insertion order
    // TC: O(n), SC: O(1) — at most 26 keys
    public static char firstNonRepeating(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (char c : s.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);
        for (Map.Entry<Character, Integer> e : map.entrySet())
            if (e.getValue() == 1) return e.getKey();
        return '\0'; // no non-repeating char found
    }

    public static void main(String[] args) {
        // Palindrome
        System.out.println(isPalindrome("racecar"));   // true
        System.out.println(isPalindrome("hello"));     // false

        // Capitalize
        System.out.println(capitalizeWords("hello world java")); // Hello World Java

        // Compression
        System.out.println(compress("aaabbc"));  // a3b2c1
        System.out.println(compress("abc"));     // abc (no compression since not shorter)

        // First non-repeating
        char r1 = firstNonRepeating("aabbcde");
        char r2 = firstNonRepeating("aabb");
        System.out.println("First non-repeating (aabbcde): " + (r1 == '\0' ? "none" : r1)); // c
        System.out.println("First non-repeating (aabb): "    + (r2 == '\0' ? "none" : r2)); // none
    }
}
