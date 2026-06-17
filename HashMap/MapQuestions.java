import java.util.*;

public class MapQuestions {

    // ─── MAJORITY ELEMENT ─────────────────────────────────────────────────────────
    // Element appearing more than n/2 times.
    // Count frequencies using HashMap; return key with count > n/2.
    // TC: O(n), SC: O(n)
    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) freq.put(n, freq.getOrDefault(n, 0) + 1);
        for (Map.Entry<Integer, Integer> e : freq.entrySet())
            if (e.getValue() > nums.length / 2) return e.getKey();
        return -1;
    }

    // ─── VALID ANAGRAM ────────────────────────────────────────────────────────────
    // Two strings are anagrams if they have the same character frequencies.
    // Count chars of s, decrement for t; all counts must reach 0.
    // TC: O(n), SC: O(1) — at most 26 keys
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) freq.put(c, freq.getOrDefault(c, 0) + 1);
        for (char c : t.toCharArray()) {
            if (!freq.containsKey(c)) return false;
            freq.put(c, freq.get(c) - 1);
            if (freq.get(c) < 0) return false;
        }
        return true;
    }

    // ─── FIND ITINERARY ───────────────────────────────────────────────────────────
    // Given a list of airline tickets [from, to], reconstruct the itinerary
    // starting from "JFK" in lexical order.
    // Approach: Hierholzer's algorithm (Eulerian path).
    //   - Build adjacency map: from → min-heap of destinations (lexical order).
    //   - DFS: always pick the smallest next destination; add to result on backtrack.
    // TC: O(E log E), SC: O(E)  where E = number of tickets
    public static List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> t : tickets)
            graph.computeIfAbsent(t.get(0), k -> new PriorityQueue<>()).offer(t.get(1));
        LinkedList<String> result = new LinkedList<>();
        dfs("JFK", graph, result);
        return result;
    }

    // ─── LARGEST SUBARRAY WITH SUM 0 ───────────────────────────────────────────────
    // prefix sum approach: if prefixSum[i] == prefixSum[j], subarray (i+1..j) sums to 0.
    // Store first occurrence of each prefix sum in a HashMap.
    // TC: O(n), SC: O(n)
    public static int largestSubarraySumZero(int[] arr) {
        Map<Integer, Integer> firstSeen = new HashMap<>();
        firstSeen.put(0, -1); // sum 0 seen before index 0
        int sum = 0, maxLen = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (firstSeen.containsKey(sum))
                maxLen = Math.max(maxLen, i - firstSeen.get(sum));
            else
                firstSeen.put(sum, i); // store first occurrence only
        }
        return maxLen;
    }

    // ─── SUBARRAY SUM EQUAL TO K ──────────────────────────────────────────────────
    // Count subarrays whose sum equals k.
    // prefix sum approach: if prefixSum[j] - prefixSum[i] == k → subarray (i+1..j) = k.
    // Store frequency of each prefix sum; for each index check if (sum - k) exists.
    // TC: O(n), SC: O(n)
    public static int subarraySumK(int[] arr, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        freq.put(0, 1); // empty prefix
        int sum = 0, count = 0;
        for (int n : arr) {
            sum += n;
            count += freq.getOrDefault(sum - k, 0);
            freq.put(sum, freq.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    private static void dfs(String src, Map<String, PriorityQueue<String>> graph, LinkedList<String> result) {
        PriorityQueue<String> neighbors = graph.get(src);
        while (neighbors != null && !neighbors.isEmpty())
            dfs(neighbors.poll(), graph, result);
        result.addFirst(src); // add to front after all neighbors visited
    }

    public static void main(String[] args) {
        // Majority Element
        System.out.println("─── Majority Element ───");
        System.out.println(majorityElement(new int[]{3, 2, 3}));              // 3
        System.out.println(majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2})); // 2

        // Valid Anagram
        System.out.println("─── Valid Anagram ───");
        System.out.println(isAnagram("anagram", "nagaram")); // true
        System.out.println(isAnagram("rat", "car"));         // false
        System.out.println(isAnagram("listen", "silent"));   // true

        // Find Itinerary
        System.out.println("─── Find Itinerary ───");
        // Tickets: JFK->MUC, MUC->LHR, LHR->SFO, SFO->SJC
        List<List<String>> tickets1 = Arrays.asList(
            Arrays.asList("MUC", "LHR"),
            Arrays.asList("JFK", "MUC"),
            Arrays.asList("SFO", "SJC"),
            Arrays.asList("LHR", "SFO")
        );
        System.out.println(findItinerary(tickets1)); // [JFK, MUC, LHR, SFO, SJC]

        // Tickets with multiple choices — pick lexical order
        List<List<String>> tickets2 = Arrays.asList(
            Arrays.asList("JFK", "SFO"),
            Arrays.asList("JFK", "ATL"),
            Arrays.asList("SFO", "ATL"),
            Arrays.asList("ATL", "JFK"),
            Arrays.asList("ATL", "SFO")
        );
        System.out.println(findItinerary(tickets2)); // [JFK, ATL, JFK, SFO, ATL, SFO]

        // Largest Subarray with Sum 0
        System.out.println("─── Largest Subarray Sum 0 ───");
        System.out.println(largestSubarraySumZero(new int[]{15, -2, 2, -8, 1, 7, 10, 23})); // 5
        System.out.println(largestSubarraySumZero(new int[]{1, 2, 3}));                      // 0
        System.out.println(largestSubarraySumZero(new int[]{1, -1, 3, -3, 2}));              // 4

        // Subarray Sum Equal to K
        System.out.println("─── Subarray Sum Equal to K ───");
        System.out.println(subarraySumK(new int[]{1, 1, 1}, 2));          // 2
        System.out.println(subarraySumK(new int[]{1, 2, 3}, 3));          // 2  (3 and 1+2)
        System.out.println(subarraySumK(new int[]{3, 4, 7, 2, -3, 1, 4, 2}, 7)); // 4
    }
}
