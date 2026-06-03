public class ValidBST {

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

    // ─── VALID BST ───────────────────────────────────────────────────────────────
    // Pass min/max bounds to each node.
    // Every node must satisfy: min < node.data < max
    // TC: O(n), SC: O(h)
    public boolean isValidBST(Node root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(Node node, long min, long max) {
        if (node == null) return true;
        if (node.data <= min || node.data >= max) return false;
        return validate(node.left,  min, node.data)
            && validate(node.right, node.data, max);
    }

    public static void main(String[] args) {
        ValidBST t = new ValidBST();
        for (int v : new int[]{5, 3, 7, 2, 4, 6, 8}) t.root = t.insert(t.root, v);
        System.out.println(t.isValidBST(t.root)); // true

        // Manually corrupt the tree
        t.root.left.data = 10; // violates BST property
        System.out.println(t.isValidBST(t.root)); // false
    }
}
