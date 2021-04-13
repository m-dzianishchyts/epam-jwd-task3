package by.epamtc.arrays.task2.util;

import by.epamtc.arrays.exception.InvalidArgumentException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JaggedArrayUtilsTest {

    private static final int JUGGED_ARRAY_ROWS_AMOUNT = randomInt(10, 20);

    private static int[][] initialJaggedArray;
    private static int[][] staticJaggedArray;

    @BeforeAll
    static void initJaggedArray() {
        initialJaggedArray = new int[JUGGED_ARRAY_ROWS_AMOUNT][];
        for (int i = 0; i < initialJaggedArray.length; i++) {
            int rowLength = randomInt(-5, 15);
            initialJaggedArray[i] = rowLength >= 0 ? new int[rowLength] : null;
            initArray(initialJaggedArray[i]);
        }
        resetJuggedArray();
    }

    static void initArray(int[] array) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                array[i] = randomInt(-20, 20);
            }
        }
    }

    private static int randomInt(int minValue, int maxValue) {
        int randomInt = (int) (Math.random() * (maxValue - minValue) + minValue);
        return randomInt;
    }

    private static void resetJuggedArray() {
        staticJaggedArray = Arrays.stream(initialJaggedArray).map(ints -> {
            if (ints == null) {
                return null;
            }
            return ints.clone();
        }).toArray(int[][]::new);
    }

    static boolean checkSortedJaggedArray(int[][] jaggedArray, Comparator<int[]> comparator, SortingMode sortingMode) {
        if (sortingMode == SortingMode.DESCENDING) {
            comparator = comparator.reversed();
        }
        for (int i = 1; i < jaggedArray.length; i++) {
            if (comparator.compare(jaggedArray[i], jaggedArray[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }

    static void reverseJaggedArray(int[][] jaggedArray) {
        for (int i = 0; i < jaggedArray.length / 2; i++) {
            JaggedArrayUtils.swap(jaggedArray, i, jaggedArray.length - 1 - i);
        }
    }

    static void sortTest(Comparator<int[]> comparator, SortingMode sortingMode) throws InvalidArgumentException {
        JaggedArrayUtils.sort(staticJaggedArray, comparator, sortingMode);
        assertTrue(checkSortedJaggedArray(staticJaggedArray, comparator, sortingMode));
    }

    static void comparatorTest(int[][] jaggedArray, Comparator<int[]> comparator, SortingMode sortingMode) {
        SortingMode oppositeSortingMode = sortingMode == SortingMode.ASCENDING
                                          ? SortingMode.DESCENDING
                                          : SortingMode.ASCENDING;
        assertTrue(checkSortedJaggedArray(jaggedArray, comparator, sortingMode));
        assertFalse(checkSortedJaggedArray(jaggedArray, comparator, oppositeSortingMode));
        reverseJaggedArray(jaggedArray);
        assertFalse(checkSortedJaggedArray(jaggedArray, comparator, sortingMode));
        assertTrue(checkSortedJaggedArray(jaggedArray, comparator, oppositeSortingMode));
    }

    @AfterEach
    void reset() {
        resetJuggedArray();
    }

    @Test
    void sortViaRowSumAscendingTest() throws InvalidArgumentException {
        sortTest(JaggedArrayUtils.ROW_SUM_COMPARATOR, SortingMode.ASCENDING);
    }

    @Test
    void sortViaRowSumDescendingTest() throws InvalidArgumentException {
        sortTest(JaggedArrayUtils.ROW_SUM_COMPARATOR, SortingMode.DESCENDING);

    }

    @Test
    void sortViaMaxElementAscendingTest() throws InvalidArgumentException {
        sortTest(JaggedArrayUtils.MAX_ELEMENT_COMPARATOR, SortingMode.ASCENDING);
    }

    @Test
    void sortViaMaxElementDescendingTest() throws InvalidArgumentException {
        sortTest(JaggedArrayUtils.MAX_ELEMENT_COMPARATOR, SortingMode.DESCENDING);
    }

    @Test
    void sortViaMinElementAscendingTest() throws InvalidArgumentException {
        sortTest(JaggedArrayUtils.MIN_ELEMENT_COMPARATOR, SortingMode.ASCENDING);
    }

    @Test
    void sortViaMinElementDescendingTest() throws InvalidArgumentException {
        sortTest(JaggedArrayUtils.MIN_ELEMENT_COMPARATOR, SortingMode.DESCENDING);
    }

    @Test
    void sumComparatorTest() {
        int[][] jaggedArray = {null, {-20}, {-15}, {12, -20}, {}, {}, {0}, {12, 5}, {-14, 40}};
        comparatorTest(jaggedArray, JaggedArrayUtils.ROW_SUM_COMPARATOR, SortingMode.ASCENDING);
    }

    @Test
    void maxElementComparatorTest() {
        int[][] jaggedArray = {null, {}, {}, {-15, 0}, {12, -20}, {12, 5, -11}, {-20, 16}, {-14, 40}, {0, 63, 85}};
        assertTrue(checkSortedJaggedArray(jaggedArray, JaggedArrayUtils.MAX_ELEMENT_COMPARATOR, SortingMode.ASCENDING));
        comparatorTest(jaggedArray, JaggedArrayUtils.MAX_ELEMENT_COMPARATOR, SortingMode.ASCENDING);
    }

    @Test
    void minElementComparatorTest() {
        int[][] jaggedArray = {null, {}, {}, {12, -20}, {-20, 16}, {-15, 0}, {-14, 40}, {12, 5, -11}, {0, 63, 85}};
        comparatorTest(jaggedArray, JaggedArrayUtils.MIN_ELEMENT_COMPARATOR, SortingMode.ASCENDING);
    }
}