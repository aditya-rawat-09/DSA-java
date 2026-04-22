import java.util.ArrayList;

public class ArrayListStack {

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

    public static void main(String[] args) {
        ArrayListStack s = new ArrayListStack();
        s.push(10); s.push(20); s.push(30);
        System.out.println("Peek: " + s.peek());   // 30
        System.out.println("Pop: "  + s.pop());    // 30
        System.out.println("Empty: "+ s.isEmpty()); // false
    }
}
