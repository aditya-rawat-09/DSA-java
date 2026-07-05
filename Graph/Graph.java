import java.util.*;

public class Graph {

    // ─── GRAPH CREATION ───────────────────────────────────────────────────────────
    // Adjacency list for unweighted graph.
    // Weighted adjacency list: int[] = {neighbor, weight}
    // TC: O(1) per addEdge, SC: O(V + E)
    Map<Integer, List<Integer>> adjList = new HashMap<>();
    Map<Integer, List<int[]>> weightedAdj = new HashMap<>();

    public void addEdge(int u, int v, boolean directed) {
        adjList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        if (!directed)
            adjList.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
    }

    public void addWeightedEdge(int u, int v, int w, boolean directed) {
        weightedAdj.computeIfAbsent(u, k -> new ArrayList<>()).add(new int[]{v, w});
        if (!directed)
            weightedAdj.computeIfAbsent(v, k -> new ArrayList<>()).add(new int[]{u, w});
    }

    public void display() {
        for (Map.Entry<Integer, List<Integer>> e : adjList.entrySet())
            System.out.println(e.getKey() + " -> " + e.getValue());
    }

    public static void main(String[] args) {
        // ── Unweighted Graph ──
        //   0 -- 1 -- 2
        //   |         |
        //   3 ------- 4
        Graph g = new Graph();
        g.addEdge(0, 1, false);
        g.addEdge(1, 2, false);
        g.addEdge(0, 3, false);
        g.addEdge(2, 4, false);
        g.addEdge(3, 4, false);
        System.out.println("─── Adjacency List ───");
        g.display();

        // ── Weighted Graph ──
        //   0 -(4)- 1 -(1)- 2
        Graph wg = new Graph();
        wg.addWeightedEdge(0, 1, 4, false);
        wg.addWeightedEdge(1, 2, 1, false);
        System.out.println("─── Weighted Adj ───");
        for (var e : wg.weightedAdj.entrySet())
            System.out.println(e.getKey() + " -> " + Arrays.deepToString(e.getValue().toArray()));
    }
}
