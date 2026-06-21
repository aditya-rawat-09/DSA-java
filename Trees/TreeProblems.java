package trees;

import java.util.*;

public class TreeProblems {

    // ════════════════════════════════════════════════════════════════════════════
    // Section 1: Advanced tree problems (LCA, kth ancestor, sum tree, invert, etc.)
    // ════════════════════════════════════════════════════════════════════════════
    public static class AdvancedTreeProblems {
        Node root;
        int idx = 0;
        int count = 0;
        int maxSum = Integer.MIN_VALUE;

        static class Node {
            int data;
            Node left, right;
            Node(int data) { this.data = data; }
        }

        public Node build(int[] arr) {
            if (idx >= arr.length || arr[idx] == -1) { idx++; return null; }
            Node node = new Node(arr[idx++]);
            node.left  = build(arr);
            node.right = build(arr);
            return node;
        }

        // ─── KTH LEVEL NODES ─────────────────────────────────────────────────────
        // Print all nodes at level k (root = level 1). TC: O(n), SC: O(h)
        public void kthLevel(Node root, int k) {
            if (root == null) return;
            if (k == 1) { System.out.print(root.data + " "); return; }
            kthLevel(root.left,  k - 1);
            kthLevel(root.right, k - 1);
        }

        // ─── LOWEST COMMON ANCESTOR ──────────────────────────────────────────────
        // If root matches p or q, root itself is LCA. TC: O(n), SC: O(h)
        public Node lca(Node root, int p, int q) {
            if (root == null) return null;
            if (root.data == p || root.data == q) return root;
            Node left  = lca(root.left,  p, q);
            Node right = lca(root.right, p, q);
            if (left != null && right != null) return root;
            return left != null ? left : right;
        }

        // ─── MINIMUM DISTANCE BETWEEN TWO NODES ──────────────────────────────────
        // dist(p, q) = dist(LCA→p) + dist(LCA→q). TC: O(n), SC: O(h)
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

        // ─── KTH ANCESTOR ────────────────────────────────────────────────────────
        // Recurse until target found, then count k steps back up. TC: O(n), SC: O(h)
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

        // ─── TRANSFORM TO SUM TREE ───────────────────────────────────────────────
        // Each node's value = sum of its left + right subtrees (original values). TC: O(n), SC: O(h)
        public int toSumTree(Node node) {
            if (node == null) return 0;
            int oldVal = node.data;
            node.data  = toSumTree(node.left) + toSumTree(node.right);
            return oldVal + node.data;
        }

        // ─── IS UNIVALUED TREE ───────────────────────────────────────────────────
        // A tree is univalued if every node has the same value. TC: O(n), SC: O(h)
        public boolean isUnivalued(Node root) {
            if (root == null) return true;
            if (root.left  != null && root.left.data  != root.data) return false;
            if (root.right != null && root.right.data != root.data) return false;
            return isUnivalued(root.left) && isUnivalued(root.right);
        }

        // ─── INVERT BINARY TREE ──────────────────────────────────────────────────
        // Swap left and right children at every node recursively. TC: O(n), SC: O(h)
        public Node invert(Node root) {
            if (root == null) return null;
            Node temp  = root.left;
            root.left  = invert(root.right);
            root.right = invert(temp);
            return root;
        }

        // ─── DELETE LEAF NODES WITH VALUE X ──────────────────────────────────────
        // Post-order: delete children first, then check if current became a leaf with value x.
        public Node deleteLeaf(Node root, int x) {
            if (root == null) return null;
            root.left  = deleteLeaf(root.left,  x);
            root.right = deleteLeaf(root.right, x);
            if (root.left == null && root.right == null && root.data == x) return null;
            return root;
        }

        // ─── FIND DUPLICATE SUBTREES ──────────────────────────────────────────────
        // Serialize each subtree; use a map to count. Add on 2nd occurrence. TC: O(n^2), SC: O(n^2)
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

