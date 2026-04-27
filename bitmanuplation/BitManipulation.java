public class BitManipulation {

    // Q.1 check even or odd
    // last bit 0 = even, 1 = odd
    // TC: O(1)
    public static boolean isEven(int n) {
        return (n & 1) == 0;
    }

    // Q.2 get i-th bit (0-indexed from right)
    // shift 1 to i-th position and AND with n
    // TC: O(1)
    public static int getIthBit(int n, int i) {
        return (n >> i) & 1;
    }

    // Q.3 set i-th bit (make it 1)
    // OR with 1 shifted to i-th position
    // TC: O(1)
    public static int setIthBit(int n, int i) {
        return n | (1 << i);
    }

    // Q.4 clear i-th bit (make it 0)
    // AND with NOT of 1 shifted to i-th position
    // TC: O(1)
    public static int clearIthBit(int n, int i) {
        return n & ~(1 << i);
    }

    // Q.5 clear range of bits from i to j (inclusive, 0-indexed)
    // create a mask with 0s in range [i..j] and 1s elsewhere
    // TC: O(1)
    public static int clearRangeBits(int n, int i, int j) {
        int mask = ~(((1 << (j - i + 1)) - 1) << i);
        return n & mask;
    }

    // Q.6 check if number is power of 2
    // power of 2 has exactly one set bit: n & (n-1) == 0
    // TC: O(1)
    public static boolean isPowerOf2(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    // Q.7 swap two numbers without temp variable
    // using XOR: a^=b, b^=a, a^=b
    // TC: O(1)
    public static int[] swapTwoNumbers(int a, int b) {
        a ^= b;
        b ^= a;
        a ^= b;
        return new int[]{a, b};
    }

    // Q.8 count set bits in a number (Brian Kernighan's algorithm)
    // n & (n-1) clears the lowest set bit each time
    // TC: O(number of set bits)
    public static int countSetBits(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("isEven(4): "          + isEven(4));           // true
        System.out.println("isEven(7): "          + isEven(7));           // false

        System.out.println("getIthBit(10,1): "    + getIthBit(10, 1));    // 1  (10 = 1010)
        System.out.println("getIthBit(10,0): "    + getIthBit(10, 0));    // 0

        System.out.println("setIthBit(10,0): "    + setIthBit(10, 0));    // 11 (1011)
        System.out.println("clearIthBit(10,1): "  + clearIthBit(10, 1));  // 8  (1000)

        System.out.println("clearRange(255,2,5): "+ clearRangeBits(255, 2, 5)); // 195 (11000011)

        System.out.println("isPowerOf2(16): "     + isPowerOf2(16));      // true
        System.out.println("isPowerOf2(18): "     + isPowerOf2(18));      // false

        int[] swapped = swapTwoNumbers(5, 9);
        System.out.println("swap(5,9): "          + swapped[0] + ", " + swapped[1]); // 9, 5

        System.out.println("countSetBits(13): "   + countSetBits(13));    // 3 (1101)
    }
}
