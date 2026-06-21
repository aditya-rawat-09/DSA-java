package trees;

public class AVLTree {

    static class Node {
        int data, height;
        Node left, right;
        Node(int data) { this.data = data; this.height = 1; }
    }

    Node root;

    private int height(Node n)      { return n == null ? 0 : n.height; }
    private int balanceFactor(Node n) { return n == null ? 0 : height(n.left) - height(n.right); }

    private void updateHeight(Node n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    // ─── RIGHT ROTATE ─────────────────────────────────────────────────────────────
    //       y                x
    //      / \     →        / \
    //     x   T3           T1   y
    //    / \                   / \
    //   T1  T2                T2  T3
    private Node rotateRight(Node y) {
        Node x = y.left, T2 = x.right;
        x.right = y; y.left = T2;
        updateHeight(y); updateHeight(x);
        return x;
    }

    // ─── LEFT ROTATE ──────────────────────────────────────────────────────────────
    //     x                  y
    //    / \     →           / \
    //   T1   y              x   T3
    //       / \            / \
    //      T2  T3         T1  T2
    private Node rotateLeft(Node x) {
        Node y = x.right, T2 = y.left;
        y.left = x; x.right = T2;
        updateHeight(x); updateHeight(y);
        return y;
    }

    // ─── INSERT ───────────────────────────────────────────────────────────────────
    // Standard BST insert + rebalance on the way back up.
    // TC: O(log n), SC: O(log n)
    public Node insert(Node node, int val) {
        if (node == null) return new Node(val);
        if (val < node.data) node.left  = insert(node.left,  val);
        else if (val > node.data) node.right = insert(node.right, val);
        else return node; // duplicates not allowed

        updateHeight(node);
        int bf = balanceFactor(node);

        // LL case
        if (bf > 1 && val < node.left.data)  return rotateRight(node);
        // RR case
        if (bf < -1 && val > node.right.data) return rotateLeft(node);
        // LR case
        if (bf > 1 && val > node.left.data)  { node.left = rotateLeft(node.left); return rotateRight(node); }
        // RL case
        if (bf < -1 && val < node.right.data) { node.right = rotateRight(node.right); return rotateLeft(node); }

        return node;
    }

    // ─── INORDER ──────────────────────────────────────────────────────────────────
    public void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }

    public static void main(String[] args) {
        AVLTree t = new AVLTree();
        // insert in sorted order — without AVL this would be a skewed tree
        for (int v : new int[]{10, 20, 30, 40, 50, 25}) t.root = t.insert(t.root, v);
        t.inorder(t.root); System.out.println(); // 10 20 25 30 40 50

        // root should be 30 (balanced)
        System.out.println("Root: " + t.root.data);   // 30
        System.out.println("Height: " + t.root.height); // 3
    }
}
