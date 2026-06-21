package patterns;

import java.util.*;

public class TopKElements {
    // returns the k most frequent numbers
    public int[] topKFrequent(int[] nums, int k) {
        // step 1: count how many times each number appears
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) freq.merge(n, 1, Integer::sum);

        // step 2: min-heap sorted by frequency (smallest freq at top)
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(freq::get));

        for (int num : freq.keySet()) {
            heap.offer(num);
            if (heap.size() > k) heap.poll(); // remove least frequent, keep only top k
        }

        // step 3: collect results from heap
        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) res[i] = heap.poll();
        return res;
    }
}
