public class rhombus {
    public static void main(String[] args) {
        int n = 5; // You can change this value to adjust the size of the rhombus

        // Upper half of the rhombus
        for (int i = 1; i <= n; i++) {
            // Leading spaces
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= n; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
}
