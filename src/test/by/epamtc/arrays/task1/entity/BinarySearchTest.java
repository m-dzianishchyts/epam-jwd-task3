package by.epamtc.arrays.task1.entity;

import by.epamtc.arrays.task1.exception.InvalidArgumentException;
import by.epamtc.arrays.task1.util.IntegerArrayUtils;
import by.epamtc.arrays.task1.util.RandomUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchTest {

    private static Array array;
    private static final int BINARY_SEARCH_TESTS_AMOUNT = 100;

    @BeforeAll
    static void initArray() throws InvalidArgumentException {
        int arraySize = (int) Math.pow(2, 20);
        int minInitialValue = (int) -Math.pow(2, 10);
        int maxInitialValue = (int) Math.pow(2, 10);
        array = new Array(arraySize);
        array.initByRandomValues(minInitialValue, maxInitialValue);
        IntegerArrayUtils.doRadixSort(array.getContent());
    }

    @RepeatedTest(BINARY_SEARCH_TESTS_AMOUNT)
    void findViaBinarySearch() {
        int minPossibleValue = (int) -Math.pow(2, 10);
        int maxPossibleValue = (int) Math.pow(2, 10);
        int valueToFind = RandomUtils.gengerateRandomInt(minPossibleValue, maxPossibleValue);
        int indexOfValue = array.findViaBinarySearch(valueToFind);
        int expectedIndexOfValue = Arrays.binarySearch(array.getContent(), valueToFind);
        assertEquals(expectedIndexOfValue, indexOfValue);
    }
}
