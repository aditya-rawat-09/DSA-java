public class MinDistance {

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

    // ─── MINIMUM DISTANCE BETWEEN TWO NODES ──────────────────────────────────────
    // dist(p, q) = dist(root, p) + dist(root, q) - 2 * dist(root, LCA(p,q))
    // TC: O(n), SC: O(h)
    public int minDistance(Node root, int p, int q) {
        Node ancestor = lca(root, p, q);
        return distFromNode(ancestor, p, 0) + distFromNode(ancestor, q, 0);
    }

    private Node lca(Node root, int p, int q) {
        if (root == null) return null;
        if (root.data == p || root.data == q) return root;
        Node left  = lca(root.left,  p, q);
        Node right = lca(root.right, p, q);
        if (left != null && right != null) return root;
        return left != null ? left : right;
    }

    // Distance from given node to target value
    private int distFromNode(Node node, int target, int dist) {
        if (node == null) return -1;
        if (node.data == target) return dist;
        int left = distFromNode(node.left,  target, dist + 1);
        if (left != -1) return left;
        return distFromNode(node.right, target, dist + 1);
    }

    public static void main(String[] args) {
        // Tree:       1
        //            / \
        //           2   3
        //          / \
        //         4   5
        MinDistance t = new MinDistance();
        t.root = t.build(new int[]{1, 2, 4, -1, -1, 5, -1, -1, 3, -1, -1});

        System.out.println(t.minDistance(t.root, 4, 5)); // 2
        System.out.println(t.minDistance(t.root, 4, 3)); // 3
        System.out.println(t.minDistance(t.root, 4, 2)); // 1
    }
}
