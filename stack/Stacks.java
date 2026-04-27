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
    }
}
