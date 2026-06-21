// Merge Sort on Linked List
// Idea: split list into two halves → sort each half → merge them back
// Time: O(n log n) | Space: O(log n) for recursion stack
public class mergesort {

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
}
