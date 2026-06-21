package trees;

import java.util.*;

public class BinaryTree {

    Node root;

    class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }
    }

    // ─── BUILD TREE FROM PREORDER ────────────────────────────────────────────────
    // preorder array with -1 as null marker: [1, 2, 4, -1, -1, 5, -1, -1, 3, -1, -1]
    // TC: O(n), SC: O(n)
    int idx = 0;
    public Node buildFromPreorder(int[] arr) {
        if (idx >= arr.length || arr[idx] == -1) { idx++; return null; }
        Node node = new Node(arr[idx++]);
        node.left  = buildFromPreorder(arr);
        node.right = buildFromPreorder(arr);
        return node;
    }

    // ─── LEVEL ORDER TRAVERSAL (BFS) ─────────────────────────────────────────────
    // visit nodes level by level using a queue
    // TC: O(n), SC: O(n)
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node curr = q.poll();
                level.add(curr.data);
                if (curr.left  != null) q.offer(curr.left);
                if (curr.right != null) q.offer(curr.right);
            }
            result.add(level);
        }
        return result;
    }

    // ─── HEIGHT OF TREE ──────────────────────────────────────────────────────────
    // height = longest path from root to any leaf
    // TC: O(n), SC: O(h)
    public int height(Node root) {
        if (root == null) return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }

    // ─── COUNT OF NODES ──────────────────────────────────────────────────────────
    // TC: O(n), SC: O(h)
    public int countNodes(Node root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    // ─── SUM OF NODES ────────────────────────────────────────────────────────────
    // TC: O(n), SC: O(h)
    public int sumNodes(Node root) {
        if (root == null) return 0;
        return root.data + sumNodes(root.left) + sumNodes(root.right);
    }

    // ─── DIAMETER OF TREE ────────────────────────────────────────────────────────
    // diameter = longest path between any two nodes (may not pass through root)
    // at each node: diameter candidate = height(left) + height(right)
    // TC: O(n), SC: O(h)
    int diameter = 0;
    public int diameter(Node root) {
        diameterHelper(root);
        return diameter;
    }
    private int diameterHelper(Node node) {
        if (node == null) return 0;
        int lh = diameterHelper(node.left);
        int rh = diameterHelper(node.right);
        diameter = Math.max(diameter, lh + rh); // update global max
        return 1 + Math.max(lh, rh);
    }

    public static void main(String[] args) {
        BinaryTree t = new BinaryTree();

        // build tree:       1
        //                  / \
        //                 2   3
        //                / \
        //               4   5
        int[] preorder = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, -1};
        t.root = t.buildFromPreorder(preorder);

        // Level order
        System.out.println(t.levelOrder(t.root)); // [[1], [2, 3], [4, 5]]

        // Height
        System.out.println(t.height(t.root));      // 3

        // Count
        System.out.println(t.countNodes(t.root));  // 5

        // Sum
        System.out.println(t.sumNodes(t.root));    // 15

        // Diameter
        System.out.println(t.diameter(t.root));    // 4 (path: 4->2->1->3 or 5->2->1->3)
    }
}
