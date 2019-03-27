package hw6;

import exceptions.EmptyException;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Priority queue implemented as a binary heap.
 *
 * Use the ranked array representation of a binary heap!
 * Keep the arithmetic simple by sticking a null into slot 0 of the
 * ArrayList; wasting one reference is an okay price to pay for nicer
 * code.
 * 
 * TODd: implement the missing methods. (Hint: create helper methods!)
 *
 * @param <T> Element type.
 */
public class BinaryHeapPriorityQueue<T extends Comparable<? super T>>
    implements PriorityQueue<T> {

    // The default comparator uses the "natural" ordering.
    private static class DefaultComparator<T extends Comparable<? super T>>
        implements Comparator<T> {
        public int compare(T t1, T t2) {
            return t1.compareTo(t2);
        }
    }


    private ArrayList<T> data;
    private Comparator<T> cmp;
    private int size;

    /**
     * A binary heap using the "natural" ordering of T.
     */
    public BinaryHeapPriorityQueue() {
        this(new DefaultComparator<>());
    }

    /**
     * A binary heap using the given comparator for T.
     * @param cmp Comparator to use.
     */
    public BinaryHeapPriorityQueue(Comparator<T> cmp) {
        this.cmp = cmp;
        this.data = new ArrayList<T>();
        this.data.add(null);  // "wasted" spot
        this.size = 0;
    }


    private boolean greater(T i, T j) {
        return this.cmp.compare(i, j) > 0;
    }

    /*private int bestIndex() {
        int best = this.data.size() - 1;
        if (best > 0) {
            try {
                if (greater(this.data.get(best - 1), this.data.get(best))) {
                    best -= 1;
                }
            }
            catch (NullPointerException e) {  }
        }
        return best;
    }*/


    private void bubbleUp() {
        int i = this.data.size() - 1;
        while (i / 2 > 0) {
            if (greater(this.data.get(i), this.data.get(i / 2))) {
                T temp = this.data.get(i / 2);
                this.data.set(i / 2, this.data.get(i));
                this.data.set(i, temp);
            }
            i = i / 2;
        }
    }

    /** Code to start at root and swap down with children.
     *
     */
    public void swapDown() {
        int i = 1;
        int swapped = 0;
        while (i * 2 < this.data.size()) {
            if (i * 2 + 1 < this.data.size()) {
                if (greater(this.data.get(i * 2 + 1), this.data.get(i * 2))) {
                    swapped = i * 2 + 1;
                }
                else {
                    swapped = i * 2;
                }
            }
            else {
                swapped = i * 2;
            }
            if (greater(this.data.get(swapped), this.data.get(i))) {
                T temp = this.data.get(swapped);
                this.data.set(swapped, this.data.get(i));
                this.data.set(i, temp);
            }
            else {
                break;
            }
            i = swapped;
        }
    }

    /**
     * Just a get method for debugging.
     * @param i index
     * @return the item at that index in the heap array
     */
    public T get(int i) {
        return this.data.get(i);
    }

    @Override
    public void insert(T t) {
        this.size += 1;
        this.data.add(t);
        this.bubbleUp();
    }

    @Override
    public void remove() throws EmptyException {
        if (this.empty()) {
            throw new EmptyException();
        }
        this.data.set(1, this.data.get(size - 1)); // remove best element
        this.data.remove(size - 1);
        this.size -= 1;
        this.swapDown();
    }

    @Override
    public T best() throws EmptyException {
        if (this.empty()) {
            throw new EmptyException();
        }
        return this.data.get(1); // return best element
    }

    @Override
    public boolean empty() {
        return this.size == 0;
    }

}
