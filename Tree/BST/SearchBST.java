public class SearchBST {

    Node root;

    class Node {
        int data;
        Node left, right;
        Node(int data) { this.data = data; }
    }

    public Node insert(Node root, int val) {
        if (root == null) return new Node(val);
        if (val < root.data) root.left  = insert(root.left,  val);
        else if (val > root.data) root.right = insert(root.right, val);
        return root;
    }

    // ─── SEARCH IN BST ───────────────────────────────────────────────────────────
    // Use BST property: go left if val < root, right if val > root.
    // TC: O(h), SC: O(h)
    public Node search(Node root, int val) {
        if (root == null || root.data == val) return root;
        if (val < root.data) return search(root.left,  val);
        return search(root.right, val);
    }

    public static void main(String[] args) {
        // BST:       5
        //           / \
        //          3   7
        //         / \ / \
        //        2  4 6   8
        SearchBST t = new SearchBST();
        for (int v : new int[]{5, 3, 7, 2, 4, 6, 8}) t.root = t.insert(t.root, v);

        Node res = t.search(t.root, 4);
        System.out.println(res != null ? "Found: " + res.data : "Not found"); // Found: 4

        res = t.search(t.root, 9);
        System.out.println(res != null ? "Found: " + res.data : "Not found"); // Not found
    }
}
