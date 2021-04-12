package by.epamtc.arrays.task2.util;

import by.epamtc.arrays.exception.InvalidArgumentException;

import java.util.Comparator;

public final class JaggedArrayUtils {

    public static final Comparator<int[]> ROW_SUM_COMPARATOR = (row1, row2) -> {
        try {
            int sum1 = ArrayUtils.sum(row1);
            int sum2 = ArrayUtils.sum(row2);
            return Integer.compare(sum1, sum2);
        } catch (InvalidArgumentException argumentException) {
            return compareNullableRows(row1, row2);
        }
    };
    public static final Comparator<int[]> MAX_ELEMENT_COMPARATOR = (row1, row2) -> {
        try {
            int max1 = ArrayUtils.findMax(row1);
            int max2 = ArrayUtils.findMax(row2);
            return Integer.compare(max1, max2);
        } catch (InvalidArgumentException argumentException) {
            return compareNullableRows(row1, row2);
        }
    };
    public static final Comparator<int[]> MIN_ELEMENT_COMPARATOR = (row1, row2) -> {
        try {
            int min1 = ArrayUtils.findMin(row1);
            int min2 = ArrayUtils.findMin(row2);
            return Integer.compare(min1, min2);
        } catch (InvalidArgumentException argumentException) {
            return compareNullableRows(row1, row2);
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
    public static void sort(int[][] jaggedArray, Comparator<int[]> comparator, SortingMode sortingMode)
            throws InvalidArgumentException {
        if (jaggedArray == null) {
            throw new InvalidArgumentException("Jagged array cannot be null");
        }
        if (comparator == null) {
            throw new InvalidArgumentException("Comparator cannot be null");
        }
        if (sortingMode == null) {
            throw new InvalidArgumentException("Sorting mode cannot be null");
        }
        if (sortingMode == SortingMode.DESCENDING) {
            comparator = comparator.reversed();
        }
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
