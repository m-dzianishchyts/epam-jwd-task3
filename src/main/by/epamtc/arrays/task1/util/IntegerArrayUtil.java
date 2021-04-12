package by.epamtc.arrays.task1.util;

import by.epamtc.arrays.task1.entity.Array;
import by.epamtc.arrays.task1.exception.InvalidArgumentException;

public final class IntegerArrayUtil {

    private IntegerArrayUtil() {
    }

    public static void doRadixSort(Array array) {
        final int byteValuesRangeSize = 1 << Byte.SIZE;
        final int byteMask = byteValuesRangeSize - 1;
        int[] originalContent = array.getContent();
        int[] auxiliaryContent = new int[originalContent.length];

        for (int byteIndex = 0; byteIndex < Integer.BYTES; byteIndex++) {

            // Compute frequency counts.
            int[] frequencyCounters = new int[byteValuesRangeSize + 1];
            for (int value : originalContent) {
                int filteredByteValue = (value >> Byte.SIZE * byteIndex) & byteMask;
                frequencyCounters[filteredByteValue + 1]++;
            }

            // Compute cumulates
            for (int byteValue = 0; byteValue < byteValuesRangeSize; byteValue++) {
                frequencyCounters[byteValue + 1] += frequencyCounters[byteValue];
            }

            // For most significant byte 0x80-0xFF comes before 0x00-0x7F.
            if (byteIndex == Integer.BYTES - 1) {
                int shift1 = frequencyCounters[byteValuesRangeSize] - frequencyCounters[byteValuesRangeSize / 2];
                int shift2 = frequencyCounters[byteValuesRangeSize / 2];
                for (int byteValue = 0; byteValue < byteValuesRangeSize / 2; byteValue++) {
                    frequencyCounters[byteValue] += shift1;
                }
                for (int byteValue = byteValuesRangeSize / 2; byteValue < byteValuesRangeSize; byteValue++) {
                    frequencyCounters[byteValue] -= shift2;
                }
            }

            // Permutation by cumulates.
            for (int value : originalContent) {
                int filteredByteValue = (value >> Byte.SIZE * byteIndex) & byteMask;
                auxiliaryContent[frequencyCounters[filteredByteValue]++] = value;
            }

            // Copy back to original.
            System.arraycopy(auxiliaryContent, 0, originalContent, 0, originalContent.length);
        }
        try {
            array.setContent(originalContent);
        } catch (InvalidArgumentException impossible) {
            impossible.printStackTrace();
        }
    }
}
