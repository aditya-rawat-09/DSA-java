import java.util.*;

public class BST {

    Node root;

    class Node {
        int data;
        Node left, right;
        Node(int data) { this.data = data; }
    }

    // ─── INSERT INTO BST ─────────────────────────────────────────────────────────
    // Values < node go left, values > node go right.
    // TC: O(h), SC: O(h)
    public Node insert(Node root, int val) {
        if (root == null) return new Node(val);
        if (val < root.data) root.left  = insert(root.left,  val);
        else if (val > root.data) root.right = insert(root.right, val);
        return root;
    }

    // ─── INORDER TRAVERSAL ───────────────────────────────────────────────────────
    // Inorder of BST always gives sorted (ascending) output.
    // TC: O(n), SC: O(h)
    public void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    // ─── SEARCH IN BST ───────────────────────────────────────────────────────────
    // Use BST property: go left if val < root, right if val > root.
    // TC: O(h), SC: O(h)
    public Node search(Node root, int val) {
        if (root == null || root.data == val) return root;
        if (val < root.data) return search(root.left,  val);
        return search(root.right, val);
    }

    // ─── DELETE NODE FROM BST ────────────────────────────────────────────────────
    // Case 1: no children  → return null
    // Case 2: one child    → return that child
    // Case 3: two children → replace with inorder successor (smallest in right subtree)
    // TC: O(h), SC: O(h)
    public Node delete(Node root, int val) {
        if (root == null) return null;
        if (val < root.data) root.left  = delete(root.left,  val);
        else if (val > root.data) root.right = delete(root.right, val);
        else {
            if (root.left  == null) return root.right; // case 1 & 2
            if (root.right == null) return root.left;  // case 2
            Node successor = findMin(root.right);      // case 3
            root.data  = successor.data;
            root.right = delete(root.right, successor.data);
        }
        return root;
    }

    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // ─── PRINT NODES IN RANGE [lo, hi] ───────────────────────────────────────────
    // Skip left subtree if root < lo, skip right subtree if root > hi.
    // TC: O(n), SC: O(h)
    public void printInRange(Node root, int lo, int hi) {
        if (root == null) return;
        if (root.data >= lo) printInRange(root.left,  lo, hi);
        if (root.data >= lo && root.data <= hi) System.out.print(root.data + " ");
        if (root.data <= hi) printInRange(root.right, lo, hi);
    }

    // ─── ROOT TO LEAF PATHS ───────────────────────────────────────────────────────
    // DFS: carry path list, print when leaf is reached, backtrack after.
    // TC: O(n), SC: O(h)
    public void printPaths(Node root, List<Integer> path) {
        if (root == null) return;
        path.add(root.data);
        if (root.left == null && root.right == null) {
            System.out.println(path);
        } else {
            printPaths(root.left,  path);
            printPaths(root.right, path);
        }
        path.remove(path.size() - 1); // backtrack
    }

    // ─── VALID BST ───────────────────────────────────────────────────────────────
    // Pass min/max bounds down; every node must satisfy min < node.data < max.
    // TC: O(n), SC: O(h)
    public boolean isValidBST(Node root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(Node node, long min, long max) {
        if (node == null) return true;
        if (node.data <= min || node.data >= max) return false;
        return validate(node.left,  min, node.data)
            && validate(node.right, node.data, max);
    }

    // ─── MIRROR BST ──────────────────────────────────────────────────────────────
    // Swap left and right at every node → inorder gives descending order.
    // TC: O(n), SC: O(h)
    public Node mirror(Node root) {
        if (root == null) return null;
        Node temp  = root.left;
        root.left  = mirror(root.right);
        root.right = mirror(temp);
        return root;
    }

    // ─── SORTED ARRAY TO BALANCED BST ────────────────────────────────────────────
    // Pick mid as root, recurse on left and right halves → guarantees balanced tree.
    // Array must be sorted; if unsorted, sort it first.
    // TC: O(n), SC: O(log n)
    public Node sortedArrayToBST(int[] arr, int lo, int hi) {
        if (lo > hi) return null;
        int mid    = lo + (hi - lo) / 2;
        Node node  = new Node(arr[mid]);
        node.left  = sortedArrayToBST(arr, lo,      mid - 1);
        node.right = sortedArrayToBST(arr, mid + 1, hi);
        return node;
    }

    // ─── UNBALANCED BST TO BALANCED BST ──────────────────────────────────────────
    // Step 1: collect inorder (sorted) into a list  → O(n)
    // Step 2: build balanced BST from sorted list   → O(n)
    // TC: O(n), SC: O(n)
    public Node balanceBST(Node root) {
        List<Integer> sorted = new ArrayList<>();
        collectInorder(root, sorted);
        int[] arr = sorted.stream().mapToInt(i -> i).toArray();
        return sortedArrayToBST(arr, 0, arr.length - 1);
    }

    private void collectInorder(Node node, List<Integer> list) {
        if (node == null) return;
        collectInorder(node.left,  list);
        list.add(node.data);
        collectInorder(node.right, list);
    }

    public static void main(String[] args) {
        // BST:       5
        //           / \
        //          3   7
        //         / \ / \
        //        2  4 6   8
        BST t = new BST();
        for (int v : new int[]{5, 3, 7, 2, 4, 6, 8}) t.root = t.insert(t.root, v);

        // Inorder
        t.inorder(t.root); System.out.println(); // 2 3 4 5 6 7 8

        // Search
        Node res = t.search(t.root, 4);
        System.out.println(res != null ? "Found: " + res.data : "Not found"); // Found: 4
        res = t.search(t.root, 9);
        System.out.println(res != null ? "Found: " + res.data : "Not found"); // Not found

        // Delete
        t.root = t.delete(t.root, 3);
        t.inorder(t.root); System.out.println(); // 2 4 5 6 7 8

        // Print in range
        t.printInRange(t.root, 3, 7); System.out.println(); // 4 5 6 7

        // Root to leaf paths
        BST t2 = new BST();
        for (int v : new int[]{5, 3, 7, 2, 4, 6, 8}) t2.root = t2.insert(t2.root, v);
        t2.printPaths(t2.root, new ArrayList<>());
        // [5, 3, 2]  [5, 3, 4]  [5, 7, 6]  [5, 7, 8]

        // Valid BST
        System.out.println(t2.isValidBST(t2.root)); // true
        t2.root.left.data = 10;
        System.out.println(t2.isValidBST(t2.root)); // false

        // Mirror BST
        BST t3 = new BST();
        for (int v : new int[]{5, 3, 7, 2, 4, 6, 8}) t3.root = t3.insert(t3.root, v);
        t3.mirror(t3.root);
        t3.inorder(t3.root); System.out.println(); // 8 7 6 5 4 3 2

        // Sorted Array to Balanced BST
        // Array: [1, 2, 3, 4, 5, 6, 7] → balanced BST with root = 4
        BST t4 = new BST();
        int[] sorted = {1, 2, 3, 4, 5, 6, 7};
        t4.root = t4.sortedArrayToBST(sorted, 0, sorted.length - 1);
        t4.inorder(t4.root); System.out.println(); // 1 2 3 4 5 6 7

        // Unbalanced BST to Balanced BST
        // Skewed BST: 1->2->3->4->5->6->7 (inserted in order)
        BST t5 = new BST();
        for (int v : new int[]{1, 2, 3, 4, 5, 6, 7}) t5.root = t5.insert(t5.root, v);
        t5.root = t5.balanceBST(t5.root);
        t5.inorder(t5.root); System.out.println(); // 1 2 3 4 5 6 7
    }
}
