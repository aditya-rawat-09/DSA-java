package pattern;

// Pattern: Fast and Slow Pointers (Floyd's Cycle Detection)
// Use this when: you need to detect a cycle in a linked list
// Idea: slow moves 1 step, fast moves 2 steps
//       if there's a cycle, they will eventually meet

public class fastslow {

    // Node of a linked list
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // Returns true if the linked list has a cycle (loop)
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;       // move 1 step
            fast = fast.next.next;  // move 2 steps

            if (slow == fast) return true; // they met → cycle exists
        }

        return false; // fast reached end → no cycle
    }
}
