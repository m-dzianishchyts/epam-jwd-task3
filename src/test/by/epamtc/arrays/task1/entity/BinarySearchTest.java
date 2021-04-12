package by.epamtc.arrays.task1.entity;

import by.epamtc.arrays.task1.exception.IncompatibleStateException;
import by.epamtc.arrays.task1.exception.InvalidArgumentException;
import by.epamtc.arrays.task1.util.IntegerArrayUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchTest {

    private static Array array;

    @BeforeEach
    void initArray() throws InvalidArgumentException {
        int[] initialArrayContent = new int[(int) Math.pow(2, 20)];
        initByRandomValues(initialArrayContent, (int) -Math.pow(2, 10), (int) Math.pow(2, 10));
        array = new Array(Arrays.copyOf(initialArrayContent, initialArrayContent.length));
        IntegerArrayUtil.doRadixSort(array);
    }

    @RepeatedTest(50)
    void findViaBinarySearch() {
        int valueToFind = randomInt((int) -Math.pow(2, 10), (int) Math.pow(2, 10));
        int indexOfValue = array.findViaBinarySearch(valueToFind);
        int expectedIndexOfValue = Arrays.binarySearch(array.getContent(), valueToFind);
        assertEquals(expectedIndexOfValue, indexOfValue);
    }

    private static void initByRandomValues(int[] someArrayContent, int minValue, int maxValue) {
        for (int i = 0; i < someArrayContent.length; i++) {
            someArrayContent[i] = randomInt(minValue, maxValue);
        }
    }

    private static int randomInt(int minValue, int maxValue) {
        int randomInt = (int) (Math.random() * (maxValue - minValue) + minValue);
        return randomInt;
    }
}