        // ─── MAXIMUM PATH SUM ──────────────────────────────────────────────────────
        // Path can start and end at any node. At each node: candidate = left + node + right.
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

        public void inorder(Node node) {
            if (node == null) return;
            inorder(node.left);
            System.out.print(node.data + " ");
            inorder(node.right);
        }
    }

    // ════════════════════════════════════════════════════════════════════════════
    // Section 2: Subtree check (LC 572 — Subtree of Another Tree)
    // ════════════════════════════════════════════════════════════════════════════
    public static class SubTreeProblems {
        Node root;
        int idx = 0;

        static class Node {
            int data;
            Node left, right;
            Node(int data) { this.data = data; }
        }

        public Node buildFromPreorder(int[] arr) {
            if (idx >= arr.length || arr[idx] == -1) { idx++; return null; }
            Node node = new Node(arr[idx++]);
            node.left  = buildFromPreorder(arr);
            node.right = buildFromPreorder(arr);
            return node;
        }

        // ─── IS SUBTREE ──────────────────────────────────────────────────────────
        // Check if subRoot is a subtree of root. For every node in main tree, check identical.
        // TC: O(m * n), SC: O(h) where m = nodes in main, n = nodes in sub
        public boolean isSubTree(Node root, Node subRoot) {
            if (subRoot == null) return true;
            if (root == null) return false;
            if (isIdentical(root, subRoot)) return true;
            return isSubTree(root.left, subRoot) || isSubTree(root.right, subRoot);
        }

        private boolean isIdentical(Node a, Node b) {
            if (a == null && b == null) return true;
            if (a == null || b == null) return false;
            return a.data == b.data
                && isIdentical(a.left, b.left)
                && isIdentical(a.right, b.right);
        }
    }

    // ════════════════════════════════════════════════════════════════════════════
    // Section 3: Top View of a binary tree
    // ════════════════════════════════════════════════════════════════════════════
    public static class TopViewProblems {
        Node root;
        int idx = 0;

        static class Node {
            int data;
            Node left, right;
            Node(int data) { this.data = data; }
        }

        public Node buildFromPreorder(int[] arr) {
            if (idx >= arr.length || arr[idx] == -1) { idx++; return null; }
            Node node = new Node(arr[idx++]);
            node.left  = buildFromPreorder(arr);
            node.right = buildFromPreorder(arr);
            return node;
        }

        // ─── TOP VIEW ────────────────────────────────────────────────────────────
        // Top view = first node visible at each horizontal distance (HD) from root.
        // BFS with (node, HD) pair; store first node seen at each HD. TC: O(n log n), SC: O(n)
        public List<Integer> topView(Node root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) return result;

            TreeMap<Integer, Integer> hdMap = new TreeMap<>();
            topViewBFS(root, hdMap);

            result.addAll(hdMap.values());
            return result;
        }

