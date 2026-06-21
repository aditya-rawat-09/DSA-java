import java.util.*;

public class StringMedium {

    // Q.1 shortest path in a string direction sequence
    // Given a string of directions (N, S, E, W), find shortest path distance from origin
    // TC: O(n), SC: O(1)
    public static int shortestPath(String path) {
        int x = 0, y = 0;
        for (char c : path.toCharArray()) {
            if      (c == 'N') y++;
            else if (c == 'S') y--;
            else if (c == 'E') x++;
            else if (c == 'W') x--;
        }
        return (int) Math.sqrt(x * x + y * y);
    }

    public static void main(String[] args) {
        // Shortest path
        System.out.println(shortestPath("NESW"));  // 0 (back to origin)
        System.out.println(shortestPath("NE"));    // 1 (diagonal = sqrt(2) ~ 1)
        System.out.println(shortestPath("NNNEE")); // 3 (sqrt(4+9) ~ 3)
    }
}
