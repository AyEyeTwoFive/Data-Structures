package hw6;

import exceptions.EmptyException;
import java.util.Comparator;

/**
 * Priority queue implemented using our (unordered) abstract List,
 * specifically with a LinkedList implementation.
 *
 * TODo: write the missing method bodies below.
 * (Hint: create helper method(s)!)
 *
 * @param <T> Element type.
 */
public class ListPriorityQueue<T extends Comparable<? super T>>
    implements PriorityQueue<T> {

    // The default comparator uses the "natural" ordering.
    private static class DefaultComparator<T extends Comparable<? super T>>
        implements Comparator<T> {
        public int compare(T t1, T t2) {
            return t1.compareTo(t2);
        }
    }

    private List<T> list;
    private Comparator<T> cmp;

    /**
     * An unordered List PQ using the "natural" ordering of T.
     */
    public ListPriorityQueue() {
        this(new DefaultComparator<>());
    }

    /**
     * An unordered List PQ using the given comparator for T.
     * @param cmp Comparator to use.
     */
    public ListPriorityQueue(Comparator<T> cmp) {
        this.list = new LinkedList<T>();
        this.cmp = cmp;
    }

    private boolean greater(T i, T j) {
        return this.cmp.compare(i, j) > 0;
    }


    /** A method to find the position object with the best element.
     *
     * @return the position
     */
    private Position<T> bestIndex() {
        T max = this.list.front().get(); // start at beginning
        Position<T> cur = this.list.front();
        Position<T> maxPos = this.list.front();
        while (!(this.list.last(cur))) { // keep looking until we reach end
            if (greater(cur.get(), max)) { // replace max if greater
                max = cur.get();
                maxPos = cur;
            }
            cur = this.list.next(cur);
        }
        if (greater(this.list.back().get(), max)) {
            maxPos = this.list.back();
        }
        return maxPos;
        // if we get all the way to the end, just insert t at the back
        // because its the greatest
    }

    /*@Override
    public void insert(T t) {
        if (this.empty()) { //empty
            this.list.insertBack(t);
            return;
        }
        Position<T> cur = this.list.front(); // start at beginning
        while (!(this.list.last(cur))) { // keep looking until we reach end
            if (greaterOrEqual(cur.get(), t)) {
                this.list.insertBefore(cur, t);
                return;
                // if t is less than thing at cur, insert t before cur
                // (lowest at head, greatest at tail)
            }
            cur = this.list.next(cur);
        }
        this.list.insertBack(t);
        // if we get all the way to the end, just insert t at the back
        // because its the greatest
    }*/

    @Override
    public void insert(T t) {
        this.list.insertBack(t);
    }

    /*@Override
    public void remove() throws EmptyException {
        this.list.removeBack(); // remove the thing at back (greatest)
    }*/



    @Override
    public void remove() throws EmptyException {
        if (this.empty()) {
            throw new EmptyException();
        }
        this.list.remove(this.bestIndex()); // remove
    }

    @Override
    public T best() throws EmptyException {
        if (this.empty()) {
            throw new EmptyException();
        }
        return this.bestIndex().get(); // return
    }

    @Override
    public boolean empty() {
        return this.list.empty();
    }

}
