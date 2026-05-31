import java.util.*;

public class KthLevel {

    Node root;

    class Node {
        int data;
        Node left, right;
        Node(int data) { this.data = data; }
    }

    int idx = 0;
    public Node build(int[] arr) {
        if (idx >= arr.length || arr[idx] == -1) { idx++; return null; }
        Node node = new Node(arr[idx++]);
        node.left  = build(arr);
        node.right = build(arr);
        return node;
    }

    // ─── KTH LEVEL NODES ─────────────────────────────────────────────────────────
    // Print all nodes at level k (root = level 1)
    // TC: O(n), SC: O(h)
    public void kthLevel(Node root, int k) {
        if (root == null) return;
        if (k == 1) { System.out.print(root.data + " "); return; }
        kthLevel(root.left,  k - 1);
        kthLevel(root.right, k - 1);
    }

    public static void main(String[] args) {
        // Tree:       1
        //            / \
        //           2   3
        //          / \
        //         4   5
        KthLevel t = new KthLevel();
        t.root = t.build(new int[]{1, 2, 4, -1, -1, 5, -1, -1, 3, -1, -1});

        t.kthLevel(t.root, 1); System.out.println(); // 1
        t.kthLevel(t.root, 2); System.out.println(); // 2 3
        t.kthLevel(t.root, 3); System.out.println(); // 4 5
    }
}
