import java.util.logging.Logger;

public class backtracking {

    private static final Logger logger = Logger.getLogger(backtracking.class.getName());

    static char[][] L = {
        {}, {},
        { 'a', 'b', 'c' },
        { 'd', 'e', 'f' },
        { 'g', 'h', 'i' },
        { 'j', 'k', 'l' },
        { 'm', 'n', 'o' },
        { 'p', 'q', 'r', 's' },
        { 't', 'u', 'v' },
        { 'w', 'x', 'y', 'z' }
    };

    // keypad combinations
    public static void letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            logger.info("empty input");
            return;
        }
        solve(digits, 0, new StringBuilder());
    }

    public static void solve(String digits, int pos, StringBuilder sb) {
        if (sb == null) throw new IllegalArgumentException("sb must not be null");
        if (pos == digits.length()) {
            logger.info(sb.toString().replaceAll("[\r\n]", ""));
            return;
        }
        char[] letters = L[Character.getNumericValue(digits.charAt(pos))];
        for (char c : letters) {
            sb.append(c);
            solve(digits, pos + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        letterCombinations("23");
    }
}
