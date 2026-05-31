import java.util.*;

public class InvertTree {

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

    // ─── INVERT BINARY TREE ──────────────────────────────────────────────────────
    // Swap left and right children at every node recursively.
    // TC: O(n), SC: O(h)
    public Node invert(Node root) {
        if (root == null) return null;
        Node temp   = root.left;
        root.left   = invert(root.right);
        root.right  = invert(temp);
        return root;
    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
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
            res.add(level);
        }
        return res;
    }

    public static void main(String[] args) {
        // Tree:       1          Inverted:    1
        //            / \                     / \
        //           2   3                   3   2
        //          / \                         / \
        //         4   5                       5   4
        InvertTree t = new InvertTree();
        t.root = t.build(new int[]{1, 2, 4, -1, -1, 5, -1, -1, 3, -1, -1});
        t.invert(t.root);
        System.out.println(t.levelOrder(t.root)); // [[1], [3, 2], [5, 4]]
    }
}
