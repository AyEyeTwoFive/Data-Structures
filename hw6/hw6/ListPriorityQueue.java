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
        this.list= new LinkedList<T>();
        this.cmp = cmp;
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
