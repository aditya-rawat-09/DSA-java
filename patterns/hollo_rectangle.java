public class hollo_rectangle {

public static void hollo_rectangle(int row, int column){
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                if (i == 1 || j == 1 || i == row || j == column) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                } 

            
        }
System.out.println();
        }

    }

    public static void main(String[] args) {
        hollo_rectangle(19, 40);

    }
}