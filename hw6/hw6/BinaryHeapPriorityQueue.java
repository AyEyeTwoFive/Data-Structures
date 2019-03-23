package hw6;

import exceptions.EmptyException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Priority queue implemented as a binary heap.
 *
 * Use the ranked array representation of a binary heap!
 * Keep the arithmetic simple by sticking a null into slot 0 of the
 * ArrayList; wasting one reference is an okay price to pay for nicer
 * code.
 * 
 * TODO: implement the missing methods. (Hint: create helper methods!)
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
    private boolean empty;

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
        this.empty = true;
    }


    private boolean greater(T i, T j) {
        return this.cmp.compare(i, j) > 0;
    }

    @Override
    public void insert(T t) {
        /*if (this.empty = true) { // first add
            this.data.add(1, t); // add at position 1
            this.empty = false;
        }
        else {
            for (int i = 1; i < this.data.size(); i *= 2) {
                // loop through parents first

        }*/
        // TODO
        // Percolate up
        this.empty = false;
        this.data.add(0, t);
        int place;
        for(place = this.data.size() - 1; greater(this.data.get(place / 2),t); place /= 2 ) {
            this.data.add(place, this.data.get(place / 2));
        }
        this.data.add(place, t);
    }

    @Override
    public void remove() throws EmptyException {
        if (this.empty) {
            throw new EmptyException();
        }
        this.data.remove(this.data.size() - 1); // remove last element
    }

    @Override
    public T best() throws EmptyException {
        if (this.empty) {
            throw new EmptyException();
        }
        return this.data.get(this.data.size() - 1); // return last element
    }

    @Override
    public boolean empty() {
        return this.empty;
    }

}
