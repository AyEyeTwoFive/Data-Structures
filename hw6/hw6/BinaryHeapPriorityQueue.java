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

    private int bestIndex() {
        int best;
        int i = this.data.size() - 1;
        try { if (greater(this.data.get(i - 1), this.data.get(i))) {
            best = i - 1;
        } else best = i; }
        catch (NullPointerException e) {
            best = i;
        }
        return best;
    }


    private void bubble_up() {
        int i = this.data.size() - 1;
        while (i / 2 > 0) {
            if (greater(this.data.get(i/2), this.data.get(i))) {
                T temp = this.data.get(i / 2);
                this.data.set(i / 2, this.data.get(i));
                this.data.set(i, temp);
            }
            i = i / 2;
        }
    }

    public T get(int i) {
        return this.data.get(i);
    }

    @Override
    public void insert(T t) {
        this.empty = false;
        this.data.add(t);
        this.bubble_up();
    }

    @Override
    public void remove() throws EmptyException {
        if (this.empty) {
            throw new EmptyException();
        }
        this.data.remove(bestIndex()); // remove best element
    }

    @Override
    public T best() throws EmptyException {
        if (this.empty) {
            throw new EmptyException();
        }
        return this.data.get(bestIndex()); // return best element
    }

    @Override
    public boolean empty() {
        return this.empty;
    }

}
