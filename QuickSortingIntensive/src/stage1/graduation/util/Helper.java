package stage1.graduation.util;

import java.util.Comparator;

public class Helper {

    public static <T> void quicksort(Comparable<T>[] objects, int startBound, int endBound) {
        if (startBound < endBound) {
            int pivotIndex = partition(objects, startBound, endBound);
            quicksort(objects, startBound, pivotIndex - 1);
            quicksort(objects, pivotIndex + 1, endBound);
        }
    }

    private static <T> int partition(Comparable<T>[] objects, int startBound, int endBound) {
        Comparable<T> pivot = objects[endBound];
        int index = startBound - 1;

        for (int i = startBound; i < endBound; i++) {
            if (objects[i].compareTo((T) pivot) < 0) {
                index++;
                swap(objects, index, i);
            }
        }
        swap(objects, index + 1, endBound);
        return index + 1;
    }

    private static <T> void swap(T[] objects, int first, int second) {
        T swap = objects[first];
        objects[first] = objects[second];
        objects[second] = swap;
    }

    public static <T> void quicksort(T[] objects, int startBound, int endBound, Comparator<T> comparator) {
        if (startBound < endBound) {
            int pivotIndex = partition(objects, startBound, endBound, comparator);
            quicksort(objects, startBound, pivotIndex - 1, comparator);
            quicksort(objects, pivotIndex + 1, endBound, comparator);
        }
    }

    private static <T> int partition(T[] objects, int startBound, int endBound, Comparator<T> comparator) {
        T pivot = objects[endBound];
        int index = startBound - 1;

        for (int i = startBound; i < endBound; i++) {
            if (comparator.compare(objects[i], pivot) < 0) {
                index++;
                swap(objects, index, i);

                T temp = objects[index];
                objects[index] = objects[i];
                objects[i] = temp;
            }
        }
        swap(objects, index + 1, endBound);
        return index + 1;
    }

    public static <T> void sortByEvens(T[] elements, Comparator<T> comparatorByEvens) {
        for (int i = 0; i < elements.length; i++) {
            T min = elements[i];
            int indexMin = i;
            for (int j = i; j < elements.length; j++) {
                if (comparatorByEvens.compare(min, elements[j]) > 0) {
                    min = elements[j];
                    indexMin = j;
                }
            }
            if (i != indexMin) {
                swap(elements, i, indexMin);
            }
        }
    }

    public static <T extends Comparable<T>> int binarySearch(Comparable<T>[] objects, T target) {
        int startBound = 0;
        int endBound = objects.length - 1;

        while (startBound <= endBound) {
            int mid = startBound + (endBound - startBound) / 2;
            int comparison = objects[mid].compareTo(target);

            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                startBound = mid + 1;
            } else {
                endBound = mid - 1;
            }
        }
        return -1;  // Объект не найден
    }
}