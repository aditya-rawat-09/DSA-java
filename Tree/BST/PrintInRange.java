public class PrintInRange {

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

    // ─── PRINT NODES IN RANGE [lo, hi] ───────────────────────────────────────────
    // Use BST property to prune unnecessary branches:
    //   - if root.data < lo  → skip left subtree
    //   - if root.data > hi  → skip right subtree
    // TC: O(n), SC: O(h)
    public void printInRange(Node root, int lo, int hi) {
        if (root == null) return;
        if (root.data >= lo) printInRange(root.left,  lo, hi); // explore left only if needed
        if (root.data >= lo && root.data <= hi) System.out.print(root.data + " ");
        if (root.data <= hi) printInRange(root.right, lo, hi); // explore right only if needed
    }

    public static void main(String[] args) {
        // BST:       5
        //           / \
        //          3   7
        //         / \ / \
        //        2  4 6   8
        PrintInRange t = new PrintInRange();
        for (int v : new int[]{5, 3, 7, 2, 4, 6, 8}) t.root = t.insert(t.root, v);

        t.printInRange(t.root, 3, 7); // 3 4 5 6 7
        System.out.println();
        t.printInRange(t.root, 1, 4); // 2 3 4
        System.out.println();
    }
}
