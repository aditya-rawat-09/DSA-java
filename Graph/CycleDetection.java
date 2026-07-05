import java.util.*;

public class CycleDetection {

    // ─── CYCLE DETECTION (Undirected) ────────────────────────────────────────────
    // DFS tracking parent — if a visited neighbor isn't the parent, back edge = cycle.
    // TC: O(V + E), SC: O(V)
    public boolean hasCycleUndirected(Map<Integer, List<Integer>> graph) {
        Set<Integer> visited = new HashSet<>();
        for (int node : graph.keySet())
            if (!visited.contains(node))
                if (dfsCycleUndirected(graph, node, -1, visited)) return true;
        return false;
    }

    private boolean dfsCycleUndirected(Map<Integer, List<Integer>> graph, int node,
                                        int parent, Set<Integer> visited) {
        visited.add(node);
        for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                if (dfsCycleUndirected(graph, neighbor, node, visited)) return true;
            } else if (neighbor != parent) return true;
        }
        return false;
    }

    // ─── CYCLE DETECTION (Directed) ───────────────────────────────────────────────
    // DFS with recursion stack — if we reach a node still in current path = cycle.
    // TC: O(V + E), SC: O(V)
    public boolean hasCycleDirected(Map<Integer, List<Integer>> graph) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> recStack = new HashSet<>();
        for (int node : graph.keySet())
            if (!visited.contains(node))
                if (dfsCycleDirected(graph, node, visited, recStack)) return true;
        return false;
    }

    private boolean dfsCycleDirected(Map<Integer, List<Integer>> graph, int node,
                                      Set<Integer> visited, Set<Integer> recStack) {
        visited.add(node);
        recStack.add(node);
        for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                if (dfsCycleDirected(graph, neighbor, visited, recStack)) return true;
            } else if (recStack.contains(neighbor)) return true;
        }
        recStack.remove(node);
        return false;
    }

    public static void main(String[] args) {
        CycleDetection cd = new CycleDetection();

        // ── Undirected: 0-1-2-0 (cycle) ──
        System.out.println("─── Cycle Detection (Undirected) ───");
        Map<Integer, List<Integer>> cg = new HashMap<>();
        cg.put(0, Arrays.asList(1, 2));
        cg.put(1, Arrays.asList(0, 2));
        cg.put(2, Arrays.asList(0, 1));
        System.out.println("Has cycle: " + cd.hasCycleUndirected(cg)); // true

        Map<Integer, List<Integer>> acyclic = new HashMap<>();
        acyclic.put(0, Arrays.asList(1));
        acyclic.put(1, Arrays.asList(0, 2));
        acyclic.put(2, Arrays.asList(1));
        System.out.println("Has cycle: " + cd.hasCycleUndirected(acyclic)); // false

        // ── Directed: 0→1→2→0 (cycle) ──
        System.out.println("─── Cycle Detection (Directed) ───");
        Map<Integer, List<Integer>> dcg = new HashMap<>();
        dcg.put(0, Arrays.asList(1));
        dcg.put(1, Arrays.asList(2));
        dcg.put(2, Arrays.asList(0));
        System.out.println("Has cycle: " + cd.hasCycleDirected(dcg)); // true

        Map<Integer, List<Integer>> dag = new HashMap<>();
        dag.put(0, Arrays.asList(1));
        dag.put(1, Arrays.asList(2));
        dag.put(2, new ArrayList<>());
        System.out.println("Has cycle: " + cd.hasCycleDirected(dag)); // false
    }
}
