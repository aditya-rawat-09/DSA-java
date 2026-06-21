public class LinkedListStack {

    Node top;

    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public void push(int data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
    }

    public int pop() {
        if (isEmpty()) { System.out.println("Stack Underflow"); return -1; }
        int val = top.data;
        top = top.next;
        return val;
    }

    public int peek() {
        if (isEmpty()) { System.out.println("Stack is empty"); return -1; }
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public static void main(String[] args) {
        LinkedListStack s = new LinkedListStack();
        s.push(10); s.push(20); s.push(30);
        System.out.println("Peek: " + s.peek());    // 30
        System.out.println("Pop: "  + s.pop());     // 30
        System.out.println("Empty: "+ s.isEmpty()); // false
    }
}
