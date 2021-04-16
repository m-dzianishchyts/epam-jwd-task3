package by.epamtc.arrays.task1.runner;

import by.epamtc.arrays.task1.entity.Array;
import by.epamtc.arrays.task1.exception.IncompatibleStateException;
import by.epamtc.arrays.task1.exception.InvalidArgumentException;
import by.epamtc.arrays.task1.util.DataScanningUtils;
import by.epamtc.arrays.task1.util.IntegerArrayUtils;
import by.epamtc.arrays.task1.util.IntegerUtils;

import java.io.File;
import java.util.Comparator;
import java.util.List;

public class Runner {

    private static Array array;
    private static final int MIN_RANDOM_VALUE = (int) -Math.pow(2, 10);
    private static final int MAX_RANDOM_VALUE = (int) Math.pow(2, 10);
    private static final int INITIAL_ARRAY_SIZE = (int) Math.pow(2, 10);

    public static void main(String[] args) throws InvalidArgumentException {
        initByRandomValues();
        List<Integer> primeNumbers = IntegerArrayUtils.extractNumbersByPredicate(array.getContent(), IntegerUtils.THREE_DIGIT_NUMBER_WITH_NO_SAME_DIGITS);
        primeNumbers.sort(Comparator.naturalOrder());
        primeNumbers.stream().distinct().forEach(System.out::println);
//        initFromFile();
//        initFromConsole();
    }

    private static void initFromConsole() throws InvalidArgumentException {
        System.out.println("Init from console.");
        array = new Array(INITIAL_ARRAY_SIZE);
        array.initFromConsole();
        System.out.println(array);
    }

    private static void initFromFile() throws InvalidArgumentException, IncompatibleStateException {
        System.out.println("Init from file.");
        System.out.print("File path: ");
        File file = DataScanningUtils.enterExistingFilePathConsole();
        array = new Array(file);
        System.out.println(array);
    }

    private static void initByRandomValues() throws InvalidArgumentException {
        System.out.println("Init by random values.");
        array = new Array(INITIAL_ARRAY_SIZE);
        array.initByRandomValues(MIN_RANDOM_VALUE, MAX_RANDOM_VALUE);
//        System.out.println(array);
    }
}
