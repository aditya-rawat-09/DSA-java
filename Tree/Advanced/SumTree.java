public class SumTree {

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

    // ─── TRANSFORM TO SUM TREE ───────────────────────────────────────────────────
    // Each node's value = sum of all nodes in its left + right subtrees (original values).
    // Leaf nodes become 0.
    // TC: O(n), SC: O(h)
    public int toSumTree(Node node) {
        if (node == null) return 0;
        int oldVal = node.data;
        node.data  = toSumTree(node.left) + toSumTree(node.right);
        return oldVal + node.data; // return original + children sum for parent
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
        //          / \
        //         4   5
        // Sum tree:   11
        //            / \
        //           9   0
        //          / \
        //         0   0
        SumTree t = new SumTree();
        t.root = t.build(new int[]{1, 2, 4, -1, -1, 5, -1, -1, 3, -1, -1});
        t.toSumTree(t.root);
        t.inorder(t.root); // 0 9 0 11 0 0
        System.out.println();
    }
}
