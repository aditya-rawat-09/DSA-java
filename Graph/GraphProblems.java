import java.util.*;

public class GraphProblems {

    // ─── FLOOD FILL ───────────────────────────────────────────────────────────────
    // DFS from (sr,sc): replace all connected cells of same color with newColor.
    // TC: O(M * N), SC: O(M * N)
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        if (oldColor != newColor) dfsFlood(image, sr, sc, oldColor, newColor);
        return image;
    }

    private void dfsFlood(int[][] image, int r, int c, int oldColor, int newColor) {
        if (r < 0 || r >= image.length || c < 0 || c >= image[0].length) return;
        if (image[r][c] != oldColor) return;
        image[r][c] = newColor;
        dfsFlood(image, r+1, c, oldColor, newColor);
        dfsFlood(image, r-1, c, oldColor, newColor);
        dfsFlood(image, r, c+1, oldColor, newColor);
        dfsFlood(image, r, c-1, oldColor, newColor);
    }

    // ─── MOTHER VERTEX ────────────────────────────────────────────────────────────
    // A mother vertex can reach ALL other vertices via directed paths.
    // Kosaraju's idea: last finished node in DFS is a candidate; verify with DFS.
    // TC: O(V + E), SC: O(V)
    public int motherVertex(Map<Integer, List<Integer>> graph, int V) {
        boolean[] visited = new boolean[V];
        int candidate = -1;
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfsVisit(graph, i, visited);
                candidate = i;
            }
        }
        // Verify candidate can reach all nodes
        Arrays.fill(visited, false);
        dfsVisit(graph, candidate, visited);
        for (boolean v : visited) if (!v) return -1;
        return candidate;
    }

    private void dfsVisit(Map<Integer, List<Integer>> graph, int node, boolean[] visited) {
        visited[node] = true;
        for (int neighbor : graph.getOrDefault(node, new ArrayList<>()))
            if (!visited[neighbor]) dfsVisit(graph, neighbor, visited);
    }

    // ─── ALIEN DICTIONARY ─────────────────────────────────────────────────────────
    // Given sorted alien words, derive character order using topological sort.
    // Compare adjacent words to find ordering edges, then topo sort (BFS/Kahn's).
    // TC: O(N * L + K + E) where N=words, L=avg length, K=unique chars, E=edges
    public String alienOrder(String[] words) {
        Map<Character, List<Character>> graph = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();

        for (String w : words)
            for (char c : w.toCharArray()) {
                graph.putIfAbsent(c, new ArrayList<>());
                inDegree.putIfAbsent(c, 0);
            }

        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i], w2 = words[i + 1];
            int len = Math.min(w1.length(), w2.length());
            // invalid: prefix word comes after longer word
            if (w1.length() > w2.length() && w1.startsWith(w2)) return "";
            for (int j = 0; j < len; j++) {
                if (w1.charAt(j) != w2.charAt(j)) {
                    graph.get(w1.charAt(j)).add(w2.charAt(j));
                    inDegree.merge(w2.charAt(j), 1, Integer::sum);
                    break;
                }
            }
        }

        // Kahn's BFS topological sort
        Queue<Character> queue = new LinkedList<>();
        for (char c : inDegree.keySet())
            if (inDegree.get(c) == 0) queue.offer(c);

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            char c = queue.poll();
            sb.append(c);
            for (char next : graph.get(c)) {
                inDegree.merge(next, -1, Integer::sum);
                if (inDegree.get(next) == 0) queue.offer(next);
            }
        }
        return sb.length() == inDegree.size() ? sb.toString() : ""; // cycle check
    }

    // ─── NUMBER OF CLOSEST ISLANDS ────────────────────────────────────────────────
    // Find distance from each land cell (1) to its nearest other island using BFS.
    // Multi-source BFS: start from all cells of one island, expand until hitting another.
    // TC: O(M * N), SC: O(M * N)
    public int numClosestIslands(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int[][] dist = new int[rows][cols];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);

        // Find first island and BFS from it
        boolean found = false;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];

        outer:
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    // BFS to mark entire first island
                    Queue<int[]> islandQ = new LinkedList<>();
                    islandQ.offer(new int[]{i, j});
                    visited[i][j] = true;
                    while (!islandQ.isEmpty()) {
                        int[] cell = islandQ.poll();
                        queue.offer(new int[]{cell[0], cell[1], 0});
                        dist[cell[0]][cell[1]] = 0;
                        for (int[] d : new int[][]{{0,1},{0,-1},{1,0},{-1,0}}) {
                            int nr = cell[0]+d[0], nc = cell[1]+d[1];
                            if (nr>=0 && nr<rows && nc>=0 && nc<cols
                                    && !visited[nr][nc] && grid[nr][nc]==1) {
                                visited[nr][nc] = true;
                                islandQ.offer(new int[]{nr, nc});
                            }
                        }
                    }
                    found = true;
                    break outer;
                }
            }
        }
        if (!found) return -1;

        // BFS outward from first island
        int minDist = Integer.MAX_VALUE;
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1], d = curr[2];
            for (int[] dir : dirs) {
                int nr = r+dir[0], nc = c+dir[1];
                if (nr<0 || nr>=rows || nc<0 || nc>=cols || visited[nr][nc]) continue;
                visited[nr][nc] = true;
                if (grid[nr][nc] == 1) {
                    minDist = Math.min(minDist, d+1);
                } else {
                    dist[nr][nc] = d+1;
                    queue.offer(new int[]{nr, nc, d+1});
                }
            }
        }
        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }

    public static void main(String[] args) {
        GraphProblems gp = new GraphProblems();

        // ── Flood Fill ──
        System.out.println("─── Flood Fill ───");
        int[][] image = {{1,1,1},{1,1,0},{1,0,1}};
        int[][] result = gp.floodFill(image, 1, 1, 2);
        for (int[] row : result) System.out.println(Arrays.toString(row));
        // [2,2,2], [2,2,0], [2,0,1]

        // ── Mother Vertex ──
        System.out.println("─── Mother Vertex ───");
        Map<Integer, List<Integer>> mg = new HashMap<>();
        mg.put(0, Arrays.asList(1, 2));
        mg.put(1, Arrays.asList(3));
        mg.put(2, Arrays.asList(1));
        mg.put(3, new ArrayList<>());
        System.out.println("Mother vertex: " + gp.motherVertex(mg, 4)); // 0

        // ── Alien Dictionary ──
        System.out.println("─── Alien Dictionary ───");
        String[] words1 = {"wrt","wrf","er","ett","rftt"};
        System.out.println("Order: " + gp.alienOrder(words1)); // "wertf"

        String[] words2 = {"z","x"};
        System.out.println("Order: " + gp.alienOrder(words2)); // "zx"

        // ── Number of Closest Islands ──
        System.out.println("─── Closest Islands ───");
        int[][] grid = {
            {1,1,0,0,0},
            {1,1,0,0,0},
            {0,0,0,1,1},
            {0,0,0,1,1}
        };
        System.out.println("Min distance: " + gp.numClosestIslands(grid)); // 2
    }
}
