public class InvertedPyramid {
    public static void pyramid(int row){
         // You can change the number of rows here

        for (int i = 1; i <= row; i++) {
            // Print leading spaces
            for (int j = 1; j <= row-i ; j++) {
                System.out.print(" ");
            }
            // Print stars
            for (int j = 1; j<=i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

    }
    public static void main(String[] args) {
       pyramid(5);
    }
}