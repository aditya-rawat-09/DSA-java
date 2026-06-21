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

    // ─── SIZE OF LARGEST BST IN BINARY TREE ──────────────────────────────────────
    // Post-order: for each node collect (isBST, size, min, max).
    // If both subtrees are BSTs and current node satisfies BST property → valid BST.
    // TC: O(n), SC: O(h)
    int largestBSTSize = 0;

    public int largestBST(Node root) {
        largestBSTSize = 0;
        bstInfo(root);
        return largestBSTSize;
    }

    // returns int[]{isBST(1/0), size, min, max}
    private int[] bstInfo(Node node) {
        if (node == null) return new int[]{1, 0, Integer.MAX_VALUE, Integer.MIN_VALUE};
        int[] left  = bstInfo(node.left);
        int[] right = bstInfo(node.right);
        if (left[0] == 1 && right[0] == 1 && node.data > left[3] && node.data < right[2]) {
            int size = left[1] + right[1] + 1;
            largestBSTSize = Math.max(largestBSTSize, size);
            return new int[]{1, size, Math.min(node.data, left[2]), Math.max(node.data, right[3])};
        }
        return new int[]{0, 0, 0, 0};
    }

    // ─── MERGE TWO BSTs ───────────────────────────────────────────────────────────
    // Step 1: inorder of both BSTs → two sorted lists
    // Step 2: merge two sorted lists
    // Step 3: build balanced BST from merged sorted list
    // TC: O(m+n), SC: O(m+n)
    public Node mergeBSTs(Node r1, Node r2) {
        List<Integer> l1 = new ArrayList<>(), l2 = new ArrayList<>();
        collectInorder(r1, l1);
        collectInorder(r2, l2);
        List<Integer> merged = mergeSortedLists(l1, l2);
        int[] arr = merged.stream().mapToInt(i -> i).toArray();
        return sortedArrayToBST(arr, 0, arr.length - 1);
    }

    private List<Integer> mergeSortedLists(List<Integer> a, List<Integer> b) {
        List<Integer> res = new ArrayList<>();
        int i = 0, j = 0;
        while (i < a.size() && j < b.size())
            res.add(a.get(i) <= b.get(j) ? a.get(i++) : b.get(j++));
        while (i < a.size()) res.add(a.get(i++));
        while (j < b.size()) res.add(b.get(j++));
        return res;
    }

    // ─── RANGE SUM OF BST ─────────────────────────────────────────────────────────
    // Sum all nodes with values in [lo, hi].
    // Prune left if root <= lo, prune right if root >= hi.
    // TC: O(n), SC: O(h)
    public int rangeSum(Node root, int lo, int hi) {
        if (root == null) return 0;
        if (root.data < lo) return rangeSum(root.right, lo, hi);
        if (root.data > hi) return rangeSum(root.left,  lo, hi);
        return root.data + rangeSum(root.left, lo, hi) + rangeSum(root.right, lo, hi);
    }

    // ─── CLOSEST ELEMENT IN BST ───────────────────────────────────────────────────
    // Navigate BST like a search; track closest at each step.
    // TC: O(h), SC: O(1)
    public int closestElement(Node root, int target) {
        int closest = root.data;
        while (root != null) {
            if (Math.abs(root.data - target) < Math.abs(closest - target))
                closest = root.data;
            root = target < root.data ? root.left : root.right;
        }
        return closest;
    }

    // ─── KTH SMALLEST ELEMENT IN BST ─────────────────────────────────────────────
    // Inorder of BST = sorted order; kth element in inorder = kth smallest.
    // TC: O(n), SC: O(h)
    int kthCount = 0, kthResult = -1;

    public int kthSmallest(Node root, int k) {
        kthCount = 0; kthResult = -1;
        kthHelper(root, k);
        return kthResult;
    }

    private void kthHelper(Node node, int k) {
        if (node == null || kthCount >= k) return;
        kthHelper(node.left, k);
        kthCount++;
        if (kthCount == k) { kthResult = node.data; return; }
        kthHelper(node.right, k);
    }

    // ─── MAXIMUM SUM BST IN BINARY TREE ──────────────────────────────────────────
    // Post-order: for each valid BST subtree, track its sum.
    // Returns int[]{isBST, sum, min, max}.
    // TC: O(n), SC: O(h)
    int maxBSTSum = 0;

    public int maxSumBST(Node root) {
        maxBSTSum = 0;
        sumBSTInfo(root);
        return maxBSTSum;
    }

    private int[] sumBSTInfo(Node node) {
        if (node == null) return new int[]{1, 0, Integer.MAX_VALUE, Integer.MIN_VALUE};
        int[] left  = sumBSTInfo(node.left);
        int[] right = sumBSTInfo(node.right);
        if (left[0] == 1 && right[0] == 1 && node.data > left[3] && node.data < right[2]) {
            int sum = left[1] + right[1] + node.data;
            maxBSTSum = Math.max(maxBSTSum, sum);
            return new int[]{1, sum, Math.min(node.data, left[2]), Math.max(node.data, right[3])};
        }
        return new int[]{0, 0, 0, 0};
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
        BST t5 = new BST();
        for (int v : new int[]{1, 2, 3, 4, 5, 6, 7}) t5.root = t5.insert(t5.root, v);
        t5.root = t5.balanceBST(t5.root);
        t5.inorder(t5.root); System.out.println(); // 1 2 3 4 5 6 7

        // Largest BST in Binary Tree
        // Tree:      10
        //           /  \
        //          5    15
        //         / \     \
        //        1   8    7   ← right subtree not valid BST (7 < 15)
        // Largest BST = left subtree {1,5,8} → size 3
        BST t6 = new BST();
        t6.root         = t6.new Node(10);
        t6.root.left    = t6.new Node(5);
        t6.root.right   = t6.new Node(15);
        t6.root.left.left  = t6.new Node(1);
        t6.root.left.right = t6.new Node(8);
        t6.root.right.right = t6.new Node(7);
        System.out.println(t6.largestBST(t6.root)); // 3

        // Merge Two BSTs
        // BST1: 2,4,6   BST2: 1,3,5
        BST t7 = new BST();
        Node bst1 = null, bst2 = null;
        for (int v : new int[]{4, 2, 6}) bst1 = t7.insert(bst1, v);
        for (int v : new int[]{3, 1, 5}) bst2 = t7.insert(bst2, v);
        Node merged = t7.mergeBSTs(bst1, bst2);
        t7.inorder(merged); System.out.println(); // 1 2 3 4 5 6

        // Range Sum
        BST t8 = new BST();
        for (int v : new int[]{5, 3, 7, 2, 4, 6, 8}) t8.root = t8.insert(t8.root, v);
        System.out.println(t8.rangeSum(t8.root, 3, 7)); // 3+4+5+6+7 = 25

        // Closest Element
        System.out.println(t8.closestElement(t8.root, 0)); // 2
        System.out.println(t8.closestElement(t8.root, 5)); // 5
        System.out.println(t8.closestElement(t8.root, 9)); // 8

        // Kth Smallest
        System.out.println(t8.kthSmallest(t8.root, 1)); // 2
        System.out.println(t8.kthSmallest(t8.root, 3)); // 4
        System.out.println(t8.kthSmallest(t8.root, 5)); // 6

        // Maximum Sum BST in Binary Tree
        BST t9 = new BST();
        t9.root         = t9.new Node(1);
        t9.root.left    = t9.new Node(4);
        t9.root.right   = t9.new Node(3);
        t9.root.left.left  = t9.new Node(2);
        t9.root.left.right = t9.new Node(4);
        t9.root.right.left  = t9.new Node(2);
        t9.root.right.right = t9.new Node(5);
        t9.root.right.right.left  = t9.new Node(4);
        t9.root.right.right.right = t9.new Node(6);
        System.out.println(t9.maxSumBST(t9.root)); // 20  (subtree rooted at 3: 2+3+5+4+6)
    }
}
