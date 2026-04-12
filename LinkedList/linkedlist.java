import java.util.*;
public class linkedlist {

    //Add first
    public static ListNode addFirst(ListNode head, int val) {
        ListNode newNode = new ListNode(val);
        newNode.next = head;
        return newNode;
    }

    //Add last
    public static ListNode addLast(ListNode head, int val) {    
        ListNode newNode = new ListNode(val);
        if (head == null) return newNode;
        ListNode curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = newNode;
        return head;
    }

    // Add at index
    public static ListNode addAtIndex(ListNode head, int index, int val) {
        if (index < 0) return head;
        if (index == 0) return addFirst(head, val);
        ListNode newNode = new ListNode(val);
        ListNode curr = head;
        for (int i = 0; i < index - 1; i++) {
            if (curr == null) return head;
            curr = curr.next;
        }
        if (curr == null) return head;
        newNode.next = curr.next;
        curr.next = newNode;
        return head;
    }


    // delete at index
    public static ListNode deleteAtIndex(ListNode head, int index) {
        if (index < 0 || head == null) return head;
        if (index == 0) return head.next;
        ListNode curr = head;
        for (int i = 0; i < index - 1; i++) {
            if (curr.next == null) return head;
            curr = curr.next;
        }
        if (curr.next == null) return head;
        curr.next = curr.next.next;
        return head;
    }



    // reverse linked list
    public static ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    } 

//check palindrome
    public static boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode secondHalf = reverseList(slow);
        ListNode firstHalf = head;
        while (secondHalf != null) {
            if (firstHalf.val != secondHalf.val)
                return false;
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        return true;
    }


    //loop detection
    public static boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }

    //remove of loop
    public static ListNode removeCycle(ListNode head) {
        if (head == null) return null;
        ListNode slow = head, fast = head;
        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        if (!hasCycle) return head;

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        ListNode cycleStart = slow;

        ListNode prev = null, curr = cycleStart;
        do {
            prev = curr;
            curr = curr.next;
        } while (curr != cycleStart);
        prev.next = null;

        return head;
    }

    public static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) {
                System.out.print(" -> ");
            }
            curr = curr.next;
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

        System.out.print("Original list: ");
        printList(head);

        head = reverseList(head);

        System.out.print("Reversed list: ");
        printList(head);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }
}
