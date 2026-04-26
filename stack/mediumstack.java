import java.util.*;
public class mediumstack {
    // Q.1 valid parentheses
    public static boolean isValidParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if ((c == ')' && top != '(') || (c == '}' && top != '{') || (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    // Q.2 stock span problem
    public static int[] stockSpan(int[] arr) {
        int[] span = new int[arr.length];
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!s.isEmpty() && arr[s.peek()] <= arr[i]) {
                s.pop();
            }
            if (s.isEmpty()) {
                span[i] = i + 1;
            } else {
                span[i] = i - s.peek();
            }
            s.push(i);
        }
        return span;
    }

    // Q.3 duplicate parentheses
    public static boolean hasDuplicateParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == ')') {
                if (stack.isEmpty() || stack.peek() == '(') {
                    return true;
                }
                while (!stack.isEmpty() && stack.peek() != '(') {
                    stack.pop();
                }
                stack.pop(); // Pop the opening parenthesis
            } else {
                stack.push(c);
            }
        }
        return false;
    }



    // Q.4 largest rectangle in histogram
    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> s = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i <= heights.length; i++) {
            while (!s.isEmpty() && (i == heights.length || heights[s.peek()] >= heights[i])) {
                int height = heights[s.pop()];
                int width = s.isEmpty() ? i : i - s.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            s.push(i);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        String s = "({[]})";
        System.out.println(isValidParentheses(s));

         int[] arr = {100, 80, 60, 70, 60, 75, 85};
         int[] span = stockSpan(arr);
         System.out.println("Stock Span:");
         for (int i = 0; i < span.length; i++) {
             System.out.print(span[i] + " ");
         }
         System.out.println();

         String s2 = "((a+b))";
         System.out.println(hasDuplicateParentheses(s2));

         int[] heights = {2, 1, 5, 6, 2, 3};
         System.out.println("Largest Rectangle Area: " + largestRectangleArea(heights));
        }
}
