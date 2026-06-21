// Zigzag Linked List
// Goal: rearrange list so it goes: first half node → last half node → second node → second last → ...
// Example: 1 -> 2 -> 3 -> 4 -> 5  becomes  1 -> 5 -> 2 -> 4 -> 3
//
// Steps:
//   1. Find the middle of the list
//   2. Reverse the second half
//   3. Merge both halves alternately (one from each)
// Time: O(n) | Space: O(1)

public class zigzag {

    public static ListNode zigzagList(ListNode head) {
        if (head == null || head.next == null) return head;

        // step 1: find middle using fast/slow pointers
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // step 2: reverse the second half
        ListNode secondHalf = reverse(slow.next);
        slow.next = null; // cut the list into two halves

        // step 3: merge both halves alternately
        ListNode first = head;
        ListNode second = secondHalf;

        while (second != null) {
            ListNode tmp1 = first.next;  // save next of first half
            ListNode tmp2 = second.next; // save next of second half

            first.next = second;  // first → second
            second.next = tmp1;   // second → next of first

            first = tmp1;         // move forward in first half
            second = tmp2;        // move forward in second half
        }

        return head;
    }

    // reverse a linked list
    private static ListNode reverse(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    // helper to add node at end
    public static ListNode addLast(ListNode head, int val) {
        ListNode node = new ListNode(val);
        if (head == null) return node;
        ListNode curr = head;
        while (curr.next != null) curr = curr.next;
        curr.next = node;
        return head;
    }

    // helper to print list
    public static void print(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode head = null;
        head = addLast(head, 1);
        head = addLast(head, 2);
        head = addLast(head, 3);
        head = addLast(head, 4);
        head = addLast(head, 5);

        System.out.print("Before: ");
        print(head);

        head = zigzagList(head);

        System.out.print("After:  ");
        print(head);
        // Output: 1 -> 5 -> 2 -> 4 -> 3
    }
}
