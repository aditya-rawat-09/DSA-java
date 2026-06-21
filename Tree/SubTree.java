import java.util.*;

public class SubTree {

    Node root;

    class Node {
        int data;
        Node left, right;
        Node(int data) { this.data = data; }
    }

    int idx = 0;
    public Node buildFromPreorder(int[] arr) {
        if (idx >= arr.length || arr[idx] == -1) { idx++; return null; }
        Node node = new Node(arr[idx++]);
        node.left  = buildFromPreorder(arr);
        node.right = buildFromPreorder(arr);
        return node;
    }

    // ─── IS SUBTREE ──────────────────────────────────────────────────────────────
    // Check if subRoot is a subtree of root
    // Approach: for every node in main tree, check if trees are identical
    // TC: O(m * n), SC: O(h)  where m = nodes in main, n = nodes in sub
    public boolean isSubTree(Node root, Node subRoot) {
        if (subRoot == null) return true;
        if (root == null) return false;
        if (isIdentical(root, subRoot)) return true;
        return isSubTree(root.left, subRoot) || isSubTree(root.right, subRoot);
    }

    // Check if two trees are identical
    private boolean isIdentical(Node a, Node b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.data == b.data
            && isIdentical(a.left, b.left)
            && isIdentical(a.right, b.right);
    }

    public static void main(String[] args) {
        // Main tree:        1
        //                  / \
        //                 2   3
        //                / \
        //               4   5
        SubTree t1 = new SubTree();
        int[] main = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, -1};
        t1.root = t1.buildFromPreorder(main);

        // Sub tree:         2
        //                  / \
        //                 4   5
        SubTree t2 = new SubTree();
        int[] sub = {2, 4, -1, -1, 5, -1, -1};
        t2.root = t2.buildFromPreorder(sub);

        System.out.println(t1.isSubTree(t1.root, t2.root)); // true

        // Sub tree not present: 2 -> 4 -> 6
        SubTree t3 = new SubTree();
        int[] notSub = {2, 4, 6, -1, -1, -1, -1};
        t3.root = t3.buildFromPreorder(notSub);

        System.out.println(t1.isSubTree(t1.root, t3.root)); // false
    }
}
