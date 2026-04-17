package pattern;

import java.util.*;

// Pattern: Merge Intervals
// Use this when: you have a list of intervals and need to merge overlapping ones
// Idea: sort intervals by start time
//       if current interval overlaps with last one in result → merge them
//       otherwise → just add it to result
// Time: O(n log n)

public class mergeintervals {
    public int[][] merge(int[][] intervals) {
        // step 1: sort by start time so overlapping intervals are next to each other
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> res = new ArrayList<>();

        for (int[] curr : intervals) {
            // if result is empty OR current interval doesn't overlap with last → add it
            if (res.isEmpty() || res.get(res.size() - 1)[1] < curr[0]) {
                res.add(curr);
            } else {
                // overlap found → extend the end of last interval if needed
                res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1], curr[1]);
            }
        }

        return res.toArray(new int[0][]);
    }
}
