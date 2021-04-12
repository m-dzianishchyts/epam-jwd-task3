package by.epamtc.arrays.task1.runner;

import by.epamtc.arrays.task1.entity.Array;
import by.epamtc.arrays.task1.exception.IncompatibleStateException;
import by.epamtc.arrays.task1.exception.InvalidArgumentException;
import by.epamtc.arrays.task1.util.DataScanningUtils;

import java.io.File;

public class Runner {

    private static final int MIN_RANDOM_VALUE = 0;
    private static final int MAX_RANDOM_VALUE = 10;
    private static final int INITIAL_ARRAY_SIZE = 5;

    public static void main(String[] args) throws InvalidArgumentException, IncompatibleStateException {
        initByRandomValues();
        initFromFile();
        initFromConsole();
    }

    private static void initFromConsole() throws InvalidArgumentException {
        System.out.println("Init from console.");
        Array array = new Array(INITIAL_ARRAY_SIZE);
        array.initFromConsole();
        System.out.println(array);
    }

    private static void initFromFile() throws InvalidArgumentException, IncompatibleStateException {
        System.out.println("Init from file.");
        System.out.print("File path: ");
        File file = DataScanningUtils.enterExistingFilePathConsole();
        Array array = new Array(file);
        System.out.println(array);
    }

    private static void initByRandomValues() throws InvalidArgumentException {
        System.out.println("Init by random values.");
        Array array = new Array(INITIAL_ARRAY_SIZE);
        array.initByRandomValues(MIN_RANDOM_VALUE, MAX_RANDOM_VALUE);
        System.out.println(array);
    }
}
