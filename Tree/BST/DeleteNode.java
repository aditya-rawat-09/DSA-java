public class DeleteNode {

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

    // ─── DELETE NODE FROM BST ────────────────────────────────────────────────────
    // Case 1: no children  → return null
    // Case 2: one child    → return that child
    // Case 3: two children → replace with inorder successor (smallest in right subtree)
    // TC: O(h), SC: O(h)
    public Node delete(Node root, int val) {
        if (root == null) return null;
        if (val < root.data) { root.left  = delete(root.left,  val); }
        else if (val > root.data) { root.right = delete(root.right, val); }
        else {
            if (root.left  == null) return root.right; // case 1 & 2
            if (root.right == null) return root.left;  // case 2
            // case 3: find inorder successor
            Node successor = findMin(root.right);
            root.data  = successor.data;
            root.right = delete(root.right, successor.data);
        }
        return root;
    }

    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public static void main(String[] args) {
        // BST:       5
        //           / \
        //          3   7
        //         / \ / \
        //        2  4 6   8
        DeleteNode t = new DeleteNode();
        for (int v : new int[]{5, 3, 7, 2, 4, 6, 8}) t.root = t.insert(t.root, v);

        t.root = t.delete(t.root, 3); // delete node with two children
        t.inorder(t.root); // 2 4 5 6 7 8
        System.out.println();

        t.root = t.delete(t.root, 8); // delete leaf
        t.inorder(t.root); // 2 4 5 6 7
        System.out.println();
    }
}
