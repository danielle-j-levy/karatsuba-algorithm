import java.math.BigInteger;
import java.util.Random;


public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        BigInteger x = new BigInteger(500, rand);
        BigInteger y = new BigInteger(500, rand);
        System.out.printf("first number is %s%n", x);
        System.out.printf("second number is %s%n", y);
        BigInteger multiplier = karatsubaMultiplication(x, y);
        System.out.printf("karatsuba multiplier: %s%n", multiplier);
        System.out.printf("regular multiplier: %s%n", x.multiply(y));
        System.out.printf("is karatsuba multiplication correct? %s%n", x.multiply(y).equals(multiplier));
    }

    public static BigInteger karatsubaMultiplication(BigInteger x, BigInteger y) {
        int maxNumLen = Math.max(x.bitLength(), y.bitLength());
        int dividedLen = (maxNumLen + 1) / 2;

        if (maxNumLen <= 1) {
            return x.multiply(y);
        }

        BigInteger a = x.shiftRight(dividedLen);
        BigInteger b = x.subtract(a.shiftLeft(dividedLen));
        BigInteger c = y.shiftRight(dividedLen);
        BigInteger d = y.subtract(c.shiftLeft(dividedLen));
        BigInteger A1 = karatsubaMultiplication(a, c);
        BigInteger A2 = karatsubaMultiplication(b, d);
        BigInteger A3 = karatsubaMultiplication(a.add(b), c.add(d));
        return A1.multiply(BigInteger.valueOf(2).pow(2 * dividedLen))
                .add(A3.subtract(A1).subtract(A2).multiply(BigInteger.valueOf(2).pow(dividedLen)))
                .add(A2);
    }
}
