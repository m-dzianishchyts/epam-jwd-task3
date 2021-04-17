package by.epamtc.arrays.task1.util;

public final class RandomUtils {

    private RandomUtils() {
    }

    public static int gengerateRandomInt(int minValue, int maxValue) {
        int randomInt = (int) (Math.random() * (maxValue - minValue) + minValue);
        return randomInt;
    }
}
