package by.epamtc.arrays.task1.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public final class IntegerArrayUtil {

    private static final Set<Integer> fibonacciNumbersCache = new HashSet<>();

    static {
        prepareFibonacciNumbers();
    }

    private static void prepareFibonacciNumbers() {
        int fibonacciNumber1 = 0;
        int fibonacciNumber2 = 1;
        while ((long) fibonacciNumber1 + fibonacciNumber2 <= Integer.MAX_VALUE) {
            int fibonacciNumber3 = fibonacciNumber1 + fibonacciNumber2;
            fibonacciNumbersCache.add(fibonacciNumber3);
            fibonacciNumber1 = fibonacciNumber2;
            fibonacciNumber2 = fibonacciNumber3;
        }
    }

    private IntegerArrayUtil() {
    }

    public static void doRadixSort(int[] array) {
        final int byteValuesRangeSize = 1 << Byte.SIZE;
        final int byteMask = byteValuesRangeSize - 1;
        int[] auxiliaryArray = new int[array.length];

        for (int byteIndex = 0; byteIndex < Integer.BYTES; byteIndex++) {

            // Compute frequency counts.
            int[] frequencyCounters = new int[byteValuesRangeSize + 1];
            for (int value : array) {
                int filteredByteValue = (value >> Byte.SIZE * byteIndex) & byteMask;
                frequencyCounters[filteredByteValue + 1]++;
            }

            // Compute cumulates
            for (int byteValue = 0; byteValue < byteValuesRangeSize; byteValue++) {
                frequencyCounters[byteValue + 1] += frequencyCounters[byteValue];
            }

            // For most significant byte 0x80-0xFF comes before 0x00-0x7F.
            if (byteIndex == Integer.BYTES - 1) {
                int shift1 = frequencyCounters[byteValuesRangeSize] - frequencyCounters[byteValuesRangeSize / 2];
                int shift2 = frequencyCounters[byteValuesRangeSize / 2];
                for (int byteValue = 0; byteValue < byteValuesRangeSize / 2; byteValue++) {
                    frequencyCounters[byteValue] += shift1;
                }
                for (int byteValue = byteValuesRangeSize / 2; byteValue < byteValuesRangeSize; byteValue++) {
                    frequencyCounters[byteValue] -= shift2;
                }
            }

            // Permutation by cumulates.
            for (int value : array) {
                int filteredByteValue = (value >> Byte.SIZE * byteIndex) & byteMask;
                auxiliaryArray[frequencyCounters[filteredByteValue]++] = value;
            }

            // Copy back to original.
            System.arraycopy(auxiliaryArray, 0, array, 0, array.length);
        }
    }

    public static List<Integer> extractFibonacciNumbers(int[] array) {
        List<Integer> result = new ArrayList<>();
        for (var value : array) {
            if (fibonacciNumbersCache.contains(value)) {
                result.add(value);
            }
        }
        return result;
    }
}
