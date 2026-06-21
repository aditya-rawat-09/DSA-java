package pattern;

import java.util.*;

// Pattern: DFS (Depth First Search)
// Use this when: you need to explore all paths, find connected components, or detect cycles
// Idea: go as deep as possible in one direction before backtracking
//       use recursion (or a stack) to keep track of where you are
// Time: O(V + E)

public class dfs {
    // counts how many connected components (groups) exist in the graph
    public int countComponents(int n, int[][] edges) {
        // step 1: build adjacency list (who is connected to who)
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) graph.put(i, new ArrayList<>());
        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]); // undirected edge: add both directions
            graph.get(e[1]).add(e[0]);
        }

        boolean[] visited = new boolean[n];
        int components = 0;

        // step 2: for each unvisited node, do a DFS — that's one full component
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(graph, visited, i); // mark all nodes in this component
                components++;           // one more group found
            }
        }

        return components;
    }

    // visits all nodes connected to 'node' and marks them visited
    private void dfs(Map<Integer, List<Integer>> graph, boolean[] visited, int node) {
        visited[node] = true;

        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) dfs(graph, visited, neighbor); // go deeper
        }
    }
}
