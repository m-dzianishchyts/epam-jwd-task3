package by.epamtc.arrays.task2.util;

import java.util.Comparator;
import java.util.OptionalInt;

public final class ArrayUtils {

    private ArrayUtils() {
    }

    public static int sum(int[] array) throws NullArrayException {
        checkArrayOnNull(array);
        int sum = 0;
        for (var value : array) {
            sum += value;
        }
        return sum;
    }

    private static void checkArrayOnNull(int[] array) throws NullArrayException {
        if (array == null) {
            throw new NullArrayException("Array cannot be null.");
        }
    }

    // Returns first index of max element in array.
    public static OptionalInt findIndexOfMax(int[] array) throws NullArrayException {
        return findIndexOfMost(array, Comparator.naturalOrder());
    }

    private static OptionalInt findIndexOfMost(int[] array, Comparator<Integer> comparator)
            throws NullArrayException {
        checkArrayOnNull(array);
        if (array.length == 0) {
            return OptionalInt.empty();
        }
        int indexOfMax = 0;
        for (int i = 1; i < array.length; i++) {
            if (comparator.compare(array[i], array[indexOfMax]) > 0) {
                indexOfMax = i;
            }
        }
        return OptionalInt.of(indexOfMax);
    }

    // Returns first index of min element in array.
    public static OptionalInt findIndexOfMin(int[] array) throws NullArrayException {
        return findIndexOfMost(array, Comparator.reverseOrder());
    }
}
