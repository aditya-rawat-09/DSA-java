import java.util.*;

public class DFS {

    // ─── DFS TRAVERSAL ────────────────────────────────────────────────────────────
    // Recursive traversal — go as deep as possible before backtracking.
    // TC: O(V + E), SC: O(V)
    public void dfs(Map<Integer, List<Integer>> graph, int src) {
        Set<Integer> visited = new HashSet<>();
        System.out.print("DFS: ");
        dfsHelper(graph, src, visited);
        System.out.println();
    }

    private void dfsHelper(Map<Integer, List<Integer>> graph, int node, Set<Integer> visited) {
        visited.add(node);
        System.out.print(node + " ");
        for (int neighbor : graph.getOrDefault(node, new ArrayList<>()))
            if (!visited.contains(neighbor))
                dfsHelper(graph, neighbor, visited);
    }

    // ─── HAS PATH (Source → Destination) ─────────────────────────────────────────
    // Check if a path exists between src and dest using DFS.
    // TC: O(V + E), SC: O(V)
    public boolean hasPath(Map<Integer, List<Integer>> graph, int src, int dest) {
        return hasPathDFS(graph, src, dest, new HashSet<>());
    }

    private boolean hasPathDFS(Map<Integer, List<Integer>> graph, int node, int dest, Set<Integer> visited) {
        if (node == dest) return true;
        visited.add(node);
        for (int neighbor : graph.getOrDefault(node, new ArrayList<>()))
            if (!visited.contains(neighbor))
                if (hasPathDFS(graph, neighbor, dest, visited)) return true;
        return false;
    }

    // ─── COUNT CONNECTED COMPONENTS ──────────────────────────────────────────────
    // Disconnected graph: iterate all nodes, run DFS for each unvisited node.
    // TC: O(V + E), SC: O(V)
    public int countComponents(Map<Integer, List<Integer>> graph) {
        Set<Integer> visited = new HashSet<>();
        int components = 0;
        for (int node : graph.keySet()) {
            if (!visited.contains(node)) {
                dfsComponent(graph, node, visited);
                components++;
            }
        }
        return components;
    }

    private void dfsComponent(Map<Integer, List<Integer>> graph, int node, Set<Integer> visited) {
        visited.add(node);
        System.out.print(node + " ");
        for (int neighbor : graph.getOrDefault(node, new ArrayList<>()))
            if (!visited.contains(neighbor))
                dfsComponent(graph, neighbor, visited);
    }

    // ─── ALL PATHS (Source → Destination) ────────────────────────────────────────
    // DFS backtracking: explore every path, snapshot when dest is reached.
    // TC: O(2^V) worst case, SC: O(V)
    public List<List<Integer>> allPaths(Map<Integer, List<Integer>> graph, int src, int dest) {
        List<List<Integer>> result = new ArrayList<>();
        allPathsDFS(graph, src, dest, new ArrayList<>(), result);
        return result;
    }

    private void allPathsDFS(Map<Integer, List<Integer>> graph, int node, int dest,
                              List<Integer> path, List<List<Integer>> result) {
        path.add(node);
        if (node == dest) {
            result.add(new ArrayList<>(path));
        } else {
            for (int neighbor : graph.getOrDefault(node, new ArrayList<>()))
                if (!path.contains(neighbor))
                    allPathsDFS(graph, neighbor, dest, path, result);
        }
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        // Graph:  0 -- 1 -- 2
        //         |         |
        //         3 ------- 4
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 3));
        graph.put(1, Arrays.asList(0, 2));
        graph.put(2, Arrays.asList(1, 4));
        graph.put(3, Arrays.asList(0, 4));
        graph.put(4, Arrays.asList(2, 3));

        DFS dfs = new DFS();

        System.out.println("─── DFS Traversal ───");
        dfs.dfs(graph, 0);                                         // DFS: 0 1 2 4 3

        System.out.println("─── Has Path ───");
        System.out.println("0->4: " + dfs.hasPath(graph, 0, 4));  // true
        System.out.println("0->9: " + dfs.hasPath(graph, 0, 9));  // false

        // Disconnected: 0-1  2-3  4
        System.out.println("─── Connected Components ───");
        Map<Integer, List<Integer>> dg = new HashMap<>();
        dg.put(0, Arrays.asList(1)); dg.put(1, Arrays.asList(0));
        dg.put(2, Arrays.asList(3)); dg.put(3, Arrays.asList(2));
        dg.put(4, new ArrayList<>());
        System.out.println("\nComponents: " + dfs.countComponents(dg)); // 3

        // Directed: 0->1, 0->2, 1->2, 1->3, 2->3
        System.out.println("─── All Paths 0->3 ───");
        Map<Integer, List<Integer>> pg = new HashMap<>();
        pg.put(0, Arrays.asList(1, 2));
        pg.put(1, Arrays.asList(2, 3));
        pg.put(2, Arrays.asList(3));
        pg.put(3, new ArrayList<>());
        System.out.println(dfs.allPaths(pg, 0, 3)); // [[0,1,3],[0,1,2,3],[0,2,3]]
    }
}
