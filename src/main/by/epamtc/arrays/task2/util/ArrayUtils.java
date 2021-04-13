package by.epamtc.arrays.task2.util;

import by.epamtc.arrays.exception.InvalidArgumentException;

public final class ArrayUtils {

    private ArrayUtils() {
    }

    public static int sum(int[] array) throws InvalidArgumentException {
        checkArrayOnNull(array);
        int sum = 0;
        for (var value : array) {
            sum += value;
        }
        return sum;
    }

    private static void checkArrayOnNull(int[] array) throws InvalidArgumentException {
        if (array == null) {
            throw new InvalidArgumentException("Array cannot be null.");
        }
    }

    public static int findMax(int[] array) throws InvalidArgumentException {
        checkArrayOnNull(array);
        checkArrayOnEmpty(array);
        int maxValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }
        return maxValue;
    }

    private static void checkArrayOnEmpty(int[] array) throws InvalidArgumentException {
        if (array.length == 0) {
            throw new InvalidArgumentException("Array is empty.");
        }
    }

    public static int findMin(int[] array) throws InvalidArgumentException {
        checkArrayOnNull(array);
        checkArrayOnEmpty(array);
        int minValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
            }
        }
        return minValue;
    }
}
