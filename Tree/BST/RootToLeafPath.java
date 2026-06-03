import java.util.*;

public class RootToLeafPath {

    Node root;

    class Node {
        int data;
        Node left, right;
        Node(int data) { this.data = data; }
    }

    public Node insert(Node root, int val) {
        if (root == null) return new Node(val);
        if (val < root.data) root.left  = insert(root.left,  val);
        else if (val > root.data) root.right = insert(root.right, val);
        return root;
    }

    // ─── ROOT TO LEAF PATHS ───────────────────────────────────────────────────────
    // DFS: carry path list, print when leaf is reached.
    // TC: O(n), SC: O(h)
    public void printPaths(Node root, List<Integer> path) {
        if (root == null) return;
        path.add(root.data);
        if (root.left == null && root.right == null) {
            System.out.println(path); // leaf reached → print path
        } else {
            printPaths(root.left,  path);
            printPaths(root.right, path);
        }
        path.remove(path.size() - 1); // backtrack
    }

    public static void main(String[] args) {
        // BST:       5
        //           / \
        //          3   7
        //         / \ / \
        //        2  4 6   8
        RootToLeafPath t = new RootToLeafPath();
        for (int v : new int[]{5, 3, 7, 2, 4, 6, 8}) t.root = t.insert(t.root, v);

        t.printPaths(t.root, new ArrayList<>());
        // [5, 3, 2]
        // [5, 3, 4]
        // [5, 7, 6]
        // [5, 7, 8]
    }
}
