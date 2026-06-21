package patterns;

import java.util.*;

public class SlidingWindow {
    // finds the length of longest substring with all unique characters
    public int solve(String s) {
        Set<Character> set = new HashSet<>(); // tracks characters in current window
        int l = 0, max = 0;

        for (int r = 0; r < s.length(); r++) {
            // if current char already in window, shrink from left until it's removed
            while (set.contains(s.charAt(r))) {
                set.remove(s.charAt(l)); // remove leftmost char
                l++;                     // move left pointer forward
            }

            set.add(s.charAt(r));              // add new char to window
            max = Math.max(max, r - l + 1);    // update max window size
        }

        return max;
    }
}
