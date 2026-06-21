package stack;

import java.util.ArrayList;

// Bonus: two extra stack implementations backed by ArrayList and LinkedList
public class StackVariants {

    public static class ArrayStack {
        ArrayList<Integer> stack = new ArrayList<>();

        public void push(int data) {
            stack.add(data);
        }

        public int pop() {
            if (isEmpty()) { System.out.println("Stack Underflow"); return -1; }
            return stack.remove(stack.size() - 1);
        }

        public int peek() {
            if (isEmpty()) { System.out.println("Stack is empty"); return -1; }
            return stack.get(stack.size() - 1);
        }

        public boolean isEmpty() {
            return stack.isEmpty();
        }
    }

    public static class LinkedStack {
        Node top;

        static class Node {
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
    }

    public static void main(String[] args) {
        ArrayStack s1 = new ArrayStack();
        s1.push(10); s1.push(20); s1.push(30);
        System.out.println("Peek: " + s1.peek());    // 30
        System.out.println("Pop: "  + s1.pop());     // 30
        System.out.println("Empty: "+ s1.isEmpty()); // false

        LinkedStack s2 = new LinkedStack();
        s2.push(10); s2.push(20); s2.push(30);
        System.out.println("Peek: " + s2.peek());    // 30
        System.out.println("Pop: "  + s2.pop());     // 30
        System.out.println("Empty: "+ s2.isEmpty()); // false
    }
}
