public class LCA {

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

    // ─── LOWEST COMMON ANCESTOR ──────────────────────────────────────────────────
    // If root matches p or q, root itself is LCA.
    // Recurse left and right; if both return non-null, current node is LCA.
    // TC: O(n), SC: O(h)
    public Node lca(Node root, int p, int q) {
        if (root == null) return null;
        if (root.data == p || root.data == q) return root;

        Node left  = lca(root.left,  p, q);
        Node right = lca(root.right, p, q);

        if (left != null && right != null) return root; // p and q on different sides
        return left != null ? left : right;
    }

    public static void main(String[] args) {
        // Tree:       1
        //            / \
        //           2   3
        //          / \
        //         4   5
        LCA t = new LCA();
        t.root = t.build(new int[]{1, 2, 4, -1, -1, 5, -1, -1, 3, -1, -1});

        System.out.println(t.lca(t.root, 4, 5).data); // 2
        System.out.println(t.lca(t.root, 4, 3).data); // 1
        System.out.println(t.lca(t.root, 2, 3).data); // 1
    }
}
