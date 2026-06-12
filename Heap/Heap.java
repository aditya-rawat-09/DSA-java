import java.util.*;

public class Heap {

    // ─── MIN HEAP IMPLEMENTATION ─────────────────────────────────────────────────
    int[] arr;
    int size;

    Heap(int capacity) { arr = new int[capacity]; size = 0; }

    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i)   { return 2 * i + 1; }
    private int right(int i)  { return 2 * i + 2; }
    private void swap(int[] a, int i, int j) { int t = a[i]; a[i] = a[j]; a[j] = t; }

    // ─── INSERT INTO HEAP ─────────────────────────────────────────────────────────
    // Add at end, bubble up (sift up) to restore heap property.
    // TC: O(log n), SC: O(1)
    public void insert(int val) {
        arr[size++] = val;
        int i = size - 1;
        while (i > 0 && arr[parent(i)] > arr[i]) {
            swap(arr, i, parent(i));
            i = parent(i);
        }
    }

    // ─── REMOVE FROM HEAP (remove min) ───────────────────────────────────────────
    // Swap root with last element, reduce size, heapify down from root.
    // TC: O(log n), SC: O(1)
    public int remove() {
        if (size == 0) throw new NoSuchElementException("Heap is empty");
        int min = arr[0];
        arr[0] = arr[--size];
        heapifyDown(arr, size, 0);
        return min;
    }

    // ─── HEAPIFY ──────────────────────────────────────────────────────────────────
    // Sift down node at index i in array of given length to restore min-heap.
    // TC: O(log n), SC: O(1)
    private void heapifyDown(int[] a, int n, int i) {
        int smallest = i;
        int l = left(i), r = right(i);
        if (l < n && a[l] < a[smallest]) smallest = l;
        if (r < n && a[r] < a[smallest]) smallest = r;
        if (smallest != i) { swap(a, i, smallest); heapifyDown(a, n, smallest); }
    }

    // Build max-heap in-place using heapify (for heap sort).
    // TC: O(n), SC: O(1)
    private void buildMaxHeap(int[] a, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) heapifyDownMax(a, n, i);
    }

    private void heapifyDownMax(int[] a, int n, int i) {
        int largest = i, l = 2 * i + 1, r = 2 * i + 2;
        if (l < n && a[l] > a[largest]) largest = l;
        if (r < n && a[r] > a[largest]) largest = r;
        if (largest != i) { swap(a, i, largest); heapifyDownMax(a, n, largest); }
    }

    // ─── HEAP SORT ────────────────────────────────────────────────────────────────
    // Build max-heap, then repeatedly extract max to end of array.
    // TC: O(n log n), SC: O(1)
    public void heapSort(int[] a) {
        int n = a.length;
        buildMaxHeap(a, n);
        for (int i = n - 1; i > 0; i--) {
            swap(a, 0, i);           // move current max to end
            heapifyDownMax(a, i, 0); // restore heap on reduced array
        }
    }

    // ─── NEAREST CAR ─────────────────────────────────────────────────────────────
    // Given a list of cars as [x, y, z] coordinates and a query point,
    // find the k nearest cars by Euclidean distance (squared to avoid sqrt).
    // Use a max-heap of size k to keep k closest.
    // TC: O(n log k), SC: O(k)
    public int[][] kNearestCars(int[][] cars, int[] query, int k) {
        // max-heap by squared distance
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> b[0] - a[0]  // b[0] - a[0] = max-heap on distance
        );
        for (int[] car : cars) {
            int dist = (int)(Math.pow(car[0] - query[0], 2)
                           + Math.pow(car[1] - query[1], 2)
                           + Math.pow(car[2] - query[2], 2));
            maxHeap.offer(new int[]{dist, car[0], car[1], car[2]});
            if (maxHeap.size() > k) maxHeap.poll(); // remove farthest
        }
        int[][] res = new int[k][3];
        int i = 0;
        while (!maxHeap.isEmpty()) { int[] e = maxHeap.poll(); res[i++] = new int[]{e[1], e[2], e[3]}; }
        return res;
    }

    // ─── CONNECT N ROPES (minimum cost) ──────────────────────────────────────────
    // Greedy: always connect the two shortest ropes first.
    // Use min-heap; at each step pop two minimums, add their sum, push sum back.
    // TC: O(n log n), SC: O(n)
    public int connectRopes(int[] ropes) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int r : ropes) minHeap.offer(r);
        int cost = 0;
        while (minHeap.size() > 1) {
            int sum = minHeap.poll() + minHeap.poll();
            cost += sum;
            minHeap.offer(sum);
        }
        return cost;
    }

    // ─── WEAKEST SOLDIER (weakest k rows in matrix) ───────────────────────────────
    // Each row = soldiers (1s) followed by civilians (0s).
    // Rank rows by soldier count (ties broken by row index).
    // Use min-heap of size n, return indices of k weakest rows.
    // TC: O(n log n + k log n), SC: O(n)
    public int[] kWeakestRows(int[][] mat, int k) {
        // min-heap: [soldierCount, rowIndex]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]
        );
        for (int i = 0; i < mat.length; i++) {
            int count = 0;
            for (int v : mat[i]) count += v;
            minHeap.offer(new int[]{count, i});
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) res[i] = minHeap.poll()[1];
        return res;
    }

    // ─── SLIDING WINDOW MAXIMUM ───────────────────────────────────────────────────
    // For each window of size k, find the maximum element.
    // Use a deque (monotonic decreasing) storing indices.
    // Front of deque always holds index of max in current window.
    // TC: O(n), SC: O(k)
    public int[] slidingWindowMax(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<>(); // stores indices
        for (int i = 0; i < n; i++) {
            // remove indices outside window
            if (!dq.isEmpty() && dq.peekFirst() < i - k + 1) dq.pollFirst();
            // remove smaller elements from back (they can never be max)
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) dq.pollLast();
            dq.offerLast(i);
            if (i >= k - 1) res[i - k + 1] = nums[dq.peekFirst()];
        }
        return res;
    }

    // ─── KTH LARGEST ELEMENT IN A STREAM ────────────────────────────────────────
    // Maintain a min-heap of size k; root is always the kth largest.
    // On each add: push element, if size > k pop min.
    // TC: O(n log k), SC: O(k)
    static class KthLargest {
        PriorityQueue<Integer> minHeap;
        int k;
        KthLargest(int k, int[] nums) {
            this.k = k;
            minHeap = new PriorityQueue<>();
            for (int n : nums) add(n);
        }
        public int add(int val) {
            minHeap.offer(val);
            if (minHeap.size() > k) minHeap.poll();
            return minHeap.peek();
        }
    }

    // ─── MINIMUM TIME TO FILL N SLOTS ────────────────────────────────────────────
    // slots[] has positions; pipes[] has [start, end] intervals (1-indexed).
    // Binary search on answer (time t): check if t units of time can fill all slots.
    // A pipe [s,e] fills slots from max(s,1) to min(e,n) in 1 unit each.
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
        // each pipe covers [start-1 .. end] range; use difference array
        int[] diff = new int[n + 2];
        for (int[] p : pipes) {
            int l = Math.max(p[0], 1), r = Math.min(p[1], n);
            if (l <= r) { diff[l]++; diff[r + 1]--; }
        }
        int filled = 0, slots = 0;
        for (int i = 1; i <= n; i++) {
            filled += diff[i];
            if (filled == 0) slots++; // this slot not covered
        }
        return slots == 0;
    }

    // ─── PATH WITH MINIMUM EFFORT ─────────────────────────────────────────────────
    // Effort = max absolute difference along a path from (0,0) to (m-1,n-1).
    // Dijkstra with min-heap: state = [effort, row, col].
    // TC: O(m*n log(m*n)), SC: O(m*n)
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        int[][] dist = new int[m][n];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        dist[0][0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, 0, 0}); // {effort, row, col}
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
    // Greedy: always halve the largest element.
    // Use max-heap; keep halving until total sum <= half of original.
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
    // Push head of each list into min-heap.
    // Poll min, append to result, push its next node.
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
        // ── Insert & Remove ──
        Heap h = new Heap(10);
        for (int v : new int[]{5, 3, 8, 1, 2}) h.insert(v);
        System.out.println(h.remove()); // 1
        System.out.println(h.remove()); // 2

        // ── Heap Sort ──
        int[] arr = {4, 10, 3, 5, 1};
        h.heapSort(arr);
        System.out.println(Arrays.toString(arr)); // [1, 3, 4, 5, 10]

        // ── Nearest Car ──
        // Cars at (1,3,1),(1,3,2),(1,3,3) query=(0,0,0) k=1
        int[][] cars = {{1,3,1},{1,3,2},{1,3,3},{2,4,1}};
        int[][] nearest = h.kNearestCars(cars, new int[]{0,0,0}, 1);
        System.out.println(Arrays.deepToString(nearest)); // [[1, 3, 1]]

        // ── Connect N Ropes ──
        System.out.println(h.connectRopes(new int[]{4, 3, 2, 6})); // 29

        // ── Weakest Soldier ──
        int[][] mat = {
            {1,1,0,0,0},
            {1,1,1,1,0},
            {1,0,0,0,0},
            {1,1,0,0,0},
            {1,1,1,1,1}
        };
        System.out.println(Arrays.toString(h.kWeakestRows(mat, 3))); // [2, 0, 3]

        // ── Sliding Window Maximum ──
        System.out.println(Arrays.toString(
            h.slidingWindowMax(new int[]{1,3,-1,-3,5,3,6,7}, 3)
        )); // [3, 3, 5, 5, 6, 7]

        // ── Kth Largest in Stream ──
        KthLargest kl = new KthLargest(3, new int[]{4, 5, 8, 2});
        System.out.println(kl.add(3));  // 4
        System.out.println(kl.add(5));  // 5
        System.out.println(kl.add(10)); // 5
        System.out.println(kl.add(9));  // 8

        // ── Minimum Time to Fill N Slots ──
        // n=4 slots, pipes cover different ranges
        int[][] pipes = {{1,2},{2,4},{3,4}};
        System.out.println(h.minTimeFillSlots(4, pipes)); // 1

        // ── Path With Minimum Effort ──
        int[][] heights = {{1,2,2},{3,8,2},{5,3,5}};
        System.out.println(h.minimumEffortPath(heights)); // 2

        // ── Minimum Operations to Halve Array Sum ──
        System.out.println(h.minOperationsToHalve(new int[]{5,19,8,1})); // 3

        // ── Merge K Sorted Linked Lists ──
        // Lists: 1->4->5 , 1->3->4 , 2->6
        ListNode l1 = new ListNode(1); l1.next = new ListNode(4); l1.next.next = new ListNode(5);
        ListNode l2 = new ListNode(1); l2.next = new ListNode(3); l2.next.next = new ListNode(4);
        ListNode l3 = new ListNode(2); l3.next = new ListNode(6);
        ListNode merged = h.mergeKLists(new ListNode[]{l1, l2, l3});
        while (merged != null) { System.out.print(merged.val + " "); merged = merged.next; }
        System.out.println(); // 1 1 2 3 4 4 5 6
    }
}
