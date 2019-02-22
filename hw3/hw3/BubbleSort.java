/* BubbleSort.java */

package hw3;

import hw2.Array;

/**
 * The Bubble Sort algorithm with the optimized "quick" break to exit
 * if the array is sorted.
 * @param <T> The type being sorted.
 */
public final class BubbleSort<T extends Comparable<T>> implements SortingAlgorithm<T> {

    @Override
    public void sort(Array<T> array) {
        int len = array.length();
        for (int i = 0; i < len - 1; i++) {
            boolean haveSwapped = false;
            for (int j = 0; j < len - i - 1; j++) {
                if (array.get(j).compareTo(array.get(j + 1)) > 0) //swap
                {
                    haveSwapped = true;
                    T temp = array.get(j);
                    array.put(j, array.get(j + 1));
                    array.put(j + 1, temp);
                }
            }
            if (haveSwapped) {
                break;
            }
        }
    }
    // TODO


    @Override
    public String name() {
        return "Bubble Sort";
    }
}
