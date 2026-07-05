import java.util.*;

public class Bipartite {

    // ─── BIPARTITE CHECK ──────────────────────────────────────────────────────────
    // BFS 2-coloring: assign alternating colors (0/1) to neighbors.
    // If two adjacent nodes share the same color → odd cycle → not bipartite.
    // TC: O(V + E), SC: O(V)
    public boolean isBipartite(Map<Integer, List<Integer>> graph) {
        Map<Integer, Integer> color = new HashMap<>();
        for (int node : graph.keySet()) {
            if (color.containsKey(node)) continue;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(node);
            color.put(node, 0);
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                for (int neighbor : graph.getOrDefault(curr, new ArrayList<>())) {
                    if (!color.containsKey(neighbor)) {
                        color.put(neighbor, 1 - color.get(curr));
                        queue.offer(neighbor);
                    } else if (color.get(neighbor).equals(color.get(curr))) return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Bipartite b = new Bipartite();

        // ── Bipartite: 0-1-2-3 (even/odd coloring works) ──
        System.out.println("─── Bipartite Check ───");
        Map<Integer, List<Integer>> bg = new HashMap<>();
        bg.put(0, Arrays.asList(1));
        bg.put(1, Arrays.asList(0, 2));
        bg.put(2, Arrays.asList(1, 3));
        bg.put(3, Arrays.asList(2));
        System.out.println("Is bipartite: " + b.isBipartite(bg)); // true

        // ── Not Bipartite: 0-1-2-0 (odd cycle) ──
        Map<Integer, List<Integer>> nbg = new HashMap<>();
        nbg.put(0, Arrays.asList(1, 2));
        nbg.put(1, Arrays.asList(0, 2));
        nbg.put(2, Arrays.asList(0, 1));
        System.out.println("Is bipartite: " + b.isBipartite(nbg)); // false
    }
}
