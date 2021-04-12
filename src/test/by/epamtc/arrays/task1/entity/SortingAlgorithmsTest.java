package by.epamtc.arrays.task1.entity;

import by.epamtc.arrays.task1.exception.InvalidArgumentException;
import by.epamtc.arrays.task1.util.IntegerArrayUtil;
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

    @BeforeAll
    @Order(1)
    static void initArray() throws InvalidArgumentException {
        initialArrayContent = new int[(int) Math.pow(2, 20)];
        for (int i = 0; i < initialArrayContent.length; i++) {
            initialArrayContent[i] = (int) (Math.random() * Math.pow(2, 20) - Math.pow(2, 19));
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

    @RepeatedTest(10)
    void doQuickSortTest() {
        array.doQuickSort();
        assertArrayEquals(expectedSortedArrayContent, array.getContent());
    }

    @RepeatedTest(100)
    void doMergeSortTest() {
        array.doMergeSort();
        assertArrayEquals(expectedSortedArrayContent, array.getContent());
    }

    @RepeatedTest(10)
    void doRadixSortTest() {
        IntegerArrayUtil.doRadixSort(array);
        assertArrayEquals(expectedSortedArrayContent, array.getContent());
    }
}
