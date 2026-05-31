import java.util.*;

public class DeleteLeaf {

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

    // ─── DELETE LEAF NODES WITH VALUE X ─────────────────────────────────────────
    // Post-order: delete children first, then check if current node became a leaf with value x.
    // TC: O(n), SC: O(h)
    public Node deleteLeaf(Node root, int x) {
        if (root == null) return null;
        root.left  = deleteLeaf(root.left,  x);
        root.right = deleteLeaf(root.right, x);
        // after deleting children, if this is now a leaf with value x → delete it
        if (root.left == null && root.right == null && root.data == x) return null;
        return root;
    }

    public void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }

    public static void main(String[] args) {
        // Tree:       1
        //            / \
        //           2   3
        //          / \   \
        //         2   2   2
        // Delete leaves with value 2:
        //             1
        //            / \
        //           2   3
        // (node 2 becomes leaf after children deleted → also deleted)
        //             1
        //              \
        //               3
        DeleteLeaf t = new DeleteLeaf();
        t.root = t.build(new int[]{1, 2, 2, -1, -1, 2, -1, -1, 3, -1, 2, -1, -1});
        t.root = t.deleteLeaf(t.root, 2);
        t.inorder(t.root); // 1 3
        System.out.println();
    }
}
