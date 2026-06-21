package patterns;

public class FastSlowPointer {

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
