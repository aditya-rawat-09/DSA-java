// Basic LinkedList operations
// covers: add, delete, reverse, palindrome check, cycle detection
public class linkedlist {

    // add a new node at the beginning
    public static ListNode addFirst(ListNode head, int val) {
        ListNode node = new ListNode(val);
        node.next = head;
        return node;
    }

    // add a new node at the end
    public static ListNode addLast(ListNode head, int val) {
        ListNode node = new ListNode(val);
        if (head == null) return node;
        ListNode curr = head;
        while (curr.next != null) curr = curr.next;
        curr.next = node;
        return head;
    }

    // add a new node at a given index
    public static ListNode addAtIndex(ListNode head, int index, int val) {
        if (index == 0) return addFirst(head, val);
        ListNode node = new ListNode(val);
        ListNode curr = head;
        for (int i = 0; i < index - 1; i++) {
            if (curr == null) return head;
            curr = curr.next;
        }
        if (curr == null) return head;
        node.next = curr.next;
        curr.next = node;
        return head;
    }

    // delete node at a given index
    public static ListNode deleteAtIndex(ListNode head, int index) {
        if (head == null || index < 0) return head;
        if (index == 0) return head.next;
        ListNode curr = head;
        for (int i = 0; i < index - 1; i++) {
            if (curr.next == null) return head;
            curr = curr.next;
        }
        if (curr.next != null) curr.next = curr.next.next;
        return head;
    }

    // reverse the entire linked list
    public static ListNode reverse(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    // check if linked list is a palindrome
    // idea: find middle → reverse second half → compare both halves
    public static boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        ListNode slow = head, fast = head;

        // find middle using fast/slow pointers
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode secondHalf = reverse(slow); // reverse second half
        ListNode first = head;

        while (secondHalf != null) {
            if (first.val != secondHalf.val) return false;
            first = first.next;
            secondHalf = secondHalf.next;
        }
        return true;
    }

    // detect if linked list has a cycle (loop)
    public static boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true; // they met → cycle exists
        }
        return false;
    }

    // remove the cycle from linked list if it exists
    public static ListNode removeCycle(ListNode head) {
        ListNode slow = head, fast = head;
        boolean hasCycle = false;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) { hasCycle = true; break; }
        }

        if (!hasCycle) return head;

        // find start of cycle
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        // find the last node in cycle and break it
        ListNode prev = null, curr = slow;
        do {
            prev = curr;
            curr = curr.next;
        } while (curr != slow);
        prev.next = null;

        return head;
    }

    // rotate linked list to the right by k places
    // idea: find length → make it circular → break at (len - k % len - 1)
    // TC: O(n), SC: O(1)
    public static ListNode rotateByK(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        // find length and tail
        int len = 1;
        ListNode tail = head;
        while (tail.next != null) { tail = tail.next; len++; }

        k = k % len;
        if (k == 0) return head;

        // make circular
        tail.next = head;

        // new tail is at (len - k - 1) steps from head
        ListNode newTail = head;
        for (int i = 0; i < len - k - 1; i++) newTail = newTail.next;

        ListNode newHead = newTail.next;
        newTail.next = null; // break the circle
        return newHead;
    }

    // print the list
    public static void print(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(" -> ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode head = null;
        head = addLast(head, 1);
        head = addLast(head, 2);
        head = addLast(head, 3);
        head = addLast(head, 2);
        head = addLast(head, 1);

        System.out.print("Original: ");
        print(head);

        System.out.println("Palindrome: " + isPalindrome(head));

        head = reverse(head);
        System.out.print("Reversed: ");
        print(head);

        // rotate by k
        ListNode head2 = null;
        head2 = addLast(head2, 1);
        head2 = addLast(head2, 2);
        head2 = addLast(head2, 3);
        head2 = addLast(head2, 4);
        head2 = addLast(head2, 5);
        System.out.print("Before rotate: "); print(head2);
        head2 = rotateByK(head2, 2);
        System.out.print("After rotate k=2: "); print(head2); // 4 -> 5 -> 1 -> 2 -> 3
    }
}
