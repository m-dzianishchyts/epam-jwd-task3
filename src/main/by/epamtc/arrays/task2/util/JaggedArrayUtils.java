package by.epamtc.arrays.task2.util;

import java.util.Comparator;

public final class JaggedArrayUtils {

    public static final Comparator<int[]> ROW_SUM_COMPARATOR = (row1, row2) -> {
        var optionalSum1 = ArrayUtils.sum(row1);
        var optionalSum2 = ArrayUtils.sum(row2);
        if (optionalSum1.isEmpty() || optionalSum2.isEmpty()) {
            return compareNullableRows(row1, row2);
        }
        int sum1 = optionalSum1.get();
        int sum2 = optionalSum2.get();
        return Integer.compare(sum1, sum2);
    };
    public static final Comparator<int[]> MAX_ELEMENT_COMPARATOR = (row1, row2) -> {
        var indexOfMax1 = ArrayUtils.findIndexOfMax(row1);
        var indexOfMax2 = ArrayUtils.findIndexOfMax(row2);
        if (indexOfMax1.isEmpty() && indexOfMax2.isPresent()) {
            return -1;
        }
        if (indexOfMax1.isPresent() && indexOfMax2.isEmpty()) {
            return 1;
        }
        if (indexOfMax1.isPresent()) {
            int min1 = row1[indexOfMax1.getAsInt()];
            int min2 = row2[indexOfMax2.getAsInt()];
            return Integer.compare(min1, min2);
        } else {
            return compareNullableRows(row1, row2);
        }
    };
    public static final Comparator<int[]> MIN_ELEMENT_COMPARATOR = (row1, row2) -> {
        var indexOfMin1 = ArrayUtils.findIndexOfMin(row1);
        var indexOfMin2 = ArrayUtils.findIndexOfMin(row2);
        if (indexOfMin1.isEmpty() && indexOfMin2.isPresent()) {
            return -1;
        }
        if (indexOfMin1.isPresent() && indexOfMin2.isEmpty()) {
            return 1;
        }
        if (indexOfMin1.isPresent()) {
            int min1 = row1[indexOfMin1.getAsInt()];
            int min2 = row2[indexOfMin2.getAsInt()];
            return Integer.compare(min1, min2);
        } else {
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
    public static void sort(int[][] jaggedArray, Comparator<int[]> comparator) throws SortArgumentsException {
        if (jaggedArray == null) {
            throw new SortArgumentsException("Jagged array cannot be null");
        }
        if (comparator == null) {
            throw new SortArgumentsException("Comparator cannot be null");
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
