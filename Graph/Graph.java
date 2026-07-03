import java.util.*;

public class Graph {

    // ─── CREATE GRAPH ─────────────────────────────────────────────────────────────
    // Adjacency list representation using HashMap.
    // Supports both directed and undirected graphs.
    // TC: O(1) per addEdge, SC: O(V + E)
    Map<Integer, List<Integer>> adjList = new HashMap<>();

    public void addEdge(int u, int v, boolean directed) {
        adjList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        if (!directed)
            adjList.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
    }

    public void display() {
        for (Map.Entry<Integer, List<Integer>> e : adjList.entrySet())
            System.out.println(e.getKey() + " -> " + e.getValue());
    }

    // ─── BFS (Breadth First Search) ───────────────────────────────────────────────
    // Level-order traversal using a queue.
    // Visits all nodes at distance 1, then 2, then 3 ...
    // TC: O(V + E), SC: O(V)
    public void bfs(int src) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(src);
        visited.add(src);
        System.out.print("BFS: ");
        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");
            for (int neighbor : adjList.getOrDefault(node, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        System.out.println();
    }

    // ─── DFS (Depth First Search) ─────────────────────────────────────────────────
    // Recursive traversal — go as deep as possible before backtracking.
    // TC: O(V + E), SC: O(V)
    public void dfs(int src) {
        Set<Integer> visited = new HashSet<>();
        System.out.print("DFS: ");
        dfsHelper(src, visited);
        System.out.println();
    }

    private void dfsHelper(int node, Set<Integer> visited) {
        visited.add(node);
        System.out.print(node + " ");
        for (int neighbor : adjList.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbor))
                dfsHelper(neighbor, visited);
        }
    }

    // ─── HAS PATH ─────────────────────────────────────────────────────────────────
    // Check if a path exists between src and dest using DFS.
    // TC: O(V + E), SC: O(V)
    public boolean hasPath(int src, int dest) {
        Set<Integer> visited = new HashSet<>();
        return hasPathDFS(src, dest, visited);
    }

    private boolean hasPathDFS(int node, int dest, Set<Integer> visited) {
        if (node == dest) return true;
        visited.add(node);
        for (int neighbor : adjList.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbor))
                if (hasPathDFS(neighbor, dest, visited)) return true;
        }
        return false;
    }

    // ─── DISCONNECTED GRAPH (BFS on all components) ───────────────────────────────
    // A disconnected graph has multiple connected components.
    // To traverse fully: iterate over all nodes, run BFS/DFS for each unvisited node.
    // TC: O(V + E), SC: O(V)
    public int countComponents() {
        Set<Integer> visited = new HashSet<>();
        int components = 0;
        for (int node : adjList.keySet()) {
            if (!visited.contains(node)) {
                bfsComponent(node, visited);
                components++;
            }
        }
        return components;
    }

    private void bfsComponent(int src, Set<Integer> visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(src);
        visited.add(src);
        System.out.print("Component: ");
        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");
            for (int neighbor : adjList.getOrDefault(node, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        System.out.println();
    }

    // ─── CYCLE DETECTION (Undirected) ────────────────────────────────────────────
    // Use DFS — if we visit an already-visited node that isn't the parent, cycle exists.
    // TC: O(V + E), SC: O(V)
    public boolean hasCycleUndirected() {
        Set<Integer> visited = new HashSet<>();
        for (int node : adjList.keySet())
            if (!visited.contains(node))
                if (dfsCycleUndirected(node, -1, visited)) return true;
        return false;
    }

    private boolean dfsCycleUndirected(int node, int parent, Set<Integer> visited) {
        visited.add(node);
        for (int neighbor : adjList.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                if (dfsCycleUndirected(neighbor, node, visited)) return true;
            } else if (neighbor != parent) return true;
        }
        return false;
    }

    // ─── BIPARTITE CHECK ──────────────────────────────────────────────────────────
    // BFS 2-coloring: assign alternating colors (0/1) to neighbors.
    // If two adjacent nodes share the same color → not bipartite.
    // TC: O(V + E), SC: O(V)
    public boolean isBipartite() {
        Map<Integer, Integer> color = new HashMap<>();
        for (int node : adjList.keySet()) {
            if (color.containsKey(node)) continue;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(node);
            color.put(node, 0);
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                for (int neighbor : adjList.getOrDefault(curr, new ArrayList<>())) {
                    if (!color.containsKey(neighbor)) {
                        color.put(neighbor, 1 - color.get(curr));
                        queue.offer(neighbor);
                    } else if (color.get(neighbor).equals(color.get(curr))) return false;
                }
            }
        }
        return true;
    }

    // ─── CYCLE DETECTION (Directed) ───────────────────────────────────────────────
    // DFS with a recursion stack — if we revisit a node still in the current path, cycle exists.
    // TC: O(V + E), SC: O(V)
    public boolean hasCycleDirected() {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> recStack = new HashSet<>();
        for (int node : adjList.keySet())
            if (!visited.contains(node))
                if (dfsCycleDirected(node, visited, recStack)) return true;
        return false;
    }

    private boolean dfsCycleDirected(int node, Set<Integer> visited, Set<Integer> recStack) {
        visited.add(node);
        recStack.add(node);
        for (int neighbor : adjList.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                if (dfsCycleDirected(neighbor, visited, recStack)) return true;
            } else if (recStack.contains(neighbor)) return true;
        }
        recStack.remove(node);
        return false;
    }

    // ─── TOPOLOGICAL SORT (DFS) ───────────────────────────────────────────────────
    // Post-order DFS: push node to stack after visiting all its neighbors.
    // Reverse of finish order = topological order. Only valid for DAGs.
    // TC: O(V + E), SC: O(V)
    public List<Integer> topoSortDFS() {
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int node : adjList.keySet())
            if (!visited.contains(node))
                topoSortDFSHelper(node, visited, stack);
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) result.add(stack.pop());
        return result;
    }

    private void topoSortDFSHelper(int node, Set<Integer> visited, Deque<Integer> stack) {
        visited.add(node);
        for (int neighbor : adjList.getOrDefault(node, new ArrayList<>()))
            if (!visited.contains(neighbor))
                topoSortDFSHelper(neighbor, visited, stack);
        stack.push(node);
    }

    // ─── TOPOLOGICAL SORT (BFS / Kahn's Algorithm) ───────────────────────────────
    // Repeatedly remove nodes with in-degree 0.
    // If result size < V, graph has a cycle.
    // TC: O(V + E), SC: O(V)
    public List<Integer> topoSortBFS() {
        Map<Integer, Integer> inDegree = new HashMap<>();
        for (int node : adjList.keySet()) inDegree.put(node, 0);
        for (int node : adjList.keySet())
            for (int neighbor : adjList.get(node))
                inDegree.merge(neighbor, 1, Integer::sum);

        Queue<Integer> queue = new LinkedList<>();
        for (int node : inDegree.keySet())
            if (inDegree.get(node) == 0) queue.offer(node);

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            for (int neighbor : adjList.getOrDefault(node, new ArrayList<>())) {
                inDegree.merge(neighbor, -1, Integer::sum);
                if (inDegree.get(neighbor) == 0) queue.offer(neighbor);
            }
        }
        return result; // size < V means cycle exists
    }

    // ─── ALL PATHS FROM SOURCE TO DESTINATION ────────────────────────────────────
    // DFS backtracking: explore every path, add to result when dest is reached.
    // TC: O(2^V) worst case, SC: O(V)
    public List<List<Integer>> allPaths(int src, int dest) {
        List<List<Integer>> result = new ArrayList<>();
        allPathsDFS(src, dest, new ArrayList<>(), result);
        return result;
    }

    private void allPathsDFS(int node, int dest, List<Integer> path, List<List<Integer>> result) {
        path.add(node);
        if (node == dest) {
            result.add(new ArrayList<>(path));
        } else {
            for (int neighbor : adjList.getOrDefault(node, new ArrayList<>()))
                if (!path.contains(neighbor))
                    allPathsDFS(neighbor, dest, path, result);
        }
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        // ── Create Graph (undirected) ──
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

        System.out.println("─── BFS from 0 ───");
        g.bfs(0); // 0 1 3 2 4

        System.out.println("─── DFS from 0 ───");
        g.dfs(0); // 0 1 2 4 3

        System.out.println("─── Has Path ───");
        System.out.println(g.hasPath(0, 4)); // true
        System.out.println(g.hasPath(0, 9)); // false (node 9 doesn't exist)

        // ── Disconnected Graph ──
        //   0 -- 1    2 -- 3    4
        System.out.println("─── Disconnected Graph ───");
        Graph dg = new Graph();
        dg.addEdge(0, 1, false);
        dg.addEdge(2, 3, false);
        dg.adjList.computeIfAbsent(4, k -> new ArrayList<>()); // isolated node

        System.out.println("Components: " + dg.countComponents()); // 3
        // Component: 0 1
        // Component: 2 3
        // Component: 4

        // ── Cycle Detection (Undirected) ──
        //   0 -- 1 -- 2 -- 0  (cycle)
        System.out.println("─── Cycle Detection (Undirected) ───");
        Graph cg = new Graph();
        cg.addEdge(0, 1, false);
        cg.addEdge(1, 2, false);
        cg.addEdge(2, 0, false);
        System.out.println("Has cycle: " + cg.hasCycleUndirected()); // true

        Graph acyclic = new Graph();
        acyclic.addEdge(0, 1, false);
        acyclic.addEdge(1, 2, false);
        System.out.println("Has cycle: " + acyclic.hasCycleUndirected()); // false

        // ── Bipartite Check ──
        //   0 -- 1 -- 2 -- 3  (bipartite: even/odd coloring)
        System.out.println("─── Bipartite Check ───");
        Graph bg = new Graph();
        bg.addEdge(0, 1, false);
        bg.addEdge(1, 2, false);
        bg.addEdge(2, 3, false);
        System.out.println("Is bipartite: " + bg.isBipartite()); // true

        Graph notBipartite = new Graph();
        notBipartite.addEdge(0, 1, false);
        notBipartite.addEdge(1, 2, false);
        notBipartite.addEdge(2, 0, false); // odd cycle → not bipartite
        System.out.println("Is bipartite: " + notBipartite.isBipartite()); // false

        // ── Cycle Detection (Directed) ──
        //   0 → 1 → 2 → 0  (cycle)
        System.out.println("─── Cycle Detection (Directed) ───");
        Graph dcg = new Graph();
        dcg.addEdge(0, 1, true);
        dcg.addEdge(1, 2, true);
        dcg.addEdge(2, 0, true);
        System.out.println("Has cycle: " + dcg.hasCycleDirected()); // true

        Graph dag = new Graph();
        dag.addEdge(0, 1, true);
        dag.addEdge(1, 2, true);
        System.out.println("Has cycle: " + dag.hasCycleDirected()); // false

        // ── Topological Sort ──
        //   5 → 0 ← 4
        //   5 → 2 → 3 → 1
        //           4 → 1
        System.out.println("─── Topological Sort (DFS) ───");
        Graph tg = new Graph();
        tg.addEdge(5, 0, true);
        tg.addEdge(5, 2, true);
        tg.addEdge(4, 0, true);
        tg.addEdge(4, 1, true);
        tg.addEdge(2, 3, true);
        tg.addEdge(3, 1, true);
        System.out.println("Topo DFS: " + tg.topoSortDFS()); // e.g. [5, 4, 2, 3, 1, 0]

        System.out.println("─── Topological Sort (BFS/Kahn's) ───");
        System.out.println("Topo BFS: " + tg.topoSortBFS()); // e.g. [4, 5, 0, 2, 3, 1]

        // ── All Paths from Source to Destination ──
        //   0 → 1 → 3
        //   0 → 2 → 3
        //   0 → 1 → 2 → 3
        System.out.println("─── All Paths (0 → 3) ───");
        Graph pg = new Graph();
        pg.addEdge(0, 1, true);
        pg.addEdge(0, 2, true);
        pg.addEdge(1, 2, true);
        pg.addEdge(1, 3, true);
        pg.addEdge(2, 3, true);
        System.out.println(pg.allPaths(0, 3)); // [[0,1,3],[0,1,2,3],[0,2,3]]
    }
}
