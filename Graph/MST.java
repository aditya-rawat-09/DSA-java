import java.util.*;

public class MST {

    // ─── PRIM'S ALGORITHM ─────────────────────────────────────────────────────────
    // Greedy: grow MST one edge at a time by always picking the min-weight edge
    // that connects a visited node to an unvisited node.
    // TC: O((V + E) log V), SC: O(V)
    public int primsMST(Map<Integer, List<int[]>> graph, int V) {
        boolean[] inMST = new boolean[V];
        // PQ: {weight, node}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, 0});
        int totalWeight = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int w = curr[0], node = curr[1];
            if (inMST[node]) continue;
            inMST[node] = true;
            totalWeight += w;
            for (int[] edge : graph.getOrDefault(node, new ArrayList<>())) {
                if (!inMST[edge[0]])
                    pq.offer(new int[]{edge[1], edge[0]});
            }
        }
        return totalWeight;
    }

    // ─── KRUSKAL'S ALGORITHM ──────────────────────────────────────────────────────
    // Sort all edges by weight, add edge if it doesn't form a cycle (Union-Find).
    // TC: O(E log E), SC: O(V)
    public int kruskalMST(int V, int[][] edges) {
        // edges: {u, v, weight}
        Arrays.sort(edges, Comparator.comparingInt(a -> a[2]));
        int[] parent = new int[V], rank = new int[V];
        for (int i = 0; i < V; i++) parent[i] = i;

        int totalWeight = 0, edgesUsed = 0;
        for (int[] e : edges) {
            int pu = find(parent, e[0]), pv = find(parent, e[1]);
            if (pu != pv) {
                union(parent, rank, pu, pv);
                totalWeight += e[2];
                edgesUsed++;
                if (edgesUsed == V - 1) break;
            }
        }
        return totalWeight;
    }

    // ─── CONNECTING CITIES WITH MINIMUM COST ──────────────────────────────────────
    // Same as MST — find min cost to connect all cities.
    // Uses Kruskal's: sort edges, union-find to avoid cycles.
    // TC: O(E log E), SC: O(V)
    public int connectingCities(int n, int[][] connections) {
        // connections: {city1, city2, cost}
        Arrays.sort(connections, Comparator.comparingInt(a -> a[2]));
        int[] parent = new int[n + 1], rank = new int[n + 1];
        for (int i = 0; i <= n; i++) parent[i] = i;

        int totalCost = 0, edgesUsed = 0;
        for (int[] c : connections) {
            int pu = find(parent, c[0]), pv = find(parent, c[1]);
            if (pu != pv) {
                union(parent, rank, pu, pv);
                totalCost += c[2];
                edgesUsed++;
                if (edgesUsed == n - 1) return totalCost;
            }
        }
        return -1; // not all cities connected
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) parent[x] = find(parent, parent[x]); // path compression
        return parent[x];
    }

    private void union(int[] parent, int[] rank, int a, int b) {
        if (rank[a] < rank[b]) parent[a] = b;
        else if (rank[a] > rank[b]) parent[b] = a;
        else { parent[b] = a; rank[a]++; }
    }

    public static void main(String[] args) {
        MST mst = new MST();

        // ── Prim's MST ──
        //   0 -(2)- 1 -(3)- 2
        //   |       |
        //  (6)     (8)
        //   |       |
        //   3 -(5)- 4
        System.out.println("─── Prim's MST ───");
        Map<Integer, List<int[]>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(new int[]{1,2}, new int[]{3,6}));
        graph.put(1, Arrays.asList(new int[]{0,2}, new int[]{2,3}, new int[]{4,8}));
        graph.put(2, Arrays.asList(new int[]{1,3}));
        graph.put(3, Arrays.asList(new int[]{0,6}, new int[]{4,5}));
        graph.put(4, Arrays.asList(new int[]{1,8}, new int[]{3,5}));
        System.out.println("MST weight: " + mst.primsMST(graph, 5)); // 16

        // ── Kruskal's MST ──
        System.out.println("─── Kruskal's MST ───");
        int[][] edges = {{0,1,2},{1,2,3},{0,3,6},{3,4,5},{1,4,8}};
        System.out.println("MST weight: " + mst.kruskalMST(5, edges)); // 16

        // ── Connecting Cities ──
        // 4 cities, connections: 1-2(3), 3-4(4), 2-3(5), 1-4(8)
        System.out.println("─── Connecting Cities ───");
        int[][] connections = {{1,2,3},{3,4,4},{2,3,5},{1,4,8}};
        System.out.println("Min cost: " + mst.connectingCities(4, connections)); // 12
        // impossible case
        int[][] noConn = {{1,2,3}};
        System.out.println("Min cost: " + mst.connectingCities(4, noConn)); // -1
    }
}
