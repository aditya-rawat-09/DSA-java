package trie;

import java.util.*;

public class TrieProblems {

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


    // ─── TRIE NODE ────────────────────────────────────────────────────────────────
    static class TrieNode3 {
        TrieNode3[] children = new TrieNode3[26];
        boolean isEnd;
    }

    static class Trie3 {
        TrieNode3 root = new TrieNode3();

        void insert(String word) {
            TrieNode3 cur = root;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (cur.children[idx] == null) cur.children[idx] = new TrieNode3();
                cur = cur.children[idx];
            }
            cur.isEnd = true;
        }

        boolean search(String word) {
            TrieNode3 cur = root;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (cur.children[idx] == null) return false;
                cur = cur.children[idx];
            }
            return cur.isEnd;
        }
    }

    // ─── LONGEST WORD IN DICTIONARY ──────────────────────────────────────────────
    // Find the longest word buildable one char at a time where every prefix exists.
    // DFS from root visiting only isEnd nodes; track longest path found.
    // Tie-break: lexicographically smallest.
    // TC: O(W*L), SC: O(W*L)
    public static String longestWordInDictionary(String[] words) {
        Trie3 trie = new Trie3();
        for (String w : words) trie.insert(w);
        String[] result = {""};
        dfsLongest(trie.root, new StringBuilder(), result);
        return result[0];
    }

    private static void dfsLongest(TrieNode3 node, StringBuilder path, String[] result) {
        if (path.length() > result[0].length() ||
           (path.length() == result[0].length() && path.toString().compareTo(result[0]) < 0))
            result[0] = path.toString();
        for (int i = 0; i < 26; i++) {
            TrieNode3 child = node.children[i];
            if (child != null && child.isEnd) {
                path.append((char)('a' + i));
                dfsLongest(child, path, result);
                path.deleteCharAt(path.length() - 1);
            }
        }
    }

    // ─── LONGEST WORD WITH ALL PREFIXES ──────────────────────────────────────────
    // Find the longest word in the array such that every prefix of that word
    // also exists in the array.
    // Insert all words; then for each word check if all its prefixes are in trie.
    // Tie-break: lexicographically smaller word wins.
    // TC: O(W*L), SC: O(W*L)
    public static String longestWordWithAllPrefixes(String[] words) {
        Trie3 trie = new Trie3();
        for (String w : words) trie.insert(w);

        String result = "";
        for (String w : words) {
            if (allPrefixesExist(w, trie)) {
                if (w.length() > result.length() ||
                   (w.length() == result.length() && w.compareTo(result) < 0))
                    result = w;
            }
        }
        return result;
    }

    private static boolean allPrefixesExist(String word, Trie3 trie) {
        for (int i = 1; i < word.length(); i++)
            if (!trie.search(word.substring(0, i))) return false;
        return true;
    }

    // ─── WORD BREAK I (Trie3 + DP) ─────────────────────────────────────────────────
    // Return true if s can be segmented into one or more dictionary words.
    // dp[i] = true if s[0..i-1] can be segmented.
    // TC: O(n^2 + W*L), SC: O(n + W*L)
    public static boolean wordBreak(String s, List<String> wordDict) {
        Trie3 trie = new Trie3();
        for (String word : wordDict) trie.insert(word);

        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 0; i < n; i++) {
            if (!dp[i]) continue;
            TrieNode3 cur = trie.root;
            for (int j = i; j < n; j++) {
                int idx = s.charAt(j) - 'a';
                if (cur.children[idx] == null) break;
                cur = cur.children[idx];
                if (cur.isEnd) dp[j + 1] = true;
            }
        }
        return dp[n];
    }

    // ─── WORD BREAK II (Trie3 + Backtracking) ─────────────────────────────────────
    // Return all possible sentences formed by segmenting s using dict words.
    // TC: O(2^n) worst case, SC: O(n)
    public static List<String> wordBreakII(String s, List<String> wordDict) {
        Trie3 trie = new Trie3();
        for (String word : wordDict) trie.insert(word);
        List<String> result = new ArrayList<>();
        backtrack(s, 0, trie, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(String s, int start, Trie3 trie,
                                   List<String> current, List<String> result) {
        if (start == s.length()) { result.add(String.join(" ", current)); return; }
        TrieNode3 cur = trie.root;
        for (int i = start; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            if (cur.children[idx] == null) break;
            cur = cur.children[idx];
            if (cur.isEnd) {
                current.add(s.substring(start, i + 1));
                backtrack(s, i + 1, trie, current, result);
                current.remove(current.size() - 1); // backtrack
            }
        }
    }

    public static void demoMain2(String[] args) {
        // Longest Word In Dictionary
        System.out.println("─── Longest Word In Dictionary ───");
        System.out.println(longestWordInDictionary(
            new String[]{"w", "wo", "wor", "worl", "world"}
        )); // world
        System.out.println(longestWordInDictionary(
            new String[]{"a", "banana", "app", "appl", "ap", "apply", "apple"}
        )); // apple

        // Longest Word With All Prefixes
        System.out.println("─── Longest Word With All Prefixes ───");
        System.out.println(longestWordWithAllPrefixes(
            new String[]{"a", "ap", "app", "appl", "apple", "apply", "bat"}
        )); // apple  (apply also valid but apple < apply lexicographically)

        System.out.println(longestWordWithAllPrefixes(
            new String[]{"w", "wo", "wor", "worl", "world"}
        )); // world

        System.out.println(longestWordWithAllPrefixes(
            new String[]{"a", "banana", "app", "appl", "ap", "apply", "apple"}
        )); // apple

        // Word Break I
        System.out.println("─── Word Break I ───");
        System.out.println(wordBreak("leetcode",
            Arrays.asList("leet", "code")));                              // true
        System.out.println(wordBreak("applepenapple",
            Arrays.asList("apple", "pen")));                              // true
        System.out.println(wordBreak("catsandog",
            Arrays.asList("cats", "dog", "sand", "and", "cat")));         // false

        // Word Break II
        System.out.println("─── Word Break II ───");
        System.out.println(wordBreakII("catsanddog",
            Arrays.asList("cat", "cats", "and", "sand", "dog")));
        // [cats and dog, cat sand dog]

        System.out.println(wordBreakII("pineapplepenapple",
            Arrays.asList("apple", "pen", "applepen", "pine", "pineapple")));
        // [pine apple pen apple, pine applepen apple, pineapple pen apple]
    }
}
