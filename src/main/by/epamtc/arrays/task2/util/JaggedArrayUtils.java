package by.epamtc.arrays.task2.util;

import by.epamtc.arrays.exception.InvalidArgumentException;

import java.util.Comparator;

public final class JaggedArrayUtils {

    public static final Comparator<int[]> ROW_SUM_COMPARATOR_ASCENDING = (row1, row2) -> {
        try {
            int sum1 = ArrayUtils.sum(row1);
            int sum2 = ArrayUtils.sum(row2);
            return Integer.compare(sum1, sum2);
        } catch (InvalidArgumentException argumentException) {
            return compareNullableRows(row1, row2);
        }
    };
    public static final Comparator<int[]> ROW_SUM_COMPARATOR_DESCENDING = (row1, row2) -> {
        try {
            int sum1 = ArrayUtils.sum(row1);
            int sum2 = ArrayUtils.sum(row2);
            return Integer.compare(sum2, sum1);
        } catch (InvalidArgumentException argumentException) {
            return compareNullableRows(row2, row1);
        }
    };
    public static final Comparator<int[]> MAX_ELEMENT_COMPARATOR_ASCENDING = (row1, row2) -> {
        try {
            int max1 = ArrayUtils.findMax(row1);
            int max2 = ArrayUtils.findMax(row2);
            return Integer.compare(max1, max2);
        } catch (InvalidArgumentException argumentException) {
            return compareNullableRows(row1, row2);
        }
    };
    public static final Comparator<int[]> MAX_ELEMENT_COMPARATOR_DESCENDING = (row1, row2) -> {
        try {
            int max1 = ArrayUtils.findMax(row1);
            int max2 = ArrayUtils.findMax(row2);
            return Integer.compare(max2, max1);
        } catch (InvalidArgumentException argumentException) {
            return compareNullableRows(row2, row1);
        }
    };
    public static final Comparator<int[]> MIN_ELEMENT_COMPARATOR_ASCENDING = (row1, row2) -> {
        try {
            int min1 = ArrayUtils.findMin(row1);
            int min2 = ArrayUtils.findMin(row2);
            return Integer.compare(min1, min2);
        } catch (InvalidArgumentException argumentException) {
            return compareNullableRows(row1, row2);
        }
    };
    public static final Comparator<int[]> MIN_ELEMENT_COMPARATOR_DESCENDING = (row1, row2) -> {
        try {
            int min1 = ArrayUtils.findMax(row1);
            int min2 = ArrayUtils.findMax(row2);
            return Integer.compare(min2, min1);
        } catch (InvalidArgumentException argumentException) {
            return compareNullableRows(row2, row1);
        }
    };

    private JaggedArrayUtils() {
    }

    private static int compareNullableRows(int[] nullableRow1, int[] nullableRow2) {
        if (nullableRow1 == nullableRow2) {
            return 0;
        }
        if (nullableRow1 == null) {
            return -1;
        } else {
            return 1;
        }
    }

    // Bubble sort.
    public static void sort(int[][] jaggedArray, Comparator<int[]> comparator) {
        boolean swapped = true;
        int ceilIndex = jaggedArray.length;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < ceilIndex - 1; i++) {
                if (comparator.compare(jaggedArray[i], jaggedArray[i + 1]) > 0) {
                    swap(jaggedArray, i, i + 1);
                    swapped = true;
                }
            }
            ceilIndex--;
        }
    }

    public static void swap(int[][] jaggedArray, int i, int j) {
        int[] temp = jaggedArray[i];
        jaggedArray[i] = jaggedArray[j];
        jaggedArray[j] = temp;
    }
}
