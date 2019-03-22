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

    // TODO: add array or ArrayList to hold the data
    private Comparator<T> cmp;

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
        // TODO: initialize the data container
    }


    @Override
    public void insert(T t) {
        // TODO
    }

    @Override
    public void remove() throws EmptyException {
        // TODO
    }

    @Override
    public T best() throws EmptyException {
        // TODO
        return null;
    }

    @Override
    public boolean empty() {
        // TODO
        return false;
    }

}
