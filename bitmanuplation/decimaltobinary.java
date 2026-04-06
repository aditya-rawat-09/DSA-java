import java.util.Scanner;
import java.util.logging.Logger;

public class decimaltobinary {

    private static final Logger logger = Logger.getLogger(decimaltobinary.class.getName());

    public static void dectobin(int n) {
        int binary = 0, pow = 0;
        while (n > 0) {
            int lastDigit = n % 2;
            binary += lastDigit * (int) Math.pow(10, pow);
            pow++;
            n /= 2;
        }
        logger.info("Binary number is: " + binary);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        logger.info("Enter a decimal number:");
        int n = scanner.nextInt();
        if (n >= 0) {
            dectobin(n);
        } else {
            logger.warning("Invalid input: negative numbers not supported.");
        }
        scanner.close();
    }
}
