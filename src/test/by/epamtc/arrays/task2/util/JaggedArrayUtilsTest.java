package by.epamtc.arrays.task2.util;

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

    static boolean checkSortedJaggedArray(int[][] jaggedArray, Comparator<int[]> comparator) {
        if (jaggedArray == null || jaggedArray.length <= 1) {
            return true;
        }
        for (int i = 1; i < jaggedArray.length; i++) {
            if (comparator.compare(jaggedArray[i - 1], jaggedArray[i]) > 0) {
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

    static void sortTest(Comparator<int[]> comparator) throws SortArgumentsException {
        JaggedArrayUtils.sort(staticJaggedArray, comparator);
        assertTrue(checkSortedJaggedArray(staticJaggedArray, comparator));
    }

    static void comparatorTest(int[][] jaggedArray, Comparator<int[]> comparator) {
        assertTrue(checkSortedJaggedArray(jaggedArray, comparator));
        assertFalse(checkSortedJaggedArray(jaggedArray, comparator.reversed()));
        reverseJaggedArray(jaggedArray);
        assertFalse(checkSortedJaggedArray(jaggedArray, comparator));
        assertTrue(checkSortedJaggedArray(jaggedArray, comparator.reversed()));
    }

    @AfterEach
    void reset() {
        resetJuggedArray();
    }

    @Test
    void sortViaRowSumAscendingTest() throws SortArgumentsException {
        sortTest(JaggedArrayUtils.ROW_SUM_COMPARATOR);
    }

    @Test
    void sortViaRowSumDescendingTest() throws SortArgumentsException {
        sortTest(JaggedArrayUtils.ROW_SUM_COMPARATOR.reversed());

    }

    @Test
    void sortViaMaxElementAscendingTest() throws SortArgumentsException {
        sortTest(JaggedArrayUtils.MAX_ELEMENT_COMPARATOR);
    }

    @Test
    void sortViaMaxElementDescendingTest() throws SortArgumentsException {
        sortTest(JaggedArrayUtils.MAX_ELEMENT_COMPARATOR.reversed());
    }

    @Test
    void sortViaMinElementAscendingTest() throws SortArgumentsException {
        sortTest(JaggedArrayUtils.MIN_ELEMENT_COMPARATOR);
    }

    @Test
    void sortViaMinElementDescendingTest() throws SortArgumentsException {
        sortTest(JaggedArrayUtils.MIN_ELEMENT_COMPARATOR.reversed());
    }

    @Test
    void sumComparatorTest() {
        int[][] jaggedArray = {null, {-20}, {-15}, {12, -20}, {}, {}, {0}, {12, 5}, {-14, 40}};
        comparatorTest(jaggedArray, JaggedArrayUtils.ROW_SUM_COMPARATOR);
    }

    @Test
    void maxElementComparatorTest() {
        int[][] jaggedArray = {null, {}, {}, {-15, 0}, {12, -20}, {12, 5, -11}, {-20, 16}, {-14, 40}, {0, 63, 85}};
        assertTrue(checkSortedJaggedArray(jaggedArray, JaggedArrayUtils.MAX_ELEMENT_COMPARATOR));
        comparatorTest(jaggedArray, JaggedArrayUtils.MAX_ELEMENT_COMPARATOR);
    }

    @Test
    void minElementComparatorTest() {
        int[][] jaggedArray = {null, {}, {}, {12, -20}, {-20, 16}, {-15, 0}, {-14, 40}, {12, 5, -11}, {0, 63, 85}};
        comparatorTest(jaggedArray, JaggedArrayUtils.MIN_ELEMENT_COMPARATOR);
    }
}