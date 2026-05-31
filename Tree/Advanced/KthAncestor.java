public class KthAncestor {

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

    // ─── KTH ANCESTOR ────────────────────────────────────────────────────────────
    // Find the kth ancestor of node with given value.
    // Recurse until target found, then count k steps back up.
    // Returns the ancestor node, or null if not found / k exceeds depth.
    // TC: O(n), SC: O(h)
    int count = 0;
    public Node kthAncestor(Node root, int target, int k) {
        if (root == null) return null;
        if (root.data == target) return root; // found target, start counting

        Node left  = kthAncestor(root.left,  target, k);
        Node right = kthAncestor(root.right, target, k);

        if (left != null || right != null) {
            count++;
            if (count == k) return root; // this node is the kth ancestor
            return root; // keep bubbling up
        }
        return null;
    }

    public static void main(String[] args) {
        // Tree:       1
        //            / \
        //           2   3
        //          / \
        //         4   5
        KthAncestor t = new KthAncestor();
        t.root = t.build(new int[]{1, 2, 4, -1, -1, 5, -1, -1, 3, -1, -1});

        // 1st ancestor of 4 → 2
        t.count = 0;
        Node res = t.kthAncestor(t.root, 4, 1);
        System.out.println(res != null ? res.data : -1); // 2

        // 2nd ancestor of 4 → 1
        t.count = 0;
        res = t.kthAncestor(t.root, 4, 2);
        System.out.println(res != null ? res.data : -1); // 1
    }
}
