import java.util.logging.Logger;

public class Array2D {

    private static final Logger logger = Logger.getLogger(Array2D.class.getName());

    // diagonal sum
    public static int diagonalSum(int matrix[][]) {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[i][i];
            if (i != matrix.length - 1 - i) {
                sum += matrix[i][matrix.length - 1 - i];
            }
        }
        return sum;
    }

    // spiral matrix
    public static void printspiral(int matrix[][]) {
        int startrow = 0, startcol = 0;
        int endrow = matrix.length - 1, endcol = matrix[0].length - 1;
        StringBuilder sb = new StringBuilder();
        while (startrow <= endrow && startcol <= endcol) {
            for (int j = startcol; j <= endcol; j++)
                sb.append(matrix[startrow][j]).append(" ");
            for (int i = startrow + 1; i <= endrow; i++)
                sb.append(matrix[i][endcol]).append(" ");
            for (int j = endcol - 1; j >= startcol; j--) {
                if (startrow == endrow) break;
                sb.append(matrix[endrow][j]).append(" ");
            }
            for (int i = endrow - 1; i >= startrow + 1; i--) {
                if (startcol == endcol) break;
                sb.append(matrix[i][startcol]).append(" ");
            }
            startrow++; startcol++;
            endrow--;   endcol--;
        }
        logger.info(sb.toString().trim());
    }

    public static void main(String[] args) {
        int matrix[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        logger.info(String.valueOf(diagonalSum(matrix)));
    }
}
