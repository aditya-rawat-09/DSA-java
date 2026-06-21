public class circularLL {
    Node head;
    int size;

    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    // Add at end
    public void addLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            newNode.next = head; // Point to itself
        } else {
            Node curr = head;
            while (curr.next != head) curr = curr.next; // Traverse until last node
            curr.next = newNode; // Last node points to new node
            newNode.next = head; // New node points to head
        }
        size++;
    }

    // Add at beginning
    public void addFirst(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            newNode.next = head; // Point to itself
        } else {
            Node curr = head;
            while (curr.next != head) curr = curr.next; // Traverse until last node
            curr.next = newNode; // Last node points to new node
            newNode.next = head; // New node points to old head
            head = newNode; // Update head to new node
        }
        size++;
    }

    // Delete by value
    public void delete(int data) {
        if (head == null) return;
        Node curr = head, prev = null;
        do {
            if (curr.data == data) {
                if (prev == null) {
                    Node last = head;
                    while (last.next != head) last = last.next;
                    if (head == head.next) { head = null; }
                    else { head = head.next; last.next = head; }
                } else {
                    prev.next = curr.next;
                }
                size--;
                return;
            }
            prev = curr;
            curr = curr.next;
        } while (curr != head);
        System.out.println(data + " not found");
    }

    public void print() {
        if (head == null) return;
        Node curr = head;
        do {
            System.out.print(curr.data + " -> ");
            curr = curr.next;
        } while (curr != head);
        System.out.println("null");
    }

    public static void main(String[] args) {
        circularLL list = new circularLL();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addFirst(5);
        list.print();    // 5 -> 10 -> 20 -> 30 -> null
        list.delete(20);
        list.print();    // 5 -> 10 -> 30 -> null
    }
}