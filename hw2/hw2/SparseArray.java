/* TODO - Add your name, JHED, and email.
    Asef Islam
    aislam5
    aislam5@jhu.edu
 * SparseArray.java
 */

package hw2;

import exceptions.IndexException;
import exceptions.LengthException;
import java.util.Iterator;


/**
 * TODO - Write a good Javadoc description for potential clients.
 * Array implementation that keeps track of only stored values that have changed from the default value
 * @param <T> Element type.
 */
public class SparseArray<T> implements Array<T> {

    private static class Node<T> {
        T data;
        int position;
        Node<T> next;
    }

    // TODO - You'll need this to implement the iterator() method
    private class SparseArrayIterator implements Iterator<T> {

        Node<T> current; //current position
        SparseArrayIterator() {
            this.current = SparseArray.this.list;
        }

        @Override
        public boolean hasNext() {
            // TODO
            return this.current != null;
        }

        @Override
        public T next() {
            // TODO
            T t = this.current.data;
            this.current = this.current.next;
            return t;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * An array that is meant to be filled primarily with a default value
     * that is not going to change - with the benefit of that default
     * value not being stored numerous times as opposed to once.
     * @param length The number of indexes the array should have.
     * @param defaultValue The default value for the array.
    */

    private Node<T> list;
    private int len;
    private T defval;

    public SparseArray(int length, T defaultValue) throws LengthException {
        // TODO
        if (length <= 0) {
            throw new LengthException();
        }
        this.len = length;
        defval = defaultValue;
        this.list = null;
    }

    @Override
    public int length() {
        // TODO
        return this.len;
    }

    @Override
    public T get(int i) throws IndexException {
        // TODO
        if (i >= this.len || i < 0) { //out of bounds
            throw new IndexException();
        }
        Node<T> n = this.list;
        while (n != null) {
            if(n.position == i) {
                return n.data;
            }
            n = n.next;
        }
        return this.defval;
    }

    @Override
    public void put(int i, T t) throws IndexException {
        if (i >= this.len || i < 0) { //out of bounds
            throw new IndexException();
        }
        if (t == this.defval) { //default value
            return; //do nothing
        }
        Node<T> n = this.list;
        Node<T> thisnode = new Node<T>();
        if (n == null) { //empty
            Node<T>  firstnode = new Node<T>();
            firstnode.data = t;
            firstnode.position = i;
            firstnode.next = null;
            this.list = firstnode;
            return;
        }
        while (n != null) {
            if(n.position == i) {
                n.data = t;
                return;
            }
            thisnode = n;
            n = n.next;
        }
        n = new Node<T>();
        n.data = t;
        n.position = i;
        n.next = null;
        thisnode.next = n;
        return;
        // TODO
    }

    @Override
    public Iterator<T> iterator() {
        // TODO
        return new SparseArrayIterator();
    }
}
