import java.util.*;

public class UnionFind {

    // ─── UNION FIND (Disjoint Set Union) ─────────────────────────────────────────
    // Efficiently tracks which elements belong to the same set.
    // Path compression (find) + Union by rank → nearly O(1) per operation.
    // TC: O(α(N)) amortized per find/union, SC: O(N)
    int[] parent, rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    // Find root with path compression
    public int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    // Union by rank — attach smaller tree under larger tree
    public boolean union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) return false; // already in same set → cycle
        if (rank[pa] < rank[pb]) parent[pa] = pb;
        else if (rank[pa] > rank[pb]) parent[pb] = pa;
        else { parent[pb] = pa; rank[pa]++; }
        return true;
    }

    public boolean connected(int a, int b) {
        return find(a) == find(b);
    }

    public static void main(String[] args) {
        // ── Basic Union-Find ──
        System.out.println("─── Union Find ───");
        UnionFind uf = new UnionFind(6);
        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(3, 4);
        System.out.println("0-2 connected: " + uf.connected(0, 2)); // true
        System.out.println("0-3 connected: " + uf.connected(0, 3)); // false
        System.out.println("3-4 connected: " + uf.connected(3, 4)); // true

        // ── Cycle Detection using Union-Find ──
        // Adding edge 0-2 when 0 and 2 are already connected → cycle
        System.out.println("─── Cycle Detection via Union-Find ───");
        UnionFind uf2 = new UnionFind(3);
        System.out.println("union(0,1): " + uf2.union(0, 1)); // true
        System.out.println("union(1,2): " + uf2.union(1, 2)); // true
        System.out.println("union(0,2): " + uf2.union(0, 2)); // false → cycle
    }
}