        private void topViewBFS(Node root, TreeMap<Integer, Integer> hdMap) {
            Queue<Object[]> q = new LinkedList<>();
            q.offer(new Object[]{root, 0});

            while (!q.isEmpty()) {
                Object[] pair = q.poll();
                Node node = (Node) pair[0];
                int hd    = (int) pair[1];

                hdMap.putIfAbsent(hd, node.data); // only the FIRST node at each HD (top-most)

                if (node.left  != null) q.offer(new Object[]{node.left,  hd - 1});
                if (node.right != null) q.offer(new Object[]{node.right, hd + 1});
            }
        }
    }

    public static void main(String[] args) {
        // ── Advanced tree demo ──
        // Tree:       1
        //            / \
        //           2   3
        //          / \
        //         4   5
        AdvancedTreeProblems t = new AdvancedTreeProblems();
        t.root = t.build(new int[]{1, 2, 4, -1, -1, 5, -1, -1, 3, -1, -1});

        t.kthLevel(t.root, 2); System.out.println(); // 2 3
        t.kthLevel(t.root, 3); System.out.println(); // 4 5

        System.out.println(t.lca(t.root, 4, 5).data); // 2
        System.out.println(t.lca(t.root, 4, 3).data); // 1

        System.out.println(t.minDistance(t.root, 4, 5)); // 2
        System.out.println(t.minDistance(t.root, 4, 3)); // 3

        t.count = 0;
        AdvancedTreeProblems.Node res = t.kthAncestor(t.root, 4, 1);
        System.out.println(res != null ? res.data : -1); // 2
        t.count = 0;
        res = t.kthAncestor(t.root, 4, 2);
        System.out.println(res != null ? res.data : -1); // 1

        AdvancedTreeProblems t2 = new AdvancedTreeProblems();
        t2.root = t2.build(new int[]{1, 2, 4, -1, -1, 5, -1, -1, 3, -1, -1});
        t2.toSumTree(t2.root);
        t2.inorder(t2.root); System.out.println(); // 0 9 0 11 0 0

        AdvancedTreeProblems t3 = new AdvancedTreeProblems();
        t3.root = t3.build(new int[]{1, 1, 1, -1, -1, 1, -1, -1, 1, -1, -1});
        System.out.println(t3.isUnivalued(t3.root)); // true
        System.out.println(t.isUnivalued(t.root));   // false

        AdvancedTreeProblems t4 = new AdvancedTreeProblems();
        t4.root = t4.build(new int[]{1, 2, 4, -1, -1, 5, -1, -1, 3, -1, -1});
        t4.invert(t4.root);
        t4.inorder(t4.root); System.out.println(); // 3 1 5 2 4

        AdvancedTreeProblems t5 = new AdvancedTreeProblems();
        t5.root = t5.build(new int[]{1, 2, 2, -1, -1, 2, -1, -1, 3, -1, 2, -1, -1});
        t5.root = t5.deleteLeaf(t5.root, 2);
        t5.inorder(t5.root); System.out.println(); // 1 3

        AdvancedTreeProblems t6 = new AdvancedTreeProblems();
        t6.root = t6.build(new int[]{1, 2, 4, -1, -1, -1, 3, 2, 4, -1, -1, -1, 4, -1, -1});
        List<AdvancedTreeProblems.Node> dups = t6.findDuplicates(t6.root);
        for (AdvancedTreeProblems.Node n : dups) System.out.print(n.data + " ");
        System.out.println(); // 4 2

        AdvancedTreeProblems t7 = new AdvancedTreeProblems();
        t7.root = t7.build(new int[]{-10, 9, -1, -1, 20, 15, -1, -1, 7, -1, -1});
        System.out.println(t7.maxPathSum(t7.root)); // 42

        // ── Subtree demo ──
        // Main tree: 1(2(4,5),3) ; Sub tree: 2(4,5)
        SubTreeProblems s1 = new SubTreeProblems();
        s1.root = s1.buildFromPreorder(new int[]{1, 2, 4, -1, -1, 5, -1, -1, 3, -1, -1});
        SubTreeProblems s2 = new SubTreeProblems();
        s2.root = s2.buildFromPreorder(new int[]{2, 4, -1, -1, 5, -1, -1});
        System.out.println(s1.isSubTree(s1.root, s2.root)); // true

        SubTreeProblems s3 = new SubTreeProblems();
        s3.root = s3.buildFromPreorder(new int[]{2, 4, 6, -1, -1, -1, -1});
        System.out.println(s1.isSubTree(s1.root, s3.root)); // false

        // ── Top view demo ──
        // Tree:             1
        //                  / \
        //                 2   3
        //                / \   \
        //               4   5   6
        // Top view: 4 2 1 3 6
        TopViewProblems tv = new TopViewProblems();
        tv.root = tv.buildFromPreorder(new int[]{1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1});
        System.out.println(tv.topView(tv.root)); // [4, 2, 1, 3, 6]
    }
}
