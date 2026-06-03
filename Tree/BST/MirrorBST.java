public class MirrorBST {

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

    // ─── MIRROR BST ──────────────────────────────────────────────────────────────
    // Swap left and right at every node → produces a mirror image.
    // Inorder of mirrored BST gives descending order.
    // TC: O(n), SC: O(h)
    public Node mirror(Node root) {
        if (root == null) return null;
        Node temp   = root.left;
        root.left   = mirror(root.right);
        root.right  = mirror(temp);
        return root;
    }

    // Inorder of mirrored tree → descending
    public void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public static void main(String[] args) {
        // BST:       5              Mirrored:    5
        //           / \                         / \
        //          3   7                       7   3
        //         / \ / \                     / \ / \
        //        2  4 6   8                  8  6 4   2
        MirrorBST t = new MirrorBST();
        for (int v : new int[]{5, 3, 7, 2, 4, 6, 8}) t.root = t.insert(t.root, v);

        t.mirror(t.root);
        t.inorder(t.root); // 8 7 6 5 4 3 2  (descending)
        System.out.println();
    }
}
