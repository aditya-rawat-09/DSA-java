public class MaxPathSum {

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

    // ─── MAXIMUM PATH SUM ────────────────────────────────────────────────────────
    // Path can start and end at any node (doesn't need to pass through root).
    // At each node: candidate = left + node + right.
    // Return to parent: node + max(left, right) — can only extend one side.
    // TC: O(n), SC: O(h)
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(Node root) {
        maxSum = Integer.MIN_VALUE;
        helper(root);
        return maxSum;
    }

    private int helper(Node node) {
        if (node == null) return 0;
        int left  = Math.max(0, helper(node.left));  // ignore negative paths
        int right = Math.max(0, helper(node.right));
        maxSum = Math.max(maxSum, left + node.data + right); // update global max
        return node.data + Math.max(left, right);            // return best single path
    }

    public static void main(String[] args) {
        // Tree:       -10
        //             / \
        //            9   20
        //               / \
        //              15   7
        // Max path: 15 -> 20 -> 7 = 42
        MaxPathSum t = new MaxPathSum();
        t.root = t.build(new int[]{-10, 9, -1, -1, 20, 15, -1, -1, 7, -1, -1});
        System.out.println(t.maxPathSum(t.root)); // 42

        // Tree:       1
        //            / \
        //           2   3
        MaxPathSum t2 = new MaxPathSum();
        t2.root = t2.build(new int[]{1, 2, -1, -1, 3, -1, -1});
        System.out.println(t2.maxPathSum(t2.root)); // 6
    }
}
