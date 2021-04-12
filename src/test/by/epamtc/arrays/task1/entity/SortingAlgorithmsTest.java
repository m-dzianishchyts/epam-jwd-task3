package by.epamtc.arrays.task1.entity;

import by.epamtc.arrays.task1.exception.InvalidArgumentException;
import by.epamtc.arrays.task1.util.IntegerArrayUtil;
import by.epamtc.arrays.task1.util.RandomUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SortingAlgorithmsTest {

    private static Array array;
    private static int[] initialArrayContent;
    private static int[] expectedSortedArrayContent;

    private static final int SORTING_TESTS_AMOUNT = 10;

    @BeforeAll
    @Order(1)
    static void initArray() throws InvalidArgumentException {
        int arraySize = (int) Math.pow(2, 20);
        int minInitialValue = (int) -Math.pow(2, 10);
        int maxInitialValue = (int) Math.pow(2, 10);
        initialArrayContent = new int[arraySize];
        for (int i = 0; i < initialArrayContent.length; i++) {
            initialArrayContent[i] = RandomUtils.randomInt(minInitialValue, maxInitialValue);
        }
        array = new Array(Arrays.copyOf(initialArrayContent, initialArrayContent.length));
    }

    @BeforeAll
    @Order(2)
    static void prepareExpectedSortResult() {
        expectedSortedArrayContent = Arrays.copyOf(initialArrayContent, initialArrayContent.length);
        Arrays.sort(expectedSortedArrayContent);
    }

    @AfterEach
    void resetArrayContent() throws InvalidArgumentException {
        array = new Array(Arrays.copyOf(initialArrayContent, initialArrayContent.length));
    }

    @RepeatedTest(SORTING_TESTS_AMOUNT)
    void doQuickSortTest() {
        array.doQuickSort();
        assertArrayEquals(expectedSortedArrayContent, array.getContent());
    }

    @RepeatedTest(SORTING_TESTS_AMOUNT)
    void doMergeSortTest() {
        array.doMergeSort();
        assertArrayEquals(expectedSortedArrayContent, array.getContent());
    }

    @RepeatedTest(SORTING_TESTS_AMOUNT)
    void doRadixSortTest() {
        IntegerArrayUtil.doRadixSort(array.getContent());
        assertArrayEquals(expectedSortedArrayContent, array.getContent());
    }
}
