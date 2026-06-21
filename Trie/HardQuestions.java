import java.util.*;

public class HardQuestions {

    // ─── TRIE NODE ────────────────────────────────────────────────────────────────
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd;
    }

    static class Trie {
        TrieNode root = new TrieNode();

        void insert(String word) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (cur.children[idx] == null) cur.children[idx] = new TrieNode();
                cur = cur.children[idx];
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
    }

    // ─── LONGEST WORD IN DICTIONARY ──────────────────────────────────────────────
    // Find the longest word buildable one char at a time where every prefix exists.
    // DFS from root visiting only isEnd nodes; track longest path found.
    // Tie-break: lexicographically smallest.
    // TC: O(W*L), SC: O(W*L)
    public static String longestWordInDictionary(String[] words) {
        Trie trie = new Trie();
        for (String w : words) trie.insert(w);
        String[] result = {""};
        dfsLongest(trie.root, new StringBuilder(), result);
        return result[0];
    }

    private static void dfsLongest(TrieNode node, StringBuilder path, String[] result) {
        if (path.length() > result[0].length() ||
           (path.length() == result[0].length() && path.toString().compareTo(result[0]) < 0))
            result[0] = path.toString();
        for (int i = 0; i < 26; i++) {
            TrieNode child = node.children[i];
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
        Trie trie = new Trie();
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

    private static boolean allPrefixesExist(String word, Trie trie) {
        for (int i = 1; i < word.length(); i++)
            if (!trie.search(word.substring(0, i))) return false;
        return true;
    }

    // ─── WORD BREAK I (Trie + DP) ─────────────────────────────────────────────────
    // Return true if s can be segmented into one or more dictionary words.
    // dp[i] = true if s[0..i-1] can be segmented.
    // TC: O(n^2 + W*L), SC: O(n + W*L)
    public static boolean wordBreak(String s, List<String> wordDict) {
        Trie trie = new Trie();
        for (String word : wordDict) trie.insert(word);

        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 0; i < n; i++) {
            if (!dp[i]) continue;
            TrieNode cur = trie.root;
            for (int j = i; j < n; j++) {
                int idx = s.charAt(j) - 'a';
                if (cur.children[idx] == null) break;
                cur = cur.children[idx];
                if (cur.isEnd) dp[j + 1] = true;
            }
        }
        return dp[n];
    }

    // ─── WORD BREAK II (Trie + Backtracking) ─────────────────────────────────────
    // Return all possible sentences formed by segmenting s using dict words.
    // TC: O(2^n) worst case, SC: O(n)
    public static List<String> wordBreakII(String s, List<String> wordDict) {
        Trie trie = new Trie();
        for (String word : wordDict) trie.insert(word);
        List<String> result = new ArrayList<>();
        backtrack(s, 0, trie, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(String s, int start, Trie trie,
                                   List<String> current, List<String> result) {
        if (start == s.length()) { result.add(String.join(" ", current)); return; }
        TrieNode cur = trie.root;
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

    public static void main(String[] args) {
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
