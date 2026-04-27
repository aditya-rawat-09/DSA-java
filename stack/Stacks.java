import java.util.*;
public  class Stacks {
   //  Q.1 push at bottom of stack
    public static void pushAtBottom(Stack<Integer> s, int data) {
        if (s.isEmpty()) {
            s.push(data);
            return;
        }
        int top = s.pop();
        pushAtBottom(s, data);
        s.push(top);
    }


    // Q.2 reverse a stack
    public static void reverseStack(Stack<Integer> s) {
        if (s.isEmpty()) {
            return;
        }
        int top = s.pop();
        reverseStack(s);
        pushAtBottom(s, top);
    }


    // Q.3 sort a stack
    public static void sortStack(Stack<Integer> s) {
        if (s.isEmpty()) return;
        int top = s.pop();
        sortStack(s);
        insertSorted(s, top);
    }

    private static void insertSorted(Stack<Integer> s, int data) {
        if (s.isEmpty() || s.peek() <= data) {
            s.push(data);
            return;
        }
        int top = s.pop();
        insertSorted(s, data);
        s.push(top);
    }

    // Q.4 next greater element
    public static int[] nextGreaterElement(int[] arr) {
        int[] nge = new int[arr.length];
        Stack<Integer> s = new Stack<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!s.isEmpty() && s.peek() <= arr[i]) {
                s.pop();
            }
            if (s.isEmpty()) {
                nge[i] = -1;
            } else {
                nge[i] = s.peek();
            }
            s.push(arr[i]);
        }
        return nge;
    }



    
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(2);
        s.push(3);
        pushAtBottom(s, 4);
        while (!s.isEmpty()) {
            System.out.println(s.pop());
        }

        Stack<Integer> s2 = new Stack<>();
        s2.push(1);
        s2.push(2);
        s2.push(3);
        reverseStack(s2);
        while (!s2.isEmpty()) {
            System.out.println(s2.pop());
        }

        Stack<Integer> s3 = new Stack<>();
        s3.push(3); s3.push(1); s3.push(4); s3.push(2);
        sortStack(s3);
        System.out.print("Sorted Stack: ");
        while (!s3.isEmpty()) System.out.print(s3.pop() + " ");
        System.out.println();

        int[] arr = {4, 5, 2, 10, 8};
        int[] nge = nextGreaterElement(arr);
        System.out.print("Next Greater Elements: ");
        for (int x : nge) System.out.print(x + " ");
        System.out.println();
    }
}
