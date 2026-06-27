import java.util.*;

public class BFS {

    // ─── BFS TRAVERSAL ────────────────────────────────────────────────────────────
    // Level-order traversal; returns nodes in BFS order.
    // TC: O(V + E), SC: O(V)
    public List<Integer> bfsTraversal(Map<Integer, List<Integer>> graph, int src) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(src);
        visited.add(src);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return result;
    }

    // ─── SHORTEST PATH (BFS) ──────────────────────────────────────────────────────
    // Returns shortest distance from src to target in an unweighted graph.
    // BFS guarantees shortest path in unweighted graphs.
    // TC: O(V + E), SC: O(V)
    public int shortestPath(Map<Integer, List<Integer>> graph, int src, int target) {
        if (src == target) return 0;
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(src);
        visited.add(src);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                int node = queue.poll();
                for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                    if (neighbor == target) return level;
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return -1; // not reachable
    }

    public static void main(String[] args) {
        // Graph:  0 -- 1 -- 2
        //              |
        //              3 -- 4
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1));
        graph.put(1, Arrays.asList(0, 2, 3));
        graph.put(2, Arrays.asList(1));
        graph.put(3, Arrays.asList(1, 4));
        graph.put(4, Arrays.asList(3));

        BFS bfs = new BFS();

        System.out.println("BFS from 0: " + bfs.bfsTraversal(graph, 0)); // [0, 1, 2, 3, 4]
        System.out.println("Shortest path 0->4: " + bfs.shortestPath(graph, 0, 4)); // 3
        System.out.println("Shortest path 0->2: " + bfs.shortestPath(graph, 0, 2)); // 2
        System.out.println("Shortest path 0->9: " + bfs.shortestPath(graph, 0, 9)); // -1
    }
}
