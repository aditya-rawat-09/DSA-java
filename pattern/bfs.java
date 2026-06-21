package pattern;

import java.util.*;

// Pattern: BFS (Breadth First Search)
// Use this when: you need shortest path in an unweighted graph or level-by-level traversal
// Idea: visit all neighbors of current node before going deeper
//       use a queue — process nodes level by level
//       each level = 1 step further from the source
// Time: O(V + E)

public class bfs {
    // returns the shortest distance from src to target
    // returns -1 if target is not reachable
    public int shortestPath(Map<Integer, List<Integer>> graph, int src, int target) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.offer(src);   // start from source
        visited.add(src);
        int level = 0;      // level = number of steps from src

        while (!queue.isEmpty()) {
            int size = queue.size(); // number of nodes at current level

            for (int i = 0; i < size; i++) {
                int node = queue.poll();

                if (node == target) return level; // reached target

                // add all unvisited neighbors to queue for next level
                for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }

            level++; // done with this level, go one step deeper
        }

        return -1; // target not reachable
    }
}
