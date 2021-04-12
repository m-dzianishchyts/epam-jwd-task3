package by.epamtc.arrays.task1.util;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public final class IntegerUtils {

    public static final Predicate<Integer> PRIME_NUMBER = IntegerUtils::isPrime;
    public static final Predicate<Integer> FIBONACCI_NUMBER = IntegerUtils::isFibonacciNumber;
    public static final Predicate<Integer> THREE_DIGIT_NUMBER_WITH_NO_SAME_DIGITS =
            IntegerUtils::isThreeDigitNumberWithNoSameDigits;

    private static final Set<Integer> FIBONACCI_NUMBERS_CACHE = new HashSet<>();
    private static final int MIN_THREE_DIGIT_NUMBER = 100;
    private static final int MAX_THREE_DIGIT_NUMBER = 999;

    static {
        prepareFibonacciNumbers();
    }

    private IntegerUtils() {
    }

    private static void prepareFibonacciNumbers() {
        int fibonacciNumber1 = 0;
        int fibonacciNumber2 = 1;
        while ((long) fibonacciNumber1 + fibonacciNumber2 <= Integer.MAX_VALUE) {
            int fibonacciNumber3 = fibonacciNumber1 + fibonacciNumber2;
            FIBONACCI_NUMBERS_CACHE.add(fibonacciNumber3);
            fibonacciNumber1 = fibonacciNumber2;
            fibonacciNumber2 = fibonacciNumber3;
        }
    }

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isFibonacciNumber(int number) {
        boolean isFibonacciNumber = FIBONACCI_NUMBERS_CACHE.contains(number);
        return isFibonacciNumber;
    }

    public static boolean isThreeDigitNumberWithNoSameDigits(int number) {
        int absValue = Math.abs(number);
        if (absValue < MIN_THREE_DIGIT_NUMBER || absValue > MAX_THREE_DIGIT_NUMBER) {
            return false;
        }
        char[] chars = String.valueOf(absValue).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] == chars[j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
