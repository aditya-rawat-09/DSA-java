public class butterfly {

    static void printRow(int n, int i) {
        for (int j = 1; j <= i; j++) System.out.print("* ");
        for (int j = 1; j <= 2 * (n - i); j++) System.out.print("  ");
        for (int j = 1; j <= i; j++) System.out.print("* ");
        System.out.println();
    }

    public static void main(String[] args) {
        int n = 5;
        for (int i = 1; i <= n; i++) printRow(n, i);
        for (int i = n; i >= 1; i--) printRow(n, i);
    }
}
