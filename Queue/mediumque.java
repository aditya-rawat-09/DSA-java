
import java.util.*;
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

    //connect n ropes with minimum cost
    public static int minCost(int[] ropes) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int rope : ropes) {
            pq.add(rope);
        }
        int cost = 0;
        while (pq.size() > 1) {
            int a = pq.remove();
            int b = pq.remove();
            cost += a + b;
            pq.add(a + b);
        }
        return cost;
    }

    //job scheduling with deadlines and profits
    public static void jobScheduling(int[] deadlines, int[] profits) {
        int n = deadlines.length;
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(deadlines[i], profits[i]);
        }
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);
        boolean[] slot = new boolean[n];
        int totalProfit = 0;
        for (Job job : jobs) {
            for (int j = Math.min(n, job.deadline) - 1; j >= 0; j--) {
                if (!slot[j]) {
                    slot[j] = true;
                    totalProfit += job.profit;
                    break;
                }
            }
        }
        System.out.println("Total Profit: " + totalProfit);
    }

    //Maximum of all subarrays of size k
    

    public static void main(String[] args) {
        int n = 10;
        System.out.println("Binary numbers from 1 to " + n + ":");
        generateBinaryNumbers(n);
    }
}