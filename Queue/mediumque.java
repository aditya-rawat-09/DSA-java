
import java.util.*;
import java.util.LinkedList;
import java.util.Queue;

public class mediumque {

    //generate binary numbers from 1 to n
    public static void generateBinaryNumbers(int n) {
        Queue<String> q = new LinkedList<String>();
        q.add("1");
        for (int i = 0; i < n; i++) {
            String s = q.remove();
            System.out.println(s);
            q.add(s + "0");
            q.add(s + "1");
        }
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println("Binary numbers from 1 to " + n + ":");
        generateBinaryNumbers(n);
    }
}