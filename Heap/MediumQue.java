import java.util.*;

public class MediumQue {

    // ─── NEAREST CAR ─────────────────────────────────────────────────────────────
    // Find k nearest cars by squared Euclidean distance from query point.
    // Use max-heap of size k; discard farthest when size exceeds k.
    // TC: O(n log k), SC: O(k)
    public int[][] kNearestCars(int[][] cars, int[] query, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int[] car : cars) {
            int dist = (int)(Math.pow(car[0] - query[0], 2)
                           + Math.pow(car[1] - query[1], 2)
                           + Math.pow(car[2] - query[2], 2));
            maxHeap.offer(new int[]{dist, car[0], car[1], car[2]});
            if (maxHeap.size() > k) maxHeap.poll();
        }
        int[][] res = new int[k][3];
        int i = 0;
        while (!maxHeap.isEmpty()) { int[] e = maxHeap.poll(); res[i++] = new int[]{e[1], e[2], e[3]}; }
        return res;
    }

    // ─── CONNECT N ROPES (minimum cost) ──────────────────────────────────────────
    // Greedy: always connect the two shortest ropes first using min-heap.
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

    // ─── WEAKEST SOLDIER ─────────────────────────────────────────────────────────
    // Return indices of k weakest rows (fewest soldiers), ties broken by row index.
    // TC: O(n log n + k log n), SC: O(n)
    public int[] kWeakestRows(int[][] mat, int k) {
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
    // Monotonic decreasing deque of indices; front = max of current window.
    // TC: O(n), SC: O(k)
    public int[] slidingWindowMax(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (!dq.isEmpty() && dq.peekFirst() < i - k + 1) dq.pollFirst();
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) dq.pollLast();
            dq.offerLast(i);
            if (i >= k - 1) res[i - k + 1] = nums[dq.peekFirst()];
        }
        return res;
    }

    // ─── KTH LARGEST ELEMENT IN A STREAM ─────────────────────────────────────────
    // Min-heap of size k; root is always the kth largest seen so far.
    // TC per add: O(log k), SC: O(k)
    static class KthLargest {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int k;
        KthLargest(int k, int[] nums) {
            this.k = k;
            for (int n : nums) add(n);
        }
        public int add(int val) {
            minHeap.offer(val);
            if (minHeap.size() > k) minHeap.poll();
            return minHeap.peek();
        }
    }

    public static void main(String[] args) {
        MediumQue mq = new MediumQue();

        // Nearest Car
        int[][] cars = {{1,3,1},{1,3,2},{1,3,3},{2,4,1}};
        System.out.println(Arrays.deepToString(mq.kNearestCars(cars, new int[]{0,0,0}, 1))); // [[1, 3, 1]]

        // Connect N Ropes
        System.out.println(mq.connectRopes(new int[]{4, 3, 2, 6})); // 29

        // Weakest Soldier
        int[][] mat = {{1,1,0,0,0},{1,1,1,1,0},{1,0,0,0,0},{1,1,0,0,0},{1,1,1,1,1}};
        System.out.println(Arrays.toString(mq.kWeakestRows(mat, 3))); // [2, 0, 3]

        // Sliding Window Maximum
        System.out.println(Arrays.toString(mq.slidingWindowMax(new int[]{1,3,-1,-3,5,3,6,7}, 3))); // [3,3,5,5,6,7]

        // Kth Largest in Stream
        KthLargest kl = new KthLargest(3, new int[]{4, 5, 8, 2});
        System.out.println(kl.add(3));  // 4
        System.out.println(kl.add(5));  // 5
        System.out.println(kl.add(10)); // 5
        System.out.println(kl.add(9));  // 8
    }
}
