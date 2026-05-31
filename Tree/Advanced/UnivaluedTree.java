public class UnivaluedTree {

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

    // ─── IS UNIVALUED TREE ───────────────────────────────────────────────────────
    // A tree is univalued if every node has the same value.
    // TC: O(n), SC: O(h)
    public boolean isUnivalued(Node root) {
        if (root == null) return true;
        if (root.left  != null && root.left.data  != root.data) return false;
        if (root.right != null && root.right.data != root.data) return false;
        return isUnivalued(root.left) && isUnivalued(root.right);
    }

    public static void main(String[] args) {
        UnivaluedTree t1 = new UnivaluedTree();
        // All 1s → true
        t1.root = t1.build(new int[]{1, 1, 1, -1, -1, 1, -1, -1, 1, -1, -1});
        System.out.println(t1.isUnivalued(t1.root)); // true

        UnivaluedTree t2 = new UnivaluedTree();
        // Mixed values → false
        t2.root = t2.build(new int[]{1, 2, 4, -1, -1, 5, -1, -1, 3, -1, -1});
        System.out.println(t2.isUnivalued(t2.root)); // false
    }
}
