public class number_pyramid {
    public static void pyramid(int row){

        for (int i = 1; i <= row; i++) {
            // Print leading spaces
            for (int j = 1; j <= row-i ; j++) {
                System.out.print(" ");
            }
            // Print numbers
            for (int j = 1; j<=i; j++) {
                System.out.print(j+" ");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
       pyramid(5);
    }
}
