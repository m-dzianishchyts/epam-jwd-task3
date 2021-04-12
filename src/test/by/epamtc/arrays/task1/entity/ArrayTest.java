package by.epamtc.arrays.task1.entity;

import by.epamtc.arrays.task1.exception.IncompatibleStateException;
import by.epamtc.arrays.task1.exception.InvalidArgumentException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class ArrayTest {

    private static Array array;
    private static int[] initialArrayContent;
    private static int expectedMax;
    private static int expectedMin;

    @BeforeAll
    static void prepare() throws InvalidArgumentException {
        initArray();
        prepareExpectedMaxMin();
    }

    static void initArray() throws InvalidArgumentException {
        initialArrayContent = new int[(int) Math.pow(2, 20)];
        initByRandomValues(initialArrayContent, (int) -Math.pow(2, 10), (int) Math.pow(2, 10));
        array = new Array(Arrays.copyOf(initialArrayContent, initialArrayContent.length));
    }

    private static void initByRandomValues(int[] someArrayContent, int minValue, int maxValue) {
        for (int i = 0; i < someArrayContent.length; i++) {
            someArrayContent[i] = (int) (Math.random() * (maxValue - minValue) + minValue);
        }
    }

    static void prepareExpectedMaxMin() {
        var optionalMax = Arrays.stream(array.getContent()).max();
        var optionalMin = Arrays.stream(array.getContent()).min();
        if (optionalMax.isPresent() && optionalMin.isPresent()) {
            expectedMax = optionalMax.getAsInt();
            expectedMin = optionalMin.getAsInt();
        } else {
            throw new IllegalStateException("Array is empty.");
        }
    }

    @AfterEach
    void resetArrayContent() throws InvalidArgumentException {
        array = new Array(Arrays.copyOf(initialArrayContent, initialArrayContent.length));
    }

    @RepeatedTest(10)
    void compareTo() throws InvalidArgumentException {
        int[] someArrayContent = new int[array.getLength()];
        initByRandomValues(someArrayContent, (int) -Math.pow(2, 10), (int) Math.pow(2, 10));
        int expectedResult = Arrays.compare(array.getContent(), someArrayContent);
        int observableResult = array.compareTo(new Array(someArrayContent));
        assertEquals(expectedResult, observableResult);
    }

    @Test
    void compareToSame() throws InvalidArgumentException {
        int expectedResult = Arrays.compare(array.getContent(), array.getContent());
        int observableResult = array.compareTo(new Array(array.getContent()));
        assertEquals(expectedResult, observableResult);
    }

    @Test
    void findMaxValid() throws IncompatibleStateException {
        assertEquals(expectedMax, array.findMax());
    }

    @Test
    void findMaxInvalid() throws InvalidArgumentException {
        array.setContent(new int[0]);
        assertThrows(IncompatibleStateException.class ,() -> array.findMax());
    }

    @Test
    void findMinValid() throws IncompatibleStateException {
        assertEquals(expectedMin, array.findMin());
    }

    @Test
    void findMinInvalid() throws InvalidArgumentException {
        array.setContent(new int[0]);
        assertThrows(IncompatibleStateException.class ,() -> array.findMin());
    }
}