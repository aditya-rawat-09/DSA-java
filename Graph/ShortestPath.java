import java.util.*;

public class ShortestPath {

    // ─── DIJKSTRA'S ALGORITHM ─────────────────────────────────────────────────────
    // Greedy: always expand the unvisited node with smallest known distance.
    // Uses a min-heap (PriorityQueue). Does NOT work with negative weights.
    // TC: O((V + E) log V), SC: O(V)
    public Map<Integer, Integer> dijkstra(Map<Integer, List<int[]>> graph, int src) {
        Map<Integer, Integer> dist = new HashMap<>();
        for (int node : graph.keySet()) dist.put(node, Integer.MAX_VALUE);
        dist.put(src, 0);

        // PQ: {distance, node}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, src});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int d = curr[0], node = curr[1];
            if (d > dist.getOrDefault(node, Integer.MAX_VALUE)) continue; // stale entry
            for (int[] edge : graph.getOrDefault(node, new ArrayList<>())) {
                int neighbor = edge[0], weight = edge[1];
                int newDist = dist.get(node) + weight;
                if (newDist < dist.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    dist.put(neighbor, newDist);
                    pq.offer(new int[]{newDist, neighbor});
                }
            }
        }
        return dist;
    }

    // ─── BELLMAN-FORD ALGORITHM ───────────────────────────────────────────────────
    // Relax all edges V-1 times. Works with negative weights.
    // Detects negative weight cycles on the V-th relaxation.
    // TC: O(V * E), SC: O(V)
    public Map<Integer, Integer> bellmanFord(Map<Integer, List<int[]>> graph, int src, int V) {
        Map<Integer, Integer> dist = new HashMap<>();
        for (int node : graph.keySet()) dist.put(node, Integer.MAX_VALUE);
        dist.put(src, 0);

        // Collect all edges: {u, v, w}
        List<int[]> edges = new ArrayList<>();
        for (int u : graph.keySet())
            for (int[] edge : graph.get(u))
                edges.add(new int[]{u, edge[0], edge[1]});

        // Relax V-1 times
        for (int i = 0; i < V - 1; i++)
            for (int[] e : edges)
                if (dist.getOrDefault(e[0], Integer.MAX_VALUE) != Integer.MAX_VALUE)
                    if (dist.get(e[0]) + e[2] < dist.getOrDefault(e[1], Integer.MAX_VALUE))
                        dist.put(e[1], dist.get(e[0]) + e[2]);

        // V-th relaxation: detect negative cycle
        for (int[] e : edges)
            if (dist.getOrDefault(e[0], Integer.MAX_VALUE) != Integer.MAX_VALUE)
                if (dist.get(e[0]) + e[2] < dist.getOrDefault(e[1], Integer.MAX_VALUE)) {
                    System.out.println("Negative weight cycle detected!");
                    return null;
                }
        return dist;
    }

    public static void main(String[] args) {
        ShortestPath sp = new ShortestPath();

        // ── Dijkstra ──
        //   0 -(4)- 1 -(1)- 2
        //   |               |
        //  (2)             (5)
        //   |               |
        //   3 -----(8)----- 4
        System.out.println("─── Dijkstra from 0 ───");
        Map<Integer, List<int[]>> wg = new HashMap<>();
        wg.put(0, Arrays.asList(new int[]{1, 4}, new int[]{3, 2}));
        wg.put(1, Arrays.asList(new int[]{0, 4}, new int[]{2, 1}));
        wg.put(2, Arrays.asList(new int[]{1, 1}, new int[]{4, 5}));
        wg.put(3, Arrays.asList(new int[]{0, 2}, new int[]{4, 8}));
        wg.put(4, Arrays.asList(new int[]{2, 5}, new int[]{3, 8}));
        System.out.println(sp.dijkstra(wg, 0)); // {0=0, 1=4, 2=5, 3=2, 4=10}

        // ── Bellman-Ford ──
        //   0 →(6) 1 →(5) 3
        //   0 →(7) 2 →(-3) 3
        //   1 →(-2) 2
        System.out.println("─── Bellman-Ford from 0 ───");
        Map<Integer, List<int[]>> bg = new HashMap<>();
        bg.put(0, Arrays.asList(new int[]{1, 6}, new int[]{2, 7}));
        bg.put(1, Arrays.asList(new int[]{2, -2}, new int[]{3, 5}));
        bg.put(2, Arrays.asList(new int[]{3, -3}));
        bg.put(3, new ArrayList<>());
        System.out.println(sp.bellmanFord(bg, 0, 4)); // {0=0, 1=6, 2=4, 3=1}
    }
}
