package hw6;

import exceptions.EmptyException;

import java.util.Comparator;

/**
 * Priority queue implemented using our (unordered) abstract List,
 * specifically with a LinkedList implementation.
 *
 * TODO: write the missing method bodies below. 
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
    private boolean empty;

    /**
     * An unordered List PQ using the "natural" ordering of T.
     */
    public ListPriorityQueue() {
        this(new DefaultComparator<>());
        this.empty = true;
    }

    /**
     * An unordered List PQ using the given comparator for T.
     * @param cmp Comparator to use.
     */
    public ListPriorityQueue(Comparator<T> cmp) {
        this.list= new LinkedList<T>();
        this.cmp = cmp;
        this.empty = true;
    }

    private boolean greater(T i, T j) {
        return this.cmp.compare(i, j) > 0;
    }

    @Override
    public void insert(T t) {
        if (this.empty) { //empty
            this.list.insertBack(t);
            this.empty = false;
            return;
        }
        this.empty = false;
        Position<T> cur = this.list.front(); // start at beginning
        while (!(this.list.last(cur))) { // keep looking until we reach end
            if (greater(cur.get(),t)) {
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
    }

    @Override
    public void remove() throws EmptyException {
        this.list.removeBack(); // remove the thing at back (greatest)
    }

    @Override
    public T best() throws EmptyException {
        return this.list.back().get(); // return back (greatest)
    }

    @Override
    public boolean empty() {
        // if we've inserted, this becomes false
        return this.empty;
    }

}
