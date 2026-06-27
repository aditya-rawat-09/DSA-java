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
    }
}
