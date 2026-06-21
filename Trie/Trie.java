public class Trie {

    // ─── TRIE NODE ────────────────────────────────────────────────────────────────
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd;
        int prefixCount; // counts how many words pass through this node
    }

    TrieNode root = new TrieNode();

    // ─── INSERT ───────────────────────────────────────────────────────────────────
    // Add a word into the trie character by character.
    // Create new node if child doesn't exist; mark last node as end of word.
    // TC: O(L), SC: O(L)
    public void insert(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (cur.children[idx] == null) cur.children[idx] = new TrieNode();
            cur = cur.children[idx];
            cur.prefixCount++;
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

    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("apple");
        t.insert("app");
        t.insert("bat");
        t.insert("ball");

        System.out.println("─── Search ───");
        System.out.println(t.search("apple")); // true
        System.out.println(t.search("app"));   // true
        System.out.println(t.search("ap"));    // false
        System.out.println(t.search("cat"));   // false

        System.out.println("─── StartsWith ───");
        System.out.println(t.startsWith("app")); // true
        System.out.println(t.startsWith("ba"));  // true
        System.out.println(t.startsWith("cat")); // false
    }
}
