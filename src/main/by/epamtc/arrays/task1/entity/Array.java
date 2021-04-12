package by.epamtc.arrays.task1.entity;

import by.epamtc.arrays.task1.exception.IncompatibleStateException;
import by.epamtc.arrays.task1.exception.InvalidArgumentException;

public class Array implements Comparable<Array> {

    private int[] content;

    public Array(int[] content) throws InvalidArgumentException {
        checkContent(content);
        this.content = content;
    }

    private void checkContent(int[] content) throws InvalidArgumentException {
        if (content == null) {
            throw new InvalidArgumentException("Array cannot be null.");
        }
    }

    public int getLength() {
        return content.length;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        for (var element : content) {
            hashCode = (Integer.SIZE - 1) * hashCode + element;
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Array thatArray = (Array) o;
        return (content.length == thatArray.content.length && compareTo(thatArray) == 0);
    }

    @Override
    public int compareTo(Array thatArray) {
        int mismatchPosition = mismatchPosition(content, thatArray.content,
                                                Math.min(content.length, thatArray.content.length));
        if (mismatchPosition >= 0) {
            return content[mismatchPosition] > thatArray.content[mismatchPosition] ? 1 : -1;
        }
        return content.length - thatArray.content.length;
    }

    private int mismatchPosition(int[] array1, int[] array2, int length) {
        for (int i = 0; i < length; i++) {
            if (array1[i] != array2[i]) {
                return i;
            }
        }
        return -1;
    }

    public int[] getContent() {
        return content;
    }

    public void setContent(int[] content) throws InvalidArgumentException {
        checkContent(content);
        this.content = content;
    }

    public int findMax() throws IncompatibleStateException {
        checkOnEmpty();
        int maxValue = content[0];
        for (int i = 1; i < content.length; i++) {
            if (content[i] > maxValue) {
                maxValue = content[i];
            }
        }
        return maxValue;
    }

    private void checkOnEmpty() throws IncompatibleStateException {
        if (content.length == 0) {
            throw new IncompatibleStateException("Array is empty.");
        }
    }

    public int findMin() throws IncompatibleStateException {
        checkOnEmpty();
        int minValue = content[0];
        for (int i = 1; i < content.length; i++) {
            if (content[i] < minValue) {
                minValue = content[i];
            }
        }
        return minValue;
    }

    public void doQuickSort() {
        if (content.length <= 1) {
            return;
        }
        doQuickSortRecursive(0, content.length - 1);
    }

    private void doQuickSortRecursive(int beginPos, int endPos) {
        if (beginPos < endPos) {
            int partitionIndex = makeSeparation(beginPos, endPos);
            doQuickSortRecursive(beginPos, partitionIndex - 1);
            doQuickSortRecursive(partitionIndex + 1, endPos);
        }
    }

    private int makeSeparation(int beginPos, int endPos) {
        int pivot = content[endPos];
        int i = (beginPos - 1);
        for (int j = beginPos; j < endPos; j++) {
            if (content[j] <= pivot) {
                i++;
                int swapTemp = content[i];
                content[i] = content[j];
                content[j] = swapTemp;
            }
        }
        int swapTemp = content[i + 1];
        content[i + 1] = content[endPos];
        content[endPos] = swapTemp;
        return i + 1;
    }

    public void doMergeSort() {
        if (content.length < 2) {
            return;
        }
        doMergeSortRecursive(0, content.length - 1);
    }

    public void doMergeSortRecursive(int beginPos, int endPos) {
        if (beginPos < endPos) {
            int midPos = (beginPos + endPos) / 2;
            doMergeSortRecursive(beginPos, midPos);
            doMergeSortRecursive(midPos + 1, endPos);
            merge(beginPos, endPos);
        }
    }

    void merge(int beginPos, int endPos) {
        int midPos = (beginPos + endPos) / 2;
        int[] buffer = new int[endPos - beginPos + 1];
        int bufferIndex = 0;
        int rightIndex = midPos + 1;
        int leftIndex = beginPos;

        // Merge until at least one part is finished.
        while (leftIndex <= midPos && rightIndex <= endPos) {
            if (content[leftIndex] < content[rightIndex]) {
                buffer[bufferIndex] = content[leftIndex];
                leftIndex++;
            } else {
                buffer[bufferIndex] = content[rightIndex];
                rightIndex++;
            }
            bufferIndex++;
        }

        // If left part is finished, put everything remaining from right part into buffer.
        if (leftIndex > midPos) {
            System.arraycopy(content, rightIndex, buffer, bufferIndex, endPos - rightIndex + 1);

        // Or, if right part is finished, put everything remaining from left part info buffer.
        } else {
            System.arraycopy(content, leftIndex, buffer, bufferIndex, midPos - leftIndex + 1);
        }

        // Copy sorted part to original content.
        System.arraycopy(buffer, 0, content, beginPos, buffer.length);
    }

    // The result is undefined if array is not sorted.
    int findViaBinarySearch(int value) {
        if (content.length == 0) {
            return -1;
        }
        int indexOfValue = findViaBinarySearchRecursive(0, content.length - 1, value);
        return indexOfValue;
    }

    int findViaBinarySearchRecursive(int beginPos, int endPos, int value) {
        if (endPos >= beginPos) {
            int mid = beginPos + (endPos - beginPos) / 2;
            if (content[mid] == value) {
                return mid;
            }
            if (value < content[mid]) {
                return findViaBinarySearchRecursive(beginPos, mid - 1, value);
            } else {
                return findViaBinarySearchRecursive(mid + 1, endPos, value);
            }
        }
        return -(beginPos + 1);
    }
}
