public class BST {

    Node root;

    class Node {
        int data;
        Node left, right;
        Node(int data) { this.data = data; }
    }

    // ─── INSERT INTO BST ─────────────────────────────────────────────────────────
    // Values < node go left, values > node go right.
    // TC: O(h), SC: O(h)  [h = log n for balanced, n for skewed]
    public Node insert(Node root, int val) {
        if (root == null) return new Node(val);
        if (val < root.data) root.left  = insert(root.left,  val);
        else if (val > root.data) root.right = insert(root.right, val);
        return root;
    }

    // ─── INORDER TRAVERSAL ───────────────────────────────────────────────────────
    // Inorder of BST always gives sorted (ascending) output.
    // TC: O(n), SC: O(h)
    public void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public static void main(String[] args) {
        // Build BST:       5
        //                 / \
        //                3   7
        //               / \ / \
        //              2  4 6   8
        BST t = new BST();
        int[] vals = {5, 3, 7, 2, 4, 6, 8};
        for (int v : vals) t.root = t.insert(t.root, v);

        t.inorder(t.root); // 2 3 4 5 6 7 8
        System.out.println();
    }
}
