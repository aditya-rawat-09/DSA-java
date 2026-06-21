import java.util.Scanner;
import java.util.logging.Logger;

public class binarytodecimal {

    private static final Logger logger = Logger.getLogger(binarytodecimal.class.getName());

    public static void dectobin(int n) {
        int binary = 0, pow = 0;
        while (n > 0) {
            int lastDigit = n % 10;
            binary += lastDigit * (int) Math.pow(2, pow);
            pow++;
            n /= 10;
        }
        logger.info("Decimal number is: " + binary);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        logger.info("Enter a binary number:");
        int n = scanner.nextInt();
        if (String.valueOf(n).matches("[01]+")) {
            dectobin(n);
        } else {
            logger.warning("Invalid binary number entered.");
        }
        scanner.close();
    }
}
