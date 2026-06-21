package linkedlist;

public class LinkedListProblems {

    // main function: recursively splits and sorts
    public static ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) return head; // base case

        ListNode mid = getMid(head);   // find middle and split
        ListNode left = mergeSort(head); // sort left half
        ListNode right = mergeSort(mid); // sort right half
        return merge(left, right);       // merge both sorted halves
    }

    // merge two sorted linked lists into one sorted list
    private static ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;

        while (left != null && right != null) {
            if (left.val < right.val) {
                curr.next = left;
                left = left.next;
            } else {
                curr.next = right;
                right = right.next;
            }
            curr = curr.next;
        }

        curr.next = (left != null) ? left : right; // attach remaining nodes
        return dummy.next;
    }

    // find middle of list using fast/slow pointers, then split it
    private static ListNode getMid(ListNode head) {
        ListNode slow = head, fast = head, prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (prev != null) prev.next = null; // split the list into two halves
        return slow; // slow is now the start of second half
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
        head = addLast(head, 5);
        head = addLast(head, 3);
        head = addLast(head, 8);
        head = addLast(head, 1);
        head = addLast(head, 9);
        head = addLast(head, 2);

        System.out.print("Before: ");
        print(head);

        head = mergeSort(head);

        System.out.print("After:  ");
        print(head);
    }


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

    public static void demoMain2(String[] args) {
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
