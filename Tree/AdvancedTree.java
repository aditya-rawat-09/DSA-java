import java.util.*;

public class AdvancedTree {

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

    // ─── KTH LEVEL NODES ─────────────────────────────────────────────────────────
    // Print all nodes at level k (root = level 1)
    // TC: O(n), SC: O(h)
    public void kthLevel(Node root, int k) {
        if (root == null) return;
        if (k == 1) { System.out.print(root.data + " "); return; }
        kthLevel(root.left,  k - 1);
        kthLevel(root.right, k - 1);
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
        if (left != null && right != null) return root;
        return left != null ? left : right;
    }

    // ─── MINIMUM DISTANCE BETWEEN TWO NODES ──────────────────────────────────────
    // dist(p, q) = dist(LCA→p) + dist(LCA→q)
    // TC: O(n), SC: O(h)
    public int minDistance(Node root, int p, int q) {
        Node ancestor = lca(root, p, q);
        return distFromNode(ancestor, p, 0) + distFromNode(ancestor, q, 0);
    }

    private int distFromNode(Node node, int target, int dist) {
        if (node == null) return -1;
        if (node.data == target) return dist;
        int left = distFromNode(node.left,  target, dist + 1);
        if (left != -1) return left;
        return distFromNode(node.right, target, dist + 1);
    }

    // ─── KTH ANCESTOR ────────────────────────────────────────────────────────────
    // Recurse until target found, then count k steps back up.
    // TC: O(n), SC: O(h)
    int count = 0;
    public Node kthAncestor(Node root, int target, int k) {
        if (root == null) return null;
        if (root.data == target) return root;
        Node left  = kthAncestor(root.left,  target, k);
        Node right = kthAncestor(root.right, target, k);
        if (left != null || right != null) {
            count++;
            if (count == k) return root;
            return root;
        }
        return null;
    }

    // ─── TRANSFORM TO SUM TREE ───────────────────────────────────────────────────
    // Each node's value = sum of its left + right subtrees (original values).
    // TC: O(n), SC: O(h)
    public int toSumTree(Node node) {
        if (node == null) return 0;
        int oldVal = node.data;
        node.data  = toSumTree(node.left) + toSumTree(node.right);
        return oldVal + node.data;
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

    // ─── INVERT BINARY TREE ──────────────────────────────────────────────────────
    // Swap left and right children at every node recursively.
    // TC: O(n), SC: O(h)
    public Node invert(Node root) {
        if (root == null) return null;
        Node temp  = root.left;
        root.left  = invert(root.right);
        root.right = invert(temp);
        return root;
    }

    // ─── DELETE LEAF NODES WITH VALUE X ─────────────────────────────────────────
    // Post-order: delete children first, then check if current became a leaf with value x.
    // TC: O(n), SC: O(h)
    public Node deleteLeaf(Node root, int x) {
        if (root == null) return null;
        root.left  = deleteLeaf(root.left,  x);
        root.right = deleteLeaf(root.right, x);
        if (root.left == null && root.right == null && root.data == x) return null;
        return root;
    }

    // ─── FIND DUPLICATE SUBTREES ─────────────────────────────────────────────────
    // Serialize each subtree; use a map to count. Add on 2nd occurrence.
    // TC: O(n^2), SC: O(n^2)
    public List<Node> findDuplicates(Node root) {
        List<Node> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        serialize(root, map, result);
        return result;
    }

    private String serialize(Node node, Map<String, Integer> map, List<Node> result) {
        if (node == null) return "#";
        String serial = node.data + "," + serialize(node.left,  map, result)
                                  + "," + serialize(node.right, map, result);
        map.put(serial, map.getOrDefault(serial, 0) + 1);
        if (map.get(serial) == 2) result.add(node);
        return serial;
    }

    // ─── MAXIMUM PATH SUM ────────────────────────────────────────────────────────
    // Path can start and end at any node.
    // At each node: candidate = left + node + right.
    // TC: O(n), SC: O(h)
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(Node root) {
        maxSum = Integer.MIN_VALUE;
        pathHelper(root);
        return maxSum;
    }

    private int pathHelper(Node node) {
        if (node == null) return 0;
        int left  = Math.max(0, pathHelper(node.left));
        int right = Math.max(0, pathHelper(node.right));
        maxSum = Math.max(maxSum, left + node.data + right);
        return node.data + Math.max(left, right);
    }

    // ─── HELPER: INORDER ─────────────────────────────────────────────────────────
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
        AdvancedTree t = new AdvancedTree();
        t.root = t.build(new int[]{1, 2, 4, -1, -1, 5, -1, -1, 3, -1, -1});

        // Kth Level
        t.kthLevel(t.root, 2); System.out.println(); // 2 3
        t.kthLevel(t.root, 3); System.out.println(); // 4 5

        // LCA
        System.out.println(t.lca(t.root, 4, 5).data); // 2
        System.out.println(t.lca(t.root, 4, 3).data); // 1

        // Min Distance
        System.out.println(t.minDistance(t.root, 4, 5)); // 2
        System.out.println(t.minDistance(t.root, 4, 3)); // 3

        // Kth Ancestor
        t.count = 0;
        Node res = t.kthAncestor(t.root, 4, 1);
        System.out.println(res != null ? res.data : -1); // 2
        t.count = 0;
        res = t.kthAncestor(t.root, 4, 2);
        System.out.println(res != null ? res.data : -1); // 1

        // Sum Tree
        AdvancedTree t2 = new AdvancedTree();
        t2.root = t2.build(new int[]{1, 2, 4, -1, -1, 5, -1, -1, 3, -1, -1});
        t2.toSumTree(t2.root);
        t2.inorder(t2.root); System.out.println(); // 0 9 0 11 0 0

        // Univalued
        AdvancedTree t3 = new AdvancedTree();
        t3.root = t3.build(new int[]{1, 1, 1, -1, -1, 1, -1, -1, 1, -1, -1});
        System.out.println(t3.isUnivalued(t3.root)); // true
        System.out.println(t.isUnivalued(t.root));   // false

        // Invert
        AdvancedTree t4 = new AdvancedTree();
        t4.root = t4.build(new int[]{1, 2, 4, -1, -1, 5, -1, -1, 3, -1, -1});
        t4.invert(t4.root);
        t4.inorder(t4.root); System.out.println(); // 3 1 5 2 4

        // Delete Leaf with value 2
        AdvancedTree t5 = new AdvancedTree();
        t5.root = t5.build(new int[]{1, 2, 2, -1, -1, 2, -1, -1, 3, -1, 2, -1, -1});
        t5.root = t5.deleteLeaf(t5.root, 2);
        t5.inorder(t5.root); System.out.println(); // 1 3

        // Duplicate Subtrees
        AdvancedTree t6 = new AdvancedTree();
        t6.root = t6.build(new int[]{1, 2, 4, -1, -1, -1, 3, 2, 4, -1, -1, -1, 4, -1, -1});
        List<Node> dups = t6.findDuplicates(t6.root);
        for (Node n : dups) System.out.print(n.data + " "); System.out.println(); // 4 2

        // Max Path Sum
        AdvancedTree t7 = new AdvancedTree();
        t7.root = t7.build(new int[]{-10, 9, -1, -1, 20, 15, -1, -1, 7, -1, -1});
        System.out.println(t7.maxPathSum(t7.root)); // 42
    }
}
