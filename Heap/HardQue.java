import java.util.*;

public class HardQue {

    // ─── MINIMUM TIME TO FILL N SLOTS ────────────────────────────────────────────
    // Binary search on time t; validate using a difference array.
    // Pipe [s,e] covers slots s to e; check if all n slots are covered.
    // TC: O(n log n), SC: O(n)
    public int minTimeFillSlots(int n, int[][] pipes) {
        int lo = 1, hi = n, ans = n;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (canFill(n, pipes, mid)) { ans = mid; hi = mid - 1; }
            else lo = mid + 1;
        }
        return ans;
    }

    private boolean canFill(int n, int[][] pipes, int t) {
        int[] diff = new int[n + 2];
        for (int[] p : pipes) {
            int l = Math.max(p[0], 1), r = Math.min(p[1], n);
            if (l <= r) { diff[l]++; diff[r + 1]--; }
        }
        int filled = 0;
        for (int i = 1; i <= n; i++) {
            filled += diff[i];
            if (filled == 0) return false;
        }
        return true;
    }

    // ─── PATH WITH MINIMUM EFFORT ─────────────────────────────────────────────────
    // Dijkstra: effort of a path = max absolute height diff between adjacent cells.
    // Min-heap state = {effort, row, col}; relax neighbors greedily.
    // TC: O(m*n log(m*n)), SC: O(m*n)
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        int[][] dist = new int[m][n];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        dist[0][0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, 0, 0});
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int effort = cur[0], r = cur[1], c = cur[2];
            if (r == m - 1 && c == n - 1) return effort;
            if (effort > dist[r][c]) continue;
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                int newEffort = Math.max(effort, Math.abs(heights[nr][nc] - heights[r][c]));
                if (newEffort < dist[nr][nc]) {
                    dist[nr][nc] = newEffort;
                    pq.offer(new int[]{newEffort, nr, nc});
                }
            }
        }
        return dist[m - 1][n - 1];
    }

    // ─── MINIMUM OPERATIONS TO HALVE ARRAY SUM ───────────────────────────────────
    // Greedy: always halve the current largest element using a max-heap.
    // Stop when cumulative reduction >= half of original sum.
    // TC: O(n log n), SC: O(n)
    public int minOperationsToHalve(int[] nums) {
        PriorityQueue<Double> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        double total = 0;
        for (int n : nums) { total += n; maxHeap.offer((double) n); }
        double target = total / 2, reduced = 0;
        int ops = 0;
        while (reduced < target) {
            double top = maxHeap.poll() / 2;
            reduced += top;
            maxHeap.offer(top);
            ops++;
        }
        return ops;
    }

    // ─── MERGE K SORTED LINKED LISTS ─────────────────────────────────────────────
    // Push head of each list into min-heap; poll min, push its next.
    // TC: O(N log k) where N = total nodes, SC: O(k)
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode node : lists) if (node != null) minHeap.offer(node);
        ListNode dummy = new ListNode(0), cur = dummy;
        while (!minHeap.isEmpty()) {
            cur.next = minHeap.poll();
            cur = cur.next;
            if (cur.next != null) minHeap.offer(cur.next);
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        HardQue hq = new HardQue();

        // Min Time to Fill N Slots
        System.out.println(hq.minTimeFillSlots(4, new int[][]{{1,2},{2,4},{3,4}})); // 1

        // Path With Minimum Effort
        System.out.println(hq.minimumEffortPath(new int[][]{{1,2,2},{3,8,2},{5,3,5}})); // 2

        // Min Operations to Halve Array Sum
        System.out.println(hq.minOperationsToHalve(new int[]{5,19,8,1})); // 3

        // Merge K Sorted Linked Lists: 1->4->5, 1->3->4, 2->6
        ListNode l1 = new ListNode(1); l1.next = new ListNode(4); l1.next.next = new ListNode(5);
        ListNode l2 = new ListNode(1); l2.next = new ListNode(3); l2.next.next = new ListNode(4);
        ListNode l3 = new ListNode(2); l3.next = new ListNode(6);
        ListNode merged = hq.mergeKLists(new ListNode[]{l1, l2, l3});
        while (merged != null) { System.out.print(merged.val + " "); merged = merged.next; }
        System.out.println(); // 1 1 2 3 4 4 5 6
    }
}
