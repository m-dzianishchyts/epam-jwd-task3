package by.epamtc.arrays.task1.entity;

import by.epamtc.arrays.exception.IncompatibleStateException;
import by.epamtc.arrays.exception.InvalidArgumentException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArrayTest {

    private static final int COMPARE_TO_TESTS_AMOUNT = 10;
    private static Array array;
    private static Array initialArray;
    private static int expectedMax;
    private static int expectedMin;

    @BeforeAll
    static void prepare() throws InvalidArgumentException {
        initArray();
        prepareExpectedMaxMin();
    }

    static void initArray() throws InvalidArgumentException {
        int arraySize = (int) Math.pow(2, 20);
        int minInitialValue = (int) -Math.pow(2, 10);
        int maxInitialValue = (int) Math.pow(2, 10);
        initialArray = new Array(arraySize);
        initialArray.initByRandomValues(minInitialValue, maxInitialValue);
        array = new Array(initialArray);
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
        array = new Array(initialArray);
    }

    @RepeatedTest(COMPARE_TO_TESTS_AMOUNT)
    void compareTo() throws InvalidArgumentException {
        int minInitialValue = (int) -Math.pow(2, 10);
        int maxInitialValue = (int) Math.pow(2, 10);
        Array someArray = new Array(array.getLength());
        someArray.initByRandomValues(minInitialValue, maxInitialValue);
        int expectedResult = Arrays.compare(array.getContent(), someArray.getContent());
        int observableResult = array.compareTo(someArray);
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
        assertThrows(IncompatibleStateException.class, () -> array.findMax());
    }

    @Test
    void findMinValid() throws IncompatibleStateException {
        assertEquals(expectedMin, array.findMin());
    }

    @Test
    void findMinInvalid() throws InvalidArgumentException {
        array.setContent(new int[0]);
        assertThrows(IncompatibleStateException.class, () -> array.findMin());
    }
}