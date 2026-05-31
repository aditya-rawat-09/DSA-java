import java.util.*;

public class DuplicateSubtree {

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

    // ─── FIND DUPLICATE SUBTREES ─────────────────────────────────────────────────
    // Serialize each subtree as a string; use a map to count occurrences.
    // When a serialization is seen for the 2nd time, that subtree is a duplicate.
    // TC: O(n^2) due to string concat, SC: O(n^2)
    public List<Node> findDuplicates(Node root) {
        List<Node> result = new HashMap<String, Integer>() {{ }}.isEmpty() ? new ArrayList<>() : null;
        result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        serialize(root, map, result);
        return result;
    }

    private String serialize(Node node, Map<String, Integer> map, List<Node> result) {
        if (node == null) return "#";
        String serial = node.data + "," + serialize(node.left, map, result)
                                  + "," + serialize(node.right, map, result);
        map.put(serial, map.getOrDefault(serial, 0) + 1);
        if (map.get(serial) == 2) result.add(node); // add once when duplicate found
        return serial;
    }

    public static void main(String[] args) {
        // Tree:         1
        //              / \
        //             2   3
        //            /   / \
        //           4   2   4
        //              /
        //             4
        // Duplicate subtrees: [2->4] and [4]
        DuplicateSubtree t = new DuplicateSubtree();
        t.root = t.build(new int[]{1, 2, 4, -1, -1, -1, 3, 2, 4, -1, -1, -1, 4, -1, -1});
        List<Node> dups = t.findDuplicates(t.root);
        for (DuplicateSubtree.Node n : dups) {
            System.out.println(n.data); // 4, 2
        }
    }
}
