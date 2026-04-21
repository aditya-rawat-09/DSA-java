public class Stack {

    int[] arr;
    int top;
    int capacity;

    Stack(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        top = -1;
    }

    // Push element onto stack
    public void push(int data) {
        if (top == capacity - 1) {
            System.out.println("Stack Overflow");
            return;
        }
        arr[++top] = data;
    }

    // Pop element from stack
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow");
            return -1;
        }
        return arr[top--];
    }

    // Peek top element
    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return arr[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }

    public void print() {
        for (int i = top; i >= 0; i--) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Stack stack = new Stack(5);
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.print();         // 30 20 10
        System.out.println("Peek: " + stack.peek());  // 30
        System.out.println("Pop: " + stack.pop());    // 30
        stack.print();         // 20 10
        System.out.println("Size: " + stack.size());  // 2
    }
}
