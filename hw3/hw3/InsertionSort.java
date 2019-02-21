/* InsertionSort.java */

package hw3;

import hw2.Array;


/**
 * The Insertion Sort algorithm.
 * @param <T> Element type.
 */
public final class InsertionSort<T extends Comparable<T>>
    implements SortingAlgorithm<T> {


    @Override
    public void sort(Array<T> array) {
        int len = array.length();
        for (int i = 1; i< len; ++i)
        {
            T temp = array.get(i);
            int j = i - 1;

            //check and move greater
            while (j >= 0 && array.get(j) > temp)
            {
                array.put(j+1,array.get(j));
                j = j - 1;
            }
            array.put(j+1, temp);
        }
    }

    @Override
    public String name() {
        return "Insertion Sort";
    }
}
