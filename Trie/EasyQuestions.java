import java.util.*;

public class EasyQuestions {

    // ─── TRIE NODE ────────────────────────────────────────────────────────────────
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd;
        int prefixCount; // number of words passing through this node
    }

    static class Trie {
        TrieNode root = new TrieNode();

        void insert(String word) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (cur.children[idx] == null) cur.children[idx] = new TrieNode();
                cur = cur.children[idx];
                cur.prefixCount++;
            }
            cur.isEnd = true;
        }

        boolean search(String word) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (cur.children[idx] == null) return false;
                cur = cur.children[idx];
            }
            return cur.isEnd;
        }

        boolean startsWith(String prefix) {
            TrieNode cur = root;
            for (char c : prefix.toCharArray()) {
                int idx = c - 'a';
                if (cur.children[idx] == null) return false;
                cur = cur.children[idx];
            }
            return true;
        }

        int prefixCount(String prefix) {
            TrieNode cur = root;
            for (char c : prefix.toCharArray()) {
                int idx = c - 'a';
                if (cur.children[idx] == null) return 0;
                cur = cur.children[idx];
            }
            return cur.prefixCount;
        }
    }

    // ─── PREFIX SUM IN TRIE ───────────────────────────────────────────────────────
    // Count how many inserted words have the given prefix.
    // Each node stores prefixCount = number of words passing through it.
    // TC: O(L), SC: O(1)
    public static int countWordsWithPrefix(String[] words, String prefix) {
        Trie trie = new Trie();
        for (String w : words) trie.insert(w);
        return trie.prefixCount(prefix);
    }

    // ─── STARTS WITH PROBLEM ─────────────────────────────────────────────────────
    // Given a list of words and a list of prefixes, for each prefix
    // return all words that start with it.
    // TC: O(W*L + Q*L + results), SC: O(W*L)
    public static List<String> wordsWithPrefix(String[] words, String prefix) {
        Trie trie = new Trie();
        for (String w : words) trie.insert(w);
        List<String> result = new ArrayList<>();
        for (String w : words)
            if (trie.startsWith(prefix) && w.startsWith(prefix)) result.add(w);
        return result;
    }

    // ─── COUNT UNIQUE STRINGS ─────────────────────────────────────────────────────
    // Count the number of distinct/unique strings in a given array.
    // Insert each string into trie; if search returns false before insert → it's new.
    // TC: O(W*L), SC: O(W*L)
    public static int countUniqueStrings(String[] words) {
        Trie trie = new Trie();
        int count = 0;
        for (String w : words) {
            if (!trie.search(w)) { trie.insert(w); count++; }
        }
        return count;
    }

    // ─── GROUP ANAGRAMS ─────────────────────────────────────────────────────────────
    // Group words that are anagrams of each other.
    // Insert each word into trie using its sorted characters as key.
    // Use HashMap<sortedKey, List<word>> to group; trie validates membership.
    // TC: O(W * L log L), SC: O(W * L)
    public static List<List<String>> groupAnagrams(String[] words) {
        Map<String, List<String>> map = new HashMap<>();
        Trie trie = new Trie();
        for (String w : words) {
            char[] ch = w.toCharArray();
            Arrays.sort(ch);
            String key = new String(ch);
            trie.insert(key);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(w);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        // Prefix Sum in Trie
        System.out.println("─── Prefix Count ───");
        String[] words = {"apple", "app", "application", "apt", "bat", "ball"};
        System.out.println(countWordsWithPrefix(words, "app")); // 3 (apple,app,application)
        System.out.println(countWordsWithPrefix(words, "ba"));  // 2 (bat,ball)
        System.out.println(countWordsWithPrefix(words, "xyz")); // 0

        // StartsWith Problem
        System.out.println("─── Words With Prefix ───");
        System.out.println(wordsWithPrefix(words, "app")); // [apple, app, application]
        System.out.println(wordsWithPrefix(words, "ba"));  // [bat, ball]

        // Count Unique Strings
        System.out.println("─── Count Unique Strings ───");
        System.out.println(countUniqueStrings(
            new String[]{"apple", "bat", "apple", "cat", "bat", "dog"}
        )); // 4 (apple, bat, cat, dog)
        System.out.println(countUniqueStrings(
            new String[]{"a", "b", "a", "c", "b"}
        )); // 3

        // Group Anagrams
        System.out.println("─── Group Anagrams ───");
        System.out.println(groupAnagrams(
            new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}
        )); // [[eat, tea, ate], [tan, nat], [bat]]

        System.out.println(groupAnagrams(
            new String[]{"abc", "bca", "cab", "xyz", "zyx"}
        )); // [[abc, bca, cab], [xyz, zyx]]
    }
}
