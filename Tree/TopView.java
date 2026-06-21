import java.util.*;

public class TopView {

    Node root;

    class Node {
        int data;
        Node left, right;
        Node(int data) { this.data = data; }
    }

    int idx = 0;
    public Node buildFromPreorder(int[] arr) {
        if (idx >= arr.length || arr[idx] == -1) { idx++; return null; }
        Node node = new Node(arr[idx++]);
        node.left  = buildFromPreorder(arr);
        node.right = buildFromPreorder(arr);
        return node;
    }

    // ─── TOP VIEW ────────────────────────────────────────────────────────────────
    // Top view = first node visible at each horizontal distance (HD) from root
    // Approach: BFS with (node, HD) pair; store first node seen at each HD
    // TC: O(n log n), SC: O(n)
    public List<Integer> topView(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        // HD -> first node data seen at that HD
        TreeMap<Integer, Integer> hdMap = new TreeMap<>();
        Queue<int[]> q = new LinkedList<>();   // [node reference via index trick — use wrapper]
        topViewBFS(root, hdMap);

        result.addAll(hdMap.values());
        return result;
    }

    private void topViewBFS(Node root, TreeMap<Integer, Integer> hdMap) {
        // pair: node + horizontal distance
        Queue<Object[]> q = new LinkedList<>();
        q.offer(new Object[]{root, 0});

        while (!q.isEmpty()) {
            Object[] pair = q.poll();
            Node node = (Node) pair[0];
            int hd   = (int) pair[1];

            // only store the FIRST node at each HD (top-most)
            hdMap.putIfAbsent(hd, node.data);

            if (node.left  != null) q.offer(new Object[]{node.left,  hd - 1});
            if (node.right != null) q.offer(new Object[]{node.right, hd + 1});
        }
    }

    public static void main(String[] args) {
        // Tree:             1
        //                  / \
        //                 2   3
        //                / \   \
        //               4   5   6
        // Top view: 4 2 1 3 6
        TopView t = new TopView();
        int[] preorder = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
        t.root = t.buildFromPreorder(preorder);

        System.out.println(t.topView(t.root)); // [4, 2, 1, 3, 6]
    }
}
