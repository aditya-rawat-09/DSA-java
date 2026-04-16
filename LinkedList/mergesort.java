import java.util.logging.Logger;

public class mergesort {

    private static final Logger logger = Logger.getLogger(mergesort.class.getName());

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // merge sort for linked list
    public static ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode mid = getMid(head);
        ListNode left = mergeSort(head);
        ListNode right = mergeSort(mid);
        return merge(left, right);
    }

    public static ListNode merge(ListNode left, ListNode right) {
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
        curr.next = left == null ? right : left;
        return dummy.next;
    }

    public static ListNode getMid(ListNode head) {
        if (head == null) return null;
        ListNode slow = head, fast = head, prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (prev != null) prev.next = null;
        return slow;
    }

    public static ListNode addLast(ListNode head, int val) {
        ListNode node = new ListNode(val);
        if (head == null) return node;
        ListNode curr = head;
        while (curr.next != null) curr = curr.next;
        curr.next = node;
        return head;
    }

    public static void printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) sb.append(" -> ");
            head = head.next;
        }
        logger.info(sb.toString());
    }

    public static void main(String[] args) {
        ListNode head = null;
        head = addLast(head, 5);
        head = addLast(head, 3);
        head = addLast(head, 8);
        head = addLast(head, 1);
        head = addLast(head, 9);
        head = addLast(head, 2);
        printList(head);
        head = mergeSort(head);
        printList(head);
    }
}
