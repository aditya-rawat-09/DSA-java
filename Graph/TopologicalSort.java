import java.util.*;

public class TopologicalSort {

    // ─── TOPOLOGICAL SORT (DFS) ───────────────────────────────────────────────────
    // Post-order DFS: push node to stack after visiting all its neighbors.
    // Reverse of finish order = topological order. Only valid for DAGs.
    // TC: O(V + E), SC: O(V)
    public List<Integer> topoSortDFS(Map<Integer, List<Integer>> graph) {
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int node : graph.keySet())
            if (!visited.contains(node))
                dfsHelper(graph, node, visited, stack);
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) result.add(stack.pop());
        return result;
    }

    private void dfsHelper(Map<Integer, List<Integer>> graph, int node,
                            Set<Integer> visited, Deque<Integer> stack) {
        visited.add(node);
        for (int neighbor : graph.getOrDefault(node, new ArrayList<>()))
            if (!visited.contains(neighbor))
                dfsHelper(graph, neighbor, visited, stack);
        stack.push(node);
    }

    // ─── TOPOLOGICAL SORT (BFS / Kahn's Algorithm) ───────────────────────────────
    // Repeatedly remove nodes with in-degree 0.
    // If result size < V → graph has a cycle.
    // TC: O(V + E), SC: O(V)
    public List<Integer> topoSortBFS(Map<Integer, List<Integer>> graph) {
        Map<Integer, Integer> inDegree = new HashMap<>();
        for (int node : graph.keySet()) inDegree.put(node, 0);
        for (int node : graph.keySet())
            for (int neighbor : graph.get(node))
                inDegree.merge(neighbor, 1, Integer::sum);

        Queue<Integer> queue = new LinkedList<>();
        for (int node : inDegree.keySet())
            if (inDegree.get(node) == 0) queue.offer(node);

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                inDegree.merge(neighbor, -1, Integer::sum);
                if (inDegree.get(neighbor) == 0) queue.offer(neighbor);
            }
        }
        return result; // size < V means cycle exists
    }

    public static void main(String[] args) {
        // Graph:  5 → 0 ← 4
        //         5 → 2 → 3 → 1
        //                 4 → 1
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(5, Arrays.asList(0, 2));
        graph.put(4, Arrays.asList(0, 1));
        graph.put(2, Arrays.asList(3));
        graph.put(3, Arrays.asList(1));
        graph.put(0, new ArrayList<>());
        graph.put(1, new ArrayList<>());

        TopologicalSort ts = new TopologicalSort();

        System.out.println("─── Topological Sort (DFS) ───");
        System.out.println(ts.topoSortDFS(graph)); // e.g. [5, 4, 2, 3, 1, 0]

        System.out.println("─── Topological Sort (Kahn's BFS) ───");
        System.out.println(ts.topoSortBFS(graph)); // e.g. [4, 5, 0, 2, 3, 1]
    }
}
