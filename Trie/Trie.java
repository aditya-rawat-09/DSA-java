import java.util.*;

public class Trie {

    // ─── TRIE NODE ────────────────────────────────────────────────────────────────
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd;
    }

    TrieNode root = new TrieNode();

    // ─── INSERT ───────────────────────────────────────────────────────────────────
    // Add a word into the trie character by character.
    // Create new node if child doesn't exist; mark last node as end of word.
    // TC: O(L), SC: O(L)  where L = word length
    public void insert(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (cur.children[idx] == null) cur.children[idx] = new TrieNode();
            cur = cur.children[idx];
        }
        cur.isEnd = true;
    }

    // ─── SEARCH ───────────────────────────────────────────────────────────────────
    // Return true if the exact word exists in the trie.
    // TC: O(L), SC: O(1)
    public boolean search(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (cur.children[idx] == null) return false;
            cur = cur.children[idx];
        }
        return cur.isEnd;
    }

    // ─── STARTS WITH ──────────────────────────────────────────────────────────────
    // Return true if any word in the trie starts with the given prefix.
    // TC: O(L), SC: O(1)
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for (char c : prefix.toCharArray()) {
            int idx = c - 'a';
            if (cur.children[idx] == null) return false;
            cur = cur.children[idx];
        }
        return true;
    }

    // ─── WORD BREAK (Trie + DP) ───────────────────────────────────────────────────
    // Given string s and wordDict, return true if s can be segmented into
    // one or more dictionary words.
    // dp[i] = true if s[0..i-1] can be segmented.
    // For each dp[i] = true, use trie to find all valid word endings from i.
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
        // ── Basic: Insert & Search ──
        Trie t = new Trie();
        t.insert("apple");
        t.insert("app");
        t.insert("bat");
        t.insert("ball");

        System.out.println("─── Search ───");
        System.out.println(t.search("apple"));  // true
        System.out.println(t.search("app"));    // true
        System.out.println(t.search("ap"));     // false (not a complete word)
        System.out.println(t.search("bat"));    // true
        System.out.println(t.search("cat"));    // false

        System.out.println("─── StartsWith ───");
        System.out.println(t.startsWith("app")); // true
        System.out.println(t.startsWith("ba"));  // true
        System.out.println(t.startsWith("cat")); // false

        // ── Word Break I ──
        System.out.println("─── Word Break I ───");
        System.out.println(wordBreak("leetcode",
            Arrays.asList("leet", "code")));                               // true
        System.out.println(wordBreak("applepenapple",
            Arrays.asList("apple", "pen")));                               // true
        System.out.println(wordBreak("catsandog",
            Arrays.asList("cats", "dog", "sand", "and", "cat")));          // false

        // ── Word Break II ──
        System.out.println("─── Word Break II ───");
        System.out.println(wordBreakII("catsanddog",
            Arrays.asList("cat", "cats", "and", "sand", "dog")));
        // [cats and dog, cat sand dog]

        System.out.println(wordBreakII("pineapplepenapple",
            Arrays.asList("apple", "pen", "applepen", "pine", "pineapple")));
        // [pine apple pen apple, pine applepen apple, pineapple pen apple]
    }
}
