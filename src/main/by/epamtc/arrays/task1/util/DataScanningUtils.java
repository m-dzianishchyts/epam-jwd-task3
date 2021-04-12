package by.epamtc.arrays.task1.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DataScanningUtils {

    private static final Scanner SCANNER = new Scanner(System.in);

    private DataScanningUtils() {
    }

    public static int enterIntegerFromConsole() {
        while (!SCANNER.hasNextInt()) {
            SCANNER.next();
        }
        int number = SCANNER.nextInt();
        return number;
    }

    public static File enterExistingFilePathConsole() {
        while (true) {
            String filePath = SCANNER.next();
            File file = new File(filePath);
            if (file.exists()) {
                return file;
            }
        }
    }

    public static List<Integer> parseIntegersFromString(String string) {
        List<Integer> result = new ArrayList<>();
        Matcher integerMatcher = Pattern.compile("[-]?\\d+").matcher(string);
        while (integerMatcher.find()) {
            try {
                int parsedValue = Integer.parseInt(integerMatcher.group());
                result.add(parsedValue);
            } catch (NumberFormatException ignored) {
            }
        }
        return result;
    }
}
