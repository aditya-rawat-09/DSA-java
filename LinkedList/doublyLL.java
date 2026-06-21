public class doublyLL {

    Node head;
    int size;

    class Node {
        int data;
        Node prev, next;

        Node(int data) {
            this.data = data;
        }
    }

    // Add at end
    public void addLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node curr = head;
            while (curr.next != null) curr = curr.next;
            curr.next = newNode;
            newNode.prev = curr;
        }
        size++;
    }

    // Add at beginning
    public void addFirst(int data) {
        Node newNode = new Node(data);
        if (head != null) {
            newNode.next = head;
            head.prev = newNode;
        }
        head = newNode;
        size++;
    }

    // Delete by value
    public void delete(int data) {
        Node curr = head;
        while (curr != null) {
            if (curr.data == data) {
                if (curr.prev != null) curr.prev.next = curr.next;
                else head = curr.next;
                if (curr.next != null) curr.next.prev = curr.prev;
                size--;
                return;
            }
            curr = curr.next;
        }
        System.out.println(data + " not found");
    }

    public void print() {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " <-> ");
            curr = curr.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        doublyLL list = new doublyLL();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addFirst(5);
        list.print();       // 5 <-> 10 <-> 20 <-> 30 <-> null
        list.delete(20);
        list.print();       // 5 <-> 10 <-> 30 <-> null
    }
}
